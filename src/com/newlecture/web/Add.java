package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/add")
public class Add extends HttpServlet {
	/*
	 * @Override public void init(ServletConfig config) throws ServletException
	 * { super.init(config);
	 * 
	 * 
	 * String location = config.getInitParameter(“contextConfigLocation”);
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
//Http를 지원하는 서블릿 클래스 HttpServlet 
//HttpServlet클래스는 HTTP요청 메서드에 따라 서블릿 클래스 내부의 
//메서드를 구분하여 서비스하는 것이 주요 목적이다.
//HTTP요청메서드: GET, POST, HEAD, PUT, DELETE로 구분
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		//응답스트림에 텍스트를 기록하는 것도 가능
		//응답으로 내보낼 출력 스트림을 얻어냄
		
		int x = 0;
		int y = 0;
		
		int result = 0;
		
		String _result = request.getParameter("result");
		if(_result != null)
			result = Integer.parseInt(_result);
		
		//웹페이지 요청이 POST인 경우에만 수행, 즉 폼을 통해 전달된 것만 수행
		if(request.getMethod().equals("POST"))
		{
			String _btn = request.getParameter("btn");
			
			if(_btn.equals("application"))
			{
				ServletContext application = request.getServletContext();
				application.setAttribute("a", result);
				out.write("<p>application에 저장완료</p>");
				
			}
			else if (_btn.equals("session"))
			{
				HttpSession session = request.getSession();
				session.setAttribute("s", result);
				out.write("<p>session에 저장완료</p>");
			}
			
			else if(_btn.equals("cookie"))
			{
				Cookie cookie = new Cookie("c", String.valueOf(result)); //쿠키는 문자열만 저장이 가능하기 때문에 타입변환
				cookie.setMaxAge(24*60*60); //24시간 
				response.addCookie(cookie);
				
				out.write("<p>cookie에 저장완료</p>");
			}
			else {
				String _x = request.getParameter("x");
				String _y = request.getParameter("y");
				
				if (_x != null && !_x.equals("") && _y != null && !_y.equals("") )
				{	//문자열 형태로 전달된 인자들을 int로 변환함
					x = Integer.parseInt(_x);
					y = Integer.parseInt(_y);
				}
				result = x + y;
			}
		}
		
		
		//action : 전송될 페이지의 URL, method: 데이터 전송 방식(get:공개, post:비공개)
		out.write("<form action=\"add\" method=\"post\">");
		out.write("   <ul>");
		out.write("	<li><label for=\"x\"> X: </label><input name=\"x\" /></li>");
		out.write("	<li><label for=\"y\"> Y: </label><input name=\"y\" /></li>");
		//name이라는 것을 넣지 않으면 값이 전달 되지 않는다. 
		//버튼은 액션을 취한 것만 전달이 된다.
		out.write("  </ul>");
		out.write("   <p>");
		out.printf("	<input type =\"hidden\" name=\"result\" value=\"%d\" />", result);
		out.write("	<input type =\"submit\" name=\"btn\" value=\"덧셈\" />");
		out.write("	<input type =\"submit\" name=\"btn\" value=\"application\" />");
		out.write("	<input type =\"submit\" name=\"btn\" value=\"session\" />");
		out.write("	<input type =\"submit\" name=\"btn\" value=\"cookie\" />");
		out.write("	</p>");
		out.write("</form> ");  
		out.printf("덧셈의 결과는: %d <br />", result);
		out.println("<a href = \"MyPage\">마이페이지</a>");//스트림에 텍스트를 기록
		}
	
	
//HttpServlet클래스
//HTTP의 GET요청에는 doGet()메서드가 대응
//HTTP의 POST요청에는 doPost()메서드가 대응
//하게 service()메서드를 재정의
	
	@Override   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
		
		/*response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String _cnt = request.getParameter("cnt");
		
		
		int cnt =10;
		if(_cnt!=null && !_cnt.equals(""))
			cnt = Integer.parseInt(_cnt);
		
		for(int i=0; i<cnt; i++){
		out.println("안녕Ho<b>ho</b>ho<br />");
		//out.println((i+1)+":Ho<b>ho</b>ho<br />");}
*/		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		service(request, response);
		
		/*response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		PrintWriter out = response.getWriter();
		
		String _x = request.getParameter("x");
		String _y = request.getParameter("y");
		
		int x = 0;
		int y = 0;
		
		int result = 0;
		
		if(_x!=null && !_x.equals(""))
			x = Integer.parseInt(_x);
		
		if(_y!=null && !_y.equals(""))
			y = Integer.parseInt(_y);
		
		result = x + y;
		
		out.write("<form action=\"hi\" method=\"post\">");
		out.write("   <ul>");
		out.write("	<li><label for=\"x\"> X: </label><input name=\"x\" /></li>");
		out.write("	<li><label for=\"y\"> Y: </label><input name=\"y\" /></li>");
		out.write("  </ul>");
		out.write("   <p><input type =\"submit\" value=\"덧셈\" /></p>");
		out.write("</form> ");  
		
		out.printf("덧셈의 결과는: %d </ br>", result);*/
	}
    
    

}





