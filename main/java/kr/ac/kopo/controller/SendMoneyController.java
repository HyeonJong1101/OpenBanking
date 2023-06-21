package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendMoneyController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		//HttpSession session = request.getSession();
		
		int accountNum = Integer.parseInt(request.getParameter("accountNum"));
		int transferaccountNum = Integer.parseInt(request.getParameter("transferaccountNum"));
		int money = Integer.parseInt(request.getParameter("money"));
		
		request.setAttribute("acc", accountNum);
		request.setAttribute("tra", transferaccountNum);
		request.setAttribute("m", money);
		
		return "jsp/account/transfer.jsp";
	}

	
}
