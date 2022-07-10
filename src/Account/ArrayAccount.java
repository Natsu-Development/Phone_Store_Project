package Account;
import Dungchung.Mang;
import Dungchung.Tester;
import Users.ArrayCustomer;
import Users.Customer;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
public class ArrayAccount extends Customer implements Mang{
     private Account [] accounts;
     private Customer [] customers;
     private  int n,k;
     Scanner sc = new Scanner(System.in);
     public ArrayAccount() {
    	 
     }
     public void creatData() {
    	 accounts = new Account[5];
    	 int i=0;
    	 accounts[i] = new Account("cat", "123", String.valueOf(i+1));// admin 
    	 accounts[i+1] = new Account("bao", "456", String.valueOf(i+2)); // employee
    	 // user
    	 accounts[i+2] = new Account("thuy", "789", String.valueOf(i+3));  
    	 accounts[i+3] = new Account("hau", "901", String.valueOf(i+4));
    	 accounts[i+4] = new Account("tien", "06071999", String.valueOf(i+5));
     }
     public void signUp() throws IOException {
    	 int oldLength = accounts.length;
    	 int n = accounts.length + 1;
    	 accounts = Arrays.copyOf(accounts, n);
    	 customers = Arrays.copyOf(customers, n);
    	 String accountName;
    	 for(int i = oldLength; i<n; i++) {
    	 accounts[i] = new Account();
    	 customers[i] = new Customer();
    	 input:
    	 	 while(true) {
    			 System.out.println("Nhập tên tài khoản của bạn:");
    			 accountName = sc.nextLine();
    			 for(int j=0; j<i; j++) {
    				 if(accountName.equals(accounts[j].getAccountName())) {
    					 System.out.println("\n>>Tên tài khoản này đã tồn tại. Mời bạn nhập lại.");
    					 continue input;
    				 }
    			 }
    			 break input;
    		 }
     		System.out.println("Nhập vào mật khẩu của bạn:");
     		String password = sc.nextLine();
     		Object passwordAgain;
			do {
     		System.out.println("Nhập lại mật khẩu của bạn:");
     		passwordAgain = sc.nextLine();
     		}while(!password.equals(passwordAgain));
			System.out.print("\nNhập vào họ và tên của bạn:");
			String name = sc.nextLine();
			String numberPhone;
			sdt:
			while(true){                                           
                 System.out.print("\nNhập vào số điện thoại của bạn:");                
                 numberPhone=sc.nextLine();
                 if(!Tester.numberPhone(numberPhone)){
                     System.out.println("\n>>Số điện thoại phải có 10 chữ số và không được chứa kí tự. Mời bạn nhập lại.");
                     continue sdt;
                 }
                 else {
                     customers[i].setNumberPhone(numberPhone);
                     break sdt;
                 }                        
            }
			System.out.print("\nNhập vào địa chỉ của bạn:");
            String address=sc.nextLine();
            accounts[i].setAccountName(accountName);
            accounts[i].setPassword(password);
            accounts[i].setCustomerCode(String.valueOf(i+1));
            writeFile();
            customers[i].setName(name);
            customers[i].setNumberPhone(numberPhone);
            customers[i].setAddress(address);
            customers[i].setCustomerCode(String.valueOf(i+1));
            writeFileCustomer();
    	 }
     }
     public void inputAccount(){
        while(true){        
        System.out.print("\nNhập số lượng tài khoản:");                
            try{
            	n=Integer.parseInt(sc.nextLine()); 
                accounts=new Account[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nTÀI KHOẢN THỨ "+(i+1));
                    accounts[i]=new Account();
                    String accountName;
                    nhapma:
                    while(true){	
                    System.out.print("\nTên tài khoản: ");
                    accountName=sc.nextLine();
                    sc.nextLine();
                    for(int j=0;j<i;j++){
                        if(accountName.equals(accounts[j].getAccountName())){
                            System.out.println("\n>>Tên tài khoản này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    accounts[i].setAccountName(accountName);
                    System.out.print("\nMật khẩu:");
                    accounts[i].setPassword(sc.nextLine());
                }
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Vui lòng nhập lại.");
                sc.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Vui lòng nhập lại.");
                sc.nextLine();
            }
        }
    }    
    public void display(){
    	System.out.println("o==============================================================o");
        System.out.println("|                      DANH SÁCH TÀI KHOẢN                     |");
        System.out.println("|==============================================================|");
        System.out.println("|  Tên tài khoản  |        Mật khẩu       |    Mã người dùng   |");
        System.out.println("|==============================================================|");
        for(int i=0; i<accounts.length;i++)
        {
            accounts[i].xuat();
        }
        System.out.println("o==============================================================o");
    }
    public void employeeDisplay(){
    	System.out.println("o==============================================================o");
        System.out.println("|                      DANH SÁCH TÀI KHOẢN                     |");
        System.out.println("|==============================================================|");
        System.out.println("|  Tên tài khoản  |        Mật khẩu       |    Mã người dùng   |");
        System.out.println("|==============================================================|");
        for(int i=1; i<accounts.length;i++)
        {
            accounts[i].xuat();
        }
        System.out.println("o==============================================================o");
    }
    public void add(){
        while(true){
            System.out.print("\nNhập số lượng tài khoản cần thêm:");                
            try{
                k=Integer.parseInt(sc.nextLine());  
                int oldLength=accounts.length;
                n=accounts.length+k;
                accounts = Arrays.copyOf(accounts, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nTÀI KHOẢN THỨ "+count);
                    accounts[i]=new Account();
                    String accountName;
                    nhapma:
                    while(true){
                    System.out.print("\nTên tài khoản:");
                    accountName=sc.nextLine();
                    for(int j=0;j<i;j++){ 
                        if(accountName.equals(accounts[j].getAccountName())){
                            System.out.println("\n>>Tên tài khoản này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    accounts[i].setAccountName(accountName);     
                    System.out.print("\nMật khẩu:");
                    accounts[i].setPassword(sc.nextLine());
                    count++;
                }
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
            }
        }            
    }
    
    public void remove(){
        boolean isExisted = false;
        int m;
        nhapma:
        while(true){
        System.out.print("\nNhập tên tài khoản cần xóa:");
        String accountName=sc.nextLine();
        int i=0; 
        for(i=0;i<accounts.length;i++){
        	if(accounts[i].getCustomerCode().equals("0")==false) i++;
            if(accountName.equals(accounts[i].getAccountName())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<accounts.length-1;i++)
        	accounts[i]=accounts[i+1];
        accounts = Arrays.copyOf(accounts,accounts.length-1);
        
    }
    public void employeeRemove(){
        boolean isExisted = false;
        int m;
        nhapma:
        while(true){
        System.out.print("\nNhập tên tài khoản cần xóa:");
        String accountName=sc.nextLine(); 
        for(int i=1;i<accounts.length;i++){
            if(accountName.equals(accounts[i].getAccountName())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<accounts.length-1;i++)
        	accounts[i]=accounts[i+1];
        accounts = Arrays.copyOf(accounts,accounts.length-1);
        
    }
    public void edit(){
        boolean isExisted = false;
        String accountName;
        nhapma:
        while(true){
        System.out.print("\nNhập tên tài khoản cần sửa:");
        accountName=sc.nextLine();
        for(int i=0;i<accounts.length;i++){
            if(accountName.equals(accounts[i].getAccountName())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Tên đăng nhập");        
        System.out.println("2-Mật khẩu");
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
            sc.nextLine();
            for(int i=0;i<accounts.length;i++){
                if(accountName.equals(accounts[i].getAccountName())){
                    switch(t){
                        case 1:{
                            String newAccountName;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào tên tài khoản mới:");
                                newAccountName=sc.nextLine();
                                for(int j=0;j<accounts.length;j++){
                                    if(newAccountName.equals(accounts[j].getAccountName())){
                                        System.out.println("\n>>Tên tài khoản này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            accounts[i].setAccountName(newAccountName);
                            break loop;
                        }                        
                        case 2:{
                            System.out.print("\nNhập vào mật khẩu mới:");
                            String newPassword=sc.nextLine();
                            accounts[i].setPassword(newPassword);
                            break loop;
                        }                        
                        default: {
                            System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị 1 hoặc 2.");
                            continue loop;
                        }
                    }
                }
            }
        }
    }
    public void employeeEdit(){
        boolean isExisted = false;
        String accountName;
        nhapma:
        while(true){
        System.out.print("\nNhập tên tài khoản cần sửa:");
        accountName=sc.nextLine();
        for(int i=1;i<accounts.length;i++){
            if(accountName.equals(accounts[i].getAccountName())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Tên đăng nhập");        
        System.out.println("2-Mật khẩu");
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
            sc.nextLine();
            for(int i=1;i<accounts.length;i++){
                if(accountName.equals(accounts[i].getAccountName())){
                    switch(t){
                        case 1:{
                            String newAccountName;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào tên tài khoản mới:");
                                newAccountName=sc.nextLine();
                                for(int j=0;j<accounts.length;j++){
                                    if(newAccountName.equals(accounts[j].getAccountName())){
                                        System.out.println("\n>>Tên tài khoản này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            accounts[i].setAccountName(newAccountName);
                            break loop;
                        }                        
                        case 2:{
                            System.out.print("\nNhập vào mật khẩu mới:");
                            String newPassword=sc.nextLine();
                            accounts[i].setPassword(newPassword);
                            break loop;
                        }                        
                        default: {
                            System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị 1 hoặc 2.");
                            continue loop;
                        }
                    }
                }
            }
        }
    }
    public void search(){
        boolean isExisted = false;
        String accountName;
        nhapma:
        while(true){
        System.out.print("\nNhập tên tài khoản cần tìm:");
        accountName=sc.nextLine();
        for(int i=0;i<accounts.length;i++){
            if(accountName.equals(accounts[i].getAccountName())){
            	System.out.println("o==============================================================o");
                System.out.println("|                  THÔNG TIN TÀI KHOẢN CẦN TÌM                 |");
                System.out.println("|==============================================================|");
                System.out.println("|  Tên tài khoản  |        Mật khẩu       |    Mã người dùng   |");
                System.out.println("|==============================================================|"); 
                accounts[i].xuat();         
            	System.out.println("o==============================================================o");
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public void employeeSearch(){
        boolean isExisted = false;
        String accountName;
        nhapma:
        while(true){
        System.out.print("\nNhập tên tài khoản cần tìm:");
        accountName=sc.nextLine();
        for(int i=1;i<accounts.length;i++){
            if(accountName.equals(accounts[i].getAccountName())){
            	System.out.println("o==============================================================o");
                System.out.println("|                  THÔNG TIN TÀI KHOẢN CẦN TÌM                 |");
                System.out.println("|==============================================================|");
                System.out.println("|  Tên tài khoản  |        Mật khẩu       |    Mã người dùng   |");
                System.out.println("|==============================================================|"); 
                accounts[i].xuat();         
            	System.out.println("o==============================================================o");
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    // check admin 
    public boolean checkPasswordAdmin(String x){
        if(x.equals(accounts[0].getPassword())) return true;
        return false;
    }
    // check Sale 
    public boolean checkAccountNameSale(String x){
    	if(x.equals(accounts[1].getAccountName())) return true;
    	return false;
    }
    public boolean checkPasswordSale(String x, String y){
    	if(x.equals(accounts[1].getAccountName()) && y.equals(accounts[1].getPassword())) return true;
    	return false;
    }
    // check user
    public boolean checkAccountNameUser(String x){
        for(int i=2;i<accounts.length;i++){
            if(x.equals(accounts[i].getAccountName())) return true;
        }        
        return false;
    }
    public boolean checkPasswordUser(String x,String y){
        for(int i=2;i<accounts.length;i++){
            if(x.equals(accounts[i].getAccountName()) && y.equals(accounts[i].getPassword())) return true;
        }        
        return false;
    }
    // check file NULl
    public boolean fileNull(){
        if(accounts.length==0) return true;
        return false;
    }
    public void user_change_pwd(String x){
        System.out.print("\nNhập vào mật khẩu mới:");
        String newPassword=sc.nextLine();
        for(int i=1;i<accounts.length;i++){
            if(x.equals(accounts[i].getAccountName()))
                accounts[i].setPassword(newPassword);
        }
        System.out.println("\n>>Đổi mật khẩu thành công!");
    }
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("account.txt"));
            
            oos.writeObject(accounts);
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
            fis = new FileInputStream("account.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayAccount.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             accounts=(Account[])ois.readObject();
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
   
    public void writeFileCustomer() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("customerList.txt"));
            
            oos.writeObject(customers);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            oos.close();
            
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
             Logger.getLogger(ArrayAccount.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             customers=(Customer[])ois.readObject();
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