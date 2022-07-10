package Users;
import Dungchung.Mang;
import Dungchung.Tester;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ArrayCustomer implements Mang{
     private Customer [] c;
   
     private  int n,k;
     Scanner sc=new Scanner(System.in);
    public ArrayCustomer(){
        
    }
    public void creatData() {
    	c = new Customer[3];
    	int i=0;
    	c[i] = new Customer("Thúy", "0919543870", "Tp Hồ Chí Minh", "3");
    	c[i+1] = new Customer("Hậu", "0919785468", "Tp Hồ Chí Minh", "4");
    	c[i+2] = new Customer("Tiên", "01202304081", "Tp Hồ Chí Minh", "5");
    }
    public void inputCustomer(){
        while(true){
            System.out.print("\nNhập số lượng khách hàng: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                c=new Customer[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nKHÁCH HÀNG THỨ "+(i+1));
                    c[i]=new Customer();
                    String customerCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã khách hàng:");
                    customerCode=sc.nextLine();

                    for(int j=0;j<i;j++){
                        if(customerCode.equals(c[j].getCustomerCode())){
                            System.out.println("\n>>Mã khách hàng này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    c[i].setCustomerCode(customerCode);
                    System.out.print("\nTên khách hàng:");
                    c[i].setName(sc.nextLine());
                    System.out.print("\nĐịa chỉ:");
                    c[i].setAddress(sc.nextLine());
                    String numberPhone=null;                    
                    sdt:
                    while(true){                                           
                        System.out.print("\nSố điện thoại:");                
                        numberPhone=sc.nextLine();
                        if(!Tester.numberPhone(numberPhone)){
                            System.out.println("\n>>Số điện thoại phải có 10 chữ số và không được chứa kí tự. Mời bạn nhập lại.");
                            continue sdt;
                        }
                        else {
                            c[i].setNumberPhone(numberPhone);
                            break sdt;
                        }                        
                    }
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
    	System.out.println("o===============================================================================o");
        System.out.println("|                              DANH SÁCH KHÁCH HÀNG                             |");
        System.out.println("|===============================================================================|");
        System.out.println("|  Mã khách hàng  |    Tên khách hàng     |     Địa chỉ     |   Số điện thoại   |");
        System.out.println("|===============================================================================|");
        for(int i=0; i<c.length;i++)
        {
           c[i].display();
        }
        System.out.println("o===============================================================================o");
    }
    public void add(){
         while(true){
            System.out.print("\nNhập số lượng khách hàng cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=c.length;
                n=c.length+k;
                c = Arrays.copyOf(c, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nKHÁCH HÀNG THỨ "+ count);
                    c[i]=new Customer();
                    String customerCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã khách hàng:");
                    customerCode=sc.nextLine();
                    for(int j=0;j<oldLength;j++){
                        if(customerCode.equals(c[j].getCustomerCode())){
                            System.out.println("\n>>Mã khách hàng này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    c[i].setCustomerCode(customerCode);
                    System.out.print("\nTên khách hàng:");
                    c[i].setName(sc.nextLine());
                    System.out.print("\nĐịa chỉ:");
                    c[i].setAddress(sc.nextLine());
                    String numberPhone=null;                    
                    sdt:
                    while(true){                                           
                        System.out.print("\nSố điện thoại:");                
                        numberPhone=sc.nextLine();
                        if(!Tester.numberPhone(numberPhone)){
                            System.out.println("\n>>Số điện thoại phải có 10 chữ số và không được chứa kí tự. Mời bạn nhập lại.");
                            continue sdt;
                        }
                        else {
                            c[i].setNumberPhone(numberPhone);
                            break sdt;
                        }                        
                    }            
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
    public void remove(){
        boolean isExisted = false;
        int m;
        nhapma:
        while(true){
        System.out.print("\nNhập mã khách hàng cần xóa:");
        String customerCode=sc.nextLine();
        for(int i=0;i<c.length;i++){
            if(customerCode.equals(c[i].getCustomerCode())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã khách hàng này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<c.length-1;i++)
        	c[i]=c[i+1];
        c = Arrays.copyOf(c, c.length-1);
        
    }
    public void edit(){
        boolean isExisted = false;
        String customerCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã khách hàng cần sửa:");
        customerCode=sc.nextLine();
        for(int i=0;i<c.length;i++){
            if(customerCode.equals(c[i].getCustomerCode())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã khách hàng này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");        
        System.out.println("1-Mã khách hàng");
        System.out.println("2-Tên khách hàng");
        System.out.println("3-Địa chỉ");
        System.out.println("4-Số điện thoại");
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
            for(int i=0;i<c.length;i++){
                if(customerCode.equals(c[i].getCustomerCode())){
                    switch(t){
                        case 1:{
                            String newCustomerCode;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã khách hàng mới:");
                                newCustomerCode=sc.nextLine();
                                for(int j=0;j<c.length;j++){
                                    if(newCustomerCode.equals(c[j].getCustomerCode())){
                                        System.out.println("\n>>Mã khách hàng này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            c[i].setCustomerCode(newCustomerCode);
                            break loop;
                        }
                        case 2:{
                            System.out.print("\nNhập vào tên khách hàng mới:");
                            String newName=sc.nextLine();
                            c[i].setName(newName);
                            break loop;
                        }
                        case 3:{
                            System.out.print("\nNhập vào địa chỉ mới:");
                            String x=sc.nextLine();
                            c[i].setAddress(x);
                            break loop;
                        }
                        case 4:{
                            String newNumberPhone=null;                    
                            sdt:
                            while(true){                                           
                                System.out.print("\nNhập vào số điện thoại mới:");                
                                newNumberPhone=sc.nextLine();
                                if(!Tester.numberPhone(newNumberPhone)){
                                    System.out.println("\n>>Số điện thoại phải có 10 chữ số và không được chứa kí tự. Mời bạn nhập lại.");
                                    continue sdt;
                                }
                                else {
                                    c[i].setNumberPhone(newNumberPhone);
                                    break sdt;
                                }                        
                            }
                            break loop;
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
        String customerCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã khách hàng cần tìm:");
        customerCode=sc.nextLine();
        for(int i=0;i<c.length;i++){
            if(customerCode.equals(c[i].getCustomerCode())){
            	System.out.println("o===============================================================================o");
                System.out.println("|                        THÔNG TIN KHÁCH HÀNG CẦN TÌM                           |");
                System.out.println("|===============================================================================|");
                System.out.println("|  Mã khách hàng  |    Tên khách hàng     |     Địa chỉ     |   Số điện thoại   |");
                System.out.println("|===============================================================================|");
                c[i].display();
                System.out.println("o===============================================================================o");
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã khách hàng này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public boolean fileNull(){
        if(c.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("customerList.txt"));
            
            oos.writeObject(c);
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
}