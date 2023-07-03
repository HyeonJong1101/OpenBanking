<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#account-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

h2 {
  margin-bottom: 20px;
}

.account-info {
  margin-bottom: 10px;
}

.amount {
  font-weight: bold;
  font-size: 18px;
}
#terminate{
	color : red;
}
</style>
</head>
<body>

<header>
  <%@ include file="/jsp/include/top.jsp"%>
  <%@ include file="/jsp/include/side.jsp"%>
</header>

<script>
  alert("${msg}");
</script>

<div class="container">
  <div id="account-form">
    <h2>송금완료</h2>
    <div class="account-info">
      내 계좌: "${bankName}" ${acc}
    </div>
    <div class="account-info">
      송금계좌: "${bankName_receive}" ${tra}
    </div>
    <div class="account-info">
      이체금액: <span class="amount"><fmt:formatNumber value="${m}" pattern="###,###" />원</span>
    </div>
    <c:if test="${not empty terminate}">
      <div class="account-info" id="terminate">
        계좌 해지완료
      </div>
    </c:if>
  </div>
</div>

 

</body>
</html>
