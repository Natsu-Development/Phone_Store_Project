
package Product;

import java.io.Serializable;

import Dungchung.Abstract;
 public class Product implements Serializable
{ 
    private String productCode;
    private String productName;
    private String producerName;
    private String supplierName;
    private int amount;
    private int importPrice;
    private int price;
    private String image;
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product() {
    	productCode=null;
        productName=null;
        producerName=null;
        supplierName=null;
        amount=0;
        importPrice=0;
        price=0;
    }

	public Product(String productCode, String productName, String producerName,
			String supplierName, int amount, int importPrice, int price,String image) {
		this.productCode = productCode;
		this.productName = productName;
		this.producerName = producerName;
		this.supplierName = supplierName;
		this.amount = amount;
		this.importPrice = importPrice;
		this.price = price;
		this.image = image;
	}
	
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
	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(int importPrice) {
		this.importPrice = importPrice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
    
}
