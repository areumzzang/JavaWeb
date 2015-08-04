package com.newlecture.web.controller.joinus;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.dao.mybatis.MyBatisMemberDao;
import com.newlecture.web.vo.Member;


//@WebServlet("/joinus/login")
public class LoginController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getMethod().equals("POST")) 
		{// null값인지 아닌지 비교하는 것은 하지 않아도 됨
	     // 요청이 null값으로 올리는 없다. DAO, VO가 있어야함
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			
			MemberDao memberDao = new MyBatisMemberDao();
			Member member = memberDao.getMember(uid);
			
			/*if(member.getUid().equals(uid))*/
			if(member == null)
			{
				//아이디 오류 메시지 보내기 
				request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(!member.getPwd().equals(pwd))
			{
				//비번 오류 메시지
				request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
				dispatcher.forward(request, response);
			}  
			
			else
			{
				//인증성공
				HttpSession session = request.getSession();//인증정보를 주는 법을 배움 //이제 권한을 어떻게 줄것인지를 생각
				session.setAttribute("uid", uid);
				response.sendRedirect("../customer/notice");//절대경로와 상대경로가 있다하면 상대경로가 우선!!
			}
		}

		else 
		{
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
