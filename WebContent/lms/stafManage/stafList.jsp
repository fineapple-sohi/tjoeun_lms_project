<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, kr.co.tjoeun.model.*" %>
<%ArrayList<AdminDto> adminList = (ArrayList<AdminDto>)request.getAttribute("adminList"); %>
<%ArrayList<SalesDto> salesList = (ArrayList<SalesDto>)request.getAttribute("salesList"); %>
<%ArrayList<TeacherDto> teacherList = (ArrayList<TeacherDto>)request.getAttribute("teacherList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<script>
$(document).ready(function(){	
	$('#depCode').change(function(){
		if(this.value=='all'){
			$('.adminRow').show(1);
			$('.salesRow').show(1);
			$('.teacherRow').show(1);
		}
		else if(this.value=='admin'){
			$('.adminRow').show(1);
			$('.salesRow').hide(1);
			$('.teacherRow').hide(1);
		}
		else if(this.value=='sales'){
			$('.adminRow').hide(1);
			$('.salesRow').show(1);
			$('.teacherRow').hide(1);
		}
		else if(this.value=='teacher'){
			$('.adminRow').hide(1);
			$('.salesRow').hide(1);
			$('.teacherRow').show(1);
		}
	});
	
	$('table>tbody>tr').hover(function(){
		$(this).css('background-color','rgba(255,255,0,0.2)');
	}, function(){
		$(this).css('background-color','rgba(0,0,0,0)');
	});
});
</script>
<style>
table{
	border-collapse: collapse;
}
table>thead>tr>th{
	text-align: left;
	font-weight: normal;
}
table>thead>tr>th:nth-child(1){
	width: 85px;
}
table>thead>tr>th:nth-child(2){
	width: 85px;
}
table>thead>tr>th:nth-child(3){
	width: 160px;
}
table>thead>tr>th:nth-child(4){
	width: 210px;
}
table,th,td{
	border: 1px solid gray;
}
table th,table td{
	padding: 5px;
}
table th{
	background-color: #bbbbbb;
}
table a{
	display: block;
}
.spacer{
	width: 100%;
	height: 30px;
}
</style>
</head>
<body>
	<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<h2 class="pg-tit">직원 조회</h2>
	
	<div>
	<select id="depCode">
		<option value="all">부서명</option>
		<option value="admin">행정팀</option>
		<option value="sales">영업팀</option>
		<option value="teacher">강사팀</option>
	</select>
	</div>
	<div class="spacer"></div>
	<div>
	<table>
		<thead>
			<tr>
				<th>이름</th><th>부서</th><th>전화번호</th><th>이메일</th>
			</tr>
		</thead>
		<tbody>
			<%for(AdminDto bean:adminList){%>
				<tr class="adminRow">
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=admin&stafId=<%=bean.getAdminId()%>"><%=bean.getAdminName() %></a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=admin&stafId=<%=bean.getAdminId()%>">행정팀</a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=admin&stafId=<%=bean.getAdminId()%>"><%=bean.getAdminTel() %></a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=admin&stafId=<%=bean.getAdminId()%>"><%=bean.getAdminEmail() %></a></td>
				</tr>
			<%} %>
			<%for(SalesDto bean:salesList){%>
				<tr class="salesRow">
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=sales&stafId=<%=bean.getSalesId()%>"><%=bean.getSalesName() %></a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=sales&stafId=<%=bean.getSalesId()%>">영업팀</a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=sales&stafId=<%=bean.getSalesId()%>"><%=bean.getSalesTel() %></a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=sales&stafId=<%=bean.getSalesId()%>"><%=bean.getSalesEmail() %></a></td>
				</tr>
			<%} %>
			<%for(TeacherDto bean:teacherList){%>
				<tr class="teacherRow">
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=teacher&stafId=<%=bean.getTeacherId()%>"><%=bean.getTeacherName() %></a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=teacher&stafId=<%=bean.getTeacherId()%>">강사팀</a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=teacher&stafId=<%=bean.getTeacherId()%>"><%=bean.getTeacherTel() %></a></td>
					<td><a href="<%=request.getAttribute("path") %>lms/stafManage/stafDetail.lms?stafDepCode=teacher&stafId=<%=bean.getTeacherId()%>"><%=bean.getTeacherEmail() %></a></td>
				</tr>
			<%} %>
		</tbody>
	</table>
	</div>
	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>