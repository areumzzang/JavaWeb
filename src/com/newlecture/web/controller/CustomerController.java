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
	//페이지단위로 컨트롤러를 만드는 것이 아니라 이제는 페이지들을 담고 있는 폴더단위로 Controller를 만들어야한다.
	
	/*@RequestMapping("notice")
	public void notice(PrintWriter out){//디스패처가 호출
		out.println("Hello");
		out.close();
	}*/
	
	private NoticeDao noticeDao;
	private NoticeFileDao noticeFileDao;
	
	//private NoticeDao noticeDao = new MyBatisNoticeDao();
	//멤버변수로 정의를 할 때에는 대입연산자와 new연산자로 연산을 해서는 안된다. (설계도에 공간을 만드는 격)
	
	//강한 결합력으로 노노 낮은 결합력으로 도킹이나 세터
	/*public CustomerController(){
		noticeDao = new MyBatisNoticeDao();
	}*/
	
	//setter씀!
	@Autowired  //DAO를 인젝션할 수 있도록 Autowired를 설정 자동으로넣어주세요(IoC컨테이너의객체를넣어주는거니까 빈객체를 만들어줘야함)
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	//객체가 하나만 있다면 @Autowired만 지정, 다른 객체가 여러 개 있다면 다른 방법으로 설정해줘야 한다.
	//servlet-context.xml에 객체생성해줘야함. noticeDao객체
	
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
		//사용자가 넘겨받을 수 있는 변수만 선언해주면 된다. String c
		//NoticeDao noticeDao = new MyBatisNoticeDao();
		Notice n = noticeDao.getNotice(c);  //목록중의 하나
		
		model.addAttribute("n", n);
		
		return "customer.noticeDetail";
		//return "/WEB-INF/view/customer/noticeDetail.jsp";
		
	}
	
	//noticeReg함수를 오버로드, 어노테이션으로 get요청과 post요청을 구분해준다.
	@RequestMapping(value="noticeReg", method=RequestMethod.GET)
	public String noticeReg() { //form을 달라고한다.(get요청) //forward
		return "customer.noticeReg";
		//return  "/WEB-INF/view/customer/noticeReg.jsp";
		
	}
	
	@RequestMapping(value="noticeReg", method=RequestMethod.POST) //redirect
	public String noticeReg(Notice n, MultipartFile file,  HttpServletRequest request, Principal principal
			//SecurityContextHolder holder, 
			//SecurityContext context
			) throws IOException {//title이오면 setTitle이 있는지 없는지 확인하고 있으면 셋팅이됨. 주의: 없어도 에러가 안됨. 
		
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
		
		//get요청과 post요청을 함께 처리하게 해야한다.
		//setter를 생성하고 속성을 담아주면 됨. 근데 binaryfile을 파일객체를 속성으로 담아주는 것은 권하지는 않는다.  
		//Part part = request.getPart("file");//Part를 통해서 스트림을 얻을 수 있음.
		ServletContext application = request.getServletContext();
		//System.out.println("post file");
		String url = "/resource/customer/upload";
		String path = application.getRealPath(url);
		
		/*String path = "D:\\noticeconfig\\"; 상대경로이다.*/
		String temp =file.getOriginalFilename();//part.getSubmittedFileName(); //originalfilename이 실제파일명		
		String fname = temp.substring(temp.lastIndexOf("\\")+1);
		String fpath = path + "\\" + fname;
		System.out.println(fpath);
		
		//response.getWriter().println(fpath);경로출력해주기 썼으므로 지움
		
		InputStream ins = file.getInputStream();//part.getInputStream(); //에러가남. 멀티파트파일의 경우로 왔을 때의 조치사항
		OutputStream outs = new FileOutputStream(fpath);
		
		byte[] 대야 = new byte[1024];
		int len =0;
		
		while((len = ins.read(대야, 0, 1024)) >=0)
			outs.write(대야, 0, len);
		
		outs.flush();
		outs.close();
		ins.close();
		
		NoticeFile noticeFile = new NoticeFile();
		noticeFile.setNoticeCode(lastCode);
		noticeFile.setName(fname);
		noticeFileDao.addNoticeFile(noticeFile);
		}
		
		//DAO를 모든 메서드가 사용하는 도구니까 멤버도구로 쓰면 좋을 것 같다. 메서드마다 반복되는 도구를 멤버도구로 쓰자.
		//멤버변수는 멤버들이 공유하는 변수,  this는 모든 데이터가 공유하는 데이터 
		
		//NoticeDao noticeDao = new MyBatisNoticeDao(); 
		//-------------하나의 트랜잭션으로 묶지않았기 때문에 위험하다는 것을 알아야 함.//
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
		//하나의 트랜잭션으로 묶어줘야 한다. 동시에 유저들이 접속을 해서 lastCode가 이상해질 수도 있다. 
		//서비스를 하기 전까지는 이 문제점을 깨닫지 못할 수도 있다.
		//트랜잭션 : 논리적인 실행 단위
		return "redirect:notice";
	}
}
