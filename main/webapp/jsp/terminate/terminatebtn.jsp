<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<%
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = JDBCUtil.getConnection();

			String sql = "update b_accountlist set status='F' where account_no=? ";
			stmt = conn.prepareStatement(sql);

			String productId = request.getParameter("productId");
			String account_no = request.getParameter("account_no");
		
		stmt.setString(1, account_no);
		stmt.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(stmt, conn);
	}
	%>
</body>
</html>