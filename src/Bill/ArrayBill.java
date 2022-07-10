package Bill;

import Dungchung.Mang;
import Dungchung.Tester;
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
import java.util.Scanner;
public class ArrayBill implements Mang{
     private Bill [] bills;
     private BillDetails [] billDetails;
     private  int n,k;
     Scanner sc=new Scanner(System.in);
     public void creatData() {
    	 bills = new Bill[3];
    	 int i=0;
    	 bills[i] = new Bill("1", "26/11/2020", "2", i, i);
    	 bills[i+1] = new Bill("2", "27/11/2020", "2", i, i);
    	 bills[i+2] = new Bill("3", "28/11/2020", "2", i, i);
     }
     public void creatDataDetails() {
    	billDetails = new BillDetails[11];
     	int i=0;
     	billDetails[i] = new BillDetails("Thúy", "0924714551", "tp hcm", "1", "3", "26/11/2020", "2", "1","redmiNote8", 1, 2300000, i);
     	billDetails[i+1] = new BillDetails("Hậu", "0924714552", "tp hcm", "2", "4", "26/11/2020", "2", "2","iphone 8", 2, 2300000, i);
     	billDetails[i+2] = new BillDetails("Tiên", "0924714551", "tp hcm", "3", "5", "26/11/2020", "3", "3","Samsung note 7", 3, 2300000, i);
     	billDetails[i+3] = new BillDetails("Thúy", "0924714551", "tp hcm", "4", "3", "26/11/2020", "2", "2","iphone 8", 2, 2300000, i);
     	billDetails[i+4] = new BillDetails("Hậu", "0924714551", "tp hcm", "1", "4", "27/11/2020", "2", "3","Samsung note 7", 1, 2300000, i);
     	billDetails[i+5] = new BillDetails("Tiên", "0924714551", "tp hcm", "2", "5", "27/11/2020", "3", "2","iphone 8", 2, 2300000, i);
     	billDetails[i+6] = new BillDetails("Tiên", "0924714551", "tp hcm", "3", "5", "27/11/2020", "2", "3","Samsung note 7", 2, 2300000, i);
     	billDetails[i+7] = new BillDetails("Hậu", "0924714551", "tp hcm", "1", "4", "28/11/2020", "2", "1","redmiNote8", 2, 2300000, i);
     	billDetails[i+8] = new BillDetails("Hậu", "0924714551", "tp hcm", "2", "4", "28/11/2020", "3", "2","iphone 8", 2, 2300000, i);
     	billDetails[i+9] = new BillDetails("Hậu", "0924714551", "tp hcm", "3", "4", "28/11/2020", "4", "3","Samsung note 7", 2, 2300000, i);
//     	
     }
     public void economicStatisticsh() {
    	 System.out.println("o========================================================================================================================================================o");
         System.out.println("|                                                                  THỐNG KÊ KINH DOANH                                                                   |");
         System.out.println("|========================================================================================================================================================|");
         System.out.println("| Mã phiếu |    Ngày xuất    |   Mã nhân viên   |  Tổng số lượng sản phẩm  |      Tổng tiền     |   Sản phẩm bán chạy nhất   |   Sản phẩm bán chậm nhất  |"); 
         System.out.println("|========================================================================================================================================================|");
         for(int i=0; i<bills.length;i++)
         {
             System.out.format("|%6s    | ", bills[i].getBillCode());
             System.out.format("%13s   | ", bills[i].getBillDate());
             System.out.format("%8s         | ", bills[i].getEmployeeCode());
             System.out.format("%12s             | ", totalAmount(bills[i].getBillDate()));
             System.out.format("%13s      |", totalMoney(bills[i].getBillDate()));    
             System.out.format("%20s        |", bestSeller(bills[i].getBillDate()));
             System.out.format("%19s        |\n", slowSeller(bills[i].getBillDate()));
         }
         System.out.println("o========================================================================================================================================================o");
     }
     public String bestSeller(String x) {
     	String bestSeller = null;
     	int max = 0, key = 0;
     		int amount =0;
     		for(int j=0; j<billDetails.length; j++) { 
     			if(x.equals(billDetails[j].getBillDate())) {
      				amount = billDetails[j].getAmount();
      				if(j==billDetails.length-1) {
 						if(max>amount) {
 							max=amount;
 							key=j;
 						}
 					}	
     			for(int i=j+1; i<billDetails.length;i++) {
     					if(j==billDetails.length-1) {
     						System.out.println("checkkkkkkkkkkkkkk");
     						i=j;
     					}
	     				if(billDetails[i].getProductCode().equals(billDetails[j].getProductCode()) && x.equals(billDetails[i].getBillDate()) &&i!=j) {
	     					amount += billDetails[i].getAmount();
	     				}
	     				if(max<amount && i==billDetails.length-1) {
	     					max=amount;
	     					key = j;
	     				}
	     				}
     			bestSeller = billDetails[key].getProductName();
     			}
     		}
     	return bestSeller;
     }
     public String slowSeller(String x) {
      	String slowSeller = null;
      	int min = 999999, key = 0, position=-1;
      		int amount =0;
      		for(int j=0; j<billDetails.length; j++) {
      			if(x.equals(billDetails[j].getBillDate()) && position!=j) {
       				amount = billDetails[j].getAmount();
      			for(int i=j+1; i<billDetails.length;i++) {
 	     				if(billDetails[i].getProductCode().equals(billDetails[j].getProductCode()) && x.equals(billDetails[i].getBillDate())) {
 	     					amount += billDetails[i].getAmount();
 	     					position = i;
 	     				}
 	     				if(min>amount && i==billDetails.length-1) {
 	     					min=amount;
 	     					key = j;
 	     				}
 	     				}
      			slowSeller = billDetails[key].getProductName();
      			}
      		}
      	return slowSeller;
      }
    public void inputBill(){
        while(true){
            System.out.print("\nNhập số lượng phiếu xuất: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                bills = new Bill[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nPHIẾU XUẤT THỨ "+(i+1));
                    bills[i]=new Bill();
                    String billCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu:");
                    billCode=sc.nextLine();
                    for(int j=0;j<i;j++){
                        if(billCode.equals(bills[j].getBillCode())){
                            System.out.println("\n>>Mã phiếu xuất này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    bills[i].setBillCode(billCode);
                    System.out.print("\nMã khách hàng:");
                    bills[i].setCustomerCode(sc.nextLine());
                    String date=null;
                    date:
                    while(true){                                           
                        System.out.print("\nNgày xuất hàng:");                
                        date=sc.nextLine();
                        if(!Tester.day(date)){
                            System.out.println("\n>>Ngày xuất hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue date;
                        }
                        else {
                            bills[i].setBillDate(date);
                            break date;
                        }                        
                    }
                    System.out.print("\nMã nhân viên:");
                    bills[i].setCustomerCode(sc.nextLine());                    
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
    	System.out.println("o====================================================================================o");
        System.out.println("|                                 DANH SÁCH PHIẾU XUẤT                               |");
        System.out.println("|====================================================================================|");
        System.out.println("| Mã phiếu  |     Ngày xuất     |  Mã nhân viên  |   Số Lượng   |     Tổng tiền      |"); 
        System.out.println("|====================================================================================|");
        for(int i=0; i<bills.length;i++){
            System.out.format("|%6s     | ", bills[i].getBillCode());
            System.out.format("%13s     | ", bills[i].getBillDate());
            System.out.format("%7s        | ", bills[i].getEmployeeCode());
            System.out.format("%6s       | ", totalAmount(bills[i].getBillDate()));
            System.out.format("%13s      |\n", totalMoney(bills[i].getBillDate()));
        }           
        System.out.println("o====================================================================================o");
    }
    public int totalMoney(String x){
        int s=0;
        for (int i=0;i<billDetails.length;i++) {
            if (x.equals(billDetails[i].getBillDate())) {
                s += billDetails[i].getTotal();
            }
        }
        return s;
    }
    public int totalAmount(String x) {
    	int amount = 0;
    	for(int i=0; i<billDetails.length; i++) {
    		if(x.equals(billDetails[i].getBillDate())){
    			amount += billDetails[i].getAmount();
    		}
    	}
    	return amount;
    	
    }
    public void add(){
        while(true){
            System.out.print("\nNhập số lượng phiếu xuất cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=bills.length;
                n=bills.length+k;
                bills = Arrays.copyOf(bills, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nPHIẾU XUẤT THỨ "+count);
                    bills[i]=new Bill();
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu:");
                    String billCode=sc.nextLine();
                    bills[i].setBillCode(billCode);
                    for(int j=0;j<i;j++){
                        if(billCode.equals(bills[j].getBillCode())){
                            System.out.println("\n>>Mã phiếu xuất này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    System.out.print("\nMã khách hàng:");
                    bills[i].setCustomerCode(sc.nextLine());
                    String date=null;
                    date:
                    while(true){                                           
                        System.out.print("\nNgày xuất hàng:");                
                        date=sc.nextLine();
                        if(!Tester.day(date)){
                            System.out.println("\n>>Ngày xuất hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue date;
                        }
                        else {
                            bills[i].setBillDate(date);
                            break date;
                        }                        
                    }
                    System.out.print("\nMã nhân viên:");
                    bills[i].setCustomerCode(sc.nextLine());
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
    public String removeBill(){
        boolean isExisted = false;
        int m;
        String billDate;
        nhapma:
        while(true){
        System.out.print("\nNhập vào ngày cần xóa:");
        billDate=sc.nextLine();
        for(int i=0;i<bills.length;i++){
            if(billDate.equals(bills[i].getBillDate())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Ngày của phiếu xuất này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<bills.length-1;i++)
        	bills[i]=bills[i+1];
        bills = Arrays.copyOf(bills,bills.length-1);
        return billDate;
    }
    public void edit(){
        boolean isExisted = false;
        String billCode;
        nhapma:
        while(true){
        System.out.print("Nhập mã phiếu xuất cần sửa:");
        billCode=sc.nextLine();        
        for(int i=0;i<bills.length;i++){
            if(billCode.equals(bills[i].getBillCode())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã phiếu xuất này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");        
        System.out.println("1-Mã phiếu xuất");
        System.out.println("2-Mã khách hàng");
        System.out.println("3-Ngày xuất hàng");
        System.out.println("4-Mã nhân viên");
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
        for(int i=0;i<bills.length;i++){
            if(billCode.equals(bills[i].getBillCode())){
                switch(t){
                    case 1:{
                        String newBillCode;
                        nhapma:
                        while(true){
                            System.out.print("Nhập vào mã phiếu xuất mới:");
                            newBillCode=sc.nextLine();
                            for(int j=0;j<bills.length;j++){
                                if(newBillCode.equals(bills[j].getBillCode())){
                                    System.out.println("\n>>Mã phiếu xuất này đã tồn tại. Mời bạn nhập lại.");
                                    continue nhapma;
                                }
                            }
                            break nhapma;
                        }
                        bills[i].setBillCode(newBillCode);
                        break loop;
                    }
                    case 2:{
                        System.out.print("\nNhập vào mã khách hàng mới:");
                        String newCustomerCode=sc.nextLine();
                        bills[i].setCustomerCode(newCustomerCode);
                        break loop;
                    }
                    case 3:{
                        String date=null;
                        date:
                        while(true){                                           
                            System.out.print("\nNgày xuất hàng:");                
                            date=sc.nextLine();
                            if(!Tester.day(date)){
                                System.out.println("\n>>Ngày xuất hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                                continue date;
                            }
                            else {
                                bills[i].setBillDate(date);
                                break date;
                            }                        
                        }
                        break loop;
                    }
                    case 4:{
                        System.out.print("\nNhập vào mã nhân viên mới:");
                        String newEmployeeCode=sc.nextLine();
                        bills[i].setEmployeeCode(newEmployeeCode);
                        break loop;
                    }
                    default: {System.out.println("\n>>Không hợp lệ. Mời bạn nhậ lại giá trị từ 1 đến 4.");
                              continue loop;                    
                    }
                }
            }
        }
    }
}        
    public void search(){
        boolean isExisted = false;
        String billDate;
        nhapma:
        while(true){        
        System.out.print("\nNhập ngày của phiếu xuất cần tìm:"); 
        billDate=sc.nextLine();     
        for(int i=0;i<bills.length;i++){
            if(billDate.equals(bills[i].getBillDate())){
            	System.out.println("o====================================================================================o");
                System.out.println("|                          THÔNG TIN PHIẾU XUẤT CẦN TÌM                              |");
                System.out.println("|====================================================================================|");
                System.out.println("| Mã phiếu  |     Ngày xuất     |  Mã nhân viên  |   Số Lượng   |     Tổng tiền      |"); 
                System.out.println("|====================================================================================|");
                System.out.format("|%6s     | ", bills[i].getBillCode());
                System.out.format("%13s     | ", bills[i].getBillDate());
                System.out.format("%7s        | ", bills[i].getEmployeeCode());
                System.out.format("%6s       | ", totalAmount(bills[i].getBillDate()));
                System.out.format("%13s      |\n", totalMoney(bills[i].getBillDate()));
            	System.out.println("o====================================================================================o");
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã phiếu xuất này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public boolean fileNull(){
        if(bills.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
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
    
    public void readFile() {
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
    public void readFileBillDetails() {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("billDetailList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayBill.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             billDetails=(BillDetails[])ois.readObject();
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
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
    
}