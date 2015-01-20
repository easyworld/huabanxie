package com.dhu.huabanxie.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dhu.huabanxie.domain.User;

@Repository
public class JobDao extends BaseDao {
	/**
	 * 查询所有job列表
	 * @return
	 */
	public List<Map<String, Object>> getJobList() {
		String sqlStr = "select job_id,job_name from jobs order by job_id";
		return this.getJdbcTemplate().queryForList(sqlStr);
	}
	/**
	 * 根据job_id获取job名称
	 * @param user
	 * @return
	 */
	public String getJobName(User user) {
		String sqlStr = "select job_name from jobs where job_id=?";
		Map<String, Object> map = this.getJdbcTemplate().queryForMap(sqlStr,
				new Object[] { user.getJob_id() });
		if (map == null || null == map.get("job_name"))
			return "";

		return (String) map.get("job_name");
	}
}
