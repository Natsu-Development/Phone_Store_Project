package BLL;

import java.util.List;

import Bill.BillDetails;
import DAL.customerDAL;
import DAL.productDAL;
import Product.Product;
import userService.Cart;

public class CustomerBLL {
	productDAL proDAL = new productDAL();
	customerDAL cusDAL = new customerDAL();
	
	public List<Cart> getAllCart(int userCode) {
		return cusDAL.findAll(userCode);
	}
	
	public List<BillDetails> getAllBill(int userCode) {
		return cusDAL.getAllBill(userCode);
	}
	public String addCart(Cart p, int userCode) {
		// nếu sản phẩm đã hết
		if(!proDAL.checkAmount(p.getAmount(), p.getProductCode())) {
			return "Số lượng sản phẩm nhỏ hơn số lượng bạn cần hoặc sản phẩm đã hết hàng. Vui lòng thử lại";
		}
		if(cusDAL.insert(p, userCode)) {
			return "Thêm sản phẩm vào giỏ hàng thành công";
		}
		return "Thêm sản phẩm vào giỏ hàng không thành công";
	}
	
	public String deleteCart(Cart p, int userCode) {
		if(cusDAL.delete(p, userCode)) {
			return "Xóa giỏ hàng thành công";
		}
		return "Xóa giỏ hàng không thành công";
	}
	
	public String editCart(Cart p, int userCode) {
		if(!proDAL.checkAmount(p.getAmount(), p.getProductCode())) {
			return "Số lượng sản phẩm nhỏ hơn số lượng bạn cần hoặc sản phẩm đã hết hàng. Vui lòng thử lại";
		}
		if(cusDAL.update(p, userCode)) {
			return "Cập nhật giỏ hàng thành công";
		}
		return "Cập nhật giỏ hàng không thành công";
	}
	
	public String cartBay(int usercode, Cart p) {
		if(!proDAL.checkAmount(p.getAmount(), p.getProductCode())) {
			return "Số lượng sản phẩm nhỏ hơn số lượng bạn cần hoặc sản phẩm đã hết hàng. Vui lòng thử lại";
		}
		if(cusDAL.handleCartBay(p, usercode) && cusDAL.delete(p, usercode)) {
			return "Đặt hàng thành công";
		}
		return "Đặt hàng không thành công";
	}
	public List<Cart> searchCartByProductName(String productName, int userCode) {
		return cusDAL.findByProductName(productName, userCode);
	}
	
	public List<BillDetails> searchBillByProductName(String productName, int userCode) {
		return cusDAL.findBillByProductName(productName, userCode);
	}
}
