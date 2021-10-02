<%@page import="kr.co.tjoeun.model.StuDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>

<style type="text/css">
.right-content>div{
	width: 800px;
	background-color: white;
	margin:0px auto;
}
nav,footer{
	background-color: gray;
	text-align: center;
}
section{}
section table,section table>thead>tr>th,section table>tbody>tr>td{
	border:1px solid gray;
}
section table{
	border-collapse: collapse;
	width: 500px;
	margin:0px auto;
}
section table>tbody>tr>td{
	height: 35px;
}
section table>tbody>tr>td>a{
	display: block;
	text-decoration: none;
	color:gray;
}
</style>
<script type="text/javascript">
$(function(){
	$('section table tr>td:first-child').each(function(){
		var href=$(this).find('a').attr('href');
		$(this).html(function() {
			  return "<a href='"+href+"'>"+$( this ).text()+"</a>";
		});
		$(this).siblings().html(function() {
			  return "<a href='"+href+"'>"+$( this ).text()+"</a>";
		});
	});
});
</script>
</head>
<body>
<%@ include file="../../template/lms-header.jspf" %>
<%@ include file="../../template/lms-menu.jspf" %>

	<!-- 각 페이지 내용 입력 Start -->
<h2 class="pg-tit"><%=request.getAttribute("stuTit") %></h2>
	
	
<div>
	<section>
		<div>
<%
ArrayList<StuDto> sList = null;
sList = (ArrayList<StuDto>)request.getAttribute("stuList");
%>	
			<table>
				<thead>
					<tr>
						<th>이름</th>
						<th>과정명</th>
					</tr>
				</thead>
				<tbody>
<%for(StuDto bean : sList){ %>
					<tr>
						<td><%=bean.getStuName() %></td>
						<td><%=bean.getStuLect().getLectName() %></td>
					</tr>
<%} %>
				</tbody>
			</table>

		</div>
		
	</section>	
</div>
	<!-- 각 페이지 내용 입력 End -->
<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>