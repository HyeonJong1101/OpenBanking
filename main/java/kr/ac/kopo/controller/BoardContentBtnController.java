package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.comment.CommentDAO;
import kr.ac.kopo.biz.comment.CommentVO;

public class BoardContentBtnController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
		
		String s = request.getParameter("title");
		String s2 = request.getParameter("content");
		String s3 = request.getParameter("writer");
		String s4 = request.getParameter("id");
		
		request.setAttribute("title", s);
		request.setAttribute("content", s2);
		request.setAttribute("writer", s3);
		request.setAttribute("id", s4);
		
		CommentVO vo = new CommentVO();
		CommentDAO dao = new CommentDAO();
		List<CommentVO> commentlist = dao.commentList(s4);
		request.setAttribute("commentList", commentlist);
		
		return "jsp/board/content.jsp";
	}

	
}
