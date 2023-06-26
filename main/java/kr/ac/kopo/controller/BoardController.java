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
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}catch(Exception e) {
			
		}
		
		List<BoardVO> boardList = dao.search(vo);
		List<BoardVO> boardList2 = dao.search(pageNo);
		
		int listSize = 5;
		int totalCount = dao.selectBoardCount();
		int lastPage = (totalCount % listSize == 0) ? totalCount / listSize 
													: totalCount / listSize + 1;	
		
		
		session.setAttribute("boardList", boardList2);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pageNo", pageNo);
		
		return "jsp/board/boardPage.jsp";
	}

}
