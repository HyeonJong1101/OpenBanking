package kr.ac.kopo.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.kopo.biz.product.ProductDAO;
import kr.ac.kopo.biz.product.ProductVO;

public class RegistController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      };		
		ProductVO vo = new ProductVO();
		vo.setProductID(request.getParameter("product_code"));
		vo.setProductName(request.getParameter("product_name"));
		vo.setProductInfo(request.getParameter("product_info"));
		
		ProductDAO dao = new ProductDAO();
		dao.insertProduct(vo);
		
		return "jsp/product/productMain.jsp";
	}

	
}
