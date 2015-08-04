package com.newlecture.web;

import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.web.dao.mybatis.SqlNewlecSessionFactory;

public class Program {
	
	static{//�����Լ� ȣ�� ���� ����Ǵ� ����
		
		//�ܺο� ���� ���� ��Ĺ�� �������ִ� ���̴�.
		//config�� mybatis���۸� ���������ʾƼ� null���� ���.
		SqlNewlecSessionFactory factory = new SqlNewlecSessionFactory();
		try {
			factory.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { //�����Լ� �߰��� ProgramŬ������ ������.
		
		//IoC �����̳ʿ� ��ü�� �����ϰ� �����ϴ� ���� ������ ���̴�. 
		//MyBatisNoticeDao, NoticeConsole �� 2���� ��ü 
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
