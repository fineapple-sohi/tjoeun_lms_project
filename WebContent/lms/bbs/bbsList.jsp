<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, kr.co.tjoeun.model.BbsDto" %>
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
	<h2 class="pg-tit"><%=request.getAttribute("bbsTit") %> 게시판</h2>
	
	<%
		ArrayList<BbsDto> list = (ArrayList<BbsDto>)request.getAttribute("list");
	%>
	<div class="board-wrap">
		<table>
			<colgroup>
				<col width="150">
				<col width="">
				<col width="200">
				<col width="250">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<% for(BbsDto bean:list) {%>
				<tr>
					<td><a href="bbsView.lms?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsIdx() %></a></td>
					<td><a href="bbsView.lms?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsSub() %></a></td>
					<td><a href="bbsView.lms?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsMemberId() %></a></td>
					<td><a href="bbsView.lms?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsNalja() %></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<div class="btn-wrap">
			<a class="btn-box blue" href="bbsWrite.lms?bbs_table=<%=request.getAttribute("bbsTable") %>">글쓰기</a>
		</div>
	</div>
	
	
	
	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>