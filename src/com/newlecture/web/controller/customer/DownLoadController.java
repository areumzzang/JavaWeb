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
		//���ϸ��� �޾Ƽ� �ȾƳ��� ��
		response.setContentType("application/octet-stream");
		//��������� ������ �ٿ�ε�޾����� ������ ������ �ʾҴ�. �ٿ�ε� �޾Ƶ� �̹����� ����� �ٿ�ε尡 �� ���� �ƴϿ���.
		
		ServletContext application = request.getServletContext();
		
		String url = "/customer/upload";
		String path = application.getRealPath(url);
		String fpath = path + "\\" + fname;
		
		OutputStream outs = response.getOutputStream();
		InputStream ins = new FileInputStream(fpath);
		
		byte[] ��� = new byte[1024];
		int len =0;
		
		while((len = ins.read(���, 0, 1024)) >=0)
			outs.write(���, 0, len);
		
		outs.flush();
		outs.close();
		ins.close();
		
		
	}

}