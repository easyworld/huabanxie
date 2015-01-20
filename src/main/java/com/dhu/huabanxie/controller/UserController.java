package com.dhu.huabanxie.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dhu.huabanxie.domain.User;
import com.dhu.huabanxie.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	public boolean hasUser(String username) {
		return userService.hasUser(username);
	}

	@RequestMapping(value = "insertUser.html", method = RequestMethod.POST)
	public ModelAndView insertUser(HttpServletRequest request, User user) {
		int result = -1;
		if (!userService.hasUser(user.getUsername()))
			result = userService.insertUser(user);
		ModelAndView view = new ModelAndView();
		view.addObject("status", result);
		view.setViewName("status.ftl");
		return view;
	}

	public List<Map<String, Object>> getUserList() {
		return userService.getUserList();
	}

	public int updateUser(Map<String, Object> userMap) {
		return userService.updateUser(userMap);
	}

	public int deleteUser(String username) {
		return userService.deleteUser(username);
	}
}
