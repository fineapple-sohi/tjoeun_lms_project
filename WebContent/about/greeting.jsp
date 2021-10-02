<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인사말</title>
<%@ include file="../template/head.jspf" %>
<style>
	.greet-wrap {
		padding: 80px 120px;
		background-color: #f5f5f5;
	}
	.greet-wrap h2 {
		margin-bottom: 40px;
		border-bottom: 1px solid #333;
		padding: 0 10px 10px;
	}
</style>
</head>
<body>
<%@ include file="../template/header.jspf" %>	

<div class="greet-wrap">
	<div class="container1440">
		<h2>인사말</h2>
		<p>인사말 페이지입니다.</p>
	</div>
</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>