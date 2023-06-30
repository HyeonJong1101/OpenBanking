package kr.ac.kopo.biz.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;

public class CommentDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String COMMENT_LIST="select * from b_comments where boardid=? ";

	public List<CommentVO> commentList(String s4) {
		
		List<CommentVO> commentList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(COMMENT_LIST);
			stmt.setString(1, s4);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CommentVO comment = new CommentVO();
				comment.setBoardID(rs.getInt("boardid"));
				comment.setUserID(rs.getString("userid"));
				comment.setComment(rs.getString("comments"));
				comment.setRegDate(rs.getString("regdate"));
				
				commentList.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return commentList;
		
	}

}
