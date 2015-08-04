package com.newlecture.web.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class LogAroundAdvice implements MethodInterceptor
{
   @Override
   public Object invoke(MethodInvocation method) throws Throwable 
   {
      StopWatch stopWatch = new StopWatch();
      
      System.out.println("[시간체크] 호출시작");
      stopWatch.start();
      
      Object result = method.proceed(); // 왕자님 호출 하는 조인 포인트
      
      System.out.println("[시간체크] 호출 끝");
      stopWatch.stop();
      
      System.out.println("[TIMELOG] Method : " + method.getMethod().getName()+"is finished" );
      System.out.println("[TIMELOG] Process TIME : " + stopWatch.getTotalTimeMillis());
      
      return result; 
   }
}
