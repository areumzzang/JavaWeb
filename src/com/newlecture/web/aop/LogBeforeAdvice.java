package com.newlecture.web.aop;


import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice
{
   public void before(Method method, Object[] args, Object target) throws Throwable
   {
      System.out.println("[newlec] : " + method.getName() + "BEFORE");

   }
}