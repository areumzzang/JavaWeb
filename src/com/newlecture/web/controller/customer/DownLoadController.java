package com.newlecture.web.controller.customer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/customer/download")
public class DownLoadController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fname = request.getParameter("f");
		
		response.setHeader("Content-Disposition", "attachment;filename="+fname);
		//파일명을 받아서 꽂아넣은 것
		response.setContentType("application/octet-stream");
		//여기까지는 파일을 다운로드받았지만 파일이 열리지 않았다. 다운로드 받아도 이미지가 제대로 다운로드가 된 것이 아니였음.
		
		ServletContext application = request.getServletContext();
		
		String url = "/customer/upload";
		String path = application.getRealPath(url);
		String fpath = path + "\\" + fname;
		
		OutputStream outs = response.getOutputStream();
		InputStream ins = new FileInputStream(fpath);
		
		byte[] 대야 = new byte[1024];
		int len =0;
		
		while((len = ins.read(대야, 0, 1024)) >=0)
			outs.write(대야, 0, len);
		
		outs.flush();
		outs.close();
		ins.close();
		
		
	}

}
