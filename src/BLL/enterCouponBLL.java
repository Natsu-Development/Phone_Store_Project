package BLL;

import java.util.*;

import DAL.enterCouponDAL;
import EnterCoupon.EnterCoupon;

public class enterCouponBLL {
	enterCouponDAL ecDAL = new enterCouponDAL();
	
	public List<EnterCoupon> getAllEnterCoupon() {
		return ecDAL.findAll();
	}
	
	public String addEnterCoupon(EnterCoupon p) {
		if(ecDAL.hasCouponDate(p.getCouponDate())) {
			return "Mã phiếu nhập hoặc ngày tạo phiếu nhập này đã tồn tại. Vui lòng thử lại";
		}
		if(ecDAL.insert(p)) {
			return "Thêm phiếu nhập thành công";
		}
		return "Thêm phiếu nhập không thành công";
	}
	
	public String deleteEnterCoupon(String id) {
		if(ecDAL.delete(id)) {
			return "Xóa phiếu nhập thành công";
		}
		return "Xóa phiếu nhập không thành công";
	}
	
	public String editEnterCoupon(EnterCoupon p) {
		if(ecDAL.update(p)) {
			return "Sửa phiếu nhập thành công";
		}
		return "Sửa phiếu nhập không thành công";
	}
	
	public List<EnterCoupon> searchEnterCouponByDate(String enterCouponDate) {
		return ecDAL.findByDate(enterCouponDate);
	}
	
}
