<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil"%>
<%@ page import="java.sql.*"%>
<%
  String accountId = request.getParameter("accountId");
  String accountNum = request.getParameter("accountNum");
  
  String balance = "5000"; // Replace this with your logic to retrieve the actual balance
  
  Connection conn = null;
	PreparedStatement stmt = null;
	try {
		String sql = "select * from b_account where account_no=? ";

		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(sql);

		stmt.setString(1, accountNum);
		ResultSet rs = stmt.executeQuery();
		//System.out.println(accountNum);

		while (rs.next()) {
			balance = rs.getString("money");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(stmt, conn);
	}
  
  response.setContentType("text/plain");
  response.setCharacterEncoding("UTF-8");
  response.getWriter().write(balance);
%>
