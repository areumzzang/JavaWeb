package com.newlecture.web;

import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.web.dao.mybatis.SqlNewlecSessionFactory;

public class Program {
	
	static{//메인함수 호출 전에 실행되는 영역
		
		//외부에 있을 때는 톰캣이 실행해주는 것이다.
		//config에 mybatis매퍼를 갖고있지않아서 null값이 뜬다.
		SqlNewlecSessionFactory factory = new SqlNewlecSessionFactory();
		try {
			factory.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { //메인함수 추가한 Program클래스를 만들어라.
		
		//IoC 컨테이너에 객체를 생성하고 조립하는 것을 설정할 것이다. 
		//MyBatisNoticeDao, NoticeConsole 이 2개의 객체 
		//Dependency New
		/*NoticeDao noticeDao = new MyBatisNoticeDao();
		
		NoticeConsole console = new NoticeConsole();
		console.setNoticeDao(noticeDao);*/ //injection
		
		/*org.springframework.context.*/
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("com/newlecture/web/spring-context.xml");
		
		NoticeConsole console = (NoticeConsole)context.getBean("console");
		console.print();
		
		
	}

}
