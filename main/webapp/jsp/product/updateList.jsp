<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<%
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = JDBCUtil.getConnection();

		String sql = "update b_product set productstatus=? where productid=? ";
		stmt = conn.prepareStatement(sql);

		String productId = request.getParameter("productId");
		int status = Integer.parseInt(request.getParameter("status"));
		if(status == 1){
			status = 0;
		}else if(status == 0){
			status = 1;
		}else{
			System.out.println("오류");
		}
		//String button = request.getParameter("button");
		
		stmt.setInt(1, status);
		stmt.setString(2, productId);
		stmt.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(stmt, conn);
	}
	%>

</body>
</html>