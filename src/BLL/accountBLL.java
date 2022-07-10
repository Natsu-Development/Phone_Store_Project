package BLL;

import java.util.*;

import Account.Account;
import DAL.accountDAL;
import Account.Account;

public class accountBLL {
	accountDAL accDAL = new accountDAL();
	
	public List<Account> getAllAccount(String permission) {
		return accDAL.findAll(permission);
	}
	
	public String addAccount(Account p) {
		if(accDAL.hasAccountCode(p.getId())) {
			return "Mã tài khoản này đã tồn tại. Vui lòng thử lại";
		}
		if(accDAL.insert(p)) {
			return "Thêm tài khoản thành công";
		}
		return "Thêm tài khoản không thành công";
	}
	
	public String deleteAccount(int id) {
		if(accDAL.delete(id)) {
			return "Xóa tài khoản thành công";
		}
		return "Xóa tài khoản không thành công";
	}
	
	public String editAccount(Account p) {
		if(accDAL.update(p)) {
			return "Sửa tài khoản thành công";
		}
		return "Sửa tài khoản không thành công";
	}
	
	public int getId(String accountName) {
		return accDAL.code(accountName);
	}
	
	public String getPermission(int code) {
		return accDAL.getPermission(code);
	}
	
	public List<Account> searchAccountByName(String userName) {
		return accDAL.findByUserName(userName);
	}
	
	public int getCode(String table, String dataToGet, String name, String col) {
		return accDAL.getCode(table, dataToGet, name, col);
	}
	
	public String updateAccount(Account p, int accountCode) {
		if(accDAL.updateUser(p, accountCode)) {
			return "Sửa thông tin tài khoản thành công";
		}
		return "Sửa thông tin tài khoản không thành công";
	}
	
	public int getAccountCode() {
		return accDAL.getAccountId();
	}
	public List<String> getList(String request) {
		return accDAL.getList(request);
	}
} 
