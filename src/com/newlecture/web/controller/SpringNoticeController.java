package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.vo.Notice;

public class SpringNoticeController implements Controller {//사용자는 맵핑된 URL주소를 알게된다.

	//NotcieController를 복사하여 Controller를 implements하기 
	//servlet으로 선택하여!! 내가 알아서 디스패쳐해줄게!! 그대신 디스패처하기위한 URL은 알려줘야한다.
	//Model과 View를 알려주지않을래 내가 알아서 담아서 forward할게
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		NoticeDao noticeDao = new MyBatisNoticeDao();
		List<Notice> list = noticeDao.getNotices();  
		/*request.setAttribute("list", list);*/  
		/* 다른클래스(jstl클래스)에 list값을 셋팅해달라고 요청 */
		//C와 V를 분리 : 성능, 재사용, 유지보수, 협업가능 등의 장점이 있는데 반면 구현하기가 다소 어렵다는 단점이 있다.
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/view/customer/notice.jsp");
		mv.addObject("list", list);
		
		//dispatcher : 다른 것을 호출하는 의미, 내가 하던 것을 가져간다.
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/notice.jsp");
		dispatcher.forward(request, response); */
		
		return mv;
	}
}
