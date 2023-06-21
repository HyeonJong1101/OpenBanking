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
	private static String ACCOUNT_SEARCH="select * from b_account where accountnumber=? and money> ? ";
	private static String ACCOUNT_SEARCH2="select * from b_account where accountnumber=? ";
	private static String ACCOUNT_MONEY_UPDATE="update b_account set money=case "+
											   " when accountnumber=? then money - ? "+
											   " when accountnumber=? then money + ? "+
											   " else money end "+
											   " where accountnumber in (?,?) ";
	
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

	public AccountVO searchAccount(AccountVO vo1) {
		
		AccountVO account=null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH);
			stmt.setInt(1, vo1.getAccountNum());
			stmt.setInt(2, vo1.getMoney());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccountNum(rs.getInt("accountnumber"));
				vo.setId(rs.getString("memberid"));
				vo.setType(rs.getString("accounttype"));
				vo.setMoney(rs.getInt("money"));
				vo.setPassword(rs.getInt("password"));
				vo.setBankCode(rs.getString("bankcode"));
			
				account = vo;
				
				System.out.println(account.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return account;
	}
	
public AccountVO searchAccount_pass(AccountVO vo1) {
		
		AccountVO account=null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH2);
			stmt.setInt(1, vo1.getAccountNum());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccountNum(rs.getInt("accountnumber"));
				vo.setId(rs.getString("memberid"));
				vo.setType(rs.getString("accounttype"));
				vo.setMoney(rs.getInt("money"));
				vo.setPassword(rs.getInt("password"));
				vo.setBankCode(rs.getString("bankcode"));
			
				account = vo;
				
				System.out.println(account.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return account;
	}

	public void updateMoney(int accountNum, int accountNum2, int money) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_MONEY_UPDATE);
			stmt.setInt(1, accountNum);
			stmt.setInt(2, money);
			stmt.setInt(3, accountNum2);
			stmt.setInt(4, money);
			stmt.setInt(5, accountNum);
			stmt.setInt(6, accountNum2);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	

}
