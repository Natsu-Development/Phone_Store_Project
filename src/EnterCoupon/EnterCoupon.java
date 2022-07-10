package EnterCoupon;

import java.io.Serializable;

public class EnterCoupon implements Serializable{
    private String couponDate;
    private int amount;
    private int total;
    
    public EnterCoupon(){
    	couponDate=null;
        amount=0;
        total=0;
    }
    
	public EnterCoupon(String couponDate, int amount, int total) {
		this.couponDate = couponDate;
		this.amount = amount;
		this.total = total;
	}

	public String getCouponDate() {
		return couponDate;
	}

	public void setCouponDate(String couponDate) {
		this.couponDate = couponDate;
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
