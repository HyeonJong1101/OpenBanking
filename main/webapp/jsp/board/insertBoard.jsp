<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/top_board.jsp"%>
	</header>

	<form action="${pageContext.request.contextPath }/insertboard.do" method="post">
		<input type="text" name="title" placeholder="제목:"><br><br>
		<input type="text" name="content" placeholder="내용을 입력해주세요"><br><br>
		
		<input type="submit" value="등록하기">
	</form>
	
	
</body>
</html>