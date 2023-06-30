package kr.ac.kopo.biz.productManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.biz.common.JDBCUtil;

public class ProductManagementDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private static String PRODUCTMANAGEMENT_INSERT = "insert into b_productmanagement(account_no,productid) " +
			" values(?, ?) ";
	
	public void insertProductManagement(ProductManagementVO vo) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(PRODUCTMANAGEMENT_INSERT);
			stmt.setString(1, vo.getAccountNum());
			stmt.setString(2, vo.getProductID());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
