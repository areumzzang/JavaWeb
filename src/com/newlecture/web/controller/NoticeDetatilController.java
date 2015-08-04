package com.newlecture.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.vo.Notice;

/*@WebServlet("/customer/noticeDetail") */// 올바르게 바꾸지 않으면 톰캣이 실행하다가 오류가 난다.
public class NoticeDetatilController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//공지사항에서 공지사항 상세페이지의 내용을 볼 수 없게 해야하는걸 먼저할까? 
		//아니면 공지사항에서 글을 등록할 수 없게 하는 걸 먼저할까? 
		//우선은 공지사항목록에서 공지사항하나를 선택을 하면 상세페이지의 내용을 볼 수 없게 하자부터 
		//보안문제를 끌어들이다 보니까 아래처럼 코드를 구현하게 됨.
		//====================================//
		if(session.getAttribute("uid") == null)
			response.sendRedirect("notice?error=1"); //안좋은소식 반복==>수정과 결부 //라이브러리를 쓸래?? 그냥이거할래? /"스프링 시큐리티라이브러리"..
		//====================================//
		NoticeDao noticedao = new MyBatisNoticeDao(); 
		String code = request.getParameter("c");
		Notice n = noticedao.getNotice(code);   
		request.setAttribute("n", n);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/noticeDetail.jsp");
		dispatcher.forward(request, response); 
		
	}
}
