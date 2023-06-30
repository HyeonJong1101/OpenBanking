<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*"%>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	function activateStatus(productId) {
		var button = $("#button-" + productId);
		var statusElement = $("#status-" + productId);

		var status = statusElement.html();

		var data = {
			"status" : status,
			"productId" : productId
		};

		$.ajax({
			url : "jsp/product/updateList.jsp",
			method : "POST",
			data : data,
			success : function() {

				if (status === '1') {
					button.val('비활성화');
					statusElement.html('0');
				} else if (status === '0') {
					button.val('활성화');
					statusElement.html('1');
				} else {
					console.log('Invalid response:', response);
				}

				console.log('Status updated successfully');
			},
			error : function(xhr, status, error) {
				console.log('Error updating status:', error);
			}
		});
	}
</script>

</head>
<body>

	<header>
		<%@ include file="/jsp/include/top.jsp"%>
		<%@ include file="/jsp/include/top_product.jsp"%>
	</header>

	<br>
	<br>
	<br>
	<br> 상품리스트
	<br>
	<table border="1" id="middle">
		<tr>
			<td>상품id</td>
			<td>상품이름</td>
			<td>info</td>
			<td>status</td>
		</tr>
		<c:forEach var="product" items="${productList }">
			<tr  <c:if test="${product.productStatus == 0}">style="display: none;"</c:if>>
				<td>${product.productID }</td>
				<td>${product.productName }</td>
				<td>${product.productInfo }</td>

				<td id="status-${product.productID}">${product.productStatus}</td>
			</tr>
		</c:forEach>

	</table>
	
</body>
</html>