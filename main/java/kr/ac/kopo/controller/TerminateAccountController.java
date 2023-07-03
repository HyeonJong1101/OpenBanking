package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.accountlist.AccountListDAO;
import kr.ac.kopo.biz.accountlist.AccountListVO;

public class TerminateAccountController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
		
		String accountNum = request.getParameter("selectedAccount");
		String accountNum2 = request.getParameter("selectedAccount2").split("\\+")[0];
		String bankName = request.getParameter("selectedAccount2").split("\\+")[1];
		String balance = request.getParameter("balance");
		String pass = request.getParameter("pass");
		
		System.out.println(accountNum+accountNum2+balance+bankName);
		
		//1.내계좌상태 F
		AccountListDAO dao = new AccountListDAO();
		dao.terminate(accountNum);
		
		String bankCode=null;
		switch(bankName) {
		
		case "HJ은행" :
			System.out.println("hj");
			bankCode = "H.J";
			break;
		case "JH은행" :
			System.out.println("jh");
			bankCode = "JH";
			break;
		case "하리은행" :
			System.out.println("0758");
			bankCode = "0758";
			break;
		case "BGH은행" :
			System.out.println("bgh");
			bankCode = "BGH";
			break;
		}
		
		//2.계좌이체
		request.setAttribute("accountNum", accountNum);
		request.setAttribute("transferaccountNum", accountNum2);
		request.setAttribute("money", balance);
		request.setAttribute("bankCode", bankCode);
		request.setAttribute("bankName", "HJ은행");
		request.setAttribute("pass", pass);
		
		return "jsp/terminate/terminateSuccess.jsp";
	}

	
}
