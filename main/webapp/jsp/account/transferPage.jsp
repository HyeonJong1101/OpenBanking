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
		계좌이체<br>

		<form action="${pageContext.request.contextPath }/sendmoney.do" method="post">
			이체은행 선택 : <select name="bankCode_receive">
			<c:forEach var="bank" items="${bankList }">
				<option value="${bank.bankCode }">${bank.bankName }</option>
				</c:forEach>
			</select><br><br>
			내 계좌선택 : <select name="accountNum">
			<c:forEach var="account" items="${accountList }">
				<option>${account.accountNum }</option>
				</c:forEach>
			</select><br><br>
			이체 계좌번호 :<input type="text" name="transferaccountNum" required ><br>
			금액 :<input type="number" name="money" required min="1"><br>
			 
			<input type="submit" value="송금하기">
		</form>

	</div>


</body>
</html>