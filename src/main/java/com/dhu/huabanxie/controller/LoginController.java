package com.dhu.huabanxie.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dhu.huabanxie.service.DeptService;
import com.dhu.huabanxie.service.JobService;
import com.dhu.huabanxie.service.UserService;
import com.dhu.huabanxie.util.CommonConstant;
import com.dhu.huabanxie.util.MD5Util;
import com.dhu.huabanxie.util.StringUtil;

@Controller
public class LoginController {

	@Autowired
	private JobService jobService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;

	@RequestMapping(value = "/index.html")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("index.ftl");
	}

	@RequestMapping(value = "register.html")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> jobList = jobService.getJobList();
		List<Map<String, Object>> userList = userService.getUserList();
		List<Map<String, Object>> deptList = deptService.getDeptList();
		request.getSession().setAttribute("jobList", jobList);
		request.getSession().setAttribute("userList", userList);
		request.getSession().setAttribute("deptList", deptList);
		return "register.ftl";
	}

	@RequestMapping(value = "login.html")
	public String loginCheck(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = MD5Util.MD5(StringUtil.getVal(request
				.getParameter("password")));
		if (!userService.hasUser(username)) {
			request.getSession().setAttribute("message", 1);
			return "login.ftl";
		}
		Map<String, Object> userInfo = userService.getUserInfo(username);
		if (userInfo == null) {
			request.getSession().setAttribute("message", 1);
			return "login.ftl";
		}
		String ComparedPassword = StringUtil.getVal(userInfo.get("PASSWORD"));
		if (ComparedPassword.equals(password)) {
			request.getSession().setAttribute("message", 0);
			request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
					userInfo);
		} else {
			request.getSession().setAttribute("message", 1);
		}
		return "login.ftl";
	}
	@RequestMapping(value = "logout.html")
	public String loginOut(HttpSession session) {
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "index.ftl";
	}
}
