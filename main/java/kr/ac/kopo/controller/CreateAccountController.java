package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.accountlist.AccountListDAO;
import kr.ac.kopo.biz.accountlist.AccountListVO;
import kr.ac.kopo.biz.productManagement.ProductManagementDAO;
import kr.ac.kopo.biz.productManagement.ProductManagementVO;

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
		String productInfo = request.getParameter("goods");
		String[] values = productInfo.split("\\+");
		String productid = values[0];
		String goods = values[1];
		int password = Integer.parseInt(request.getParameter("password"));
		
		String accountNum;
		
		//계좌번호 중복체크
		AccountListDAO list = new AccountListDAO();
		List<AccountListVO> accountList = list.searchList();
		
		do {
			String num = "";
			for (int i = 0; i < 8; i++) {
		            int digit = r.nextInt(9)+1;
		            num += digit;
		        }
			accountNum = num;
		}while(accountNum.equals(accountList.get(0).getAccountNum()));
		
		//초기계좌돈,은행코드
		int money = 0;
		String code = "H.J";
		
		AccountVO vo = new AccountVO();
		vo.setId(id);
		vo.setType(goods);
		vo.setPassword(password);
		vo.setAccountNum(accountNum);
		vo.setMoney(money);
		vo.setBankCode(code);
		vo.setProductID(productid);
		
		AccountDAO dao = new AccountDAO();
		//dao.insertAccount(vo);
		
		if(dao.insertAccount(vo)) {

			/*accoutList에 계좌저장*/
			AccountListVO vo2 = new AccountListVO();
			vo2.setAccountNum(accountNum);
			vo2.setStatus("T");
			
			AccountListDAO dao2 = new AccountListDAO();
			dao2.insertList(vo2);
			
			HttpSession session = request.getSession();
			session.setAttribute("account", vo);
			
			/*prductmanagement 상품관리에 정보저장*/
			ProductManagementVO vo3 = new ProductManagementVO();
			vo3.setAccountNum(accountNum);
			vo3.setProductID(productid);
			//vo3.setEndDate();
			
			ProductManagementDAO dao3 = new ProductManagementDAO();
			dao3.insertProductManagement(vo3);
		}else {
			
		}
		
		
		return "jsp/account/createAccount.jsp";
	}

	
}
