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
		<%@ include file="/jsp/include/side.jsp"%>
</header>

<div id="account-form">
계좌가 생성되었습니다 !<br>

계좌 번호 : ${account.accountNum }

</div>
</body>
</html>