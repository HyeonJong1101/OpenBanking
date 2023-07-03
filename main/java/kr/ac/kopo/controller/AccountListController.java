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
		List<AccountVO> accountList_JH = dao.accountList_JH(vo);
		List<AccountVO> accountList_Hari = dao.accountList_Hari(vo);
		
		session.setAttribute("accountList", accountList);
		session.setAttribute("accountList_BGH", accountList_BGH);
		session.setAttribute("accountList_JH", accountList_JH);
		session.setAttribute("accountList_Hari", accountList_Hari);
		
		return "jsp/account/accountList.jsp";
	}

}
