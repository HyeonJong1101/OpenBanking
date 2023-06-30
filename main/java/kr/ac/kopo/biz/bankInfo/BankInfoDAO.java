package kr.ac.kopo.biz.bankInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;

public class BankInfoDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String BANK_LIST="select * from b_info ";
	private static String BANK_NAME="select * from b_info where bank_code=? ";
	
	public List<BankInfoVO> search() {

		List<BankInfoVO> bankList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BANK_LIST);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BankInfoVO bank = new BankInfoVO();
				bank.setBankCode(rs.getString("bank_code"));
				bank.setBankName(rs.getString("bank_name"));
				
				bankList.add(bank);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bankList;
	}

	public String search_name(String code) {

		String bank_name=null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BANK_NAME);
			stmt.setString(1,code);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				bank_name = rs.getString("bank_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return bank_name;
	}
}
