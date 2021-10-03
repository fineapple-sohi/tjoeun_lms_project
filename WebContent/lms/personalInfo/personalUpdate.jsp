<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.tjoeun.model.*" %>
<%int auth=(Integer)session.getAttribute("sessionAuth"); %>
<%
String pw=(String)request.getAttribute("pw");
String tel=(String)request.getAttribute("tel");
String email=(String)request.getAttribute("email");
String addr=(String)request.getAttribute("addr");

%>

<%String invalidItemName=(String)request.getAttribute("invalidItemName"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<script>
$(document).ready(function(){
	$('#errorMsg').hide();
	$('#successMsg').hide();
<%if(1==auth){%>
	$('#depNameLabel').text('과정명');
<%}%>

<%if("pw".equals(invalidItemName)){%>
	$('#pw').select().css('background-color','rgba(255,0,0,0.2)');
	$('#errorMsg').show();
<%} else if("tel".equals(invalidItemName)){%>
	$('#tel').select().css('background-color','rgba(255,0,0,0.2)');
	$('#errorMsg').show();
<%} else if("email".equals(invalidItemName)){%>
	$('#email').select().css('background-color','rgba(255,0,0,0.2)');
	$('#errorMsg').show();
<%} else if("addr".equals(invalidItemName)){%>
	$('#addr').select().css('background-color','rgba(255,0,0,0.2)');
	$('#errorMsg').show();
<%} else if("updateFinished".equals(invalidItemName)){%>
	$('#successMsg').show();
<%} else {%>
	$('#pw').select().css('background-color','rgba(255,255,0,0.2)');
<%}%>

	$('input').focus(function(){
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
#nameLabel,#idLabel,#telLabel,#addrLabel{
	width: 100px;
	heigth: 30px;
}
#name,#id,#tel{
	width: 150px;
	height: 30px;
}
#depNameLabel,#pwLabel,#emailLabel{
	width: 100px;
	height: 30px;
}
#depName,#pw,#email{
	width: 240px;
	height: 30px;
}
#addr{
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
.spacer4{
	width: 195px;
}
#errorMsg{
	color: red;
}
#successMsg{
	color: blue;
}
</style>
</head>
<body>
	<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<h2 class="pg-tit">정보 수정</h2>
<%
String name="";
String depName=""; //학생의 경우 lectName으로 대체됨
String depCode=""; //학생의 경우 stu
String id=(String)session.getAttribute("sessionID");
System.out.println(id);
if(1==auth){
	StuDto bean=new StuDao().selectStu(id);
	name=bean.getStuName();
	depName=bean.getStuLect().getLectName();
	depCode="stu";
	pw=(pw==null)?bean.getStuPw():pw;
	tel=(tel==null)?bean.getStuTel():tel;
	email=(email==null)?bean.getStuEmail():email;
	addr=(addr==null)?bean.getStuAddr():addr;
} else if(4==auth){
	AdminDto bean=new AdminDao().selectAdmin(id);
	name=bean.getAdminName();
	depName="행정팀";
	depCode="admin";
	pw=(pw==null)?bean.getAdminPw():pw;
	tel=(tel==null)?bean.getAdminTel():tel;
	email=(email==null)?bean.getAdminEmail():email;
	addr=(addr==null)?bean.getAdminAddr():addr;
} else if(3==auth){
	SalesDto bean=new SalesDao().selectSales(id);
	name=bean.getSalesName();
	depName="영업팀";
	depCode="sales";
	pw=(pw==null)?bean.getSalesPw():pw;
	tel=(tel==null)?bean.getSalesTel():tel;
	email=(email==null)?bean.getSalesEmail():email;
	addr=(addr==null)?bean.getSalesAddr():addr;
} else if(2==auth){
	TeacherDto bean=new TeacherDao().selectTeacher(id);
	name=bean.getTeacherName();
	depName="강사팀";
	depCode="teacher";
	pw=(pw==null)?bean.getTeacherPw():pw;
	tel=(tel==null)?bean.getTeacherTel():tel;
	email=(email==null)?bean.getTeacherEmail():email;
	addr=(addr==null)?bean.getTeacherAddr():addr;
}

%>

	<form action="#" method="post">
	<input type="hidden" name="depCode" value="<%=depCode %>" />
	<input type="hidden" name="id" value="<%=id %>" />
	
	<div>
		<span id="nameLabel">이름</span>
		<span id="name"><%=name %></span>
		<span class="spacer1"></span>
		<span id="depNameLabel">부서</span>
		<span id="dDepName"><%=depName %></span>
	</div>
	<div>
		<span id="idLabel">아이디</span>
		<span id="id"><%=id %></span>
		<span class="spacer1"></span>
		<label for="pw" id="pwLabel">비밀번호</label>
		<input type="text" id="pw" name="pw" value="<%=pw %>" size="30" maxlength="16" pattern="\w{4,16}" placeholder="비밀번호: 로마자, 숫자로  4-16 자" required/>
	</div>
	<div>
		<label for="tel" id="telLabel">전화번호</label>
		<input type="tel" id="tel" name="tel" value="<%=tel %>" size="20" maxlength="20" placeholder="전화번호: 최대 20 자" required/>
		<span class="spacer1"></span>
		<label for="email" id="emailLabel">이메일</label>
		<input type="email" id="email" name="email" value="<%=email %>" size="36" maxlength="36" placeholder="이메일: 최대 36 자" required/>
	</div>
	<div>
		<label for="addr" id="addrLabel">주소</label>
		<input type="text" id="addr" name="addr" value="<%=addr %>" size="70" maxlength="60" placeholder="주소: 최대 60 자" required/>
	</div>
	<span class="spacer2"></span>
	<div id="buttonDiv">
		<span class="spacer3"></span>
		<button class="btn-box blue">수정</button>
		<button type="reset" class="btn-box bd-blue">취소</button>
	</div>
	<span class="spacer2"></span>
	<div id="errorMsg">
		<span class="spacer4"></span>
		허용되지 않은 형태의 값을 입력하셨습니다.
	</div>
	<div id="successMsg">
		<span class="spacer4"></span>
		개인정보 갱신에 성공하였습니다.
	</div>
	</form>



	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>