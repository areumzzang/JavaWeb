package com.newlecture.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;
import com.newlecture.web.vo.NoticeFile;


//post == redirect 
@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	//������������ ��Ʈ�ѷ��� ����� ���� �ƴ϶� ������ ���������� ��� �ִ� ���������� Controller�� �������Ѵ�.
	
	/*@RequestMapping("notice")
	public void notice(PrintWriter out){//����ó�� ȣ��
		out.println("Hello");
		out.close();
	}*/
	
	private NoticeDao noticeDao;
	private NoticeFileDao noticeFileDao;
	
	//private NoticeDao noticeDao = new MyBatisNoticeDao();
	//��������� ���Ǹ� �� ������ ���Կ����ڿ� new�����ڷ� ������ �ؼ��� �ȵȴ�. (���赵�� ������ ����� ��)
	
	//���� ���շ����� ��� ���� ���շ����� ��ŷ�̳� ����
	/*public CustomerController(){
		noticeDao = new MyBatisNoticeDao();
	}*/
	
	//setter��!
	@Autowired  //DAO�� �������� �� �ֵ��� Autowired�� ���� �ڵ����γ־��ּ���(IoC�����̳��ǰ�ü���־��ִ°Ŵϱ� ��ü�� ����������)
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	//��ü�� �ϳ��� �ִٸ� @Autowired�� ����, �ٸ� ��ü�� ���� �� �ִٸ� �ٸ� ������� ��������� �Ѵ�.
	//servlet-context.xml�� ��ü�����������. noticeDao��ü
	
	@Autowired
	public void setNoticeFileDao(NoticeFileDao noticeFileDao) {
		this.noticeFileDao = noticeFileDao;
	}

	@RequestMapping("notice")
    public String notice(Model model) {
 
        List<Notice> list = noticeDao.getNotices();
 
        model.addAttribute("list", list);
        
        return "customer.notice";
        //return "customer.notice";
        //return "/WEB-INF/view/customer/notice.jsp";
 
    }

	@RequestMapping("noticeDetail")
	public String noticeDetail(String c, Model model) {
		//����ڰ� �Ѱܹ��� �� �ִ� ������ �������ָ� �ȴ�. String c
		//NoticeDao noticeDao = new MyBatisNoticeDao();
		Notice n = noticeDao.getNotice(c);  //������� �ϳ�
		
		model.addAttribute("n", n);
		
		return "customer.noticeDetail";
		//return "/WEB-INF/view/customer/noticeDetail.jsp";
		
	}
	
	//noticeReg�Լ��� �����ε�, ������̼����� get��û�� post��û�� �������ش�.
	@RequestMapping(value="noticeReg", method=RequestMethod.GET)
	public String noticeReg() { //form�� �޶���Ѵ�.(get��û) //forward
		return "customer.noticeReg";
		//return  "/WEB-INF/view/customer/noticeReg.jsp";
		
	}
	
	@RequestMapping(value="noticeReg", method=RequestMethod.POST) //redirect
	public String noticeReg(Notice n, MultipartFile file,  HttpServletRequest request, Principal principal
			//SecurityContextHolder holder, 
			//SecurityContext context
			) throws IOException {//title�̿��� setTitle�� �ִ��� ������ Ȯ���ϰ� ������ �����̵�. ����: ��� ������ �ȵ�. 
		
		/*if(request.isUserInRole("ROLE_ADMIN"))
		{
			
		}
		
		//holder.getContext();
		context.getAuthentication().getAuthorities();
		context.getAuthentication().isAuthenticated();*/
		
		n.setWriter(principal.getName());
		noticeDao.addNotice(n);
		String lastCode = noticeDao.getLastCode();
		
		if(!file.isEmpty())
		{
		
		//get��û�� post��û�� �Բ� ó���ϰ� �ؾ��Ѵ�.
		//setter�� �����ϰ� �Ӽ��� ����ָ� ��. �ٵ� binaryfile�� ���ϰ�ü�� �Ӽ����� ����ִ� ���� �������� �ʴ´�.  
		//Part part = request.getPart("file");//Part�� ���ؼ� ��Ʈ���� ���� �� ����.
		ServletContext application = request.getServletContext();
		//System.out.println("post file");
		String url = "/resource/customer/upload";
		String path = application.getRealPath(url);
		
		/*String path = "D:\\noticeconfig\\"; ������̴�.*/
		String temp =file.getOriginalFilename();//part.getSubmittedFileName(); //originalfilename�� �������ϸ�		
		String fname = temp.substring(temp.lastIndexOf("\\")+1);
		String fpath = path + "\\" + fname;
		System.out.println(fpath);
		
		//response.getWriter().println(fpath);���������ֱ� �����Ƿ� ����
		
		InputStream ins = file.getInputStream();//part.getInputStream(); //��������. ��Ƽ��Ʈ������ ���� ���� ���� ��ġ����
		OutputStream outs = new FileOutputStream(fpath);
		
		byte[] ��� = new byte[1024];
		int len =0;
		
		while((len = ins.read(���, 0, 1024)) >=0)
			outs.write(���, 0, len);
		
		outs.flush();
		outs.close();
		ins.close();
		
		NoticeFile noticeFile = new NoticeFile();
		noticeFile.setNoticeCode(lastCode);
		noticeFile.setName(fname);
		noticeFileDao.addNoticeFile(noticeFile);
		}
		
		//DAO�� ��� �޼��尡 ����ϴ� �����ϱ� ��������� ���� ���� �� ����. �޼��帶�� �ݺ��Ǵ� ������ ��������� ����.
		//��������� ������� �����ϴ� ����,  this�� ��� �����Ͱ� �����ϴ� ������ 
		
		//NoticeDao noticeDao = new MyBatisNoticeDao(); 
		//-------------�ϳ��� Ʈ��������� �����ʾұ� ������ �����ϴٴ� ���� �˾ƾ� ��.//
	/*	noticeDao.addNotice(n);
		String lastCode = noticeDao.getLastCode();
		if(file.isEmpty())
		{
		NoticeFile noticeFile = new NoticeFile();
		noticeFile.setNoticeCode(lastCode);
		noticeFile.setName(fname);
		noticeFileDao.addNoticeFile(noticeFile);
		}*/
		//-------------//
		//�ϳ��� Ʈ��������� ������� �Ѵ�. ���ÿ� �������� ������ �ؼ� lastCode�� �̻����� ���� �ִ�. 
		//���񽺸� �ϱ� �������� �� �������� ������ ���� ���� �ִ�.
		//Ʈ����� : ������ ���� ����
		return "redirect:notice";
	}
}
