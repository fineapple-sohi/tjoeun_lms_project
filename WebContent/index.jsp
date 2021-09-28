<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>더조은컴퓨터아카데미</title>
<%@ include file="template/head.jspf" %>

<link rel="stylesheet" href="css/jquery.bxslider.min.css">
<style>
	/*  .main-slide  */
	.main-slider {
		position: relative;
	}
	.bxslider .slide-bg {
		width: 100%;
		height: 500px;
		background: no-repeat 50% 50%/cover;
	}
	.bxslider .slide-bg:nth-child(1) {background-image: url(imgs/slide01.jpg);}
	.bxslider .slide-bg:nth-child(2) {background-image: url(imgs/slide02.jpg);}
	.bxslider .slide-bg:nth-child(3) {background-image: url(imgs/slide03.jpg);}
	
	.bx-controls-direction {
		position: absolute;
		top: 50%;
		left:50%;
		transform: translate(-50%,-50%);
		width: 94%;
		display: flex;
		justify-content: space-between;	
		z-index: 50;
	}
	.bx-prev, .bx-next {
		color: #fff;
		font-size: 50px;
		opacity: .5;
	}
	.bx-pager {
		position: absolute;
		bottom: 40px;
		right: 40px;
		z-index: 50;
	}
	
</style>

<script src="js/jquery.bxslider.min.js"></script>
<script>
$(function(){
	$('.bxslider').bxSlider({
		mode: 'fade',
		pager: true,
		auto: 'ture',
		pause: 3000,
		controls: 'ture',
		nextText: '>',
		prevText: '<'
  });

});
</script>

</head>

<body>
<%@ include file="template/header.jspf" %>	


<!-- main slide -->
<div class="main-slider">
	<div class="bxslider">
		<div class="slide-bg"></div>
		<div class="slide-bg"></div>
		<div class="slide-bg"></div>
	</div>
</div>
<!-- //main slide -->




<%@ include file="template/footer.jspf" %>	
</body>
</html>