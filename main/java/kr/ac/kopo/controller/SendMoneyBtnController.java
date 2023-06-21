package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.transaction.TransactionDAO;
import kr.ac.kopo.biz.transaction.TransactionVO;

public class SendMoneyBtnController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		int accountNum = Integer.parseInt(request.getParameter("accountNum"));
		int transferaccountNum = Integer.parseInt(request.getParameter("transferaccountNum"));
		int money = Integer.parseInt(request.getParameter("money"));
		int pass = Integer.parseInt(request.getParameter("password"));
		
		AccountVO vo = new AccountVO();
		AccountDAO dao = new AccountDAO();
		
		vo.setAccountNum(accountNum);
		
		vo = dao.searchAccount_pass(vo);
		if(vo.getPassword() != pass) {
			System.out.println("비번오류");
			request.setAttribute("msg", "이체실패/비번오류");
			
			return "jsp/account/transferF.jsp";
		}else {
			dao.updateMoney(accountNum,transferaccountNum,money);
			
			TransactionVO vo2 = new TransactionVO();
			vo2.setAccountNum(accountNum);
			vo2.setAccountNum2(transferaccountNum);
			vo2.setTransactiontype("입금");
			vo2.setAmount(money);
			vo2.setRegdate("20230621");
			
			TransactionDAO dao2 = new TransactionDAO();
			dao2.insert(vo2);
			
			System.out.println("성공2");
			request.setAttribute("msg", "이체성공");
			request.setAttribute("acc", accountNum);
			request.setAttribute("tra", transferaccountNum);
			request.setAttribute("m", money);
			
			return "jsp/account/transfer.jsp";
		}
		
	}

	
}
