package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.board.BoardDAO;
import kr.ac.kopo.biz.board.BoardVO;

public class BoardController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		BoardVO vo = new BoardVO();
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardVO> boardList = dao.search(vo);
		
		session.setAttribute("boardList", boardList);
		
		return "jsp/board/boardPage.jsp";
	}

}
