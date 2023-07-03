<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* Table styles */
    #account-form table {
        border-collapse: collapse;
        width: 100%;
    }
    
    #account-form th, #account-form td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    
    #account-form th {
        background-color: #f2f2f2;
    }
    
    /* Account form styles */
    #account-form {
        margin-top: 20px;
    }
</style>
</head>
<body>
    <header>
        <%@ include file="/jsp/include/top.jsp"%>
        <%@ include file="/jsp/include/side.jsp"%>
    </header>

    <div id="account-form">
    
        <h2>내 은행 계좌 리스트</h2>
        <table>
            <tr>
                <th>은행명</th>
                <th>상품명</th>
                <th>계좌번호</th>
                <th>money</th>
            </tr>
            <c:forEach var="book" items="${accountList}">
                <tr>
                    <td>${book.bankCode}</td>
                    <td>${book.type}</td>
                    <td>${book.accountNum}</td>
                    <td>${book.money}</td>
                </tr>
            </c:forEach>
        </table>
        
        <h2>다른 은행 계좌 리스트</h2>
        <table>
            <tr>
                <th>은행명</th>
                <th>상뭎명</th>
                <th>계좌번호</th>
                <th>money</th>
            </tr>
            <c:forEach var="book" items="${accountList_BGH}">
                <tr>
                    <td>${book.bankCode}</td>
                    <td>${book.type}</td>
                    <td>${book.accountNum}</td>
                    <td>${book.money}</td>
                </tr>
            </c:forEach>
            <c:forEach var="book" items="${accountList_JH}">
                <tr>
                    <td>${book.bankCode}</td>
                    <td>${book.type}</td>
                    <td>${book.accountNum}</td>
                    <td>${book.money}</td>
                </tr>
            </c:forEach>
            <c:forEach var="book" items="${accountList_Hari}">
                <tr>
                    <td>${book.bankCode}</td>
                    <td>${book.type}</td>
                    <td>${book.accountNum}</td>
                    <td>${book.money}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
