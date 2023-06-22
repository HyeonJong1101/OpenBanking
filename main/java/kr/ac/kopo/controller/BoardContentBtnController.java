package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardContentBtnController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String s = request.getParameter("title");
		String s2 = request.getParameter("content");
		String s3 = request.getParameter("id");
		System.out.println(s);
		System.out.println(s2);
		
		request.setAttribute("title", s);
		request.setAttribute("content", s2);
		request.setAttribute("id", s3);
		
		return "jsp/board/content.jsp";
	}

	
}
