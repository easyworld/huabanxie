package com.dhu.huabanxie.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhu.huabanxie.domain.Task;
import com.dhu.huabanxie.domain.User;
import com.dhu.huabanxie.service.DeptService;
import com.dhu.huabanxie.service.TaskService;
import com.dhu.huabanxie.service.UserService;
import com.dhu.huabanxie.util.CommonConstant;
import com.dhu.huabanxie.util.StringUtil;

@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;

	@RequestMapping(value = "task.html")
	public String goTask(HttpServletRequest request,
			HttpServletResponse response) {
		Object o = request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
		if (!(o instanceof Map)) {
			return "index.ftl";
		}
		List<Map<String, Object>> deptList = deptService.getDeptList();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (HashMap<String, Object>) o;
		// request.getSession().setAttribute("username",

		// map.get("username"));
		String username = StringUtil.getVal(map.get("USERNAME"));
		// for (Map.Entry<String, Object> s : map.entrySet()) {
		// if (s.getKey().equals("USERNAME")) {
		// username = StringUtil.getVal(s.getValue());
		// break;
		// }
		// }
		// request.getSession().setAttribute("job_id", map.get("job_id"));
		String job_id = StringUtil.getVal(map.get("JOB_ID"));
		String ID = StringUtil.getVal(map.get("ID"));
		String mgr_id = StringUtil.getVal(map.get("mgr_id"));
		User user = new User();
		user.setUsername(username);
		user.setJob_id(job_id);
		user.setId(ID);
		user.setMgr_id(mgr_id);
		request.setAttribute("username", username);
		if ("0".equals(job_id)) {
			request.setAttribute("userList", userService.getUserList());
			request.setAttribute("deptList", deptList);
			return "task.ftl";
		} else if ("1".equals(job_id)) {
			request.setAttribute("user_id", ID);
			request.setAttribute("subUserList",
					userService.getSubUserList(user));
			request.setAttribute("headUserList",
					userService.getHeadUserList(user));
			request.setAttribute("deptList", deptList);
			return "taskFenGuan.ftl";
		} else if ("2".equals(job_id)) {
			request.setAttribute("user_id", ID);
			request.setAttribute("headUserList", userService.getHeadUserList(user));
			request.setAttribute("deptList", deptList);
			return "taskBuMen.ftl";
		}
		request.removeAttribute("username");
		return "index.ftl";
	}

	@RequestMapping(value = "queryTaskList.html")
	@ResponseBody
	public String queryTaskList(HttpServletRequest request,
			HttpServletResponse response) {
		String str = taskService.getTaskListJson();
		return str;
	}

	@RequestMapping(value = "queryPagedTaskList.html")
	@ResponseBody
	public String queryPagedTaskList(HttpServletRequest request,
			HttpServletResponse response) {
		String page = request.getParameter("page");
		String queryOrder = request.getParameter("queryOrder");
		String queryTime = request.getParameter("queryTime");
		String queryUser = request.getParameter("queryUser");
		int pageNo = Integer.parseInt(page);
		String str = taskService.getPagedTaskListJson(pageNo, queryOrder,
				queryTime, queryUser);
		return str;
	}

	@RequestMapping(value = "insertTask.html")
	@ResponseBody
	public String insertTaskList(HttpServletRequest request,
			HttpServletResponse response, Task task) {
		int result = taskService.insertTask(task);
		return (result == 1) ? "success" : "error";
	}

	@RequestMapping(value = "updateTask.html")
	@ResponseBody
	public String updateTaskList(HttpServletRequest request,
			HttpServletResponse response, Task task) {
		if (taskService.updateTask(task) != 1)
			return "error";
		else
			return "success";
	}

	@RequestMapping(value = "deleteTask.html")
	@ResponseBody
	public String deleteTaskList(HttpServletRequest request,
			HttpServletResponse response, Task task) {
		if (taskService.deleteTask(task) != 1)
			return "error";
		else
			return "success";
	}

	@RequestMapping("exportExcel.html")
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String page = request.getParameter("page");
		String queryOrder = request.getParameter("queryOrder");
		String queryTime = request.getParameter("queryTime");
		String queryUser = request.getParameter("queryUser");
		int pageNo = Integer.parseInt(page);
		List<Map<String, Object>> list = taskService.getPagedTaskList(pageNo,
				queryOrder, queryTime, queryUser);

		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			// 进行转码，使其支持中文文件名
			codedFileName = String.valueOf(System.currentTimeMillis());
			// java.net.URLEncoder.encode("下载", "UTF-8");
			response.setHeader("content-disposition", "attachment;filename="
					+ codedFileName + ".xls");
			// response.addHeader("Content-Disposition",
			// "attachment;   filename=" + codedFileName + ".xls");
			// 产生工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();
			HSSFRow row;
			HSSFCell cell;
			//
			row = sheet.createRow(0);
			// 创建表头
			for (int i = 0; i < CommonConstant.taskColumnName.length; i++) {
				cell = row.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(CommonConstant.taskColumnName[i]);
			}
			for (int i = 1; i <= list.size(); i++) {
				row = sheet.createRow((int) i);// 创建一行
				for (int j = 0; j < CommonConstant.taskColumnName.length; j++) {
					cell = row.createCell((int) j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					Object o = list.get(i - 1).get(
							CommonConstant.taskColumnName[j]);
					String value = StringUtil.getVal(o);
					cell.setCellValue(value);
				}
			}
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
			}
		}
		System.out.println("文件生成...");
	}
}
