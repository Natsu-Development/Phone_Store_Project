package EnterCoupon;

import Product.ArrayProduct;
import Product.Product;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import Bill.Bill;
import Dungchung.Tester;

public class ArrayDetailEnterCoupon{
     private EnterCouponDetails [] ecd;  
     private EnterCoupon [] en;
     private Product [] p;
     private  int n,k;
     transient Scanner sc=new Scanner(System.in);
     public void creatData() {
    	 ecd = new EnterCouponDetails[3];
    	 int i=0;
    	 ecd[i] = new EnterCouponDetails("1", "1", "Xiaomi", "China", "redmiNote8", "26/10/2020" , 4000000, 10, 3900000, i);
    	 ecd[i+1] = new EnterCouponDetails("2", "2", "Iphone", "America", "iphone 8", "26/10/2020" , 8200000, 10, 7900000, i);
    	 ecd[i+2] = new EnterCouponDetails("3", "3", "Samsung", "China", "Samsung note 7", "26/10/2020" , 7500000, 10, 6900000, i);
     }
    public void inputDetailEnterCoupon(){
        while(true){
            System.out.print("\nNhập số lượng chi tiết phiếu nhập: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                ecd=new EnterCouponDetails[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nCHI TIẾT PHIẾU NHẬP THỨ "+(i+1));
                    ecd[i]=new EnterCouponDetails();
                    String couponCode, productCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu:");
                    couponCode=sc.nextLine();
                    System.out.print("\nMã hàng:");
                    productCode=sc.nextLine();
                    for(int j=0;j<i;j++){
                        if(couponCode.equals(ecd[j].getCouponCode()) && productCode.equals(ecd[j].getProductCode())){
                            System.out.println("\n>>Bộ mã phiếu nhập và mã hàng này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    ecd[i].setCouponCode(couponCode);
                    ecd[i].setProductCode(productCode);
                    int amount=0;
                    sl:
                    while(true){                                           
                        System.out.print("\nSố lượng:");                
                        try{
                            amount=sc.nextInt();
                            break sl;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    ecd[i].setAmount(amount);
                    int importPrice=0;
                    sl:
                    while(true){                                           
                        System.out.print("\nGiá nhập:");                
                        try{
                            importPrice=sc.nextInt();
                            break sl;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    ecd[i].setImportPrice(importPrice);
                    sc.nextLine();
                }
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                sc.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                sc.nextLine();
            }
        }
    }
    public void display(){
    	System.out.println("\n\n");
    	System.out.println("o==================================================================================================================================================================================o");
        System.out.println("|                                                                                      DANH SÁCH CHI TIẾT PHIẾU NHẬP                                                               |");
        System.out.println("|==================================================================================================================================================================================|");
        System.out.println("|   Ngày nhập   | Mã phiếu |   Tên nhà sản xuất   |   Tên nhà cung cấp   |  Mã hàng  |      Tên hàng      | Số lượng |     Giá nhập     |     Giá bán     |       Thành tiền       |"); 
        System.out.println("|==================================================================================================================================================================================|");
        for(int i=0; i<ecd.length;i++)
        {
            System.out.format("|%12s   |", ecd[i].getEnterCouponDate());
            System.out.format("%6s    | ", ecd[i].getCouponCode());
            System.out.format("%12s         |", ecd[i].getProducerName());
            System.out.format("%12s          |", ecd[i].getSupplierName());
            System.out.format("%6s     | ", ecd[i].getProductCode());
            System.out.format("%14s     |", ecd[i].getProductName());
            System.out.format("%5s     | ", ecd[i].getAmount());
            System.out.format("%11s      | ", ecd[i].getImportPrice());
            System.out.format("%11s     |", ecd[i].getPrice());
            System.out.format("%15s         |\n", ecd[i].getTotal());
        }
        System.out.println("o==================================================================================================================================================================================o");
    }
    public void add() {
        while(true){
            System.out.print("\nNhập số lượng chi tiết phiếu nhập cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=ecd.length;
                n=ecd.length+k;
                ecd = Arrays.copyOf(ecd, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nCHI TIẾT PHIẾU NHẬP THỨ "+ count);
                    ecd[i]=new EnterCouponDetails();
                    String couponCode, productCode, producerName = null, productName = null, supplierName, enterCouponDate;
                    int flag = 0;
                    checkDate:
                    	while(true){
    	                	System.out.print("\nNgày nhập:");
    	                	enterCouponDate = sc.nextLine();
    	                	if(!Tester.day(enterCouponDate)) {
    	                		System.out.println("\n>>Ngày nhập hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                                continue checkDate;
    	                	}
    	                	for(int check = 0; check < en.length; check++) {
    	                		if(enterCouponDate.equals(en[check].getDate())) {
    	                			ecd[i].setEnterCouponDate(enterCouponDate);
    	                			break checkDate;
    	                		}
    	                	}
    	                	creatEnterCoupon(enterCouponDate);
    	                	ecd[i].setEnterCouponDate(enterCouponDate);
    	                	break checkDate;
                    	}
                    nhapma:
                    while(true){    
                    System.out.print("\nMã phiếu:");
                    couponCode=sc.nextLine();
                    for(int j=0;j<i;j++){
                        if(couponCode.equals(ecd[j].getCouponCode()) && enterCouponDate.equals(ecd[j].getEnterCouponDate())){
                            System.out.println("\n>>Mã phiếu nhập của ngày này đã bị trùng. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    System.out.print("\nMã hàng:");
                    productCode=sc.nextLine();
                    for(int set=0; set<p.length; set++) {
                    	if(productCode.equals(p[set].getProductCode())) {
                    		productName = p[set].getProductName();
                    		producerName = p[set].getProducerName();
                    		flag=1;
                    	}
                    }
                    break nhapma;
                    }
                    checkProductName:
                    while(true) {
                    	if(flag==1) {
                    		System.out.println("Tên sản phẩm:" + productName);
                    		break checkProductName;
                    	}	
                    	else {
	                    System.out.println("Nhập tên sản phẩm");
	                    productName = sc.nextLine();
	                    break checkProductName;
                    	}
                    }
                    checkProducerName:
                        while(true) {
                        	if(flag==1) {
                        		System.out.println("Nhà sản xuất:" + producerName);
                        		break checkProducerName;
                        	}	
                        	else {
    	                    System.out.println("Nhập tên sản phẩm");
    	                    productName = sc.nextLine();
    	                    break checkProducerName;
                        	}
                        }
                    System.out.println("Nhập tên nhà cung cấp");
                    supplierName = sc.nextLine();
                    ecd[i].setProductName(productName);
                    ecd[i].setCouponCode(couponCode);
                    ecd[i].setProductCode(productCode);
                    ecd[i].setSupplierName(supplierName);
                    ecd[i].setProducerName(producerName);
                    int amount=0;
                    sl:
                    while(true){                                           
                        System.out.print("\nSố lượng:");                
                        try{
                            amount=sc.nextInt();
                            break sl;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    ecd[i].setAmount(amount);
                    int importPrice=0;
                    sl:
                    while(true){                                           
                        System.out.print("\nGiá nhập:");                
                        try{
                            importPrice=sc.nextInt();
                            break sl;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    int price=0;
                    sl:
                    while(true){                                           
                        System.out.print("\nGiá bán:");                
                        try{
                            price=sc.nextInt();
                            break sl;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    ecd[i].setImportPrice(importPrice);
                    ecd[i].setPrice(price);
                    sc.nextLine();
                    setProduct:
                    while(true) {
                    for(int up=0;up<p.length;up++) {
	                    if(productCode.equals(p[up].getProductCode())) {
		                    p[up].setAmount(amount + p[up].getAmount());
		                    p[up].setImportPrice(importPrice);
		                    p[up].setPrice(price);
		                    p[up].setProductCode(productCode);
		                    p[up].setProducerName(producerName);
		                    p[up].setProductName(productName);
		                    p[up].setSupplierName(supplierName);
		                    break setProduct;
	                    }
                    }
                    int oldLenght = p.length;
                	p = Arrays.copyOf(p, p.length+1);
                	p[oldLenght]=new Product();
                	p[oldLenght].setAmount(amount);
                    p[oldLenght].setImportPrice(importPrice);
                    p[oldLenght].setPrice(price);
                    p[oldLenght].setProductCode(productCode);
                    p[oldLenght].setProducerName(producerName);
                    p[oldLenght].setProductName(productName);
                    p[oldLenght].setSupplierName(supplierName);
                    break setProduct;
                    }
                    count++;
                }
                System.out.println("Thêm chi tiết phiếu nhập thành công");
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                sc.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                sc.nextLine();
            }
        }
    }
    public void creatEnterCoupon(String x) {
    	int i = en.length;
    	en = Arrays.copyOf(en, en.length+1);
    	en[i] = new EnterCoupon(String.valueOf(i+1), x, "2", i);
    }
    public void remove(String x){
        int i=0;
        while(i<ecd.length){
            if(x.equals(ecd[i].getEnterCouponDate())){
                removeDetails(i);
                i--;
            }
            i++;
        }       
    }
    public void removeDetails(int m){
        for(int i=m;i<ecd.length-1;i++)
        	ecd[i]=ecd[i+1];
        ecd = Arrays.copyOf(ecd, ecd.length-1);
    }
    public void remove(){
        boolean isExisted = false;
        int m;
        nhapma:
        while(true){
        System.out.print("\nNhập ngày cần xóa:");
        String date=sc.nextLine();
        System.out.print("\n Nhập mã phiếu nhập cần xóa");
        String code= sc.nextLine();
        int i=0; 
        for(i=0;i<ecd.length;i++){
            if(date.equals(ecd[i].getEnterCouponDate()) && code.equals(ecd[i].getCouponCode())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Ngày nhập hoặc mã phiếu nhập này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<ecd.length-1;i++)
        	ecd[i]=ecd[i+1];
        ecd = Arrays.copyOf(ecd,ecd.length-1);
        
    }
    public void edit(){
        boolean isExisted = false;
        String couponCode, enterCouponDate;
        nhapma:
        while(true){
        System.out.println("\nNhập bộ mã phiếu nhập và ngày nhập hàng cần sửa");
        System.out.print("\nMã phiếu:");
        couponCode=sc.nextLine();
        System.out.print("\nNgày nhập:");
        enterCouponDate=sc.nextLine();
        for(int i=0;i<ecd.length;i++){
            if(couponCode.equals(ecd[i].getCouponCode()) && enterCouponDate.equals(ecd[i].getEnterCouponDate())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Bộ mã phiếu nhập và mã hàng này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Mã phiếu nhập");
        System.out.println("2-Ngày nhập");
        System.out.println("3-Giá nhập");
        System.out.println("___________________________________________");
        loop:
        while(true){
            int t=0;           
            System.out.print("Nhập vào sự lựa chọn của bạn:");                
            try{
                t=sc.nextInt();                   
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số!");
            }
            sc.nextLine();//dung de bo ki tu xuong dong bi thua truoc khi nhap String
            for(int i=0;i<ecd.length;i++){
                if(couponCode.equals(ecd[i].getCouponCode()) && enterCouponDate.equals(ecd[i].getEnterCouponDate())){
                    switch(t){
                        case 1:{
                            String newCouponCode;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã phiếu mới:");
                                newCouponCode=sc.nextLine();
                                for(int j=0;j<ecd.length;j++){
                                    if(newCouponCode.equals(ecd[j].getCouponCode())){
                                        System.out.println("\n>>Mã phiếu nhập của ngày này đã tồn tại. Mời bạn nhập lại ngày nhập khác.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            ecd[i].setCouponCode(newCouponCode);
                            break loop;
                        }
                        case 2:{
                            String newEnterCouponDate=null;
                            checkDate:
                            	while(true){
            	                	System.out.print("\nNgày nhập:");
            	                	newEnterCouponDate = sc.nextLine();
            	                	if(!Tester.day(newEnterCouponDate)) {
            	                		System.out.println("\n>>Ngày nhập hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                                        continue checkDate;
            	                	}
            	                	for(int check = 0; check < en.length; check++) {
            	                		if(newEnterCouponDate.equals(en[check].getDate())) {
            	                			ecd[i].setEnterCouponDate(newEnterCouponDate);
            	                			break checkDate;
            	                		}
            	                	}
            	                	int k = en.length;
            	                	en = Arrays.copyOf(en, en.length+1);
            	                	en[k] = new EnterCoupon(String.valueOf(k+1), newEnterCouponDate, "2", i);
            	                	ecd[i].setEnterCouponDate(newEnterCouponDate);
            	                	break checkDate;
                            	}
                            break loop;
                        }
                        case 3:{
                            int newImportPrice=0;
                            sl:
                            while(true){                                           
                                System.out.print("\nNhập vào giá nhập mới:");                
                                try{
                                	newImportPrice=sc.nextInt();
                                    break sl;
                                }
                                catch(InputMismatchException ex){
                                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                                    sc.nextLine();
                                }                           
                            }
                            ecd[i].setImportPrice(newImportPrice);
                            for(int up=0; up<p.length; up++) {
                            	if(p[up].getProductCode().equals(ecd[i].getProductCode())) {
                            		p[up].setImportPrice(newImportPrice);
                            	}
                            }
                            break loop;
                        }
                        default:{
                            System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 4.");
                            continue loop;
                        }
                    }
                }
            }
        }
    }    
    public void search(){
        boolean isExisted = false;
        String couponDate;
        nhapma:
        while(true){            
            System.out.print("\nNhập ngày của phiếu nhập cần tìm:");
            couponDate=sc.nextLine();      
        	System.out.println("o==================================================================================================================================================================================o");
            System.out.println("|                                                                    THÔNG TIN CHI TIẾT PHIẾU NHẬP CẦN TÌM                                                                         |");
            System.out.println("|==================================================================================================================================================================================|");
            System.out.println("|   Ngày nhập   | Mã phiếu |   Tên nhà sản xuất   |   Tên nhà cung cấp   |  Mã hàng  |      Tên hàng      | Số lượng |     Giá nhập     |     Giá bán     |       Thành tiền       |"); 
            System.out.println("|==================================================================================================================================================================================|"); 
            for(int i=0;i<ecd.length;i++){
                if(couponDate.equals(ecd[i].getEnterCouponDate())){                    
                    System.out.format("|%12s   |", ecd[i].getEnterCouponDate());
                    System.out.format("%6s    | ", ecd[i].getCouponCode());
                    System.out.format("%12s         |", ecd[i].getProducerName());
                    System.out.format("%12s          |", ecd[i].getSupplierName());
                    System.out.format("%6s     | ", ecd[i].getProductCode());
                    System.out.format("%14s     |", ecd[i].getProductName());
                    System.out.format("%5s     | ", ecd[i].getAmount());
                    System.out.format("%11s      | ", ecd[i].getImportPrice());
                    System.out.format("%11s     |", ecd[i].getPrice());
                    System.out.format("%15s         |\n", ecd[i].getTotal());
                    isExisted = true;
                }
            }
            if(!isExisted ){
                System.out.println("\n>>Mã phiếu nhập này không tồn tại. Mời bạn nhập lại.");
                continue nhapma;
            }
        	System.out.println("o==================================================================================================================================================================================o");
            break nhapma;
        }
    }
    public boolean file_null(){
        if(ecd.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("enterCouponDetailList.txt"));
            
            oos.writeObject(ecd);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            oos.close();
            
        }
    }    
    public void readFile() {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("enterCouponDetailList.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Không tìm thấy đường dẫn");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayEnterCoupon.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             ecd=(EnterCouponDetails[])ois.readObject();
         } catch (IOException ex) {
             
             System.out.println("Loi 3");
         } catch (ClassNotFoundException ex) {
            
             System.out.println("Loi 4");
         }
       
        //--------------------------B3. Đóng Luồng-----------------------------
         try {
             ois.close();
         } catch (IOException ex) {
             
             System.out.println("Rong 3");
         }
         
         try {
             ois.close();
         } catch (IOException ex) {
             
         }
    }   
    public void writeFileProduct() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("productList.txt"));
            
            oos.writeObject(p);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            oos.close();
            
        }
    }
    
    public void readFileProduct() {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("productList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayProduct.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             p=(Product[])ois.readObject();
         } catch (IOException ex) {
             
             System.out.println("Loi 3");
         } catch (ClassNotFoundException ex) {
            
             System.out.println("Loi 4");
         }
       
        //--------------------------B3. Đóng Luồng-----------------------------
         try {
             ois.close();
         } catch (IOException ex) {
             
             System.out.println("Rong 3");
         }
         
         try {
             ois.close();
         } catch (IOException ex) {
             
         }
    }
    public void writeFileEnterCoupon() throws IOException {
        ObjectOutputStream oos = null;
        try {
             oos = new ObjectOutputStream(new FileOutputStream("enterCouponList.txt"));
             
             oos.writeObject(en);
         } catch (IOException ex) {
             System.out.println(ex.toString());
         } finally {
             oos.close();
             
         }
     }
     
     public void readFileEnterCoupon() {
         //-------------------B1. Tao doi tuong luong va lien ket luong-----------
         FileInputStream fis = null;
         try {
             fis = new FileInputStream("enterCouponList.txt");
         } catch (FileNotFoundException ex) {
             //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Không tìm thấy đường dẫn.");
         }
         ObjectInputStream ois = null;
          try {
              ois = new ObjectInputStream(fis);
          } catch (IOException ex) {
              Logger.getLogger(ArrayEnterCoupon.class.getName()).log(Level.SEVERE, null, ex);
          }
            
          try {
              en=(EnterCoupon[])ois.readObject();
          } catch (IOException ex) {
              
              System.out.println("Loi 3");
          } catch (ClassNotFoundException ex) {
             
              System.out.println("Loi 4");
          }
        
         //--------------------------B3. Đóng Luồng-----------------------------
          try {
              ois.close();
          } catch (IOException ex) {
              
              System.out.println("Rong 3");
          }
          
          try {
              ois.close();
          } catch (IOException ex) {
              
          }
     }    
}