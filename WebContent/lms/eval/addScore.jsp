<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, kr.co.tjoeun.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<style type="text/css">

</style>
<script type="text/javascript">
$(document).ready(function(){
	$('input').focus(function(){
		$(this).css('background-color','rgba(255,255,0,0.2)');
	}).blur(function(){
		$(this).css('background-color','rgba(0,0,0,0)');
	});
});

</script>
</head>
<body>

<%@ include file="../../template/lms-header.jspf" %>
<%@ include file="../../template/lms-menu.jspf" %>

<!-- 각 페이지 내용 입력 Start -->
<div>
	<section>
		<div>
			<table>
				<thead>
					<tr>
						<th>학생명</th>
						<th>과정명</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=request.getAttribute("stuName") %></td>
						<td><%=request.getAttribute("lectName") %></td>
					</tr>
				</tbody>
			</table>
		</div>
<%ArrayList<EvalDto> list = (ArrayList<EvalDto>)request.getAttribute("list"); %>
		<form method="post">
			<input type="hidden" name="stuId" value="<%=request.getAttribute("stuId") %>"/>
			<div>
				<label>1차시험</label>
				<input type="number" name="evalScore1" value="${list.get(0).getEvalScore()}"/>
			</div>
			<div>
				<label>2차시험</label>
				<input type="number" name="evalScore2" value="${list.get(1).getEvalScore()}"/>
			</div>
			<div>
				<label>3차시험</label>
				<input type="number" name="evalScore3" value="${list.get(2).getEvalScore()}"/>
			</div>
			<div>
				<label>4차시험</label>
				<input type="number" name="evalScore4" value="${list.get(3).getEvalScore()}"/>
			</div>
			<div>
				<button>submit</button>
				<button type="reset">reset</button>
				<button type="button">back</button>
			</div>
		</form>
	</section>
</div>



<!-- 각 페이지 내용 입력 End -->
<%@ include file="../../template/lms-footer.jspf" %>
	

</body>
</html>