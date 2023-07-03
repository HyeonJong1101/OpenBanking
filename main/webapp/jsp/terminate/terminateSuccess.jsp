<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

#account-form {
  display: none;
}

form {
  width: 400px;
  padding: 20px;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

form input[type="text"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
  font-size: 14px;
  margin-bottom: 10px;
}

form input[type="submit"] {
  padding: 8px 16px;
  border: none;
  border-radius: 3px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

form input[type="submit"]:hover {
  background-color: #0056b3;
}
#success{
	position : absolute;
	top : 200px;
	left : 200px;
}
</style>



</head>
<body>

<header>
  <%@ include file="/jsp/include/top.jsp"%>
  <%@ include file="/jsp/include/side.jsp"%>
</header>


<div id="success">
 <h2>금액 이체중...</h2>
 <div class="countdown">5</div>
</div>
 
 <div id="account-form">
 
  <form id="myForm" action="${pageContext.request.contextPath}/sendmoneybtn.do" method="post">
    <input type="text" name="accountNum" value="${accountNum}">
    <input type="hidden" name="transferaccountNum" value="${transferaccountNum}">
    <input type="hidden" name="money" value="${money}">
    <input type="hidden" name="bankCode" value="${bankCode}">
    <input type="hidden" name="bankName" value="${bankName}">
    <input type="hidden" name="pass" value="${pass}">
    <input type="hidden" name="terminate" value="terminate">
    <input type="submit" value="송금하기">
  </form>
</div>

<script>
// Countdown timer
var countdownElement = document.querySelector('.countdown');
var count = 5;

function countdown() {
  count--;
  countdownElement.innerText = count;
  
  if (count === 0) {
    document.getElementById('myForm').submit();
  } else {
    setTimeout(countdown, 1000);
  }
}

countdown();
</script>

</body>
</html>
