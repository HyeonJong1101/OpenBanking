package kr.ac.kopo.biz.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;

public class TransactionDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String ACCOUNT_SEARCH="select * from b_transaction where accountnumber=? ";
	private static String LIST_INSERT="insert into b_transaction(transactionid,accountnumber,accountnumber2,transactiontype,amount,regdate) "+
									  " values((select nvl(max(transactionid),0)+1 from b_transaction),?,?,?,?,?) ";
	
	public List<TransactionVO> search(TransactionVO vo) {

		List<TransactionVO> account= new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH);
			stmt.setInt(1, vo.getAccountNum());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TransactionVO vo2 = new TransactionVO();
				vo2.setTransactionID(rs.getInt("transactionid"));
				vo2.setAccountNum(rs.getInt("accountnumber"));
				vo2.setAccountNum2(rs.getInt("accountnumber2"));
				vo2.setTransactiontype(rs.getString("transactiontype"));
				vo2.setAmount(rs.getInt("amount"));
				vo2.setRegdate(rs.getString("regdate"));
			
				account.add(vo2);
				
				//System.out.println(account.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return account;
		
	}

	public void insert(TransactionVO vo) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIST_INSERT);
			stmt.setInt(1, vo.getAccountNum());
			stmt.setInt(2, vo.getAccountNum2());
			stmt.setString(3, vo.getTransactiontype());
			stmt.setInt(4, vo.getAmount());
			stmt.setString(5, vo.getRegdate());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
