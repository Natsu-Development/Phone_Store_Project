package Bill;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dungchung.Mang;
import Dungchung.Tester;
import EnterCoupon.EnterCoupon;
import Product.ArrayProduct;
import Product.Product;
import Users.ArrayCustomer;
import Users.Customer;
public class ArrayBillDetails implements Mang{
     private BillDetails[] ar;
     private Product [] p;
     private Customer [] c;
     private Bill [] bills;
     private  int n,k;
     ArrayProduct ap = new ArrayProduct();
     Scanner sc=new Scanner(System.in);
    public ArrayBillDetails(){
        
    }
    public void creatData() {
    	ar = new BillDetails[10];
    	int i=0;
    	ar[i] = new BillDetails("Thúy", "0924714551", "tp hcm", "1", "3", "26/11/2020", "2", "1","redmiNote8", 1, 2300000, i);
     	ar[i+1] = new BillDetails("Hậu", "0924714552", "tp hcm", "2", "4", "26/11/2020", "2", "2","iphone 8", 2, 2300000, i);
     	ar[i+2] = new BillDetails("Tiên", "0924714551", "tp hcm", "3", "5", "26/11/2020", "2", "3","Samsung note 7", 3, 2300000, i);
     	ar[i+3] = new BillDetails("Thúy", "0924714551", "tp hcm", "4", "3", "26/11/2020", "2", "2","iphone 8", 2, 2300000, i);
     	ar[i+4] = new BillDetails("Hậu", "0924714551", "tp hcm", "1", "4", "27/11/2020", "2", "3","Samsung note 7", 1, 2300000, i);
     	ar[i+5] = new BillDetails("Tiên", "0924714551", "tp hcm", "2", "5", "27/11/2020", "2", "2","iphone 8", 2, 2300000, i);
     	ar[i+6] = new BillDetails("Tiên", "0924714551", "tp hcm", "3", "5", "27/11/2020", "2", "3","Samsung note 7", 2, 2300000, i);
     	ar[i+7] = new BillDetails("Hậu", "0924714551", "tp hcm", "1", "4", "28/11/2020", "2", "1","redmiNote8", 2, 2300000, i);
     	ar[i+8] = new BillDetails("Hậu", "0924714551", "tp hcm", "2", "4", "28/11/2020", "2", "2","iphone 8", 3, 2300000, i);
     	ar[i+9] = new BillDetails("Hậu", "0924714551", "tp hcm", "3", "4", "28/11/2020", "2", "3","Samsung note 7", 4, 2300000, i);
    }
    public void billSale() {
    	loop:
    	while(true){
            System.out.print("\nNhập số lượng hóa đơn bán hàng cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=ar.length;
                n=ar.length+k;
                ar = Arrays.copyOf(ar, n);
                int count=1;
                for(int i=oldLength;i<n;i++){
                	System.out.print("\nPHIẾU BÁN HÀNG THỨ "+count);
                	ar[i]=new BillDetails();
                	String billDate;
                	checkDate:
                	while(true){
	                	System.out.print("\nNgày xuất:");
	                	billDate = sc.nextLine();
	                	if(!Tester.day(billDate)) {
	                		System.out.println("\n>>Ngày xuất hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue checkDate;
	                	}
	                	for(int check = 0; check < bills.length; check++) {
	                		if(billDate.equals(bills[check].getBillDate())) {
	                			ar[i].setBillDate(billDate);
	                			break checkDate;
	                		}
	                	}
	                	creatBill(billDate);
	                	ar[i].setBillDate(billDate);
	                	break checkDate;
                	}
                    String billCode;
                    nhapma:
                    while(true){
	                    System.out.print("\nMã phiếu:");
	                    billCode = sc.nextLine();
	                    for(int j=0;j<i;j++) {
	                    	 if(billCode.equals(ar[j].getBillCode()) && billDate.equals(ar[j].getBillDate())) {
	                         	System.out.println("Mã phiếu này đã tồn tại. Vui lòng nhập vào mã phiếu khác!!!");
	                         	continue nhapma;
	                         }
	                    }
	                    ar[i].setBillCode(billCode);
	                    break nhapma;
                    }
                    check:
                    while(true) {
	                    System.out.print("\nNhập vào tên khách hàng:");
	                    String customerName = sc.nextLine();
	                    System.out.print("\nNhập vào số điện thoại:");
	                    String numberPhone = sc.nextLine();
	                    for(int check = 0; check < c.length; check++) {
	                    	if(customerName.equals(c[check].getName()) && numberPhone.equals(c[check].getNumberPhone())) {
	                    		ar[i].setName(customerName);
	                    		ar[i].setNumberPhone(numberPhone);
	                    		ar[i].setCustomerCode(c[check].getCustomerCode());
	                    		break check;
	                    	}
	                    }
	                    System.out.println("Khách hàng này chưa được nhập thông tin. Vui lòng nhập vào khách hàng khác hoặc tạo thông tin của khách hàng");
	                    ar = Arrays.copyOf(ar, ar.length - k);
	                    break loop;
                    }
                    productCode:
                    while(true) {
	                    System.out.print("\nMã sản phẩm:"); // mã hàng phải trùng khớp ở bên kia 
	                    String productCode = sc.nextLine();
	                    for(int check=0;check<p.length;check++){
	                        if(productCode.equals(p[check].getProductCode())) {
	                        	ar[i].setProductCode(productCode);
	                        	System.out.println("Test product name:" + p[check].getProductName());
	                        	ar[i].setProductName(p[check].getProductName());
	                        	break productCode;
	                        }
	                    }
	                    System.out.println("Mã hàng này không tồn tại. Vui lòng nhập mã hàng khác!!!");
	                    continue productCode;
                    }
                    int amount=0;
                    amount:
        				while(true) {
		        			amount = 0;
		        			try {
		        				System.out.print("\nSố lượng:");
		        				amount = sc.nextInt();
		        			}
		        			catch(InputMismatchException ex){
			                    System.out.print("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
			                    sc.nextLine();
			                }
		        			for(int k=0; k<p.length; k++) {
		        				if(ar[i].getProductCode().equals(p[k].getProductCode())) {
				        			if(amount > p[k].getAmount()) {
				        				System.out.println("Số lượng sản phẩm không đủ. Sản phẩm chỉ còn:" + p[k].getAmount());
				        				continue amount;	
				        			}
				        			p[k].setAmount(p[k].getAmount()-amount);
		        				}
		        			}
		        			break amount;
        				}	
                    ar[i].setAmount(amount);
                    // price 
                    for(int pr =0 ; pr< p.length; pr++) {
                    	if(ar[i].getProductCode().equals(p[pr].getProductCode())) {
                    		ar[i].setPrice(p[pr].getPrice());
                    	}
                    }
                    count++;
                    sc.nextLine();
                }
                System.out.println("Tạo phiếu bán hàng thành công");
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                //sc.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                //sc.nextLine();
            }
        }
    }
    public void creatBill(String x) {
    	int i = bills.length;
    	bills = Arrays.copyOf(bills, bills.length+1);
    	bills[i] = new Bill(String.valueOf(i+1), x, "2", 0, 0);
    }
    public void inputBillDetails(){
        while(true){
            System.out.print("\nNhập số lượng phiếu xuất: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                ar=new BillDetails[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nPHIẾU XUẤT THỨ "+(i+1));
                    ar[i]=new BillDetails();
                    String billCode, productCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu:");
                    billCode=sc.nextLine();
                    System.out.print("\nMã hàng:");
                    productCode=sc.nextLine();
                    for(int j=0;j<i;j++){
                        if(billCode.equals(ar[j].getBillCode()) && productCode.equals(ar[j].getProductCode())){
                            System.out.println("\n>>Mã phiếu xuất và mã hàng này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    ar[i].setBillCode(billCode);
                    ar[i].setProductCode(productCode);
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
                    ar[i].setAmount(amount);
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
                    ar[i].setPrice(price);
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
    	System.out.println("o========================================================================================================================================================o");
        System.out.println("|                                                              DANH SÁCH CHI TIẾT PHIẾU XUẤT                                                             |");
        System.out.println("|========================================================================================================================================================|");
        System.out.println("| Mã phiếu |  Mã khách hàng  |  Tên khách hàng   |    Ngày xuất    |   Mã hàng   |   Tên sản phẩm   |  Số lượng  |     Giá bán     |      Thành tiền     |"); 
        System.out.println("|========================================================================================================================================================|");
        for(int i=0; i<ar.length;i++)
        {
            System.out.format("|%6s    | ", ar[i].getBillCode());
            System.out.format("%8s        | ", ar[i].getCustomerCode());
            System.out.format("%10s        | ", ar[i].getName());
            System.out.format("%12s    | ", ar[i].getBillDate());
            System.out.format("%6s      | ", ar[i].getProductCode());
            System.out.format("%15s  | ", ar[i].getProductName());
            System.out.format("%6s     | ", ar[i].getAmount());
            System.out.format("%9s       | ", ar[i].getPrice());
            System.out.format("%15s     | \n", ar[i].getTotal());
        }
    	System.out.println("o========================================================================================================================================================o");
    }
    public void add(){
        while(true){
            System.out.print("\nNhập số lượng chi tiết phiếu xuất cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=ar.length;
                n=ar.length+k;
                ar = Arrays.copyOf(ar, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nPHIẾU XUẤT THỨ "+count);
                    ar[i]=new BillDetails();
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu:");
                    String billCode = sc.nextLine();
                    ar[i].setBillCode(billCode);
                    System.out.print("\nMã hàng:"); // mã hàng phải trùng khớp ở bên kia 
                    String productCode = sc.nextLine();
                    ar[i].setProductCode(productCode);
                    for(int j=0;j<oldLength;j++){
                        if(billCode.equals(ar[j].getBillCode()) && productCode.equals(ar[j].getProductCode())){
                            System.out.println("\n>>Bộ mã phiếu xuất và mã hàng này đã tồn tại. Mời bạn nhập lại."); // ở đây là chi tiết phiếu xuất nên cùng 1 phiếu có thể mua được nhiều mặt hàng
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
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
                    ar[i].setAmount(amount);
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
                    ar[i].setPrice(price);
                    sc.nextLine();
                    count++;
                }
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                //sc.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                //sc.nextLine();
            }
        }
    }
    public void removeDetails(int m){
        for(int i=m;i<ar.length-1;i++)
        	ar[i]=ar[i+1];
        ar = Arrays.copyOf(ar, ar.length-1);
    }
    public void remove(){
        boolean isExisted = false;
        int m;
        nhapma:
        while(true){
        System.out.print("\nNhập ngày cần xóa:");
        String date=sc.nextLine();
        System.out.print("\n Nhập mã phiếu xuất cần xóa");
        String code= sc.nextLine();
        int i=0; 
        for(i=0;i<ar.length;i++){
            if(date.equals(ar[i].getBillDate()) && code.equals(ar[i].getBillCode())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Ngày xuất hoặc mã hóa đơn không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<ar.length-1;i++)
        	ar[i]=ar[i+1];
        ar = Arrays.copyOf(ar,ar.length-1);
        
    }
    public void edit(){
        boolean isExisted = false;
        String billCode, billDate;
        nhapma:
        while(true){
        System.out.println("\nNhập bộ mã phiếu xuất ngày xuất cần sửa");
        System.out.print("\nMã phiếu xuất:"); 
        billCode=sc.nextLine();
        System.out.print("\nNgày xuất:");
        billDate=sc.nextLine(); 
        for(int i=0;i<ar.length;i++){
            if(billCode.equals(ar[i].getBillCode()) && billDate.equals(ar[i].getBillDate())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã phiếu xuất và mã hàng này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Ngày xuất");
        System.out.println("2-Sản phẩm");
        System.out.println("3-Số lượng");
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
        for(int i=0;i<ar.length;i++){
            if(billCode.equals(ar[i].getBillCode()) && billDate.equals(ar[i].getBillDate())){
                switch(t){
                    case 1:{
                    	String newBillDate=null;
                        checkDate:
                        	while(true){
        	                	System.out.print("\nNhập vào ngày xuất mới:");
        	                	newBillDate = sc.nextLine();
        	                	if(!Tester.day(newBillDate)) {
        	                		System.out.println("\n>>Ngày xuất hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                                    continue checkDate;
        	                	}
        	                	for(int check = 0; check < bills.length; check++) {
        	                		if(newBillDate.equals(bills[check].getBillDate())) {
        	                			ar[i].setBillDate(newBillDate);
        	                			break checkDate;
        	                		}
        	                	}
        	                	int k = bills.length;
        	                	bills = Arrays.copyOf(bills, bills.length+1);
        	                    bills[k] = new Bill(String.valueOf(k+1), newBillDate, "2", i, i);
//        	                	ar[i].setBillDate(newBillDate);
        	                	break checkDate;
                        	}
                    	ar[i].setBillDate(newBillDate);
                    	System.out.println("Thay đổi thành công");
                        break loop;  
                    }   
                     case 2:{
                            String productCode;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã hàng mới:");
                                productCode=sc.nextLine();
                                // trả về số lượng
                                for(int l=0;l<p.length;l++) {
                                	if(ar[i].getProductCode().equals(p[l].getProductCode())) {
                                		p[l].setAmount(p[l].getAmount()+ar[i].getAmount());
                                	}
                                	if(productCode.equals(p[l].getProductCode())) {
                                		ar[i].setProductCode(productCode);
                                		ar[i].setProductName(p[l].getProductName());
                                		ar[i].setPrice(p[l].getPrice());
                                		ar[i].setTotal(ar[i].getPrice()*ar[i].getAmount());
                                		p[l].setAmount(p[l].getAmount()-ar[i].getAmount());
                                		break nhapma;
                                	}	
                                }
                                System.out.println("Mã sản phẩm không tồn tại. Vui lòng nhập mã sản phẩm khác");
                                continue nhapma;
                            }
                        	System.out.println("Thay đổi thành công");
                            break loop;
                        }
                    case 3:{
                        	int x=0;
                            sl:
                            while(true){                                           
                                System.out.print("\nNhập vào số lượng mới:");                
                                try{
                                    x=sc.nextInt();
                                    break sl;
                                }
                                catch(InputMismatchException ex){
                                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                                    sc.nextLine();
                                }                           
                            }
//                            ar[i].setAmount(x);
                            for(int k=0;k<p.length;k++) {
                            	if(ar[i].getProductCode().equals(p[k].getProductCode())) {
                            		if(p[k].getAmount()==0 || p[k].getAmount()<x) {
                            			System.out.println("Sản phẩm này đã hết hàng hoặc không đủ số lượng. Vui lòng chọn sản phẩm khác");
                            			break loop;
                            		}
                            		p[k].setAmount(p[k].getAmount()+ar[i].getAmount());// Trả về số lượng sản phẩm trước đó
                            		p[k].setAmount(p[k].getAmount()-x);
                            	}
                            }
                            ar[i].setAmount(x);
                        	System.out.println("Thay đổi thành công");
                            break loop;
                    }
                    default: {System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 4.");
                            continue loop;
                    }
                }
            }
        }
    }
}        
    public void search(){
        boolean isExisted = false;
        String a;
        nhapma:
        while(true){            
            System.out.print("\nNhập ngày phiếu xuất cần tìm:");
            a=sc.nextLine();      
        	System.out.println("o========================================================================================================================================================o");
            System.out.println("|                                                   THÔNG TIN CHI TIẾT PHIẾU XUẤT CẦN TÌM                                                                |");
            System.out.println("|========================================================================================================================================================|");
            System.out.println("| Mã phiếu |  Mã khách hàng  |  Tên khách hàng   |    Ngày xuất    |   Mã hàng   |   Tên sản phẩm   |  Số lượng  |     Giá bán     |      Thành tiền     |"); 
            System.out.println("|========================================================================================================================================================|"); 
            for(int i=0;i<ar.length;i++){
                if(a.equals(ar[i].getBillDate())){                    
                    System.out.format("|%6s    | ", ar[i].getBillCode());
                    System.out.format("%8s        | ", ar[i].getCustomerCode());
                    System.out.format("%10s        | ", ar[i].getName());
                    System.out.format("%12s    | ", ar[i].getBillDate());
                    System.out.format("%6s      | ", ar[i].getProductCode());
                    System.out.format("%15s  | ", ar[i].getProductName());
                    System.out.format("%6s     | ", ar[i].getAmount());
                    System.out.format("%9s       | ", ar[i].getPrice());
                    System.out.format("%15s     | \n", (int)ar[i].getTotal());
                    isExisted = true;
                }
            }
            if(!isExisted ){
                System.out.println("\n>>Mã phiếu xuất này không tồn tại. Mời bạn nhập lại.");
                continue nhapma;
            }
        	System.out.println("o========================================================================================================================================================o");
            break nhapma;
        }
    }
    public boolean fileNull(){
        if(ar.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("billDetailList.txt"));
            
            oos.writeObject(ar);
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
            fis = new FileInputStream("billDetailList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(BillDetails.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             ar=(BillDetails[])ois.readObject();
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
    public void readFileCustomer() {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("customerList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayCustomer.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             c=(Customer[])ois.readObject();
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
    public void writeFileBill() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("billList.txt"));
            
            oos.writeObject(bills);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            oos.close();
            
        }
    }
    
    public void readFileBill() {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("billList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayBill.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             bills=(Bill[])ois.readObject();
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

