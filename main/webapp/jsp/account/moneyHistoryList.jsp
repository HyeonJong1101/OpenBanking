<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/side.jsp"%>
	</header>
	
	<div id="account-form">
	
리스트
<table border="1" id="board">
		<tr>
			<td>id</td>
			<td>계좌번호</td>
			<td>계좌번호2</td>
			<td>type</td>
			<td>금액</td>
			<td>날짜</td>
		</tr>
		<c:forEach var="book" items="${historyList }">
			<tr>
				<td>${book.transactionID }</td>
				<td>${book.accountNum }</td>
				<td>${book.accountNum2 }</td>
				<td>${book.transactiontype }</td>
				<td>${book.amount}</td>
				<td>${book.regdate}</td>
			</tr>
		</c:forEach>

	</table>
	</div>
</body>
</html>