package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.accountlist.AccountListDAO;
import kr.ac.kopo.biz.accountlist.AccountListVO;

public class CreateAccountController implements Controller{

	Random r = new Random();
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
		
		String id = request.getParameter("id");
		String goods = request.getParameter("goods");
		int password = Integer.parseInt(request.getParameter("password"));
		
		int accountNum;
		String num = "";
		for (int i = 0; i < 8; i++) {
	            int digit = r.nextInt(9)+1;
	            num += digit;
	        }
		accountNum = Integer.parseInt(num);

		int money = 0;
		String code = "H.J";
		
		AccountVO vo = new AccountVO();
		vo.setId(id);
		vo.setType(goods);
		vo.setPassword(password);
		vo.setAccountNum(accountNum);
		vo.setMoney(money);
		vo.setBankCode(code);
		
		AccountDAO dao = new AccountDAO();
		dao.insertAccount(vo);
		
		AccountListVO vo2 = new AccountListVO();
		vo2.setAccountNum(accountNum);
		vo2.setStatus("T");
		
		AccountListDAO dao2 = new AccountListDAO();
		dao2.insertList(vo2);
		
		HttpSession session = request.getSession();
		session.setAttribute("account", vo);
		
		return "jsp/account/createAccount.jsp";
	}

	
}
