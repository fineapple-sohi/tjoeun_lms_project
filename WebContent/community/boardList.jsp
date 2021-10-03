<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, kr.co.tjoeun.model.BbsDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=request.getAttribute("bbsTit") %></title>
<%@ include file="../template/head.jspf" %>
<link rel="stylesheet" href="<%=request.getAttribute("path") %>css/bbs.css">
<style>
	.notice-wrap {
		padding: 80px 120px;
		background-color: #f5f5f5;
	}
	.notice-wrap h2 {
		margin-bottom: 40px;
		border-bottom: 1px solid #333;
		padding: 0 10px 10px;
	}
</style>
</head>
<body>
<%@ include file="../template/header.jspf" %>	

<div class="notice-wrap">
	<div class="container1440">
		<h2><%=request.getAttribute("bbsTit") %></h2>
		
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
					<td><a href="boardView.do?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsIdx() %></a></td>
					<td><a href="boardView.do?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsSub() %></a></td>
					<td><a href="boardView.do?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsMemberId() %></a></td>
					<td><a href="boardView.do?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>"><%=bean.getBbsNalja() %></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<%
		boolean boo = false;
		if(sessId != null){
			if(request.getAttribute("bbsTable").equals("notice") && sessAuth.equals(4)) {
				boo = true;
			} else if (request.getAttribute("bbsTable").equals("quest")) {
				boo = true;
			}
		}					
		if(boo) {	
		%>
		<div class="btn-wrap">
			<a class="btn-box blue" href="boardWrite.do?bbs_table=<%=request.getAttribute("bbsTable") %>">글쓰기</a>
		</div>
		<%} %>
	</div>
</div>

<%@ include file="../template/footer.jspf" %>	
</body>
</html>