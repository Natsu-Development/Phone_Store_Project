package BLL;

import java.text.ParseException;
import java.util.*;

import javax.swing.JOptionPane;

import DAL.billDAL;
import DAL.billDetailsDAL;
import DAL.productDAL;
import DAL.supplierDAL;
import Dungchung.statis;
import GUI.billDetailsGUI;
import GUI.billGUI;
import Product.Product;
import Supplier.Supplier;
import Bill.Bill;
import Bill.BillDetails;

public class billDetailsBLL {
	billDetailsDAL bdDAL = new billDetailsDAL();
	billDAL bDAL = new billDAL();
	productDAL pDAL = new productDAL();
	supplierDAL sDAL = new supplierDAL();
	
	public List<BillDetails> getAllBillDetails(String date) {
		return bdDAL.findAll(date);
	}
	
	public String addBillDetails(BillDetails p, List<BillDetails> billCodeList) {
		if(!pDAL.checkAmount(p.getAmount(), p.getProductCode())) {
			return "Số lượng sản phẩm nhỏ hơn số lượng bạn cần hoặc sản phẩm đã hết hàng. Vui lòng thử lại";
		}
		if(bdDAL.hasBillDetailsCode(p.getBillDate(), p.getBillDetailsCode())) {
			return "Mã phiếu nhập hoặc ngày tạo phiếu nhập này đã tồn tại. Vui lòng thử lại";
		}
		// nếu bill chung chưa tồn tại tạo phiếu chung rồi sau đó mới insert
		if(!bDAL.billNotExist(p.getBillDate())) {
			Bill b = new Bill();
			b.setBillDate(p.getBillDate());
			b.setAmount(0);
			p.setTotal(0);
			if(bDAL.insert(b) && bdDAL.insert(p) && pDAL.handleBillDetails(p)) {
				return "Thêm chi tiết phiếu xuất thành công";
			}
		}
		if(bdDAL.insert(p) && pDAL.handleBillDetails(p)) {
			return "Thêm chi tiết phiếu xuất thành công";
		}
		return "Thêm chi tiết phiếu xuất không thành công";
	}
	
	public String deleteBillDetails(String id, String date) {
		if(bdDAL.delete(id, date)) {
			return "Xóa chi tiết phiếu xuất thành công";
		}
		return "Xóa chi tiết phiếu xuất không thành công";
	}
	
	public String editBillDetails(BillDetails p) {
		if(bdDAL.update(p)) {
			return "Sửa chi tiết phiếu xuất thành công";
		}
		return "Sửa chi tiết phiếu xuất không thành công";
	}
	
	
	public List<BillDetails> searchBillDetailsByCustomerCode(String code, String col) {
		return bdDAL.findByCode(code, col);
	}
	
	public List<BillDetails> getOrderNotConfirmed() {
		return bdDAL.getOrderNotConfirmed();
	}
	public String deleteOrderNotConfirmed(BillDetails p) {
		if(bdDAL.deleteOrderNotConfirmed(p)) {
			return "Xóa đơn hàng chưa xác nhận thành công";
		}
		return "Xóa đơn hàng chưa xác nhận không thành công";
	}
	public String confirmOrder(BillDetails p) {
		if(bdDAL.confirmOrder(p)) {
			return "Xác nhận đơn hàng thành công";
		}
		return "Xác nhận đơn hàng không thành công";
	}
	
	public List<BillDetails> searchOrderNotConfirmByCusCode(String code) {
		return bdDAL.getOrderNotConfirmByCusCode(code);
	}
	
	// statistic 
	public List<statis> statistic(String monthNow) throws ParseException {
		List<Supplier> listSupplier = sDAL.findAll();
		List<Product> listProduct = pDAL.findAll(); 
		return bdDAL.statistic(listSupplier, listProduct, monthNow);
	}
	
	public List<statis> statisticByProduct(String monthNow) throws ParseException {
		List<Product> listProduct = pDAL.findAll();
		return bdDAL.statisticByProduct(listProduct, monthNow);
	}
}
