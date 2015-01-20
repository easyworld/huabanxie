package com.dhu.huabanxie.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dhu.huabanxie.util.CommonConstant;

@Repository
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private String mappingURL;// 利用正则映射到需要拦截的路径

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		Object obj = request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
		String uri = request.getRequestURI();
		String[] excludeSite = { "index.html", "register.html" };
		if (hasSite(uri, excludeSite))
			return super.preHandle(request, response, handler);

		if (mappingURL == null || url.matches(mappingURL)) {
			request.getRequestDispatcher("/index.html").forward(request,
					response);
			return false;
		}
		if (obj == null) {
			request.getRequestDispatcher("/index.html").forward(request,
					response);
			return false;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean hasSite(String uri, String[] sites) {
		for (String site : sites) {
			if (uri.contains(site))
				return true;
		}
		return false;
	}
}