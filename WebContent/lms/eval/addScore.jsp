<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<style type="text/css">
body>div{
	width: 800px;
	background-color: white;
	margin: 0px auto;
}
nav,footer{
	background-color: gray;
	text-align: center;
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$('button[type=button]').click(function(){
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
		<h2>성적입력</h2>
		<form method="post">
			<div>
				<label>1차시험</label>
				<input type="text" name="Eval_OrderNum"/>
			</div>
			<div>
				<label>2차시험</label>
				<input type="text" name="Eval_OrderNum"/>
			</div>
			<div>
				<label>3차시험</label>
				<input type="text" name="Eval_OrderNum"/>
			</div>
			<div>
				<label>4차시험</label>
				<input type="text" name="Eval_OrderNum"/>
			</div>
			<div>
				<label></label>
				<input type="text" name="Eval_Score"/>
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