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
//�̰��� "/"��Ʈ�κ��� ��θ� ����  //url���ι���
public class NoticeController extends HttpServlet {//����ڴ� ���ε� URL�ּҸ� �˰Եȴ�.

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		NoticeDao noticeDao = new MyBatisNoticeDao();
		List<Notice> list = noticeDao.getNotices();  
		request.setAttribute("list", list);  /* �ٸ�Ŭ����(jstlŬ����)�� list���� �����ش޶�� ��û */
		//C�� V�� �и� : ����, ����, ��������, �������� ���� ������ �ִµ� �ݸ� �����ϱⰡ �ټ� ��ƴٴ� ������ �ִ�.
		
		//dispatcher : �ٸ� ���� ȣ���ϴ� �ǹ�, ���� �ϴ� ���� ��������.
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customer/notice.jsp");
		dispatcher.forward(request, response); 
	}
}
