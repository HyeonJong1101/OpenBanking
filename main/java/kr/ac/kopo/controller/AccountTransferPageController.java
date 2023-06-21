package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.account.AccountVO;

public class AccountTransferPageController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		
		//System.out.println("세선:"+session.getAttribute("accountList")+"\n");
		return "jsp/account/transferPage.jsp";
	}

	
}
