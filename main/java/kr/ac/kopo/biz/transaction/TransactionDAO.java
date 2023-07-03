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
	
	private static String ACCOUNT_SEARCH="select * from b_transaction where account_no=? order by transactionid ";
	private static String LIST_INSERT="insert into b_transaction(transactionid,account_no,accountnumber2,transactiontype,amount,bankname,bankname_receive) "+
									  " values((select nvl(max(transactionid),0)+1 from b_transaction),?,?,?,?,?,?) ";
	private static String LIST_INSERT_BGH="insert into b_transfer@BGH(transfer_no,myaccount_no,mybank_code,youraccount_no,yourbank_code,transfer_detail,transfer_amount) "+
			  " values((select nvl(max(transfer_no),0)+1 from b_transfer@BGH),?,?,?,?,?,?) ";
	
	private static String LIST_INSERT_JH="insert into bank_history@JH(from_account_no,from_bank_cd,from_nm,to_account_no,to_bank_cd,to_nm,h_mount,h_class,h_balance) "+
			 " values(?,? "
			 + ",(select user_name from bank_user@JH a "
			 + "join bank_account@JH b on a.user_id = b.user_id "
			 + "where b.account_no=?) "
			 + ",?,? "
			 + ",? "
			 + ",?,? "
			 + ",(select balance from bank_account@JH where account_no=?)) ";
	
	private static String LIST_INSERT_HARI="insert into b_transaction@SB(t_sender_bank_code, t_sender_account_no, t_receiver_bank_code, t_receiver_account_no, t_amount, t_type, t_to_memo, t_from_memo, t_status, t_previous_balance, t_in_out ) "+
			 " values(? ,?,?,?,?,?,? "
			 + ",(select kor_name from b_user_account@SB a "
			 + "join b_user_info@SB b on a.user_id = b.user_id "
			 + "where account_no = ?) "
			 + ",? "
			 + ",(select total_balance from b_user_account@SB a "
			 + "join b_user_info@SB b on a.user_id = b.user_id "
			 + "where account_no = ?) "
			 + ",?) ";

	public List<TransactionVO> search(TransactionVO vo) {

		List<TransactionVO> account= new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(ACCOUNT_SEARCH);
			stmt.setString(1, vo.getAccountNum());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				TransactionVO vo2 = new TransactionVO();
				vo2.setTransactionID(rs.getInt("transactionid"));
				vo2.setAccountNum(rs.getString("account_no"));
				vo2.setAccountNum2(rs.getString("accountnumber2"));
				vo2.setTransactiontype(rs.getString("transactiontype"));
				vo2.setAmount(rs.getInt("amount"));
				vo2.setRegdate(rs.getDate("regdate"));
				vo2.setBankCode(rs.getString("bankname"));
				vo2.setBankCode_receive(rs.getString("bankname_receive"));
			
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
			stmt.setString(1, vo.getAccountNum());
			stmt.setString(2, vo.getAccountNum2());
			stmt.setString(3, vo.getTransactiontype());
			stmt.setInt(4, vo.getAmount());
			stmt.setString(5, vo.getBankCode());
			stmt.setString(6, vo.getBankCode_receive());
			
			stmt.executeUpdate();
			//System.out.println(vo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void insert_BGH(TransactionVO vo) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIST_INSERT_BGH);
			stmt.setString(1, vo.getAccountNum2());
			stmt.setString(2, vo.getBankCode_receive());
			stmt.setString(3, vo.getAccountNum());
			stmt.setString(4, vo.getBankCode());
			stmt.setString(5, vo.getTransactiontype());
			stmt.setInt(6, vo.getAmount());
			
			//conn.commit();  // 커밋
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void insert_JH(TransactionVO vo) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIST_INSERT_JH);
			stmt.setString(1, vo.getAccountNum2());
			stmt.setString(2, vo.getBankCode_receive());
			stmt.setString(3, vo.getAccountNum2());
			stmt.setString(4, vo.getAccountNum());
			stmt.setString(5, vo.getBankCode());
			stmt.setString(6, vo.getName());
			stmt.setInt(7, vo.getAmount());
			stmt.setString(8, "2");
			stmt.setString(9, vo.getAccountNum2());
			//stmt.setInt(9, 100);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void insert_Hari(TransactionVO vo) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(LIST_INSERT_HARI);
			stmt.setString(1, vo.getBankCode_receive());
			stmt.setString(2, vo.getAccountNum2());
			stmt.setString(3, vo.getBankCode());
			stmt.setString(4, vo.getAccountNum());
			stmt.setInt(5, vo.getAmount());
			stmt.setString(6, vo.getTransactiontype());
			stmt.setString(7, vo.getName());
			stmt.setString(8, vo.getAccountNum2());
			stmt.setString(9, "이체완료");
			stmt.setString(10, vo.getAccountNum2());
			stmt.setString(11, "입금");

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

}
