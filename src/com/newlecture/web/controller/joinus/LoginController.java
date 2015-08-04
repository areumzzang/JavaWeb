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
		{// null������ �ƴ��� ���ϴ� ���� ���� �ʾƵ� ��
	     // ��û�� null������ �ø��� ����. DAO, VO�� �־����
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			
			MemberDao memberDao = new MyBatisMemberDao();
			Member member = memberDao.getMember(uid);
			
			/*if(member.getUid().equals(uid))*/
			if(member == null)
			{
				//���̵� ���� �޽��� ������ 
				request.setAttribute("error", "���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.");
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
				dispatcher.forward(request, response);
			}
			
			else if(!member.getPwd().equals(pwd))
			{
				//��� ���� �޽���
				request.setAttribute("error", "���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.");
				
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/view/joinus/login.jsp");
				dispatcher.forward(request, response);
			}  
			
			else
			{
				//��������
				HttpSession session = request.getSession();//���������� �ִ� ���� ��� //���� ������ ��� �ٰ������� ����
				session.setAttribute("uid", uid);
				response.sendRedirect("../customer/notice");//�����ο� ����ΰ� �ִ��ϸ� ����ΰ� �켱!!
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
