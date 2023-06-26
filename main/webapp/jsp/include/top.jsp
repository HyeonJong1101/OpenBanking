<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

#top{
	background-color : rgb(197,233,254);
	position : relative;
}

#top_menu{
	position : absolute;
	right : 0;
	top: 10px;
	font-weight : bold;
}

#top_menu a {
	text-decoration: none;
	color : black;
}

</style>

</head>
<body>
<div id="top">
	<img src="images/logo2.png">
	<table id="top_menu">
	<tr >
		<td><a href="${pageContext.request.contextPath }/main.do">Home  |</a>
		    <a href="${pageContext.request.contextPath }/board.do">게시판 |</a>
			<a href="${pageContext.request.contextPath }/productbtn.do">상품 |</a> 
			<c:if test="${empty user}">
			<a href="${pageContext.request.contextPath }/login.do">로그인 |</a> 			
			</c:if>
			
			<c:if test="${not empty user}">
			<a href="${pageContext.request.contextPath }/accountlist.do">MyAccount  |</a> 			
			</c:if>
			<c:if test="${not empty user}">
			<a href="${pageContext.request.contextPath }/logout.do">로그아웃</a> 			
			</c:if>
			<c:if test="${empty user}">
			<a href="${pageContext.request.contextPath }/signbutton.do">회원가입</a>
			</c:if>
	</tr>
</table>
	<script>
		function showAlert() {
			alert("로그인 후에 글을 작성할 수 있습니다.");
		}
	</script>
</div>
</body>
</html>

