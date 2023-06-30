<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>

	function terminateAccount() {
		// Show confirmation modal
		$('#confirmationModal').modal('show');
	}
	
	function terminateAccount(){
		var button = $("#button");
		var accountRow = button.closest("tr");
		var account_no = $("#accountNum").text();
		
		var data={
				"account_no" : account_no
		};
		
		$.ajax({
			url : "jsp/terminate/terminatebtn.jsp",
			method : "POST",
			data : data,
			success : function(){
				accountRow.remove(); // 행을 삭제합니다.
				console.log('success updating status:');
			},
			error : function(){
				console.log('Error updating status:', error);
			}
		});
		$('#confirmationModal').modal('hide');
	}
</script>

</head>
<body>
	
	<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/side.jsp"%>
	</header>
	<div id="account-form">
	
	해지페이지
	<table border="1" id="board">
		<tr>
			<td>id</td>
			<td>type</td>
			<td>계좌번호</td>
			<td>money</td>
			<td>해지버튼</td>
		</tr>
		<c:forEach var="account" items="${accountList }">
			<tr>
				<td>${account.id }</td>
				<td>${account.type }</td>
				<td id="accountNum">${account.accountNum }</td>
				<td>${account.money}</td>
				<td id="button">
				<input type="button" value="해지하기" onclick="terminateAccount()">
				</td>
			</tr>
		</c:forEach>

	</table>
	</div>
	
	
</body>
</html>