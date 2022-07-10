package Bill;
import java.io.Serializable;

public class BillDetails implements Serializable{
	
	public String billDate;
    public int billDetailsCode;
    public String productCode;
    public String productName;
    public int customerCode;
    public int amount;
    public int price;
    public int employeeCode;
    public float total;
    public int confirm;
    
    public BillDetails(){
    	billDate = null;
        billDetailsCode=0;
        productCode=null;
        amount=0;
        price=0;
        total=amount * price;
    }
    
	public BillDetails(int billDetailCode, String billDate, int employeeCode) {
		// TODO Auto-generated constructor stub
		this.billDetailsCode = billDetailCode;
		this.billDate = billDate;
		this.employeeCode = employeeCode;
	}
	
	public BillDetails(String billDate, int billDetailsCode, int customerCode, String productCode, int employeeCode, int amount, int price, int total, int confirm) {
		this.billDate = billDate;
		this.billDetailsCode = billDetailsCode;
		this.customerCode = customerCode;
		this.productCode = productCode;
		this.employeeCode = employeeCode;
		this.amount = amount;
		this.price = price;
		this.total = amount * price;
		this.confirm = confirm;
	}
	
	public int getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal() {
		return amount * price;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public int getBillDetailsCode() {
		return billDetailsCode;
	}

	public void setBillDetailsCode(int billDetailsCode) {
		this.billDetailsCode = billDetailsCode;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public int getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(int employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	
	
}
