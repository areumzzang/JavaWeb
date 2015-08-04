<%@page import="com.newlecture.web.vo.Notice"%>
<%@page import="com.newlecture.web.dao.mybatis.MyBatisNoticeDao"%>
<%@page import="com.newlecture.web.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%
	if(request.getMethod().equals("POST"))//POST 요청이라면! 리턴한 문자열을 가지고 값을 비교 (주소를 비교할떈 ==)
	{
	//사용자가 전달한 타이틀, 파일, 내용
	//String title= new String(request.getParameter("title").getBytes("ISO8859-1"), "UTF-8"); //파라미터 얻는 작업
	String title= request.getParameter("title");
	String file=request.getParameter("file");
	String content=request.getParameter("content"); //지정한 이름으로 데이터를 얻어와야 한다.
	
	Notice notice = new Notice();
	//notice.setCode(?);
	notice.setTitle(title);
	notice.setWriter("newlec");
	notice.setContent(content);
	//notice.setRegDate("2015");
	//notice.setRegDate(0);
	// 데이터베이스에 있는 기본값을 쓰기로하고 기본값이 들어간 컬럼을 뺌 
	
	//데이터베이스에서 디폴트값을 설정해주는 것이 좋다. regDate
	
    NoticeDao noticeDao = new MyBatisNoticeDao(); 
	noticeDao.addNotice(notice);
	//입출력도구 request, response 
	//저장도구
	response.sendRedirect("Notice.jsp");
	//다른 페이지로 이동하는 메소드가 입출력도구 중에서 출력도구에 있는 것이다.
	}
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
		
		    <section> <!-- 상세페이지 -->
				<h1>공지 내용${header.host}</h1> <!-- 불필요한 페이지는 만들지 않는 것이 좋다. NoticeFrom.jsp같은 -->>
				<form method="post"><!-- action : 애가 전달한 데이터를 누가 처리하게 할까?get요청 -->
				<fieldset>                                 <!--action="" 자기자신이 행동하게 될 경우 굳이 action을 넣지 않아도됨. -->
				<legend>공지사항 정보</legend>
				<dl>
				<dt>제목</dt>
				<dd><input name="title"/></dd> <!-- name을 지정해줘야 데이터를 전달받을 수 있다. -->
				<dt>첨부파일</dt>
				<dd><input type="file" name="file" /></dd>
				<dt>내용</dt>  
				<dd>
				<textarea cols="40" rows="20" name="content"></textarea><!-- 여러 개를 등록하게 하는 것, 빈공백x -->
				</dd>
				</dl>
				<div><input type="submit" value="등록" /></div>	
				</fieldset>
				</form>
			</section>
			
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