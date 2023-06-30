package kr.ac.kopo.biz.accountlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;

public class AccountListDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String ACCOUNT_SEARCH="select * from b_accountlist where account_no=? ";
	private static String ACCOUNT_SEARCH_BGH="select * from b_accountlist@BGH where account_no=? ";
	private static String ACCOUNT_SEARCH_JH="select * from bank_account@JH where account_no=? ";
	private static String ACCOUNT_SEARCH_HARI="select * from b_user_account@SB where account_no=? ";
	private static String ACCOUNT_LIST="select * from b_accountlist ";
	private static String LIST_INSERT="insert into b_accountlist(accountlistid,account_no,status) "+
									  " values((select nvl(max(accountlistid),0)+1 from b_accountlist),?,?) ";

	public AccountListVO searchAccount(AccountListVO vo) {
		
		AccountListVO account =null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH);
			stmt.setString(1, vo.getAccountNum());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountListVO vo2 = new AccountListVO();
				vo2.setAccountNum(rs.getString("account_no"));			
				
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
			stmt.setString(1, vo.getAccountNum());
			stmt.setString(2, vo.getStatus());
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	public List<AccountListVO> searchList() {
		
		List<AccountListVO> accountList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_LIST);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountListVO vo = new AccountListVO();
				vo.setAccountNum(rs.getString("account_no"));			
				
				accountList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return accountList;
	}

	public String searchAccount_BGH(String transferaccountNum) {
		String account=null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH_BGH);
			stmt.setString(1, transferaccountNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				account = rs.getString("account_no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return account;
		
	}

	public String searchAccount_JH(String transferaccountNum) {
		String account=null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH_JH);
			stmt.setString(1, transferaccountNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				account = rs.getString("account_no");
				System.out.println(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return account;
	}

	public String searchAccount_Hari(String transferaccountNum) {
		String account=null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH_HARI);
			stmt.setString(1, transferaccountNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				account = rs.getString("account_no");
				System.out.println(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return account;
	}

}
