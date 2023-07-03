<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Terminate Account</title>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

.container {
  position: fixed;
  top: 450px;
  left: 400px;
  transform: translate(-50%, -50%);
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.form-container {
  width: 400px;
  padding: 20px;
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.form-container h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

.form-container label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
}

.form-container select,
.form-container input[type="text"],
.form-container input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
  box-sizing: border-box;
  font-size: 14px;
  margin-bottom: 10px;
}

.form-container input[type="button"],
.form-container input[type="submit"] {
  padding: 8px 16px;
  border: none;
  border-radius: 3px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

.form-container input[type="button"]:hover,
.form-container input[type="submit"]:hover {
  background-color: #0056b3;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal .modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.modal p {
  margin-bottom: 10px;
}

.modal button {
  margin-right: 10px;
  padding: 8px 16px;
  border: none;
  border-radius: 3px;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

.modal button:hover {
  background-color: #0056b3;
}

.box {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  height: 100px;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
$(document).ready(function() {
  $('#checkBalanceButton').click(function() {
    var selectedAccount = $('select[name="selectedAccount"]').val();

    if (selectedAccount) {
      var accountNum = selectedAccount;

      $.ajax({
        url: "jsp/terminate/balance.jsp",
        method: "POST",
        data: { accountNum: accountNum },
        success: function(data) {
          $('#balanceField').val(data);
        },
        error: function() {
          console.log('Error retrieving balance');
        }
      });
    }
  });
});

function terminateAccount() {
  var selectedAccount = $('select[name="selectedAccount"]').val();
  var selectedAccount2 = $('select[name="selectedAccount2"]').val();
  var accountNum = selectedAccount;
  var accountNum2 = selectedAccount2;
  var pass = $('#password').val();

  console.log(accountNum);
  console.log(pass);

  $.ajax({
    url: "jsp/terminate/terminatebtn.jsp",
    method: "POST",
    data: { accountNum: accountNum, accountNum2: accountNum2, pass: pass },
    success: function(response) {
      console.log('Success: Account terminated');
      var test = response.trim()
      console.log(test);

      if (test === "1") {
        showConfirmationModal2();
      } else if (test === "2") {
        showConfirmationModal3();
      } else if (test === "3") {
        showConfirmationModal4();
      } else if (test === "0") {
        $('form').submit();
      }
    },
    error: function(error) {
      console.log('Error terminating account:', error);
    }
  });

  closeConfirmationModal();
}

function showConfirmationModal() {
  $('#confirmationModal').show();
}

function showConfirmationModal2() {
  $('#confirmationModal2').show();
}

function showConfirmationModal3() {
  $('#confirmationModal3').show();
}

function showConfirmationModal4() {
  $('#confirmationModal4').show();
}

function closeConfirmationModal() {
  $('#confirmationModal').hide();
  $('#confirmationModal2').hide();
  $('#confirmationModal3').hide();
  $('#confirmationModal4').hide();
}
</script>

</head>
<body>

<header>
    <%@ include file="/jsp/include/top.jsp"%>
    <%@ include file="/jsp/include/side.jsp"%>
</header>

<div class="container">
  <div class="form-container">
    <h1>계좌해지 페이지</h1>
    <h2>계좌 정보</h2>

    <form method="post" action="${pageContext.request.contextPath}/terminateAccount.do">
      <label for="selectedAccount">해지계좌 선택</label>
      <select id="selectedAccount" name="selectedAccount">
        <c:forEach var="account" items="${accountList}">
          <option value="${account.accountNum}">${account.type}-${account.accountNum}</option>
        </c:forEach>
      </select>
      <br>
      <input type="text" placeholder="잔액" id="balanceField" name="balance" readonly>
      <input type="button" value="잔액조회" id="checkBalanceButton"><br><br>
      <label for="password">계좌비밀번호</label>
      <input type="password" placeholder="****" id="password" name="pass"><br><br>
      <label>입금정보</label>
      <label for="selectedAccount2">입금계좌번호</label>
      <select id="selectedAccount2" name="selectedAccount2">
        <option value="" selected disabled>계좌번호를 선택해 주세요.</option>
        <c:forEach var="account" items="${accountList}">
          <option value="${account.accountNum}+${account.bankCode}">${account.type}-${account.accountNum}</option>
        </c:forEach>
        <c:forEach var="account" items="${accountList_BGH}">
          <option value="${account.accountNum}+${account.bankCode}">${account.type}-${account.accountNum}</option>
        </c:forEach>
        <c:forEach var="account" items="${accountList_JH}">
          <option value="${account.accountNum}+${account.bankCode}">${account.type}-${account.accountNum}</option>
        </c:forEach>
        <c:forEach var="account" items="${accountList_Hari}">
          <option value="${account.accountNum}+${account.bankCode}">${account.type}-${account.accountNum}</option>
        </c:forEach>
      </select>
      <br>
      <input type="button" value="해지하기" onclick="showConfirmationModal()">
    </form>
  </div>
</div>

<div id="confirmationModal" class="modal" style="display: none;">
  <div id="box" class="box">
    <p>정말 계좌를 해지하시겠습니까?</p>
    <button onclick="terminateAccount()">예</button>
    <button onclick="closeConfirmationModal()">Cancel</button>
  </div>
</div>

<div id="confirmationModal2" class="modal" style="display: none;">
  <div id="box" class="box">
    <p>비밀번호가 다릅니다</p>
    <button onclick="closeConfirmationModal()">OK</button>
  </div>
</div>

<div id="confirmationModal3" class="modal" style="display: none;">
  <div id="box" class="box">
    <p>해지/입금계좌가 같습니다</p>
    <button onclick="closeConfirmationModal()">OK</button>
  </div>
</div>

<div id="confirmationModal4" class="modal" style="display: none;">
  <div id="box" class="box">
    <p>계좌번호를 입력해주세요</p>
    <button onclick="closeConfirmationModal()">OK</button>
  </div>
</div>

</body>
</html>
