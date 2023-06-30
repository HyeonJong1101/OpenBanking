package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.bankInfo.BankInfoDAO;
import kr.ac.kopo.biz.bankInfo.BankInfoVO;

public class AccountTransferPageController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		BankInfoDAO dao = new BankInfoDAO();
		List<BankInfoVO> list = dao.search();
		
		session.setAttribute("bankList", list);
		
		return "jsp/account/transferPage.jsp";
	}

	
}
