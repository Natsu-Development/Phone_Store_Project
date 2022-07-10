package BLL;

import java.util.*;
import DAL.productDAL;
import Product.Product;

public class productBLL {
	productDAL proDAL = new productDAL();
	
	public List<Product> getAllProduct() {
		return proDAL.findAll();
	}
	
	public String addProduct(Product p) {
		if(proDAL.hasProductCode(p.getProductCode())) {
			return "Mã sản phẩm này đã tồn tại. Vui lòng thử lại";
		}
		if(proDAL.insert(p)) {
			return "Thêm sản phẩm thành công";
		}
		return "Thêm sản phẩm không thành công";
	}
	
	public String deleteProduct(String id) {
		if(proDAL.delete(id)) {
			return "Xóa sản phẩm thành công";
		}
		return "Xóa sản phẩm không thành công";
	}
	
	public String editProduct(Product p) {
		if(proDAL.update(p)) {
			return "Sửa sản phẩm thành công";
		}
		return "Sửa sản phẩm không thành công";
	}
	
	
	public List<Product> searchProductByName(String productName) {
		return proDAL.findByFullName(productName);
	}
	
	public List<String> getSupplierList() {
		return proDAL.getSupplierList();
	}
	
	public List<String> getProductList() {
		return proDAL.getProductList();
	}
	
	public String getProduct(String id) {
		return proDAL.getProduct(id);
	}
	
}
