package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.accountlist.AccountListDAO;
import kr.ac.kopo.biz.accountlist.AccountListVO;

public class SendMoneyController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
		
		String accountNum = request.getParameter("accountNum");
		String transferaccountNum = request.getParameter("transferaccountNum");
		int money = Integer.parseInt(request.getParameter("money"));
		String bankName = "HJ은행"; 
		String bankCode_receive = request.getParameter("bankCode_receive"); 
			
		//은행 계좌확인
		switch(bankCode_receive) {
		
			case "H.J" :
				System.out.println("hj");
				return HJBank(accountNum, transferaccountNum, money, bankCode_receive, bankName, request);
			case "JH" :
				System.out.println("jh");
				return JHBank(accountNum, transferaccountNum, money, bankCode_receive, bankName, request);
			case "0758" :
				System.out.println("0758");
				return HariBank(accountNum, transferaccountNum, money, bankCode_receive, bankName, request);
			case "BGH" :
				System.out.println("bgh");
				return BGHBank(accountNum, transferaccountNum, money, bankCode_receive, bankName, request);
		}
		
		return null;
		
	}
	
	public String HJBank(String accountNum, String transferaccountNum, int money, String bankCode_receive, String bankName, HttpServletRequest request) {
	    AccountVO vo1 = new AccountVO();
	    vo1.setAccountNum(accountNum);
	    vo1.setMoney(money);

	    AccountDAO dao1 = new AccountDAO();
	    AccountVO vo1_1 = dao1.searchAccount(vo1);

	    if (accountNum.equals(transferaccountNum)) {
	        request.setAttribute("msg", "입금계좌와 출금계좌가 같습니다");
	        return "jsp/account/transferF.jsp";
	    }

	    if (vo1_1 == null) {
	        System.out.println("잔액부족");
	        request.setAttribute("msg", "이체실패/잔액부족");
	        return "jsp/account/transferF.jsp";
	    } else {
	        AccountListVO vo2 = new AccountListVO();
	        vo2.setAccountNum(transferaccountNum);

	        AccountListDAO dao2 = new AccountListDAO();
	        if (dao2.searchAccount(vo2) == null) {
	            System.out.println("계좌없음");
	            request.setAttribute("msg", "이체실패/계좌없음");
	            return "jsp/account/transferF.jsp";
	        } else {
	            System.out.println("성공");
	            request.setAttribute("msg", "이체완료");

	            // dao1.updateMoney(accountNum,transferaccountNum,money);

	            request.setAttribute("account1", accountNum);
	            request.setAttribute("account2", transferaccountNum);
	            request.setAttribute("money", money);
	            request.setAttribute("bankCode", bankCode_receive);
	            request.setAttribute("bankName", bankName);

	            return "jsp/account/transferPass.jsp";
	        }
	    }
	}
	
	public String BGHBank(String accountNum, String transferaccountNum, int money, String bankCode_receive, String bankName, HttpServletRequest request) {
	    AccountVO vo1 = new AccountVO();
	    vo1.setAccountNum(accountNum);
	    vo1.setMoney(money);

	    AccountDAO dao1 = new AccountDAO();
	    AccountVO vo1_1 = dao1.searchAccount(vo1);

	    if (accountNum.equals(transferaccountNum)) {
	        request.setAttribute("msg", "입금계좌와 출금계좌가 같습니다");
	        return "jsp/account/transferF.jsp";
	    }

	    if (vo1_1 == null) {
	        System.out.println("잔액부족");
	        request.setAttribute("msg", "이체실패/잔액부족");
	        return "jsp/account/transferF.jsp";
	    } else {
	        AccountListVO vo2 = new AccountListVO();
	        vo2.setAccountNum(transferaccountNum);

	        AccountListDAO dao2 = new AccountListDAO();
	        
	       // dao2.searchAccount_BGH(transferaccountNum);
	        
	        if (dao2.searchAccount_BGH(transferaccountNum) == null) {
	            System.out.println("계좌없음");
	            request.setAttribute("msg", "이체실패/계좌없음");
	            return "jsp/account/transferF.jsp";
	        } else {
	            System.out.println("성공");
	            request.setAttribute("msg", "이체완료");

	            // dao1.updateMoney(accountNum,transferaccountNum,money);

	            request.setAttribute("account1", accountNum);
	            request.setAttribute("account2", transferaccountNum);
	            request.setAttribute("money", money);
	            request.setAttribute("bankCode", bankCode_receive);
	            request.setAttribute("bankName", bankName);

	            return "jsp/account/transferPass.jsp";
	        }
	    }
	}
	
	public String JHBank(String accountNum, String transferaccountNum, int money, String bankCode_receive, String bankName, HttpServletRequest request) {
	    AccountVO vo1 = new AccountVO();
	    vo1.setAccountNum(accountNum);
	    vo1.setMoney(money);

	    AccountDAO dao1 = new AccountDAO();
	    AccountVO vo1_1 = dao1.searchAccount(vo1);

	    if (accountNum.equals(transferaccountNum)) {
	        request.setAttribute("msg", "입금계좌와 출금계좌가 같습니다");
	        return "jsp/account/transferF.jsp";
	    }

	    if (vo1_1 == null) {
	        System.out.println("잔액부족");
	        request.setAttribute("msg", "이체실패/잔액부족");
	        return "jsp/account/transferF.jsp";
	    } else {
	        AccountListVO vo2 = new AccountListVO();
	        vo2.setAccountNum(transferaccountNum);

	        AccountListDAO dao2 = new AccountListDAO();
	        
	       // dao2.searchAccount_BGH(transferaccountNum);
	        
	        if (dao2.searchAccount_JH(transferaccountNum) == null) {
	            System.out.println("계좌없음");
	            request.setAttribute("msg", "이체실패/계좌없음");
	            return "jsp/account/transferF.jsp";
	        } else {
	            System.out.println("성공");
	            request.setAttribute("msg", "이체완료");

	            // dao1.updateMoney(accountNum,transferaccountNum,money);

	            request.setAttribute("account1", accountNum);
	            request.setAttribute("account2", transferaccountNum);
	            request.setAttribute("money", money);
	            request.setAttribute("bankCode", bankCode_receive);
	            request.setAttribute("bankName", bankName);

	            return "jsp/account/transferPass.jsp";
	        }
	    }
	}

	public String HariBank(String accountNum, String transferaccountNum, int money, String bankCode_receive, String bankName, HttpServletRequest request) {
	    AccountVO vo1 = new AccountVO();
	    vo1.setAccountNum(accountNum);
	    vo1.setMoney(money);

	    AccountDAO dao1 = new AccountDAO();
	    AccountVO vo1_1 = dao1.searchAccount(vo1);

	    if (accountNum.equals(transferaccountNum)) {
	        request.setAttribute("msg", "입금계좌와 출금계좌가 같습니다");
	        return "jsp/account/transferF.jsp";
	    }

	    if (vo1_1 == null) {
	        System.out.println("잔액부족");
	        request.setAttribute("msg", "이체실패/잔액부족");
	        return "jsp/account/transferF.jsp";
	    } else {
	        AccountListVO vo2 = new AccountListVO();
	        vo2.setAccountNum(transferaccountNum);

	        AccountListDAO dao2 = new AccountListDAO();
	        
	       // dao2.searchAccount_BGH(transferaccountNum);
	        
	        if (dao2.searchAccount_Hari(transferaccountNum) == null) {
	            System.out.println("계좌없음");
	            request.setAttribute("msg", "이체실패/계좌없음");
	            return "jsp/account/transferF.jsp";
	        } else {
	            System.out.println("성공");
	            request.setAttribute("msg", "이체완료");

	            // dao1.updateMoney(accountNum,transferaccountNum,money);

	            request.setAttribute("account1", accountNum);
	            request.setAttribute("account2", transferaccountNum);
	            request.setAttribute("money", money);
	            request.setAttribute("bankCode", bankCode_receive);
	            request.setAttribute("bankName", bankName);

	            return "jsp/account/transferPass.jsp";
	        }
	    }
	}
	
}
