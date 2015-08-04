package com.newlecture.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeDao;
import com.newlecture.web.dao.mybatis.MyBatisNoticeFileDao;
import com.newlecture.web.vo.Notice;
import com.newlecture.web.vo.NoticeFile;


/*@WebServlet("/customer/noticeReg")*/  //url 맵핑
@MultipartConfig(
		/*location="/css",*/
		fileSizeThreshold= 1024*1024,
maxFileSize= 1024*1024*5, //5메가
maxRequestSize=1024*1024*5*5) //5메가, 5개까지
		
public class NoticeRegController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getMethod().equals("POST"))//POST 요청이라면! 리턴한 문자열을 가지고 값을 비교 (주소를 비교할땐 ==)
		{
		
		/*======================*/	
		Part part = request.getPart("file");//Part를 통해서 스트림을 얻을 수 있음.
		
		/*StringBulider sb = new StringBuilder();
		sb.append("D:\\noticeconfig\\");
		sb.append(..); 다른방식*/
		
		ServletContext application = request.getServletContext();
		String url = "/customer/upload";
		String path = application.getRealPath(url);
		//서비스되고 있는 홈디렉토리 위치를 알아내야 한다.
		
		/*String path = "D:\\noticeconfig\\"; 상대경로이다.*/
		String temp =part.getSubmittedFileName();
		String fname = temp.substring(temp.lastIndexOf("\\")+1);
		String fpath = path + "\\" + fname;
		response.getWriter().println(fpath);
		
		InputStream ins = part.getInputStream();
		OutputStream outs = new FileOutputStream(fpath);
		
		byte[] 대야 = new byte[1024];
		int len =0;
		
		while((len = ins.read(대야, 0, 1024)) >=0)
			outs.write(대야, 0, len);
		
		outs.flush();
		outs.close();
		ins.close();
		/*==========================*/
		
		/*==================*/
		String title= request.getParameter("title");
		String file=request.getParameter("file");
		String content=request.getParameter("content"); //지정한 이름으로 데이터를 얻어와야 한다.
		
		Notice notice = new Notice();
		NoticeFile noticeFile = new NoticeFile();
		
		notice.setTitle(title);
		notice.setWriter("newlec");
		notice.setContent(content);
		//notice.setRegDate("2015");
		//notice.setRegDate(0);
		NoticeDao noticeDao = new MyBatisNoticeDao(); 
		noticeDao.addNotice(notice);
		/*==================*/

		 NoticeFileDao noticeFileDao = new MyBatisNoticeFileDao();
		 noticeFile.setName(fname);
		 noticeFile.setNoticeCode(noticeDao.getLastCode());//제일 최신(마지막) 공지게시글에 파일을 첨부해야되니까!! 
		 noticeFileDao.addNoticeFile(noticeFile);
		 
		 response.sendRedirect("notice");
		}
		
		else 
		{
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/view/customer/noticeReg.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
