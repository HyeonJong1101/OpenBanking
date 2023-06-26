package kr.ac.kopo.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private static String BOARD_LIST="select * from b_board order by boardid desc ";
	private static String BOARD_LIST_PAGE="select boardid, title, memberid, regdate, content " +
										  "  from ( select rownum rnum, b.* " +
										  " 	      from ( select a.* " +
										  "		 		       from b_board a " +
										  "				      order by boardid desc ) b ) " +
										  " where rnum >= ?                                 " +
										  "   and rnum <= ?                                 ";
	private static String BOARD_INSERT="insert into b_board(boardid,memberid,title,content) "+
									   " values((select nvl(max(boardid),0)+1 from b_board),?,?,?) ";
	private static String BOARD_COUNT="select count(*) cnt from b_board ";

	private static final int LIST_SIZE = 5;
	
	public List<BoardVO> search(BoardVO vo) {

		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardID(rs.getInt("boardid"));
				board.setWriter(rs.getString("memberid"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return boardList;
		
	}

	public void insert(BoardVO board) {

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, board.getWriter());
			stmt.setString(2, board.getTitle());
			stmt.setString(3, board.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public int selectBoardCount() {
		int totalCount = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_COUNT);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				totalCount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return totalCount;
	}

	public List<BoardVO> search(int pageNo) {
		int start = (pageNo -1) * LIST_SIZE + 1;
		int end   = pageNo      * LIST_SIZE;
		
		/*int start = 1;
		int end = pageNo;*/
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST_PAGE);
			
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVO VO = new BoardVO();
				VO.setBoardID(rs.getInt("boardid"));
				VO.setWriter (rs.getString("memberid"));
				VO.setTitle  (rs.getString("title"));
				VO.setContent(rs.getString("content"));
				VO.setRegDate(rs.getDate("regdate"));
				boardList.add(VO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	}

}
