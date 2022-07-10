package userService;

import GUI.customerProductGUI;
import Product.Product;

public class Cart {
	int cartCode;
	public int getCartCode() {
		return cartCode;
	}
	public void setCartCode(int cartCode) {
		this.cartCode = cartCode;
	}
	String productCode;
	String productName;
	int amount; 
	int price; 
	int total;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Cart(int cartCode, String productCode, String productName, int amount, int price, int total) {
		this.cartCode = cartCode;
		this.productCode = productCode;
		this.productName = productName;
		this.amount = amount;
		this.price = price;
		this.total = amount * price;
	}
	
}
