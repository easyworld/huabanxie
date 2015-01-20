package com.dhu.huabanxie.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dhu.huabanxie.domain.User;
import com.dhu.huabanxie.util.MD5Util;
import com.dhu.huabanxie.util.StringUtil;

@Repository
public class UserDao extends BaseDao {

	/**
	 * 返回指定username用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username) {
		String sqlStr = "select count(id) from users where username=?";

		return (this.getJdbcTemplate().queryForObject(sqlStr,
				new Object[] { username }, Integer.class) > 0) ? true : false;
	}

	/**
	 * 创建用户：根据userMap数据插入数据库
	 * 
	 * @param userMap
	 * @return
	 */
	public int insertUser(Map<String, Object> userMap) {
		String sqlstr = "INSERT INTO SCOTT.USERS(ID, USERNAME, PASSWORD, JOB_ID, DEPT_ID, MGR_ID) "
				+ " VALUES(user_id_seq.nextval, ?, ? ,?, ?, ?) ";
		String password = MD5Util
				.MD5(StringUtil.getVal(userMap.get("password")));

		return update(
				sqlstr,
				new Object[] { String.valueOf(userMap.get("username")),
						String.valueOf(password),
						String.valueOf(userMap.get("job_id")),
						String.valueOf(userMap.get("dept_id")),
						String.valueOf(userMap.get("mgr_id")), });
	}

	/**
	 * 直接根据user领域插入数据
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		String sqlstr = "INSERT INTO SCOTT.USERS(ID, USERNAME, PASSWORD, JOB_ID, DEPT_ID, MGR_ID) "
				+ " VALUES(user_id_seq.nextval, ?, ? ,?, ?, ?) ";

		return this.getJdbcTemplate()
				.update(sqlstr,
						new Object[] { user.getUsername(), user.getPassword(),
								user.getJob_id(), user.getDept_id(),
								user.getMgr_id() });
	}

	/**
	 * 获取所有用户列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getUserList() {
		String sqlStr = "select ID, USERNAME, PASSWORD,JOB_ID, DEPT_ID, MGR_ID from users";
		return queryForList(sqlStr);
	}

	/**
	 * 按照用户名获取一个用户的信息
	 * 
	 * @param username
	 * @return
	 */
	public Map<String, Object> getUserInfo(String username) {
		String sqlStr = "select ID, USERNAME, PASSWORD,JOB_ID, DEPT_ID, MGR_ID from users where username=?";
		return queryForMap(sqlStr, new Object[] { username });
	}

	/**
	 * 更新用户数据
	 * 
	 * @param userMap
	 * @return
	 */
	public int updateUser(Map<String, Object> userMap) {
		String sqlStr = "update users set JOB_ID=?, DEPT_ID=?, MGR_ID=? where id = ?";
		return update(
				sqlStr,
				new Object[] { String.valueOf(userMap.get("job_id")),
						String.valueOf(userMap.get("dept_id")),
						String.valueOf(userMap.get("mgr_id")),
						String.valueOf(userMap.get("id")), });
	}

	/**
	 * 更新用户数据
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		String sqlStr = "update users set JOB_ID=?, DEPT_ID=?, MGR_ID=? where id = ?";
		return update(
				sqlStr,
				new Object[] { String.valueOf(user.getJob_id()),
						String.valueOf(user.getDept_id()),
						String.valueOf(user.getMgr_id()),
						String.valueOf(user.getId()) });
	}

	/**
	 * 删除用户
	 * 
	 * @param username
	 * @return
	 */
	public int deleteUser(String username) {
		String sqlStr = "delete from users where id = ?";
		return update(sqlStr, new Object[] { username });
	}

	/**
	 * 获得用户领导列表
	 * 
	 * @param user
	 * @return
	 */
	public List<Map<String, Object>> getLeaderInfo(User user) {
		String sqlStr = "select id,username from users where id = ?";
		return queryForList(sqlStr, new Object[] { user.getMgr_id() });
	}

	/**
	 * 获得用户下属列表
	 * 
	 * @param user
	 * @return
	 */
	public List<Map<String, Object>> getSubInfo(User user) {
		String sqlStr = "select id,username from users where mgr_id = ?";
		return queryForList(sqlStr, new Object[] { user.getId() });
	}

}
