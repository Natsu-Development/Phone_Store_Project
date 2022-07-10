package BLL;

import java.text.ParseException;
import java.util.List;

import DAL.enterCouponDetailsDAL;
import EnterCoupon.EnterCouponDetails;

public class enterCouponDetailsBLL {
	enterCouponDetailsDAL ecdDAL = new enterCouponDetailsDAL();
	
	public List<EnterCouponDetails> getAllEnterCouponDetails(String date) {
		return ecdDAL.findAll(date);
	}
	
	public String addEnterCouponDetails(EnterCouponDetails p) {
		if(ecdDAL.hasEnterCouponDetails(p.getCouponDate(), p.getEnterCouponCodeDetails())) {
			return "Chi tiết phiếu nhập này đã tồn tại. Vui lòng thử lại";
		}
		if(ecdDAL.insert(p)) {
			return "Thêm chi tiết phiếu nhập thành công";
		}
		return "Thêm chi tiết phiếu nhập không thành công";
	}
	
	public String deleteEnterCouponDetails(String id, String date) {
		if(ecdDAL.delete(id, date)) {
			return "Xóa chi tiết phiếu nhập thành công";
		}
		return "Xóa chi tiết phiếu nhập không thành công";
	}
	
	public String editEnterCouponDetails(EnterCouponDetails p) {
		if(ecdDAL.update(p)) {
			return "Sửa chi tiết phiếu nhập thành công";
		}
		return "Sửa chi tiết phiếu nhập không thành công";
	}
	
	
	public List<EnterCouponDetails> searchEnterCouponDetailsByEmployeeCode(String code) {
		return ecdDAL.findByCode(code);
	}
	
	// statis
	public int getTotal() throws ParseException {
		return ecdDAL.getMoneyOfEnter();
	}
}
