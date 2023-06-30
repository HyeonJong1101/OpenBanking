package kr.ac.kopo.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.user.UserDAO;
import kr.ac.kopo.biz.user.UserVO;

public class SignController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
	
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setBirthday(birth);
		vo.setPhoneNumber(phone);
		vo.setSex(sex);
		UserDAO dao = new UserDAO();
		
		try {
			dao.insertUser(vo); //회원가입 성공한 경우
			request.setAttribute("msg", "성공");
			return "jsp/sign/signT.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			sendErrorAlert(response, "실패");
			return "";
		}
		
	}

	private void sendErrorAlert(HttpServletResponse response, String message) {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println("<script>");
            out.println("alert('" + message + "');");
            out.println("history.back();");
            out.println("</script>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
