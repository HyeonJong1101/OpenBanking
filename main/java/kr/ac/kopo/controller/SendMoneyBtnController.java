package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.bankInfo.BankInfoDAO;
import kr.ac.kopo.biz.transaction.TransactionDAO;
import kr.ac.kopo.biz.transaction.TransactionVO;
import kr.ac.kopo.biz.user.UserVO;

public class SendMoneyBtnController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {


		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };
	      
	    HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		String name = user.getName();
		System.out.println(name);
		
		String accountNum = request.getParameter("accountNum");
		String transferaccountNum = request.getParameter("transferaccountNum");
		int money = Integer.parseInt(request.getParameter("money"));
		int pass = Integer.parseInt(request.getParameter("pass"));
		String bankCode = request.getParameter("bankCode");
		String bankName = request.getParameter("bankName");
		
		if(request.getParameter("terminate") != null) {
			System.out.println(request.getParameter("terminate"));
			request.setAttribute("terminate", "terminate");
		}
		
		AccountVO vo = new AccountVO();
		AccountDAO dao = new AccountDAO();
		vo.setAccountNum(accountNum);
		
		BankInfoDAO info_dao = new BankInfoDAO();
		String bankName_receive = info_dao.search_name(bankCode);//받는은행이름
		
		vo = dao.searchAccount_pass(vo);
		System.out.println(bankName_receive);
		if(vo.getPassword() != pass) {
			System.out.println("비번오류");
			request.setAttribute("msg", "이체실패/비번오류");
			
			return "jsp/account/transferF.jsp";
		}else {
			
			switch(bankName_receive) {
			case "HJ은행" :
				dao.updateMoney(accountNum,transferaccountNum,money); //계좌잔액업데이트
				break;
			case "BGH은행" :
				dao.updateMoney_BGH(accountNum,money); //내계좌잔액업데이트
				dao.updateMoney_BGH2(transferaccountNum,money); //상대계좌잔액업데이트
				break;
			case "JH은행" :
				dao.updateMoney_BGH(accountNum,money); //내계좌잔액업데이트
				dao.updateMoney_JH(transferaccountNum,money); //상대계좌잔액업데이트
				break;
			case "하리은행" :
				dao.updateMoney_BGH(accountNum,money); //내계좌잔액업데이트
				dao.updateMoney_Hari(transferaccountNum,money); //상대계좌잔액업데이트
				break;
			}
			
			//내transaction테이블 insert
			TransactionVO vo2 = new TransactionVO();
			vo2.setAccountNum(accountNum);
			vo2.setAccountNum2(transferaccountNum);
			vo2.setTransactiontype("-");
			vo2.setAmount(money);
			vo2.setBankCode(bankName);
			vo2.setBankCode_receive(bankName_receive);
			
			TransactionDAO dao2 = new TransactionDAO();
			dao2.insert(vo2);
			
			TransactionVO vo3 = new TransactionVO();
			vo3.setAccountNum(transferaccountNum);
			vo3.setAccountNum2(accountNum);
			vo3.setTransactiontype("+");
			vo3.setAmount(money);
			vo3.setBankCode(bankName_receive);
			vo3.setBankCode_receive(bankName);
			
			TransactionDAO dao3 = new TransactionDAO();
			dao3.insert(vo3);
			
			if(!bankName_receive.equals("HJ은행")) {
				
				switch(bankName_receive) {
				
				case "BGH은행" :
					update_BGH(accountNum,transferaccountNum,money,bankName,bankName_receive); //계좌내역업데이트
					break;
				case "JH은행" :
					update_JH(accountNum,transferaccountNum,money,bankName,bankName_receive,name); //계좌내역업데이트
					break;
				case "하리은행" :
					update_Hari(accountNum,transferaccountNum,money,bankName,bankName_receive,name); //계좌내역업데이트
					break;
				}
				
				
				
			}
			
			request.setAttribute("msg", "이체성공");
			request.setAttribute("acc", accountNum);
			request.setAttribute("tra", transferaccountNum);
			request.setAttribute("m", money);
			
			request.setAttribute("bankName", bankName);
			request.setAttribute("bankName_receive", bankName_receive);
			
			return "jsp/account/transfer.jsp";
		}
		
	}
	
	public void update_BGH(String accountNum, String transferaccountNum, int money,String bankName, String bankName_receive) {
		//상대transaction테이블에 insert
		TransactionVO vo3 = new TransactionVO();
		vo3.setAccountNum(accountNum);
		vo3.setAccountNum2(transferaccountNum);
		vo3.setTransactiontype("입금");
		vo3.setAmount(money);
		vo3.setBankCode("H.J");
		vo3.setBankCode_receive("BGH");
		
		TransactionDAO dao3 = new TransactionDAO();
		dao3.insert_BGH(vo3);
		
	}
	
	public void update_JH(String accountNum, String transferaccountNum, int money,String bankName, String bankName_receive, String name) {
		//상대transaction테이블에 insert
		TransactionVO vo3 = new TransactionVO();
		vo3.setAccountNum(accountNum);
		vo3.setAccountNum2(transferaccountNum);
		vo3.setTransactiontype("입금");
		vo3.setAmount(money);
		vo3.setBankCode("H.J");
		vo3.setBankCode_receive("JH");
		vo3.setName(name);
		
		TransactionDAO dao3 = new TransactionDAO();
		dao3.insert_JH(vo3);
		
	}
	
	public void update_Hari(String accountNum, String transferaccountNum, int money,String bankName, String bankName_receive, String name) {
		//상대transaction테이블에 insert
		TransactionVO vo3 = new TransactionVO();
		vo3.setAccountNum(accountNum);
		vo3.setAccountNum2(transferaccountNum);
		vo3.setTransactiontype("타행이체");
		vo3.setAmount(money);
		vo3.setBankCode("H.J");
		vo3.setBankCode_receive("0758");
		vo3.setName(name);
		
		TransactionDAO dao3 = new TransactionDAO();
		dao3.insert_Hari(vo3);
		
	}
	
}
