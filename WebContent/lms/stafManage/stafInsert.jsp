<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, kr.co.tjoeun.model.*" %>
<%String stafDepCode = (String)request.getAttribute("stafDepCode"); %>
<%String stafName = (String)request.getAttribute("stafName");
stafName = (stafName==null)?"":stafName;
String stafId = (String)request.getAttribute("stafId");
stafId = (stafId==null)?"":stafId;
String stafPw = (String)request.getAttribute("stafPw");
stafPw = (stafPw==null)?"":stafPw;
String stafTel = (String)request.getAttribute("stafTel");
stafTel = (stafTel==null)?"":stafTel;
String stafEmail = (String)request.getAttribute("stafEmail");
stafEmail = (stafEmail==null)?"":stafEmail;
String stafAddr = (String)request.getAttribute("stafAddr");
stafAddr = (stafAddr==null)?"":stafAddr;
%>
<%String invalidItemName = (String)request.getAttribute("invalidItemName"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<script>
$(document).ready(function(){

<%if("stafId".equals(invalidItemName)){%>
	$('#stafId').select().css('background-color','rgba(255,0,0,0.2)');
<%} else if("stafName".equals(invalidItemName)){%>
	$('#stafName').select().css('background-color','rgba(255,0,0,0.2)');
<%} else if("stafPw".equals(invalidItemName)){%>
	$('#stafPw').select().css('background-color','rgba(255,0,0,0.2)');
<%} else if("stafTel".equals(invalidItemName)){%>
	$('#stafTel').select().css('background-color','rgba(255,0,0,0.2)');
<%} else if("stafEmail".equals(invalidItemName)){%>
	$('#stafEmail').select().css('background-color','rgba(255,0,0,0.2)');
<%} else if("stafAddr".equals(invalidItemName)){%>
	$('#stafAddr').select().css('background-color','rgba(255,0,0,0.2)');
<%} else{%>
	$('#stafName').select().css('background-color','rgba(255,255,0,0.2)');
	$('#errorMsg').hide();
<%}%>

<%if("admin".equals(stafDepCode)){%>
	$('select').children().eq(0).attr('selected','true');
<%} else if("sales".equals(stafDepCode)){%>
	$('select').children().eq(1).attr('selected','true');
<%} else if("teacher".equals(stafDepCode)){%>
	$('select').children().eq(2).attr('selected','true');
<%}%>

	$('input').focus(function(){
		$(this).select().css('background-color','rgba(255,255,0,0.2)');
	});
	$('input').blur(function(){
		$(this).css('background-color','rgba(0,0,0,0)');
	});
	$('select').focus(function(){
		$(this).css('background-color','rgba(255,255,0,0.2)');
	}).blur(function(){
		$(this).css('background-color','rgba(0,0,0,0)');
	});

	
	
	
});
</script>
<style>
span, label{
	display: inline-block;
}
#stafNameLabel,#stafIdLabel,#stafTelLabel,#stafAddrLabel{
	width: 100px;
	heigth: 30px;
}
#stafName,#stafId,#stafTel{
	width: 240px;
	height: 30px;
}
#stafDepNameLabel,#stafPwLabel,#stafEmailLabel{
	width: 100px;
	height: 30px;
}
#stafDepName,#stafPw,#stafEmail{
	width: 240px;
	height: 30px;
}
#stafAddr{
	width: 623px;
	height: 30px;
}
.spacer1{
	width: 30px;
	height: 30px;
}
.spacer2{
	width: 100%;
	height: 20px;
}
.spacer3{
	width: 285px;
}
.spacer4{
	width: 195px;
}
#errorMsg{
	color: red;
}
</style>
</head>
<body>
	<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<%if((int)session.getAttribute("sessionAuth")!=4){ %>
	<!-- 행정팀 이외의 인원이 접근했을 때 표시되는 화면 -->
	<h2 class="pg-tit">미구현 페이지</h2>
	<p> 본 페이지는 아직 구현되지 않았습니다.</p>
	<%} else{ %>
	<!-- 행정팀 인원이 접근했을 때 표시되는 화면 -->
	<h2 class="pg-tit">직원 등록</h2>
<%
//String stafName="", 
//String stafId="",/*stafDepCode="",*/ stafPw="", stafTel="", stafEmail="", stafAddr="";
//String stafDepName="";


%>

	<form action="#" method="post">
	<div>
		<label for="stafName" id="stafNameLabel">이름</label>
		<span id="stafNameSpan"><input type="text" id="stafName" name="stafName" value="<%=stafName %>" size="30" maxlength="5" pattern=".{1,5}" placeholder="이름: 최대 5 자" required /></span>
		<span class="spacer1"></span>
		<label for="stafDepName" id="stafDepNameLabel">부서</label>
		<span id="stafDepNameSpan">
			<select id="stafDepName" name="stafDepCode">
				<option value="admin">행정팀</option>
				<option value="sales">영업팀</option>
				<option value="teacher">강사팀</option>
			</select>
		</span>
	</div>
	<div>
		<label for="stafId" id="stafIdLabel">아이디</label>
		<span id="stafIdSpan"><input type="text" id="stafId" name="stafId" value="<%=stafId %>" size="30" maxlength="16" pattern=".{4,16}" placeholder="아이디: 로마자, 숫자로 4-16 자" required/></span>
		<span class="spacer1"></span>
		<label for="stafPw" id="stafPwLabel">비밀번호</label>
		<span id="stafPwSpan"><input type="text" id="stafPw" name="stafPw" value="<%=stafPw %>" size="30" maxlength="16" pattern="\w{4,16}" placeholder="비밀번호: 로마자, 숫자로  4-16 자" required/></span>
	</div>
	<div>
		<label for="stafTel" id="stafTelLabel">전화번호</label>
		<span id="stafTelSpan"><input type="tel" id="stafTel" name="stafTel" value="<%=stafTel %>" size="20" maxlength="20" placeholder="전화번호: 최대 20 자" required/></span>
		<span class="spacer1"></span>
		<label for="stafEmail" id="stafEmailLabel">이메일</label>
		<span id="stafEmailSpan"><input type="email" id="stafEmail" name="stafEmail" value="<%=stafEmail %>" size="36" maxlength="36" placeholder="이메일: 최대 36 자" required/></span>
	</div>
	<div>
		<label for="stafAddr" id="stafAddrLabel">주소</label>
		<span id="stafAddrSpan"><input type="text" id="stafAddr" name="stafAddr" value="<%=stafAddr %>" size="70" maxlength="60" placeholder="주소: 최대 60 자" required/></span>
	</div>
	<span class="spacer2"></span>
	<div id="buttonDiv">
		<span class="spacer3"></span>
		<button class="btn-box blue">등록</button>
		<button type="reset" class="btn-box bd-blue">취소</button>
	</div>
	<span class="spacer2"></span>
	<div id="errorMsg">
		<span class="spacer4"></span>
		허용되지 않은 형태의 값을 입력하셨거나 아이디가 중복되었습니다.
	</div>
	</form>
	<%} %>


	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>