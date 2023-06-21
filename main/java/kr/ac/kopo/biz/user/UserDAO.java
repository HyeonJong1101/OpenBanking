package kr.ac.kopo.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.biz.common.JDBCUtil;

public class UserDAO {

	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private static String USER_INSERT = "insert into b_member(memberid,username,password) " +
										" values(?, ?, ?) ";
	private static String USER_SEARCH = "select * from b_member " +
									 " where memberid=? and password=? ";
	private static String USER_INFO = "select * from library_user where id=?";
	private static String CHANGE_PW = "update library_user set password=? where id=?";
	
	public void insertUser(UserVO vo) throws Exception {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getName());
			stmt.setString(3, vo.getPassword());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public UserVO searchUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_SEARCH);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("memberid"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
}
