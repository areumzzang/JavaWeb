<%@page import="com.newlecture.web.vo.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.newlecture.web.dao.mybatis.MyBatisNoticeDao"%>
<%@page import="com.newlecture.web.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	NoticeDao noticeDao = new MyBatisNoticeDao();
	List<Notice> list = noticeDao.getNotices();  
	request.setAttribute("list", list);  /* 다른클래스(jstl클래스)에 list값을 셋팅해달라고 요청 */
%>

<!DOCTYPE html> <!--  div를 section으로 다 바꿈!! 이미지를 추가하기전 -->
<html>                   <!-- 현재 -->
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/style.css"  type="text/css"  rel="stylesheet" />
</head>
<body>
	<header id="header">
		<h1>
			<img src="../images/logo.png" alt="뉴렉처"><!--  body안에 커다란 제목이 있는데 들여쓰기해야한다. 들여간다는것은 안쪽에 섹션이 따로 있다는 것이다. -->
		</h1>
		
		<section id="top" class="h1"> <!-- 왜 section으로 했나요? -->
			<h1>머릿말</h1> <!-- section으로 따로 나눴으므로 <h2>에서 <h1>으로 변경 -->
			
			<nav id="main-menu">
				<h1>메인메뉴</h1> <!-- nav라는 섹션으로 나뉘었기 때문에 h3에서 h1으로 변경 -->
				<ul>
					<li><a href="">학습가이드</a></li>
					<li><a href="">뉴렉과정</a></li>
					<li><a href="">강좌선택</a></li>
				</ul>
			</nav>   <!-- 네비게이션일뿐이지 컨텐트(의미)가 있는것은 아니다. -->
			
			<section id = "lecture-search-form">
				<h1 class="h1">강좌검색 폼</h1>
				<form>
				<fieldset>
					<legend>강좌 검색정보</legend>
					<label>강좌검색</label>
					<input type="text" />
					<input type="submit" value="검색" />
				</fieldset>
				</form>
			</section>
			
			<nav>
			<h1>회원메뉴</h1>
			<ul>
				<li><a href="">HOME</a></li>
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</ul>
			</nav>
			
			<nav>
				<h1>고객메뉴</h1>
				<ul>
					<li><a href="">마이페이지</a></li>
					<li><a href="">고객센터</a></li>
				<ul>
			</nav>
		</section>
		
	</header>
	
	<aside>
		<h1>
		고객센터
		</h1> <!-- 제목인데, 몇번이 되야하지?-->
		
		<nav>
			<h1>고객센터 메뉴</h1>
			<ul>
			<li><a href="">공지사항</a></li>
			<li><a href="">1:1고객문의</a></li>
			<li><a href="">학습안내</a></li>
			</ul>
		</nav>
		
		<nav>
			<h1>추천사이트</h1>
			<ul>
			<li><a href="">answeris</a></li>
			<li><a href="">tune-a</a></li>
			</ul>
		</nav>
	</aside>
	
	<main> <!--테이블--> <!--메인은 이 문서에서의 메인이다. body내에서만 main태그 한번만  쓸 수 있다. -->
		<section>
			<h1>
				공지사항
			</h1>
			
			<nav>
				<h1>경로</h1>
				<ul>
					<li><a href="">Home</a></li>
					<li><a href="">고객센터</a></li>
					<li><a href="">공지사항</a></li>
				</ul>
			</nav>
			
			<section id="notice-form">
				<h1>공지사항 검색폼</h1>
				<form>
				<fieldset>
					<legend>공지사항 검색정보</legend>
					<label>검색필드</label>
					<select>
					<option>제목</option>
					<option>작성자</option>
					</select>
					<label>검색어</label>
					<input type="text" />
					<input type="submit" value="검색" />
					<input type="button" value="검색" />				
					</fieldset>
				</form>
			</section>
			
			<section>
				<c:forEach var="i" begin="1" end="3">
		<%-- <c:forEach var="i" begin="-1" end="3"> 왜 에러가 나는지?--%>
				<h1>${i}공지사항 목록</h1>
				</c:forEach>
				<table>
					<thead>
						<tr>
						<th>번호</th>    
						<th>제목</th>                                      
						<th>작성자</th>   
						<th>작성일</th>           
						<th>조회수</th> 
						</tr>
					</thead>
					<tbody>
					<c:forEach var="n" items= "${list}">
					<%--  <% for(int i=0; i<10; i++){%> --%>
						<tr>
						<td>${n.code}</td>
						<td><a href="NoticeDetail.jsp?c=${n.code}">${n.title}</a></td>
						<td>${n.writer}</td>   
						<td><fmt:formatDate value="${n.regDate}" pattern="yyyy-MM-dd" /></td>
						<!-- formating중에서 날짜가 대표적이라서 다뤄봄 -->     
						<td>${n.hit}</td>
						</tr>
					<%-- <%}%> --%>
					</c:forEach>
					</tbody>
				</table>
			</section>	
			
			<section>
				<h1>현재 페이지 번호</h1>
				<p>1 of 3pages</p>
			 </section>
			 
			 <nav>
				 <h1>페이지</h1>
				 <ul>
					 <li><a href="">1</a></li> 
					 <li><a href="">2</a></li> 
					 <li><a href="">3</a></li> 
					 <li><a href="">4</a></li> 
					 <li><a href="">5</a></li>
				 </ul>
			 </nav> 
		</section>	 
	</main>
	
	
	<footer>
	<section>
		<h1>
			뉴렉처
		</h1>
		
		<section>
			<h1>관리자 정보</h1>
				<dl>
					<dt>주소 :</dt>
					<dd>서울특별시 동대문고 장안1동 423-4번지 윤진빌딩 4층</dd>
					<dt>관리자메일 :</dt>
					<dd>admin@newlecture.com</dd>
					<df>전화 :</df> 
					<dd>02-478-4084</dd>
					<df>사업자등록번호 :</df>
					<dd> 132-18-46763</dd>
					<df>통신 판매업 신고 :</df>
					<dd> 제 2013-서울강동-0969 호 상호뉴렉처</dd>
					<df>대표 :</df> 
					<dd>박용우</dd>
					<df>관리자 :</df> 
					<dd>전성일</dd>
				</dl>
		</section>	
		
		<section>
			<h1>저작권 정보</h1>
				<p>Copyright ⓒ newlecture.com 2012-2014 All Right Reserved. Contact admin@newlecture.com for more information </p>
		</section>	
	</section>
	</footer>

</body>
</html>