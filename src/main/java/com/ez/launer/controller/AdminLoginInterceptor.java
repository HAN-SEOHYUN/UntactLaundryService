package com.ez.launer.controller;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminLoginInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("관리자 로그인체크 인터셉터 실행");

		HttpSession session = request.getSession();

		String adminEmail = (String) request.getSession().getAttribute("adminEmail");
		logger.info("adminEmail = {}", adminEmail);

		if(adminEmail == null || adminEmail.isEmpty()) {
			response.setContentType("text/html; charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script type='text/javascript'>");
			out.print("alert('관리자 페이지를 이용하시려면"
					+ "\\n먼저 로그인 해주세요');");
			out.print("location.href = '" + request.getContextPath() + "/admin/adminLogin';");
			out.print("</script>");

			return false;
		}

		session.setAttribute("CHK_LOGIN", true);
		return true;	//다음 컨트롤러가 수행됨
	}
}
