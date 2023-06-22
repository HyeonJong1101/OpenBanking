<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="jsp/style.css">

</head>
<body>

		<div id="side-form">
			<a href="${pageContext.request.contextPath }/accountlist.do">내계좌 보기</a><br /><br /> 
			<a href="${pageContext.request.contextPath }/moneyhistorybtn.do" >이체 내역</a><br /><br>
			<a href="${pageContext.request.contextPath }/myaccount.do">계좌 개설</a><br /><br />
			 <a href="${pageContext.request.contextPath }/accounttransferpage.do">계좌 이체</a><br /><br />
			 계좌 해지<br /><br />
			
		</div>

	
</body>
</html>