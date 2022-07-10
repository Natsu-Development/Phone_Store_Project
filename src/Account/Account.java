package Account;
import java.io.*;


public class Account implements Serializable{
	private String accountName;
	private String password;
	private int id;
	private String permission;
	private String userName;
	private String address;
	private String numberPhone;
	private String birthday;
	
	public Account() {
		this.id = 0;
		this.accountName=null;
		this.password=null;
		this.permission=null;
	}
	//set cứng 
	public Account(int id, String accountName, String password, String permission, String userName, String address, String numberPhone, String birthday) {
		setId(id);
		setAccountName(accountName);
		setPassword(password);
		setPermission(permission);
		setUserName(userName);
		setAddress(address);
		setNumberPhone(numberPhone);
		setBirthday(birthday);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	// new
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumberPhone() {
		return numberPhone;
	}
	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}