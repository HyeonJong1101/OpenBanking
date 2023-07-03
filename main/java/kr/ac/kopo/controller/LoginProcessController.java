package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.user.UserDAO;
import kr.ac.kopo.biz.user.UserVO;

public class LoginProcessController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO dao = new UserDAO();
		vo = dao.searchUser(vo);
		if(dao.searchUser(vo) != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", vo);
			request.setAttribute("msg", "로그인성공");
		}else {
			request.setAttribute("msg", "로그인실패");
		}
		
		return "jsp/login/loginProcess.jsp"; //forword시킨것
		//return "redirect:"+request.getContextPath();
	}

	
}
