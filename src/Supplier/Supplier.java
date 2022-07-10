package Supplier;

import java.io.Serializable;
import Dungchung.person;

public class Supplier extends person implements Serializable {
	private String supplierCode;
	private String supplierName;
	private String address;
	private String numberphone;
	
    public String getNumberPhone() {
		return numberphone;
	}

	public void setNumberPhone(String numberphone) {
		this.numberphone = numberphone;
	}

	public Supplier() {
            super();
            supplierCode=null;
    }
    
    public Supplier(String name) {
    	super.setName(name);
    }
    
    public Supplier(String supplierCode, String supplierName, String address, String numberphone) {
		super();
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
		this.address = address;
		this.numberphone = numberphone;
	}

	public String getSupplierCode() {
		return supplierCode;
	}
	
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void xuat(){
        System.out.format("|%10s         | ", supplierCode);
        System.out.format("%15s         | ", super.getName());
        System.out.format("%12s    | ", super.getAddress());
        System.out.format("%13s     |\n", super.getNumberPhone());
    }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
	//LỚP NÀY ĐÃ SỬA THỨ TỰ
}
