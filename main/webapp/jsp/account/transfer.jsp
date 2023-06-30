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

	<script>
		alert("${msg}");
	</script>

	<div id="account-form">
		송금완료<br>
		내 계좌 : "${bankName}" ${acc}<br>
		송금계좌 : "${bankName_receive}" ${tra}<br>
		이체금액 : ${m}<br>
	</div>

</body>
</html>