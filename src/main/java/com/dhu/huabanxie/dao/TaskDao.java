package com.dhu.huabanxie.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dhu.huabanxie.domain.Task;
import com.dhu.huabanxie.util.Page;
import com.dhu.huabanxie.util.StringUtil;

@Repository
public class TaskDao extends BaseDao {
	/**
	 * 插入一个任务
	 * 
	 * @param task
	 * @return
	 */
	public int insertTaskInfo(Task task) {
		String sqlStr = "INSERT INTO TASKS(TASK_ID, TASK_NAME, RESP_DEPT_ID, CO_DEPT, RESP_USER_ID, DEADLINE, STATE, TASK_DESC, FINISH_DESC) "
				+ " VALUES(task_id_seq.nextval, ?, ?, ?, ?, to_date(?,'mm/dd/yyyy'), ?, ?, ?) ";
		return update(
				sqlStr,
				new Object[] { task.getTask_name(), task.getResp_dept_id(),
						task.getCo_dept(), task.getResp_user_id(),
						task.getDeadline(), task.getState(),
						task.getTask_desc(), task.getFinish_desc()

				});
	}

	/**
	 * 删除一个任务
	 * 
	 * @param task
	 * @return
	 */
	public int deleteTaskInfo(Task task) {
		String sqlStr = "DELETE FROM TASKS WHERE task_id=?";
		return update(sqlStr, new Object[] { task.getTask_id() });
	}

	/**
	 * 根据task_id更新任务
	 * 
	 * @param task
	 * @return
	 */
	public int updateTaskInfo(Task task) {
		String sqlStr = "UPDATE TASKS "
				+ "set TASK_NAME=?, RESP_DEPT_ID=?, CO_DEPT=?, RESP_USER_ID=?, DEADLINE=to_date(?,'mm/dd/yyyy'), STATE=?, TASK_DESC=?, FINISH_DESC=?  "
				+ " WHERE task_id = ?";
		return update(
				sqlStr,
				new Object[] { task.getTask_name(), task.getResp_dept_id(),
						task.getCo_dept(), task.getResp_user_id(),
						task.getDeadline(), task.getState(),
						task.getTask_desc(), task.getFinish_desc(),
						task.getTask_id() });
	}

	/**
	 * 获得taskList
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getTaskList() {
		String sqlStr = "SELECT TASK_ID, TASK_NAME, c.dept_name, CO_DEPT, b.username, to_char(DEADLINE,'yyyymmdd') as DEADLINE, STATE, TASK_DESC, FINISH_DESC   "
				+ "  FROM TASKS a,USERS b,depts c where a.RESP_DEPT_ID = c.dept_id and a.RESP_USER_ID = b.ID order by TASK_ID";
		return queryForList(sqlStr);
	}

	public Page getTaskPage(int pageNo, int pageSize) {
		String sqlStr = "SELECT TASK_ID, TASK_NAME, c.dept_name, CO_DEPT, b.username, to_char(DEADLINE,'yyyymmdd') as DEADLINE, STATE, TASK_DESC, FINISH_DESC   "
				+ "  FROM TASKS a,USERS b,depts c where a.RESP_DEPT_ID = c.dept_id and a.RESP_USER_ID = b.ID order by TASK_ID";
		return queryForPage(sqlStr, pageNo, pageSize);
	}

	public Page getTaskPage(int pageNo, int pageSize, String queryUser,
			String queryOrder, String startTime, String endTime) {
		String sqlStr = "SELECT TASK_ID, TASK_NAME, c.dept_name, CO_DEPT, b.username, to_char(DEADLINE,'yyyymmdd') as DEADLINE, STATE, TASK_DESC, FINISH_DESC   "
				+ "  FROM TASKS a,USERS b,depts c where a.RESP_DEPT_ID = c.dept_id and a.RESP_USER_ID = b.ID ";
		List<Object> param = new ArrayList<Object>();
		if (!StringUtil.isNullString(queryUser)) {
			sqlStr += "  and a.RESP_USER_ID = ? ";
			param.add(queryUser);
		}
		if (!StringUtil.isNullString(startTime)
				&& !StringUtil.isNullString(endTime)) {
			sqlStr += "  and to_date(?,'yyyymmdd') <= a.DEADLINE and a.DEADLINE <= to_date(?,'yyyymmdd') ";
			param.add(startTime);
			param.add(endTime);
		}
		if (!StringUtil.isNullString(queryOrder)) {
			sqlStr += "  order by a."+queryOrder+"  ";
		}
		return queryForPage(sqlStr, pageNo, pageSize, param.toArray());
	}

	/**
	 * 获得指定task数据
	 * 
	 * @param task
	 * @return
	 */
	public Map<String, Object> getTaskInfo(Task task) {
		String sqlStr = "SELECT TASK_ID, TASK_NAME, RESP_DEPT_ID, CO_DEPT, RESP_USER_ID, DEADLINE, STATE, TASK_DESC ,FINISH_DESC "
				+ "  FROM TASKS where task_id = ?";
		return queryForMap(sqlStr, new Object[] { task.getTask_id() });
	}
}
