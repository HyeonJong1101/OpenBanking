<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.ac.kopo.biz.board.BoardDAO" %>
<%@ page import="kr.ac.kopo.biz.board.BoardVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>


#board {
  border-collapse: collapse;
  width: 100%;
  margin-top: 20px;
}

 #board {
  border: 1px solid #ccc;
}

#board th, #board td {
  padding: 10px;
  text-align: left;
}

#board tr:nth-child(even) {
  background-color: #f9f9f9;
}

#board tr:hover {
  background-color: #e6e6e6;
}

button {
  background: none;
  border: none;
  color: blue;
  cursor: pointer;
}

#insert {
  margin-top: 20px;
  text-align: right;
}

#insert a {
  color: black;
  text-decoration: none;
  padding: 10px 20px;
  background-color: #ddd;
  border-radius: 4px;
}

#insert a:hover {
  background-color: #ccc;
}
</style>

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
	
	<%-- 페이징 테이블 --%>
		<table width="70%">
		<tr>
			<td>
			<c:forEach var="i" begin="1" end="${lastPage}">
				<c:if test="${i eq pageNo}">
					<strong>[${i}]</strong>
				</c:if>
				<c:if test="${i ne pageNo}">
					<a href="board.do?pageNo=${i}">[${i}]</a>
				</c:if>
			</c:forEach>	
			</td>
		</tr>	
		</table>
</body>
</html>