package kr.ac.kopo.biz.accountlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.biz.common.JDBCUtil;

public class AccountListDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String ACCOUNT_SEARCH="select * from b_accountlist where accountnumber=? ";

	public AccountListVO searchAccount(AccountListVO vo) {
		
		AccountListVO account =null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH);
			stmt.setInt(1, vo.getAccountNum());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountListVO vo2 = new AccountListVO();
				vo2.setAccountNum(rs.getInt("accountnumber"));			
				
				account = vo2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return account;
	}

}
