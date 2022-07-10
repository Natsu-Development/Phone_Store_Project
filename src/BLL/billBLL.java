package BLL;

import java.util.*;

import javax.swing.JOptionPane;

import DAL.billDAL;
import Bill.Bill;

public class billBLL {
	billDAL bDAL = new billDAL();
	
	public List<Bill> getAllBill() {
		return bDAL.findAll();
	}
	
	public String addBill(Bill p) {
		if(bDAL.hasBillCode(p.getBillDate())) {
			return "Ngày của phiếu xuất này đã tồn tại. Vui lòng thử lại";
		}
		if(bDAL.insert(p)) {
			return "Thêm phiếu xuất thành công";
		}
		return "Thêm phiếu xuất không thành công";
	}
	
	public String deleteBill(String id) {
		if(bDAL.delete(id)) {
			return "Xóa phiếu xuất thành công";
		}
		return "Xóa phiếu xuất không thành công";
	}
	
	public String editBill(Bill p) {
		if(bDAL.update(p)) {
			return "Sửa phiếu xuất thành công";
		}
		return "Sửa phiếu xuất không thành công";
	}
	
	
	public List<Bill> searchBillByDate(String billDate) {
		return bDAL.findByDate(billDate);
	}
}
