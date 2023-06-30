package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.account.AccountDAO;
import kr.ac.kopo.biz.account.AccountVO;
import kr.ac.kopo.biz.user.UserVO;

public class AccountListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		
		AccountDAO dao = new AccountDAO();
		
		List<AccountVO> accountList = dao.accountList(vo);
		List<AccountVO> accountList_BGH = dao.accountList_BGH(vo);
		
		session.setAttribute("accountList", accountList);
		session.setAttribute("accountList_BGH", accountList_BGH);
		
		return "jsp/account/accountList.jsp";
	}

}
