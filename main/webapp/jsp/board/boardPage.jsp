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

	<table border="1" id="board">
		<tr>
			<td>no</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>작성날짜</td>
		</tr>
		<c:forEach var="board" items="${boardList }">
			<tr>
				<td>${board.boardID }</td>
				<td>
				
				<form action="board_contentbtn.do" method="POST">
    			<input type="hidden" name="title" value="${board.title}">
    			<input type="hidden" name="content" value="${board.content}">
    			<input type="hidden" name="id" value="${board.writer}">
    			<button type="submit" style="background: none; border: none; color: blue; cursor: pointer;">${board.title}</button>
  				</form>
				
				</td>
				<td>${board.writer }</td>
				<td>${board.regDate }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div id="insert">
		
		<c:choose>
			<c:when test="${empty sessionScope.user.id}">
				<a href="#" style="color:black;" onclick="showAlert()">글쓰기</a>
			</c:when>
			<c:otherwise>
				<a href="insertboardbtn.do" style="color:black;">글쓰기</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	
</body>
</html>