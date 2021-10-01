<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오시는길</title>
<%@ include file="../template/head.jspf" %>
<style>
	.map-wrap {
		padding: 80px 120px;
		background-color: #f5f5f5;
	}
	.map-wrap h2 {
		margin-bottom: 40px;
		border-bottom: 1px solid #333;
		padding: 0 10px 10px;
	}
</style>
</head>
<body>
<%@ include file="../template/header.jspf" %>	

<div class="map-wrap">
	<div class="container1440">
		<h2>오시는길</h2>
		<div id="map" style="width: 100%; height: 400px;"></div>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e5f5bb9115d812a34ed32b190bd82edf"></script>
		<script>
			var mapContainer = document.getElementById('map'),
			    mapOption = { 
			        center: new kakao.maps.LatLng(37.5690815, 126.9846732),
			        level: 3
			    };	
			var map = new kakao.maps.Map(mapContainer, mapOption);
			var markerPosition  = new kakao.maps.LatLng(37.5690815, 126.9846732); 		
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			marker.setMap(map);
			
			var iwContent = '<div style="padding: 10px; font-weight: bold;">더조은컴퓨터아카데미</div>',
			    iwPosition = new kakao.maps.LatLng(37.5690815, 126.9846732);
			var infowindow = new kakao.maps.InfoWindow({
			    position : iwPosition, 
			    content : iwContent 
			});
			infowindow.open(map, marker); 
		</script>
	</div>
</div>

<%@ include file="../template/footer.jspf" %>
</body>
</html>