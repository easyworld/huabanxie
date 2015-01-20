package com.dhu.huabanxie.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dhu.huabanxie.domain.User;

@Repository
public class DeptDao extends BaseDao {
	/**
	 * 查询所有dept列表
	 * @return
	 */
	public List<Map<String, Object>> getJobList() {
		String sqlStr = "SELECT dept_id,dept_name FROM DEPTS order by dept_id";
		return this.getJdbcTemplate().queryForList(sqlStr);
	}
	/**
	 * 根据dept_id获取dept名称
	 * @param user
	 * @return
	 */
	public String getJobName(User user) {
		String sqlStr = "select dept_name from depts where dept_id=?";
		Map<String, Object> map = this.getJdbcTemplate().queryForMap(sqlStr,
				new Object[] { user.getDept_id() });
		if (map == null || null == map.get("dept_name"))
			return "";

		return (String) map.get("dept_name");
	}
}
