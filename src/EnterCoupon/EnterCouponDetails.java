package EnterCoupon;
import java.io.Serializable;

public class EnterCouponDetails implements Serializable{
    private String couponDate;
    private String enterCouponCodeDetails;
    private String productCode;
    private int supplierCode;
    private int employeeCode;
    private int price;
    private int importPrice;
    private int amount;
    private int total;
	public String getCouponDate() {
		return couponDate;
	}
	public void setCouponDate(String couponDate) {
		this.couponDate = couponDate;
	}
	public String getEnterCouponCodeDetails() {
		return enterCouponCodeDetails;
	}
	public void setEnterCouponCodeDetails(String enterCouponCodeDetails) {
		this.enterCouponCodeDetails = enterCouponCodeDetails;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(int supplierCode) {
		this.supplierCode = supplierCode;
	}
	public int getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(int employeeCode) {
		this.employeeCode = employeeCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(int importPrice) {
		this.importPrice = importPrice;
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
	public EnterCouponDetails(String couponDate, String enterCouponCodeDetails, String productCode, int supplierCode,
			int employeeCode, int price, int importPrice, int amount, int total) {
		super();
		this.couponDate = couponDate;
		this.enterCouponCodeDetails = enterCouponCodeDetails;
		this.productCode = productCode;
		this.supplierCode = supplierCode;
		this.employeeCode = employeeCode;
		this.price = price;
		this.amount = amount;
		this.importPrice = importPrice;
		this.total = amount * importPrice;
	}
	public EnterCouponDetails() {

	}
 
}
