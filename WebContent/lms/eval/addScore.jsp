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
table.type09{
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
}
table.type09 thead th {
	width: 350px;
    padding: 10px;
    font-weight: bold;
    font-size: 20px;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
table.type09 thead td{
	border-bottom: 3px solid #036;
}
table.type09 tbody th{
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #f3f6f7;
}
table.type09 td{
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
}

table.type10{
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
}
table.type10 tbody label{
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #f3f6f7;
}
table.type10 td{
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
}
table.type10 th{
	width: 150px;
	padding: 10px;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
	background-color: #f3f6f7;
}

.spacer{
	width: 10px;
	height: 30px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('input').focus(function(){
		$(this).css('background-color','rgba(255,255,0,0.2)');
	}).blur(function(){
		$(this).css('background-color','rgba(0,0,0,0)');
	});
	$('form button').last().click(function(){
		history.back();
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
			<table class="type09">
				<thead>
					<tr>
						<th scope="cols">성적 입력</th>
						<th scope="cols"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>학생명</th>
						<td><%=request.getAttribute("stuName") %></td>
					</tr>
					<tr>
						<th>과정명</th>
						<td><%=request.getAttribute("lectName") %></td>
					</tr>
				</tbody>
			</table>
		</div>
	</section>
	<div class="spacer"></div>
	<section>
<%ArrayList<EvalDto> list = (ArrayList<EvalDto>)request.getAttribute("list"); %>
	<div>
		<form method="post">
			<table class="type10">
				<tbody>
					<input type="hidden" name="stuId" value="<%=request.getAttribute("stuId") %>"/>
					<tr>
						<th>1차시험</th>
						<td><input type="number" name="evalScore1" value="${list.get(0).getEvalScore()}"/></td>
					</tr>
					<tr>
						<th>2차시험</th>
						<td><input type="number" name="evalScore2" value="${list.get(1).getEvalScore()}"/></td>
					</tr>
					<tr>
						<th>3차시험</th>
						<td><input type="number" name="evalScore3" value="${list.get(2).getEvalScore()}"/></td>
					</tr>
					<tr>
						<th>4차시험</th>
						<td><input type="number" name="evalScore4" value="${list.get(3).getEvalScore()}"/></td>
					</tr>
				</tbody>
			</table>
			<div class="spacer"></div>
			<table>
				<tr>
					<td>
						<button>입력</button>
						<button type="reset">취소</button>
						<button type="button">뒤로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</section>
</div>



<!-- 각 페이지 내용 입력 End -->
<%@ include file="../../template/lms-footer.jspf" %>
	

</body>
</html>