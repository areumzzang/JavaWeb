
package com.newlecture.web.aop;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.mybatis.SqlNewlecSessionFactory;
import com.newlecture.web.vo.Notice;

public class Program 
{
   static //메인함수 전에 호출 됨
   {
      // 이 코드를 삽입하지 않으면 MyBatis Mapper 정보가 없기 때문에 null값이 된다. 
      SqlNewlecSessionFactory factory = new SqlNewlecSessionFactory();
      try 
      {
         factory.init();
      } 
      catch (ServletException e) 
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }      
   }
   
   public static void main(String[] args) //스레드 1개 
   {
/*      NoticeDao noticeDao_prince = new MybatisNoticeDao();
      
      NoticeDao proxy =  (NoticeDao)Proxy.newProxyInstance(
                     noticeDao_prince.getClass().getClassLoader(),   //왕자님
                     noticeDao_prince.getClass().getInterfaces(),       //왕자님
                     new InvocationHandler() {
               
               @Override
               public Object invoke(Object proxy, Method method, Object[] args) throws Throwable // Methoid가 실제 왕자님을 호출하기 위한 도구
               {
                  System.out.println("사전");  // 보조 업무 
            
                  // 왕자가 실행되는 실행 되는 로직
                  List<Notice> list = (List<Notice>) method.invoke(noticeDao_prince, args);
                  
                  System.out.println("사후");
                  
                  return list;
               }
            }); */
      
      //스레드1개 
	   ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/web/aop/spring-context.xml");
       NoticeDao noticeDao = (NoticeDao)context.getBean("noticeDao"); // 진짜인지 가짜인지 모르는 방식
            
//      Proxy인지 진짜 인지 모름
      List<Notice> list = noticeDao.getNotices();
//      List<Notice> list = proxy.getNotices();
      Notice notice = noticeDao.getNotice("1");
      for(Notice n : list)
         System.out.printf("제목 :  %s\n", n.getTitle());
   }
}