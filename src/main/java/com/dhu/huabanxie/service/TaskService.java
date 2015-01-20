package com.dhu.huabanxie.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhu.huabanxie.dao.TaskDao;
import com.dhu.huabanxie.domain.Task;
import com.dhu.huabanxie.util.Page;

@Service
public class TaskService {
	@Autowired
	private TaskDao taskDao;

	public List<Map<String, Object>> getTaskList() {
		return taskDao.getTaskList();
	}

	public Page getTaskList(int page) {
		return taskDao.getTaskPage(page, 10);
	}

	public Page getTaskList(int page, String queryUser, String queryOrder,
			String startTime, String endTime) {
		return taskDao.getTaskPage(page, 10, queryUser, queryOrder, startTime,
				endTime);
	}

	public String getTaskListJson() {
		List<Map<String, Object>> list = getTaskList();
		String str = String.format("{\"total\":%d,\"rows\":%s}", list.size(),
				JSONArray.fromObject(list).toString());
		str = str.toLowerCase();
		return str;
	}

	public String getPagedTaskListJson(int pageNo) {
		Page page = getTaskList(pageNo);
		List<Map<String, Object>> list = page.getList();
		for (Map<String, Object> ss : list) {
			if (ss.get("STATE") != null) {
				int state = Integer.parseInt(ss.get("STATE").toString());
				switch (state) {
				case 0:
					ss.put("STATE", "未下达");
					break;
				case 1:
					ss.put("STATE", "未反馈");
					break;
				case 2:
					ss.put("STATE", "已反馈");
					break;
				case 3:
					ss.put("STATE", "已办结");
					break;
				}
			}
		}
		String str = String.format("{\"total\":%d,\"rows\":%s}",
				page.getTotalSize(), JSONArray.fromObject(list).toString());
		str = str.toLowerCase();
		return str;
	}

	public int insertTask(Task task) {
		return validateTask(task) ? taskDao.insertTaskInfo(task) : 0;
	}

	private boolean validateTask(Task task) {
		if (null == task.getCo_dept() || "".equals(task.getCo_dept()))
			return false;
		if (null == task.getDeadline() || "".equals(task.getDeadline()))
			return false;
		if (null == task.getTask_name() || "".equals(task.getTask_name()))
			return false;
		return true;
	}

	public int updateTask(Task task) {
		return taskDao.updateTaskInfo(task);
	}

	public int deleteTask(Task task) {
		return taskDao.deleteTaskInfo(task);
	}

	public String getPagedTaskListJson(int pageNo, String queryOrder,
			String queryTime, String queryUser) {
		String startTime = "19700101", endTime = "99991231";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (queryTime.equals("year")) {
			startTime = String.format("%d0101", year);
			endTime = String.format("%d1231", year);
		} else if (queryTime.equals("quarter")) {
			int month = Calendar.getInstance().get(Calendar.MONTH);
			switch (month / 3) {
			case 0:
				startTime = String.format("%d0101", year);
				endTime = String.format("%d0331", year);
				break;
			case 1:// 第二季度
				startTime = String.format("%d0401", year);
				endTime = String.format("%d0630", year);
				break;
			case 2:
				startTime = String.format("%d0701", year);
				endTime = String.format("%d0930", year);
				break;
			case 3:
				startTime = String.format("%d1001", year);
				endTime = String.format("%d1231", year);
				break;
			}
		}
		Page page = getTaskList(pageNo, queryUser, queryOrder, startTime,
				endTime);

		List<Map<String, Object>> list = page.getList();
		for (Map<String, Object> ss : list) {
			if (ss.get("STATE") != null) {
				int state = Integer.parseInt(ss.get("STATE").toString());
				switch (state) {
				case 0:
					ss.put("STATE", "未下达");
					break;
				case 1:
					ss.put("STATE", "未反馈");
					break;
				case 2:
					ss.put("STATE", "已反馈");
					break;
				case 3:
					ss.put("STATE", "已办结");
					break;
				}
			}
		}
		String str = String.format("{\"total\":%d,\"rows\":%s}",
				page.getTotalSize(), JSONArray.fromObject(list).toString());
		str = str.toLowerCase();
		return str;
	}

	public List<Map<String, Object>> getPagedTaskList(int pageNo,
			String queryOrder, String queryTime, String queryUser) {
		String startTime = "19700101", endTime = "99991231";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (("year").equals(queryTime)) {
			startTime = String.format("%d0101", year);
			endTime = String.format("%d1231", year);
		} else if (("quarter").equals(queryTime)) {
			int month = Calendar.getInstance().get(Calendar.MONTH);
			switch (month / 3) {
			case 0:
				startTime = String.format("%d0101", year);
				endTime = String.format("%d0331", year);
				break;
			case 1:// 第二季度
				startTime = String.format("%d0401", year);
				endTime = String.format("%d0630", year);
				break;
			case 2:
				startTime = String.format("%d0701", year);
				endTime = String.format("%d0930", year);
				break;
			case 3:
				startTime = String.format("%d1001", year);
				endTime = String.format("%d1231", year);
				break;
			}
		}
		Page page = getTaskList(pageNo, queryUser, queryOrder, startTime,
				endTime);

		List<Map<String, Object>> list = page.getList();
		for (Map<String, Object> ss : list) {
			if (ss.get("STATE") != null) {
				int state = Integer.parseInt(ss.get("STATE").toString());
				switch (state) {
				case 0:
					ss.put("STATE", "未下达");
					break;
				case 1:
					ss.put("STATE", "未反馈");
					break;
				case 2:
					ss.put("STATE", "已反馈");
					break;
				case 3:
					ss.put("STATE", "已办结");
					break;
				}
			}
		}

		return list;
	}
}
