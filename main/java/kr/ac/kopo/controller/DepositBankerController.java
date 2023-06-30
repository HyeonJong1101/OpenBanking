package kr.ac.kopo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.kopo.biz.product.ProductDAO;
import kr.ac.kopo.biz.product.ProductVO;

public class DepositBankerController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		ProductVO vo = new ProductVO();
		ProductDAO dao = new ProductDAO();
		
		List<ProductVO> productList = dao.searchProduct();
		
		session.setAttribute("productList", productList);

		return "jsp/product/deposit_banker.jsp";
	}

}
