<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, kr.co.tjoeun.model.*" %>
<%String stafId = (String)request.getAttribute("stafId"); %>
<%String stafDepCode = (String)request.getAttribute("stafDepCode"); %>
<%Object bean = request.getAttribute("bean"); %>
<%String invalidItemName = (String)request.getAttribute("invalidItemName"); %>
<%request.setAttribute("bean",bean); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<script>
$(document).ready(function(){
	$('#stafPw').select().css('background-color','rgba(255,255,0,0.2)');
	$('input').focus(function(){
		$(this).select();
		$(this).css('background-color','rgba(255,255,0,0.2)');
	});
	$('input').blur(function(){
		$(this).css('background-color','rgba(0,0,0,0)');
	})
	
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
	width: 150px;
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
	width: 533px;
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
	width: 240px;
}
</style>
</head>
<body>
	<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<h2 class="pg-tit">직원 수정</h2>
<%
String stafName="", /*stafId="",*//*stafDepCode="",*/ stafPw="", stafTel="", stafEmail="", stafAddr="";
String stafDepName="";
if("admin".equals(stafDepCode)){
	AdminDto adminBean=(AdminDto)bean;
	stafName=adminBean.getAdminName();
	stafPw=adminBean.getAdminPw();
	stafTel=adminBean.getAdminTel();
	stafEmail=adminBean.getAdminEmail();
	stafAddr=adminBean.getAdminAddr();
	stafDepName="행정팀";
} else if("sales".equals(stafDepCode)){
	SalesDto salesBean=(SalesDto)bean;
	stafName=salesBean.getSalesName();
	stafPw=salesBean.getSalesPw();
	stafTel=salesBean.getSalesTel();
	stafEmail=salesBean.getSalesEmail();
	stafAddr=salesBean.getSalesAddr();
	stafDepName="영업팀";
} else if("teacher".equals(stafDepCode)){
	TeacherDto teacherBean=(TeacherDto)bean;
	stafName=teacherBean.getTeacherName();
	stafPw=teacherBean.getTeacherPw();
	stafTel=teacherBean.getTeacherTel();
	stafEmail=teacherBean.getTeacherEmail();
	stafAddr=teacherBean.getTeacherAddr();
	stafDepName="강사팀";
}
%>

	<form action="#" method="post">
	<input type="hidden" name="stafDepCode" value="<%=stafDepCode %>"/>
	<input type="hidden" name="stafId" value="<%=stafId%>"/>
	<!--input type="hidden" name="stafName" value="<%=stafName %>"/-->
	<!--input type="hidden" name="stafDepName" value="<%=stafDepName %>"/-->
	
	<div>
		<span id="stafNameLabel">이름</span>
		<span id="stafName"><%=stafName %></span>
		<span class="spacer1"></span>
		<span id="stafDepNameLabel">부서</span>
		<span id="stafDepName"><%=stafDepName %></span>
	</div>
	<div>
		<span id="stafIdLabel">아이디</span>
		<span id="stafId"><%=stafId %></span>
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
		<button class="btn-box blue">수정</button>
		<button type="reset" class="btn-box bd-blue">취소</button>
	</div>
	</form>



	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>