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
			<td>은행명</td>
			<td>type</td>
			<td>계좌번호</td>
			<td>money</td>
		</tr>
		<c:forEach var="book" items="${accountList }">
			<tr>
				<td>${book.bankCode }</td>
				<td>${book.type }</td>
				<td>${book.accountNum }</td>
				<td>${book.money}</td>
			</tr>
		</c:forEach>

	</table>
	
다른은행계좌 리스트
<table border="1" id="board">
		<tr>
			<td>은행명</td>
			<td>type</td>
			<td>계좌번호</td>
			<td>money</td>
		</tr>
		<c:forEach var="book" items="${accountList_BGH }">
			<tr>
				<td>${book.bankCode }</td>
				<td>${book.type }</td>
				<td>${book.accountNum }</td>
				<td>${book.money}</td>
			</tr>
		</c:forEach>

	</table>
	</div>
</body>
</html>