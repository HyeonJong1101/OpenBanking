package kr.ac.kopo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.accountlist.AccountListDAO;
import kr.ac.kopo.biz.accountlist.AccountListVO;

public class SendMoneyController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		int accountNum = Integer.parseInt(request.getParameter("accountNum"));
		int transferaccountNum = Integer.parseInt(request.getParameter("transferaccountNum"));
		int money = Integer.parseInt(request.getParameter("money"));
		
		AccountVO vo1 = new AccountVO();
		vo1.setAccountNum(accountNum);
		vo1.setMoney(money);
		
		AccountDAO dao1 = new AccountDAO();
		AccountVO vo1_1 = new AccountVO();
		vo1_1 = dao1.searchAccount(vo1);
		
	
		
		if(vo1_1 == null) {
			System.out.println("잔액부족");
			request.setAttribute("msg", "이체실패/잔액부족");
			
			return "jsp/account/transferF.jsp";
		}else {
			
			AccountListVO vo2 = new AccountListVO();
			vo2.setAccountNum(transferaccountNum);
			
			AccountListDAO dao2 = new AccountListDAO();
			if(dao2.searchAccount(vo2) == null) {
				System.out.println("계좌없음");
				request.setAttribute("msg", "이체실패/계좌없음");
				
				return "jsp/account/transferF.jsp";
			}else {
				System.out.println("성공");
				request.setAttribute("msg", "이체완료");
				
				//dao1.updateMoney(accountNum,transferaccountNum,money);
				
				request.setAttribute("account1", accountNum);
				request.setAttribute("account2", transferaccountNum);
				request.setAttribute("money", money);
				
				return "jsp/account/transferPass.jsp";
			}
		}
		
		
	}

	
}
