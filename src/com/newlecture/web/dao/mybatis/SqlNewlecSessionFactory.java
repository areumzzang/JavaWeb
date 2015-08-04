package com.newlecture.web.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//static생성자 
//인스턴스 생성자 , 스태틱 생성자(인서트와 상관없이 클래스가 호출되자마자 생성되는)


public class SqlNewlecSessionFactory extends HttpServlet { //서블릿으로 만듬
	public static SqlSessionFactory ssf;
	
	@Override
	public void init() throws ServletException { //서블릿이 실행되면 딱 한번만 실행되는 init 메소드 오버라이드 톰캣이 실행하자마자 실행시켜줄 수 있도록 설정
		String resource = "com/newlecture/web/dao/mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		ssf = new SqlSessionFactoryBuilder().build(inputStream);
		
	}

	public SqlSessionFactory getSqlSessionFactory(){
		return ssf;
		
	}

}
