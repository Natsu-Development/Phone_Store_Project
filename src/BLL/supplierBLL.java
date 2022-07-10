package BLL;

import java.util.*;
import DAL.supplierDAL;
import Supplier.Supplier;

public class supplierBLL {
	supplierDAL supDAL = new supplierDAL();
	
	public List<Supplier> getAllSupplier() {
		return supDAL.findAll();
	}
	
	public String addSupplier(Supplier p) {
		if(supDAL.hasSupplierCode(p.getSupplierCode())) {
			return "Mã nhà cung cấp này đã tồn tại. Vui lòng thử lại";
		}
		if(supDAL.insert(p)) {
			return "Thêm nhà cung cấp thành công";
		}
		return "Thêm nhà cung cấp không thành công";
	}
	
	public String deleteSupplier(String id) {
		if(supDAL.delete(id)) {
			return "Xóa nhà cung cấp thành công";
		}
		return "Xóa nhà cung cấp không thành công";
	}
	
	public String editSupplier(Supplier p) {
		if(supDAL.update(p)) {
			return "Sửa thông tin nhà cung cấp thành công";
		}
		return "Sửa thông tin nhà cung cấp không thành công";
	}
	
	
	public List<Supplier> searchSupplierByName(String supplierName) {
		return supDAL.findByFullName(supplierName);
	}
	
	public List<String> getSupplierList() {
		return supDAL.getSupplierList();
	}
}