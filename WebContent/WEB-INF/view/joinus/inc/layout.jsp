<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!-- JSTL 활용방법 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   <!-- 날짜 변환 -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="titles" uri="http://tiles.apache.org/tags-tiles"%>
<!-- core or control 라이브러리를 따서 "c" http://java.sun.com/jsp/jstl/core 에서 처리해 주겠다는 뜻 -->

<c:set var="ctxName" value="${pageContext.request.contextPath}" />

<%
	//request.setCharacterEncoding("UTF-8"); 서블릿에서 이미 다른용도로 request메소드를 쓰고 있으므로 이것은 설정이 안된다.
	/* NoticeDao noticeDao = new MyBatisNoticeDao();
	List<Notice> list = noticeDao.getNotices();  
	request.setAttribute("list", list);  /* 다른클래스(jstl클래스)에 list값을 셋팅해달라고 요청 */ 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>index</title>
      <link href="../resource/customer/notice.css" type="text/css" rel="stylesheet" />
   </head>
   <body>
      	<%--Header영역 --%>
      	<titles:insertAttribute name="header" />
    	<%-- <jsp:include page="../inc/header.jsp" />  --%>
       
      <div id="visual" class="customer">
         <div class="top-wrapper">

         </div>
      </div>
      <div id="main">
         <div class="top-wrapper clear">
            <!-- Body(content)영역 -->
            <titles:insertAttribute name="body" />
            <%-- <jsp:include page="../inc/notice.jsp" />  --%>
            
            <!-- Aside(nav)영역 -->
            <titles:insertAttribute name="aside" />
            <%-- <jsp:include page="../inc/aside.jsp" />  --%>
      
      <!-- Footer 영역 -->
      <titles:insertAttribute name="footer" />
      	<%-- <jsp:include page="../inc/footer.jsp" />  --%>
      
   </body>
</html>