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
	private static String LIST_INSERT="insert into b_accountlist(accountlistid,accountnumber,status) "+
									  " values((select nvl(max(accountlistid),0)+1 from b_accountlist),?,?) ";

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

	public void insertList(AccountListVO vo) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIST_INSERT);
			stmt.setInt(1, vo.getAccountNum());
			stmt.setString(2, vo.getStatus());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

}
