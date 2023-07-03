<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* CSS styles for the table in the body */
    #main-table {
        /* Add your desired styles here */
        /* Example styles */
        border-collapse: collapse;
        width: 100%;
    }
    
    #main-table td, #main-table th {
        border: 1px solid #dddddd;
        padding: 8px;
        text-align: left;
    }
    
    #main-table th {
        background-color: #dddddd;
    }
</style>
</head>
<body>
<header>
    <%@ include file="/jsp/include/top.jsp"%>
    <%@ include file="/jsp/include/side.jsp"%>
</header>

<div id="account-form">
    현재 총 금액: <fmt:formatNumber value="${totalmoney}" pattern="###,###" />원

    <table border="1" id="main-table">
        <tr>
            <th>내 은행</th>
            <th>계좌번호</th>
            <th>거래 은행</th>
            <th>거래 은행 계좌번호 </th>
            <th>입금/출금</th>
            <th>금액</th>
            <th>날짜</th>
        </tr>
        <c:forEach var="book" items="${historyList}">
            <tr>
                <td>${book.bankCode}</td>
                <td>${book.accountNum}</td>
                <td>${book.bankCode_receive}</td>
                <td>${book.accountNum2}</td>
                <td>${book.transactiontype}</td>
                <td>${book.amount}</td>
                <td>${book.regdate}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
