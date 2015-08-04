package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.vo.Notice;

/*@WebServlet("/customer/notice") */
//이것은 "/"루트로부터 경로를 지정  //url맵핑문제
public class NoticeController extends HttpServlet {//사용자는 맵핑된 URL주소를 알게된다.

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		NoticeDao noticeDao = new MyBatisNoticeDao();
		List<Notice> list = noticeDao.getNotices();  
		request.setAttribute("list", list);  /* 다른클래스(jstl클래스)에 list값을 셋팅해달라고 요청 */
		//C와 V를 분리 : 성능, 재사용, 유지보수, 협업가능 등의 장점이 있는데 반면 구현하기가 다소 어렵다는 단점이 있다.
		
		//dispatcher : 다른 것을 호출하는 의미, 내가 하던 것을 가져간다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/notice.jsp");
		dispatcher.forward(request, response); 
	}
}
