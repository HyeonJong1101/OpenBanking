package kr.ac.kopo.biz.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.biz.common.JDBCUtil;

public class ProductDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private static String PRODUCT_LIST="select * from b_product";
	private static String PRODUCT_INSERT="insert into b_product(productid,productname,productinfo) values(?,?,?) ";
	
	public List<ProductVO> searchProduct() {
		
		List<ProductVO> productList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(PRODUCT_LIST);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setProductID(rs.getString("productid"));
				product.setProductName(rs.getString("productname"));
				product.setProductInfo(rs.getString("productinfo"));
				product.setProductStatus(rs.getInt("productstatus"));
				
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return productList;
	}

	public void insertProduct(ProductVO vo) {
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(PRODUCT_INSERT);
			stmt.setString(1, vo.getProductID());
			stmt.setString(2, vo.getProductName());
			stmt.setString(3, vo.getProductInfo());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
}
