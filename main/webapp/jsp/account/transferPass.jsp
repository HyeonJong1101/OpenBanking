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
		계좌비밀번호 입력<br>

		<form action="${pageContext.request.contextPath }/sendmoneybtn.do" method="post">
			
			비밀번호 :<input type="password" name="pass" required><br>
			<input type="hidden" name="accountNum" value="${account1}">
			<input type="hidden" name="transferaccountNum" value="${account2}">
			<input type="hidden" name="money" value="${money}">
			<input type="hidden" name="bankCode" value="${bankCode}">
			<input type="hidden" name="bankName" value="${bankName}">
			 
			 <input type="submit" value="송금하기">
		</form>

	</div>
</body>
</html>