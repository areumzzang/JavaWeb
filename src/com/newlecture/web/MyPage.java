package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		PrintWriter out = response.getWriter();
		
		int a =0;
		int s =0;
		int c =0;
		
		Object _a = application.getAttribute("a");
		Object _s = session.getAttribute("s");
		String _c = null;
		
		if(cookies != null)
			for(Cookie cookie : cookies)
				if("c".equals(cookie.getName()))
					_c = cookie.getValue();
		
		//쿠키즈목록을 가져올 수 있다.배열로가져왔으면 배열을 다 뒤져야한다. 
		//그중에서 C가 있는지 찾아야함 for each문 : 컬렉션에서 값을 하나씩 하나씩꺼내주는 것
		//쿠키를 하나씩 꺼내온다. 꺼내온 쿠키의 이름을 비교 
		
		
		if(_a != null)
			a = (int) _a;
		
		if(_s != null)
			s = (int) _s;
		
		if(_c != null)
			c = Integer.parseInt(_c);
		
		out.printf("Application : %d <br />", a);
		out.printf("Session : %d <br />", s);
		out.printf("Cookie : %d <br />", c);
		out.println("<a href = \"add\">돌아가기</a>");
		
		
		
	}
}
