package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.transaction.TransactionDAO;
import kr.ac.kopo.biz.transaction.TransactionVO;

public class MoneyHistoryController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String accountNum = request.getParameter("accountNum");
		
		TransactionVO vo = new TransactionVO();
		vo.setAccountNum(accountNum);
		
		TransactionDAO dao = new TransactionDAO();
		List<TransactionVO> list = dao.search(vo);
		
		session.setAttribute("historyList", list);
		
		AccountVO vo2 = new AccountVO();
		vo2.setAccountNum(accountNum);
		
		AccountDAO dao2 = new AccountDAO();
		int money = dao2.totalMoney(vo2.getAccountNum());
		request.setAttribute("totalmoney", money);
		
		return "jsp/account/moneyHistoryList.jsp";
	}

	
}
