package Employee;

import Dungchung.person;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Employee extends person implements Serializable
{
	private int accountId;
    private String employeeCode;
    private String employeeName;
    private String Address;
    private String NumberPhone;
    private String birthday;
    public Employee()
    {
        super();
        employeeCode=null;
        birthday=null;
    }
    
  
	

	public Employee(int accountId, String employeeCode, String employeeName, String address, String numberPhone, String birthday) {
		super();
		this.accountId = accountId;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		Address = address;
		NumberPhone = numberPhone;
		this.birthday = birthday;
	}

	


	public int getAccountId() {
		return accountId;
	}




	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}




	public String getEmployeeCode() {
		return employeeCode;
	}




	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}




	public String getEmployeeName() {
		return employeeName;
	}




	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}




	public String getAddress() {
		return Address;
	}




	public void setAddress(String address) {
		Address = address;
	}




	public String getNumberPhone() {
		return NumberPhone;
	}




	public void setNumberPhone(String numberPhone) {
		NumberPhone = numberPhone;
	}




	public String getBirthday() {
		return birthday;
	}




	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
}
