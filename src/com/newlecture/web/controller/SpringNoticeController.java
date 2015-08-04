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

public class SpringNoticeController implements Controller {//����ڴ� ���ε� URL�ּҸ� �˰Եȴ�.

	//NotcieController�� �����Ͽ� Controller�� implements�ϱ� 
	//servlet���� �����Ͽ�!! ���� �˾Ƽ� ���������ٰ�!! �״�� ����ó�ϱ����� URL�� �˷�����Ѵ�.
	//Model�� View�� �˷����������� ���� �˾Ƽ� ��Ƽ� forward�Ұ�
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		NoticeDao noticeDao = new MyBatisNoticeDao();
		List<Notice> list = noticeDao.getNotices();  
		/*request.setAttribute("list", list);*/  
		/* �ٸ�Ŭ����(jstlŬ����)�� list���� �����ش޶�� ��û */
		//C�� V�� �и� : ����, ����, ��������, �������� ���� ������ �ִµ� �ݸ� �����ϱⰡ �ټ� ��ƴٴ� ������ �ִ�.
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/view/customer/notice.jsp");
		mv.addObject("list", list);
		
		//dispatcher : �ٸ� ���� ȣ���ϴ� �ǹ�, ���� �ϴ� ���� ��������.
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/notice.jsp");
		dispatcher.forward(request, response); */
		
		return mv;
	}
}
