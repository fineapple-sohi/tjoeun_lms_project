<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.tjoeun.model.BbsDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<link rel="stylesheet" href="<%=request.getAttribute("path") %>css/bbs.css">
</head>
<body>
	<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<h2 class="pg-tit"><%=request.getAttribute("bbsTit") %> 상세조회</h2>
	
		
	<%
		BbsDto bean = (BbsDto)request.getAttribute("bean");
	%>
	
	<div class="board-wrap">
		<div class="borad-content">
			<div>
				<span>제목</span>
				<p><%=bean.getBbsSub() %></p>
			</div>
			<div>
				<div class="column-half">
					<span>작성자</span>
					<p><%=bean.getBbsMemberId() %></p>
				</div>
				<div class="column-half">
					<span>작성일자</span>
					<p><%=bean.getBbsNalja() %></p>
				</div>
			</div>
			<div class="content-inner">
				<%=bean.getBbsCont() %>
			</div>
			<div>
				<span>첨부파일</span>
				<p><a class="uploaded-file" href="#">파일명.jpg</a></p>
			</div>
		</div>
		<div class="btn-wrap">
			<a class="btn-box bd-blue" href="#">삭제</a>
			<a class="btn-box blue" href="#">수정</a>
			<a class="btn-box blue" href="#">목록</a>
		</div>
	</div>
	
	
	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>