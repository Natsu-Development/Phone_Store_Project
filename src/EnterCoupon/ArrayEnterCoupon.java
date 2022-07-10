package EnterCoupon;
import Dungchung.Mang;
import Dungchung.Tester;
import Employee.Employee;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ArrayEnterCoupon implements Mang{
     private EnterCoupon [] en;
     private EnterCouponDetails[] ecd;
     private Employee[] e;
     private  int n,k;
     Scanner sc=new Scanner(System.in);
     public void creatData() {
    	 en = new EnterCoupon[3];
    	 int i=0;
    	 en[i] = new EnterCoupon("1", "26/10/2020", "2", i);
    	 en[i+1] = new EnterCoupon("2", "27/10/2020", "2", i);
    	 en[i+2] = new EnterCoupon("3", "28/10/2020", "2", i);
     }
     public void creatDataDetails() {
    	 ecd = new EnterCouponDetails[3];
    	 int i=0;
    	 ecd[i] = new EnterCouponDetails("1", "1", "Xiaomi", "China", "redmiNote8", "26/10/2020" , 10, 3900000, 4000000, i);
    	 ecd[i+1] = new EnterCouponDetails("2", "2", "Iphone", "America", "iphone 8", "26/10/2020" , 10, 7900000, 8200000, i);
    	 ecd[i+2] = new EnterCouponDetails("3", "3", "Samsung", "China", "Samsung note 7", "26/10/2020" , 10, 6900000, 7500000, i);
     }
    public void inputEnterCoupon(){
        while(true){
            System.out.print("\nNhập số lượng phiếu nhập: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                en=new EnterCoupon[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nPHIẾU NHẬP THỨ "+(i+1));
                    en[i]=new EnterCoupon();
                    String enterCouponCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu: ");
                    enterCouponCode=sc.nextLine();
                    for(int j=0;j<i;j++){
                        if(enterCouponCode.equals(en[j].getCouponCode())){
                            System.out.println("\n>>Mã phiếu nhập này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    en[i].setCouponCode(enterCouponCode);
                    String date=null;
                    date:
                    while(true){                                           
                        System.out.print("\nNgày nhập hàng:");                
                        date=sc.nextLine();
                        if(!Tester.day(date)){
                            System.out.println("\n>>Ngày nhập hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue date;
                        }
                        else {
                            en[i].setDate(date);
                            break date;
                        }                        
                    }
                    System.out.print("\nMã nhân viên:");
                    en[i].setEmployeeCode(sc.nextLine());
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
    public void display()
    {
    	System.out.println("o=================================================================o");
        System.out.println("|                       DANH SÁCH PHIẾU NHẬP                      |");
        System.out.println("|=================================================================|");
        System.out.println("| Mã phiếu |      Ngày       | Mã nhân viên |      Tổng tiền      |"); 
        System.out.println("|=================================================================|");
        for(int i=0; i<en.length;i++){
            System.out.format("|%5s     | ", en[i].getCouponCode());
            System.out.format("%13s   | ", en[i].getDate());
            System.out.format("%7s      |", en[i].getEmployeeCode());
            System.out.format("%11s          |\n", totalMoney(en[i].getDate()));
        }           
        System.out.println("o=================================================================o");
    }
    public int totalMoney(String x){
        int s=0;
        for (int i=0;i<ecd.length;i++) {
            if (x.equals(ecd[i].getEnterCouponDate())) {
                s += ecd[i].getTotal();
            }
        }
        return s;
    }
    public void add(){
        while(true){
            System.out.print("\nNhập số lượng phiếu nhập cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=en.length;
                n=en.length+k;
                en = Arrays.copyOf(en, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nPHIẾU NHẬP THỨ "+count);
                    en[i]=new EnterCoupon();
                    String a;
                    nhapma:
                    while(true){
                    System.out.print("\nMã phiếu:");
                    a=sc.nextLine();
                    for(int j=0;j<oldLength;j++){
                        if(a.equals(en[j].getCouponCode())){
                            System.out.println("\n>>Mã phiếu nhập này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    en[i].setCouponCode(a);
                    String x=null;
                    date:
                    while(true){                                           
                        System.out.print("\nNgày nhập hàng:");                
                        x=sc.nextLine();
                        if(!Tester.day(x)){
                            System.out.println("\n>>Ngày nhập hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue date;
                        }
                        else {
                            en[i].setDate(x);
                            break date;
                        }                        
                    }
                    System.out.print("\nMã nhân viên:");
                    en[i].setEmployeeCode(sc.nextLine());
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
    public String removeEnterCoupon(){
        boolean isExisted = false;
        int m;
        String enterCouponDate;
        nhapma:
        while(true){
        System.out.print("\nNhập vào ngày cần xóa:");
        enterCouponDate=sc.nextLine();
        for(int i=0;i<en.length;i++){
            if(enterCouponDate.equals(en[i].getDate())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Ngày của phiếu nhập này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<en.length-1;i++)
        	en[i]=en[i+1];
        en = Arrays.copyOf(en,en.length-1);
        return enterCouponDate;
    }
    public void edit(){
        boolean isExisted = false;
        String ma;
        nhapma:
        while(true){
        System.out.print("\nNhập mã phiếu nhập cần sửa:");
        ma=sc.nextLine();
        for(int i=0;i<en.length;i++){
            if(ma.equals(en[i].getCouponCode())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã phiếu nhập này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Mã phiếu nhập");
        System.out.println("2-Ngày nhập hàng");
        System.out.println("3-Mã nhân viên");
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
            for(int i=0;i<en.length;i++){
                if(ma.equals(en[i].getCouponCode())){
                    switch(t){
                        case 1:{
                            String x;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã phiếu nhập mới:");
                                x=sc.nextLine();
                                for(int j=0;j<en.length;j++){
                                    if(x.equals(en[j].getCouponCode())){
                                        System.out.println("\n>>Mã phiếu nhập này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            en[i].setCouponCode(x);
                            break loop;
                        }
                        case 2:{
                            String x=null;
                            date:
                            while(true){                                           
                                System.out.print("\nNhập vào ngày nhập hàng mới:");                
                                x=sc.nextLine();
                                if(!Tester.day(x)){
                                    System.out.println("\n>>Ngày nhập hàng phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                                    continue date;
                                }
                                else {
                                    en[i].setDate(x);
                                    break date;
                                }                        
                            }
                            break loop;
                        }
                        case 3:{
                        	check:
                        	while(true) {
	                            System.out.print("\nNhập vào mã nhân viên mới:");
	                            String x=sc.nextLine();
	                            for(int em=0; em<e.length; em++) {
	                            	if(x.equals(e[em].getEmployeeCode())==false) {
	                            		System.out.println("Mã nhân viên này không tồn tại. Vui lòng nhập mã nhân viên khác");
	                            		continue check;
	                            	}
	                            	else {
	                            		break check;
	                            	}
	                            }
	                            en[i].setEmployeeCode(x);
	                            break loop;
                        	}
                        }
                        default: {
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
        String date;
        nhapma:
        while(true){
        System.out.print("\nNhập ngày của phiếu nhập cần tìm:");
        date=sc.nextLine();
        for(int i=0;i<en.length;i++){
            if(date.equals(en[i].getDate())){
            	System.out.println("o=================================================================o");
                System.out.println("|                  THÔNG TIN PHIẾU NHẬP CẦN TÌM                   |");
                System.out.println("|=================================================================|");
                System.out.println("| Mã phiếu |      Ngày       | Mã nhân viên |      Tổng tiền      |"); 
                System.out.println("|=================================================================|");
                System.out.format("|%5s     | ", en[i].getCouponCode());
                System.out.format("%13s   | ", en[i].getDate());
                System.out.format("%7s      |", en[i].getEmployeeCode());
                System.out.format("%11s          |\n", totalMoney(en[i].getCouponCode()));           
                System.out.println("o=================================================================o");                
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã phiếu nhập này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public boolean fileNull(){
        if(en.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
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
    
    public void readFile() {
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
     public void readFileEnterCouponDetail() {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("enterCouponDetailList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(EnterCoupon.class.getName()).log(Level.SEVERE, null, ex);
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
     public void readFileEmployee()
     {
         //-------------------B1. Tao doi tuong luong va lien ket luong-----------
         FileInputStream fis = null;
         try {
             fis = new FileInputStream("employeeList.txt");
         } catch (FileNotFoundException ex) {
             //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Không tìm thấy đường dẫn.");
         }
         ObjectInputStream ois = null;
          try {
              ois = new ObjectInputStream(fis);
          } catch (IOException ex) {
              Logger.getLogger(ArrayList.class.getName()).log(Level.SEVERE, null, ex);
          }
            
          try {
              e=(Employee[])ois.readObject();
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
     public void writeFileEmployee()throws IOException {
         ObjectOutputStream oos = null;
        try {
             oos = new ObjectOutputStream(new FileOutputStream("employeeList.txt"));
             
             oos.writeObject(e);
         } catch (IOException ex) {
             System.out.println(ex.toString());
         } finally {
             oos.close();
             
         }
     }
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	} 
}