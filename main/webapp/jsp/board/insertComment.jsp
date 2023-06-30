<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil"%>
<%@ page import="kr.ac.kopo.biz.user.UserVO"%>

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

		String sql = "INSERT INTO b_comments (comments, userid, regdate, boardid) VALUES (?, ?, ?,?) ";
		stmt = conn.prepareStatement(sql);

		String comment = request.getParameter("comment");
		String boardid = request.getParameter("boardid");
		String timestamp = request.getParameter("timestamp");
		
		UserVO vo = (UserVO) session.getAttribute("user");
		String userId = vo.getId();
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		stmt.setString(1, comment);
		stmt.setString(2, userId);
		stmt.setString(3, timestamp);
		stmt.setString(4, boardid);

		stmt.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(stmt, conn);
	}
	%>

</body>
</html>