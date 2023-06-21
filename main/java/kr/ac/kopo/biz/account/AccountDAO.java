package kr.ac.kopo.biz.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;
import kr.ac.kopo.biz.user.UserVO;

public class AccountDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String ACCOUNT_INSERT = "insert into b_account(accountnumber,memberid,accounttype,money,password,bankcode) " +
			" values(?, ?, ?, ?,?,?) ";
	private static String ACCOUNT_LIST="select * from b_account where memberid=?";
	
	public void insertAccount(AccountVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_INSERT);
			stmt.setInt(1, vo.getAccountNum());
			stmt.setString(2, vo.getId());
			stmt.setString(3, vo.getType());
			stmt.setInt(4, vo.getMoney());
			stmt.setInt(5, vo.getPassword());
			stmt.setString(6, vo.getBankCode());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public List<AccountVO> accountList(UserVO vo) {
		
		List<AccountVO> accountList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_LIST);
			stmt.setString(1, vo.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO account = new AccountVO();
				account.setAccountNum(rs.getInt("accountnumber"));
				account.setId(rs.getString("memberid"));
				account.setType(rs.getString("accounttype"));
				account.setMoney(rs.getInt("money"));
				account.setPassword(rs.getInt("password"));
				
				//System.out.println(account.toString());
				accountList.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return accountList;
	}

}
