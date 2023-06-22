package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.board.BoardDAO;
import kr.ac.kopo.biz.board.BoardVO;
import kr.ac.kopo.biz.user.UserVO;

public class InsertBoardController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		
		String title = request.getParameter("title");
		String writer = vo.getId();
		String content = request.getParameter("content");
		
		BoardVO board = new BoardVO();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		dao.insert(board);
		
		request.setAttribute("msg", "게시글등록!");
		
		return "jsp/board/insertBoardT.jsp";
	}

	
}
