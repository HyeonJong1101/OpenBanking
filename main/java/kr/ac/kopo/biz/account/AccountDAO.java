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
	
	private static String ACCOUNT_INSERT = "insert into b_account(account_no,memberid,accounttype,money,password,bankcode,productid) " +
			" values(?, ?, ?, ?,?,?,?) ";
	//private static String ACCOUNT_LIST="select * from b_account where memberid=?";
	private static String ACCOUNT_LIST="select * from b_account a "+
									   "join b_accountlist b on a.account_no=b.account_no "+
									   "where b.status='T' and a.memberid=?";
	private static String ACCOUNT_LIST_BGH="select c.* "+
									   	   "from b_account@BGH c "+
									   	   "JOIN b_accountlist@BGH d ON c.account_no = d.account_no "+
									   	   "JOIN b_user@BGH a ON c.user_id = a.user_id "+
									   	   "JOIN b_member b ON a.user_name = b.username "+
									   	   "    AND a.user_birth = b.birthday "+
									   	   "    AND a.user_phone = b.phonenumber "+
									   	   "WHERE d.accountlist_possible = 'O' "+
									   	   "and b.memberid = ? ";
	
	private static String ACCOUNT_LIST_JH="select * from bank_account@JH a "+
										  "join bank_user@JH b on a.user_id = b.user_id "+
										  "join b_member c on b.user_name = c.username "+
										  "and b.user_birthday = c.birthday "+
										  "AND SUBSTR(b.phone_no, 1, 3) || SUBSTR(b.phone_no, 5, 4) || SUBSTR(b.phone_no, 10) = c.phonenumber "+
										  " where a.status = 1 "+
										  " and c.memberid= ? ";
	
	private static String ACCOUNT_LIST_HARI="select * from b_user_account@SB a "+
											"join b_user_info@SB b on a.user_id = b.user_id "+
											"join b_member c on b.kor_name = c.username "+
											"join b_deposit@SB d on d.d_product_code = a.d_product_code "+
											"AND TO_CHAR(TO_DATE(SUBSTR(b.birthdate, 1, 2) || '-' || SUBSTR(b.birthdate, 3, 2) || '-' || SUBSTR(b.birthdate, 5, 2), 'YY-MM-DD'), 'YYYY-MM-DD') = c.birthday "+
											"and b.phone_no = c.phonenumber "+
											" and c.memberid= ? ";
	
	private static String ACCOUNT_SEARCH="select * from b_account where account_no=? and money> ? ";
	private static String ACCOUNT_SEARCH2="select * from b_account where account_no=? ";
	private static String ACCOUNT_MONEY_UPDATE="update b_account set money=case "+
											   " when account_no=? then money - ? "+
											   " when account_no=? then money + ? "+
											   " else money end "+
											   " where account_no in (?,?) ";
	private static String ACCOUNT_MONEY_UPDATE_BGH="update b_account set money=money-? "+
													" where account_no=?  ";
	private static String ACCOUNT_MONEY_UPDATE_BGH2="update b_account@BGH set account_balance=account_balance+? "+
												   " where account_no=?  ";
	private static String ACCOUNT_MONEY_UPDATE_JH="update bank_account@JH set balance=balance+? "+
			" where account_no=?  ";
	
	private static String ACCOUNT_MONEY_UPDATE_HARI="update b_user_account@SB set total_balance=total_balance+? "+
			" where account_no=?  ";
	
	private static String ACCOUNT_TOTALMONEY = "select * from b_account where account_no=? ";
	
	public boolean insertAccount(AccountVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_INSERT);
			stmt.setString(1, vo.getAccountNum());
			stmt.setString(2, vo.getId());
			stmt.setString(3, vo.getType());
			stmt.setString(4, vo.getMoney());
			stmt.setInt(5, vo.getPassword());
			stmt.setString(6, vo.getBankCode());
			stmt.setString(7, vo.getProductID());
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
				account.setAccountNum(rs.getString("account_no"));
				account.setId(rs.getString("memberid"));
				account.setType(rs.getString("accounttype"));
				account.setMoney(rs.getString("money"));
				account.setPassword(rs.getInt("password"));
				
				String bankName = rs.getString("bankcode");
				switch(bankName) {
					case "H.J" :
						bankName = "HJ은행";
						break;
					case "JH" :
						bankName = "JH은행";
						break;
					case "0758" :
						bankName = "하리은행";
						break;
					case "BGH" :
						bankName = "BGH은행";
						break;
				}
				account.setBankCode(bankName);
				
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
			stmt.setString(1, vo1.getAccountNum());
			stmt.setString(2, vo1.getMoney());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccountNum(rs.getString("account_no"));
				vo.setId(rs.getString("memberid"));
				vo.setType(rs.getString("accounttype"));
				vo.setMoney(rs.getString("money"));
				vo.setPassword(rs.getInt("password"));
				vo.setBankCode(rs.getString("bankcode"));
			
				account = vo;
				
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
			stmt.setString(1, vo1.getAccountNum());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO vo = new AccountVO();
				vo.setAccountNum(rs.getString("account_no"));
				vo.setId(rs.getString("memberid"));
				vo.setType(rs.getString("accounttype"));
				vo.setMoney(rs.getString("money"));
				vo.setPassword(rs.getInt("password"));
				vo.setBankCode(rs.getString("bankcode"));
			
				account = vo;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return account;
	}

	public void updateMoney(String accountNum, String accountNum2, int money) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_MONEY_UPDATE);
			stmt.setString(1, accountNum);
			stmt.setInt(2, money);
			stmt.setString(3, accountNum2);
			stmt.setInt(4, money);
			stmt.setString(5, accountNum);
			stmt.setString(6, accountNum2);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	public void updateMoney_BGH(String accountNum, int money) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_MONEY_UPDATE_BGH);
			stmt.setInt(1, money);
			stmt.setString(2, accountNum);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	public void updateMoney_BGH2(String transferaccountNum, int money) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_MONEY_UPDATE_BGH2);
			stmt.setInt(1, money);
			stmt.setString(2, transferaccountNum);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	public void updateMoney_JH(String accountNum, int money) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_MONEY_UPDATE_JH);
			stmt.setInt(1, money);
			stmt.setString(2, accountNum);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}
	
	public void updateMoney_Hari(String accountNum, int money) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_MONEY_UPDATE_HARI);
			stmt.setInt(1, money);
			stmt.setString(2, accountNum);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	public int totalMoney(String accountNum) {

		int totalmoney=0;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_TOTALMONEY);
			stmt.setString(1, accountNum);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int money = rs.getInt("money");
				
				totalmoney += money;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return totalmoney;
	}

	public List<AccountVO> accountList_BGH(UserVO vo) {
		
		List<AccountVO> accountList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_LIST_BGH);
			stmt.setString(1, vo.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO account = new AccountVO();
				account.setAccountNum(rs.getString("account_no"));
				account.setId(rs.getString("user_id"));
				account.setType(rs.getString("account_name"));
				account.setMoney(rs.getString("account_balance"));
				account.setPassword(rs.getInt("account_pw"));
				
				String bankName = rs.getString("bank_code");
				switch(bankName) {
					case "H.J" :
						bankName = "HJ은행";
						break;
					case "JH" :
						bankName = "JH은행";
						break;
					case "0758" :
						bankName = "하리은행";
						break;
					case "BGH" :
						bankName = "BGH은행";
						break;
				}
				account.setBankCode(bankName);
				
				accountList.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return accountList;
	}
	
	public List<AccountVO> accountList_JH(UserVO vo) {
		
		List<AccountVO> accountList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_LIST_JH);
			stmt.setString(1, vo.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO account = new AccountVO();
				account.setAccountNum(rs.getString("account_no"));
				account.setId(rs.getString("user_id"));
				account.setType(rs.getString("account_nm"));
				account.setMoney(rs.getString("balance"));
				account.setPassword(rs.getInt("account_pwd"));
				
				String bankName = rs.getString("bank_cd");
				switch(bankName) {
					case "H.J" :
						bankName = "HJ은행";
						break;
					case "JH" :
						bankName = "JH은행";
						break;
					case "0758" :
						bankName = "하리은행";
						break;
					case "BGH" :
						bankName = "BGH은행";
						break;
				}
				account.setBankCode(bankName);
				
				accountList.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return accountList;
	}

	public List<AccountVO> accountList_Hari(UserVO vo) {

		List<AccountVO> accountList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_LIST_HARI);
			stmt.setString(1, vo.getId());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AccountVO account = new AccountVO();
				account.setAccountNum(rs.getString("account_no"));
				account.setId(rs.getString("user_id"));
				account.setType(rs.getString("d_product_name"));
				account.setMoney(rs.getString("total_balance"));
				account.setPassword(rs.getInt("account_password"));
				
				String bankName = rs.getString("b_bank_code");
				switch(bankName) {
					case "H.J" :
						bankName = "HJ은행";
						break;
					case "JH" :
						bankName = "JH은행";
						break;
					case "0758" :
						bankName = "하리은행";
						break;
					case "BGH" :
						bankName = "BGH은행";
						break;
				}
				account.setBankCode(bankName);
				
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
