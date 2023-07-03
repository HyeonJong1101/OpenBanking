<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="kr.ac.kopo.biz.common.JDBCUtil"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
	<%
	try {
        request.setCharacterEncoding("UTF-8");
     } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
     };
	
	String accountNum = request.getParameter("accountNum");
	String accountNum2 = request.getParameter("accountNum2").split("\\+")[0];
	String accountPass = request.getParameter("pass");
	int pass=0;
	String test="0";
	
	System.out.println(accountNum + " " + accountPass+" "+ accountNum2);

	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = JDBCUtil.getConnection();

		String sql = "select * from b_account where account_no=? ";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, accountNum);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			pass = rs.getInt("password");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(stmt, conn);
	}
	
	if(!accountPass.equals(String.valueOf(pass))){
		test = "1";
	}else if(accountNum.equals(accountNum2)){
		test = "2";
	}else if(accountNum2.isEmpty()){
		test = "3";
	}
	
	request.setAttribute("test", test);
	%>
	
	${test }
