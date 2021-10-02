<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.tjoeun.model.BbsDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMS시스템</title>
<%@ include file="../../template/head.jspf" %>
<script src="https://cdn.ckeditor.com/ckeditor5/29.2.0/classic/ckeditor.js"></script>
<link rel="stylesheet" href="<%=request.getAttribute("path") %>css/bbs.css">
<script>
	$(function(){
		
		// 첨부파일 이름 보여주기
		var file = $('.file-hidden');	
		file.on('change', function(){
			//console.log($(this));
			var filename = $(this)[0].files[0].name;
			$('.file-name').val(filename);
		});
		
		
		// CKEditor 삽입
		ClassicEditor
		   .create( document.querySelector( '#editor' ), {
			   language: 'ko'
		   } )
		   .then( newEditor => {
			 editor = newEditor;  
		   } )
		   .catch( error => {
			 console.log( error );  
		   } );
		
		// CKEditor 입력값 저장
		$('.send').on('click', function(e){
			console.log(editor.getData());
		});
		
		
		// 취소 확인창
		$('.cls-btn').on('click', function(){
			$('.confirm-pop').fadeIn(300).addClass('on');
			
			$('.back-btn').on('click', function(){
				$('.confirm-pop').fadeOut(300).removeClass('on');
			});
			
			$('.ok-btn').on('click', function(){
				history.back();
			});
			
		});
		
		
		
	});
</script>
</head>
<body>
<%@ include file="../../template/lms-header.jspf" %>
	<%@ include file="../../template/lms-menu.jspf" %>
	
	
	<!-- 각 페이지 내용 입력 Start -->
	<h2 class="pg-tit"><%=request.getAttribute("bbsTit") %> 수정</h2>
	
	<%
		BbsDto bean = (BbsDto)request.getAttribute("bean");
	%>
	
	
	<div class="board-wrap">
		<form method="post">
			<div>
				<label class="cate" for="subject">제목</label>
				<input type="text" name="subject" id="subject" value="<%=bean.getBbsSub() %>">
			</div>
			<div>
				<textarea name="content" id="editor"><%=bean.getBbsCont() %></textarea>
			</div>
			<div>
				<span class="cate">첨부파일</span>
				<input class="file-name" value="파일선택" disabled>
				<label for="upload">업로드</label>
				<input class="file-hidden" type="file" name="upload"  id="upload">
			</div>
			<div class="btn-wrap">
				<button type="button" class="btn-box bd-blue cls-btn">취소</button>
				<button type="submit" class="btn-box blue send">수정</button>
			</div>
		</form>
	</div>
	
	<div class="confirm-pop">
		<div class="inner-box">
			<p>글쓰기를 취소하면 입력해놓은 내용은 모두 삭제됩니다.</p>
			<strong>취소하시겠습니까?</strong>
			<div class="btn-wrap">
				<button type="button" class="back-btn btn-box bd-blue">돌아가기</button>
				<button type="button" class="ok-btn btn-box blue">확인</button>
			</div>
		</div>
	</div>
	
	<!-- 각 페이지 내용 입력 End -->
	<%@ include file="../../template/lms-footer.jspf" %>
</body>
</html>