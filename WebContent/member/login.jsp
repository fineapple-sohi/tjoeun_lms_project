<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@ include file="../template/head.jspf" %>
<style>
	.login-box {
		width: 400px;
		margin: 200px auto 100px;
	}
	.login-box h2 {
		text-align: center;
		margin-bottom: 30px;
	}
	.box-inner {
		width: 400px;
		border: 1px solid #333;
		padding: 40px 60px;
	}
	form > div {
		display: flex;
		align-items: center;
		padding: 10px 0;
	}
	form label {
		width: 90px;
		font-size: 18px;
		font-weight: bold;
	}
	form button {
		display: block;
		width: 100%;
		border: 0;
		background: #0082FF;
		color: #fff;
		font-size: 20px;
		font-weight: bold;
		padding: 10px 0;
		cursor: pointer;
	}
	form input[type="text"] {
		width: calc(100% - 90px);
		height: 30px;
		font-size: 20px;
		padding: 0 5px;
	}
	.err-msg {
		color: #ff003e;
		font-size: 14px;
		margin-top: 20px;
	}
	
</style>
<script>
	$(function(){
		if(<%=request.getAttribute("loginResult")%> == -1) {
			$('.err-msg').text("아이디 또는 비밀번호가 잘못 입력되었습니다.");
		}		
	});
</script>
</head>
<body>
<%@ include file="../template/header.jspf" %>	

<div class="container1440">
	<div class="login-box">
		<h2>로그인</h2>
		<div class="box-inner">
			<form action="<%=request.getAttribute("path") %>login/login.do"  method="post">
				<div>
					<label for="id">아이디</label>
					<input type="text" name="id" id="id">
				</div>
				<div>
					<label for="pw">비밀번호</label>
					<input type="text" name="pw" id="pw">
				</div>
				<div>
					<span class="err-msg"></span>
				</div>
				<div>
					<button type="submit">로그인</button>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>