package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hi2")
public class Nana2 extends HttpServlet {
	@Override   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String _cnt = request.getParameter("cnt");
		
		
		int cnt =10;
		if(_cnt!=null && !_cnt.equals(""))
			cnt = Integer.parseInt(_cnt);
		
		for(int i=0; i<cnt; i++){
		out.println("¾È³çHo<b>ho</b>ho<br />");
		//out.println((i+1)+":Ho<b>ho</b>ho<br />");
		
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
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
		out.write("   <p><input type =\"submit\" value=\"µ¡¼À\" /></p>");
		out.write("</form> ");  
		
		out.printf("µ¡¼ÀÀÇ °á°ú´Â: %d </ br>", result);
	}
    
    

}





