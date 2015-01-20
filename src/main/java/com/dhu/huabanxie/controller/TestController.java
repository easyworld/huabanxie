package com.dhu.huabanxie.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dhu.huabanxie.service.JobService;

@Controller
public class TestController {
	@Autowired
	private JobService jobService;

	@RequestMapping(value = "/helloworld.html")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("helloWorld.ftl");
		List<Map<String, Object>> s = jobService.getJobList();
		mv.addObject("joblist", s);
		request.getSession().setAttribute("message",
				Calendar.getInstance().getTime().toString());
		return mv;
	}
}
