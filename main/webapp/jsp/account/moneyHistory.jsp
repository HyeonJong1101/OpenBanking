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
		입출금 내역<br>

		<form action="${pageContext.request.contextPath }/moneyhistory.do" method="post">
			내 계좌선택 : <select name="accountNum">
			<c:forEach var="account" items="${accountList }">
				<option>${account.accountNum }</option>
			</c:forEach>
			
			<c:forEach var="account" items="${accountList_BGH }">
			    <option>${account.accountNum }</option>
			</c:forEach>
			</select><br><br>
			<input type="submit" value="조회하기">
		</form>

	</div>

</body>
</html>