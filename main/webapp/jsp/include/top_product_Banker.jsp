<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="jsp/style.css">

<style>
#product-menu a {
	margin-right: 100px;
}
</style>
</head>
<body>
	<br> 상품관리
	<br>
	<br>
	<hr>
	<br>
	<div id="product-menu">
		
		<a href="${pageContext.request.contextPath}/deposit_Banker.do">예금관리</a>
		<a href="#">펀드</a> <a href="#">대출</a> <a href="#">신탁</a> <a href="#">ISA</a>
		<a href="#">보험/공제</a> <a href="#">골드</a> <a href="#">외화예금</a>
	</div>

</body>
</html>