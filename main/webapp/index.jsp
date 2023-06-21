<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#slide{
	
		position : relative;
		background-color : rgb(197,233,254);
	}
	
	#mid-menu{
		position : absolute;
		top : 440px;
		left : 50%;
		transform : translateX(-145%);
		
		width : 300px;
		height : 100px;
		
		background-color : #FFCC00;
		
		text-align: center;
		line-height: 100px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	}
	
	#mid-menu2{
		position : absolute;
		top : 440px;
		left : 50%;
		transform : translateX(-25.5%);
		
		width : 530px;
		height : 100px;
		
		background-color : white;
		
		text-align: center;
		line-height: 100px;
		
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
	}
</style>

</head>
<body>

<header>
	<%@ include file="/jsp/include/top.jsp" %>
</header>

<div id="slide">
	<img src="images/slide1.png">
</div>

<div id="mid-menu">
	전체계좌조회&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
	계좌이체&nbsp&nbsp&nbsp
	
</div>

<div id="mid-menu2">
	보안센터&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
	고객우대제도&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
	상담/예약&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp
	소비자보호
</div>

</body>
</html>