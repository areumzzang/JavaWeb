<%@page import="com.newlecture.web.vo.Notice"%>
<%@page import="com.newlecture.web.dao.mybatis.MyBatisNoticeDao"%>
<%@page import="com.newlecture.web.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<%
	NoticeDao noticedao = new MyBatisNoticeDao(); 
	String code = request.getParameter("c");
	Notice n = noticedao.getNotice(code);   
	request.setAttribute("n", n);
%>


<!DOCTYPE html>   <!-- div를 section으로 다 바꿈!! 이미지를 추가하기전 -->
<html>                   <!-- 현재 -->
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<header id="header">
		<h1>
			뉴렉처<!--  body안에 커다란 제목이 있는데 들여쓰기해야한다. 들여간다는것은 안쪽에 섹션이 따로 있다는 것이다. -->
		</h1>
		
		<section> <!-- 왜 section으로 했나요? -->
			<h1>머릿말</h1> <!-- section으로 따로 나눴으므로 <h2>에서 <h1>으로 변경 -->
			
			<nav>
				<h1>메인메뉴</h1> <!-- nav라는 섹션으로 나뉘었기 때문에 h3에서 h1으로 변경 -->
				<ul>
					<li><a href="">학습가이드</a></li>
					<li><a href="">뉴렉과정</a></li>
					<li><a href="">강좌선택</a></li>
				</ul>
			</nav>   <!-- 네비게이션일뿐이지 컨텐트(의미)가 있는것은 아니다. -->
			
			<section>
				<h1>강좌검색 폼</h1>
				<form>
				<fieldset>
					<legend>검색정보</legend>
					<label>과정검색</label>
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
				공지사항 내용
			</h1>
			
			<nav>
				<h1>경로</h1>
				<ul>
					<li><a href="">Home</a></li>
					<li><a href="">고객센터</a></li>
					<li><a href="">공지사항</a></li>
				</ul>
			</nav>
		
		    <article> <!-- 상세페이지 -->
				<h1>공지 내용${header.host}</h1>
				<dl>
				<dt>제목</dt>
				<dd>${n.title}</dd>
				<dt>작성일</dt>
				<dd>${n.regDate}</dd>
				<dt>작성자</dt>
				<dd>${n.writer}</dd>
				<dt>조회수</dt>
				<dd>${n.hit}</dd>
				<dt>첨부파일</dt>
				<dd></dd>
				<dt>내용</dt>
				<dd>
				${n.content}
				</dd>
				</dl>	
			</article>
			
			<nav>
				<h1>버튼 목록</h1>
				<div>
					<a href="Notice.jsp">목록</a>
				</div>
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
