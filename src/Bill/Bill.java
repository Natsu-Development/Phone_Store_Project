package Bill;
import java.awt.Button;
import java.io.Serializable;

import javax.swing.JButton;

public class Bill implements Serializable{
    public String billCode;
    public String customerCode;
    public String billDate;
    public int amount;
    public int total;
    public Bill(){
        billDate=null;
        amount=0;
        total=0;
    }

	public Bill(String billDate, int amount, int total) {
		this.billDate = billDate;
		this.amount=amount;
		this.total = total;
	}

	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
