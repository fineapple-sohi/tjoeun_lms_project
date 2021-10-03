 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.tjoeun.model.BbsDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<link rel="stylesheet" href="<%=request.getAttribute("path") %>css/bbs.css">
<script>
	// 삭제 확인창
	$(function(){
		$('.del-btn').on('click', function(){
			$('.confirm-pop').fadeIn(300).addClass('on');
			
			$('.back-btn').on('click', function(){
				$('.confirm-pop').fadeOut(300).removeClass('on');
			});
		
		});
	});
</script>
</head>
<body>
	<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<h2 class="pg-tit"><%=request.getAttribute("bbsTit") %> 상세조회</h2>
	
		
	<%
		BbsDto bean = (BbsDto)request.getAttribute("bean");
	%>
	
	<div class="board-wrap">
		<div class="borad-content">
			<div>
				<span>제목</span>
				<p><%=bean.getBbsSub() %></p>
			</div>
			<div>
				<div class="column-half">
					<span>작성자</span>
					<p><%=bean.getBbsMemberId() %></p>
				</div>
				<div class="column-half">
					<span>작성일자</span>
					<p><%=bean.getBbsNalja() %></p>
				</div>
			</div>
			<div class="content-inner">
				<%=bean.getBbsCont() %>
			</div>
			<div>
				<span>첨부파일</span>
				<p><a class="uploaded-file" href="#">파일명.jpg</a></p>
			</div>
			<%if(request.getAttribute("bbsTable").equals("quest")) { %>
			<div class="ans-content">
				<div>
					<label for="ans">문의답변</label>
					<textarea name="ansContent" id="ans"><%=bean.getAnsCont() %></textarea>
				</div>
				<a class="btn-box blue ans-btn" onclick="sendAns();" href="#">답변 등록</a>
				<script>
					function sendAns() {
						$.post("bbsAns.lms", {
							bbsTable: '<%=request.getAttribute("bbsTable") %>',
							idx: <%=bean.getBbsIdx() %>,
							ansCont: $('#ans').val()
						}).done(function(){
							alert('답변이 등록되었습니다.');
						});
					}
				</script>
			</div>
			<%} %>
		</div>
		<div class="btn-wrap">
			<%
			if(request.getAttribute("bbsTable").equals("notice") || sessId.equals(bean.getBbsMemberId())) {
			%>
			<button type="button" class="del-btn btn-box bd-blue" href="#">삭제</button>
			<a class="mod-btn btn-box blue" href="bbsEdit.lms?bbs_table=<%=request.getAttribute("bbsTable") %>&idx=<%=bean.getBbsIdx() %>">수정</a>
			<%} %>
			<a class="btn-box blue" href="bbsList.lms?bbs_table=<%=request.getAttribute("bbsTable") %>">목록</a>
		</div>
	</div>
	
	
	<div class="confirm-pop">
		<div class="inner-box">
			<p>삭제 시 해당 게시글은 다시 복구할 수 없습니다.</p>
			<strong>정말 삭제하시겠습니까?</strong>
			<div class="btn-wrap">
				<button type="button" class="back-btn btn-box bd-blue">취소</button>
				<a href="#" onclick="okDel();" class="ok-btn btn-box blue">삭제</a>
				<script>
					function okDel() {
						$.post("bbsDelete.lms", {
							bbsTable: '<%=request.getAttribute("bbsTable") %>',
							idx: <%=bean.getBbsIdx() %>
						}).done(function(){
							location.replace("bbsList.lms?bbs_table=<%=request.getAttribute("bbsTable") %>");
						});
					}
				</script>
			</div>
		</div>
	</div>
	
	
	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>