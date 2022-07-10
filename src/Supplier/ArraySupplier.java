package Supplier;


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
import java.util.Scanner;
public class ArraySupplier implements Mang{
     private Supplier [] s;
     
     private  int n,k;
     Scanner sc=new Scanner(System.in);
    public ArraySupplier(){
        
    }
    public void creatData() {
    	s = new Supplier[3];
    	int i=0;
    	s[i] = new Supplier("China", "11112222", "Bắc Kinh", "1");
    	s[i+1] = new Supplier("America", "7358937", "New York", "2");
    	s[i+2] = new Supplier("Japan", "837498297", "Tokyo", "3");
    }
    public void inputSupplier(){
        while(true){
            System.out.print("\nNhập số lượng nhà cung cấp: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                s=new Supplier[n];
                for(int i=0;i<n;i++){
                    System.out.println("\nNHÀ CUNG CẤP THỨ "+(i+1));
                    s[i]=new Supplier();
                    String supplierCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã nhà cung cấp:");
                    supplierCode=sc.nextLine();

                    for(int j=0;j<i;j++){
                        if(supplierCode.equals(s[j].getSupplierCode())){
                            System.out.println("\n>>Mã nhà cung cấp này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    s[i].setSupplierCode(supplierCode);
                    System.out.print("\nTên nhà cung cấp:");
                    s[i].setSupplierName(sc.nextLine());
                    System.out.print("\nĐịa chỉ:");
                    s[i].setAddress(sc.nextLine());
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
                            s[i].setNumberPhone(numberPhone);
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
    	System.out.println("o===================================================================================o");
        System.out.println("|                               DANH SÁCH NHÀ CUNG CẤP                              |");
        System.out.println("|===================================================================================|");
        System.out.println("|  Mã nhà cung cấp  |    Tên nhà cung cấp     |     Địa chỉ     |   Số điện thoại   |");
        System.out.println("|===================================================================================|");
        for(int i=0; i<s.length;i++) {
            s[i].xuat();
        }
        System.out.println("o===================================================================================o");
    }
    public void add(){
        while(true){
            System.out.print("\nNhập số lượng nhà cung cấp cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=s.length;
                n=s.length+k;
                s = Arrays.copyOf(s, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nNHÀ CUNG CẤP THỨ "+count);
                    s[i]=new Supplier();
                    String supplierCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã nhà cung cấp:");
                    supplierCode=sc.nextLine();
                    for(int j=0;j<oldLength;j++){
                        if(supplierCode.equals(s[j].getSupplierCode())){
                            System.out.println("\n>>Mã nhà cung cấp này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    s[i].setSupplierCode(supplierCode);
                    System.out.print("\nTên nhà cung cấp:");
                    s[i].setSupplierName(sc.nextLine());
                    System.out.print("\nĐịa chỉ:");
                    s[i].setAddress(sc.nextLine());
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
                            s[i].setNumberPhone(numberPhone);
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
        System.out.print("\nNhập mã nhà cung cấp cần xóa:");
        String supplierCode=sc.nextLine();
        for(int i=0;i<s.length;i++){
            if(supplierCode.equals(s[i].getSupplierCode())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã nhà cung cấp này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<s.length-1;i++)
        	s[i]=s[i+1];
        s = Arrays.copyOf(s,s.length-1);
        
    }
    public void edit(){
        boolean isExisted = false;
        String supplierCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã nhà cung cấp cần sửa:");
        supplierCode=sc.nextLine();
        for(int i=0;i<s.length;i++){
            if(supplierCode.equals(s[i].getSupplierCode())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã nhà cung cấp này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Mã nhà cung cấp");
        System.out.println("2-Tên nhà cung cấp");
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
            sc.nextLine();//dung de bo ki tu xuong dong bi thua truoc khi nhap String
            for(int i=0;i<s.length;i++){
                if(supplierCode.equals(s[i].getSupplierCode())){
                    switch(t){
                        case 1:{
                            String newSupplierCode;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã nhà cung cấp mới:");
                                newSupplierCode=sc.nextLine();
                                for(int j=0;j<s.length;j++){
                                    if(newSupplierCode.equals(s[j].getSupplierCode())){
                                        System.out.println("\n>>Mã nhà cung cấp này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            s[i].setSupplierCode(newSupplierCode);
                            break loop;
                        }
                        case 2:{
                            System.out.print("\nNhập vào tên nhà cung cấp mới:");
                            String newSupplierName=sc.nextLine();
                            s[i].setSupplierName(newSupplierName);
                            break loop;
                        }
                        case 3:{
                            System.out.print("\nNhập vào địa chỉ mới:");
                            String x=sc.nextLine();
                            s[i].setAddress(x);
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
                                    s[i].setNumberPhone(newNumberPhone);
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
        String supplierCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã nhà cung cấp cần tìm:");
        supplierCode=sc.nextLine();
        for(int i=0;i<s.length;i++){
            if(supplierCode.equals(s[i].getSupplierCode())){
            	System.out.println("o===================================================================================o");
                System.out.println("|                        THÔNG TIN NHÀ CUNG CẤP CẦN TÌM                             |");
                System.out.println("|===================================================================================|");
                System.out.println("|  Mã nhà cung cấp  |    Tên nhà cung cấp     |     Địa chỉ     |   Số điện thoại   |");
                System.out.println("|===================================================================================|");
                s[i].xuat();
                System.out.println("o===================================================================================o");
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã nhà cung cấp này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public boolean fileNull(){
        if(s.length==0) return true;
        return false;
    }
    
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("supplierList.txt"));
            
            oos.writeObject(s);
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
            fis = new FileInputStream("supplierList.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArraySupplier.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             s=(Supplier[])ois.readObject();
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