package Producer;
import Dungchung.Mang;
import Supplier.Supplier;
import Producer.Producer;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import Account.Account;
import Dungchung.Tester;
public class ArrayProducer implements Mang{
	private Producer [] pr;
    
     private  int n,k;
     Scanner sc=new Scanner(System.in);
    public void creatData() {
    	pr = new Producer[3];
    	int i=0;
    	pr[i]=new Producer("1", "Xiaomi", "China");
    	pr[i+1]=new Producer("2", "Realme", "China");
    	pr[i+2]=new Producer("3", "Iphone", "America");
    }
    public void inputProducer(){
        while(true){
            System.out.print("\nNhập số lượng nhà sản xuất: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                int count =1;
                pr=new Producer[n];
                for(int i=0;i<pr.length;i++){
                    System.out.println("\nNHÀ SẢN XUẤT THỨ "+count);
                    pr[i]=new Producer();
                    String producerCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã nhà sản xuất:");
                    producerCode=sc.nextLine();
                    for(int j=0;j<pr.length;j++){
                        if(producerCode.equals(pr[j].getProducerCode())){
                            System.out.println("\n>>Mã nhà sản xuất này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    pr[i].setProducerCode(producerCode);
                    System.out.print("\nTên nhà sản xuất:");
                    pr[i].setProducerName(sc.nextLine());
                    System.out.print("\nQuốc gia:");
                    pr[i].setCountry(sc.nextLine());
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
    public void add(){
        while(true){
            System.out.print("\nNhập số lượng nhà sản xuất cần thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=pr.length;
                n=pr.length+k;
                pr = Arrays.copyOf(pr, n);
                int count=1;
                for(int i=oldLength;i<n;i++){            
                    System.out.println("\nNHÀ SẢN XUẤT THỨ "+count);
                    pr[i]=new Producer();
                    String producerCode;
                    nhapma:
                    while(true){
                    System.out.print("\nMã nhà sản xuất:");
                    producerCode=sc.nextLine();
                    for(int j=0;j<oldLength;j++){
                        if(producerCode.equals(pr[j].getProducerCode())){
                            System.out.println("\n>>Mã nhà sản xuất này đã tồn tại. Mời bạn nhập lại.");
                            continue nhapma;
                        }
                    }
                    break nhapma;
                    }
                    pr[i].setProducerCode(producerCode);
                    System.out.print("\nTên nhà sản xuất:");
                    pr[i].setProducerName(sc.nextLine());
                    System.out.print("\nQuốc gia:");
                    pr[i].setCountry(sc.nextLine());            
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
    public void display(){
    	System.out.println("o=========================================================o");
        System.out.println("|                  DANH SÁCH NHÀ SẢN XUẤT                 |");
        System.out.println("|=========================================================|");
        System.out.println("| Mã nhà sản xuất |    Tên nhà sản xuất   |    Quốc gia   |");
        System.out.println("|=========================================================|");
        
        for(int i=0;i<pr.length;i++){
            System.out.format("|%9s        | ", pr[i].getProducerCode());
            System.out.format("%12s          | ", pr[i].getProducerName());
            System.out.format("%8s      | \n", pr[i].getCountry());
        }
    	System.out.println("o=========================================================o");
    }
    
    public void remove(){
    	boolean isExisted = false;
        int m;
        nhapma:
        while(true){
        System.out.print("\nNhập mã nhà sản xuất cần xóa:");
        String producerCode=sc.nextLine();
        for(int i=0;i<pr.length;i++){
            if(producerCode.equals(pr[i].getProducerCode())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã nhà sản xuất này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<pr.length-1;i++)
        	pr[i]=pr[i+1];
        pr = Arrays.copyOf(pr,pr.length-1);
    }
    public void edit(){
        boolean isExisted = false;
        String producerCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã nhà sản xuất cần sửa:");
        producerCode=sc.nextLine();
        for(int i=0;i<pr.length;i++){
            if(producerCode.equals(pr[i].getProducerCode())){
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã nhà sản xuất này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Mã nhà sản xuất");
        System.out.println("2-Mã tên nhà sản xuất");
        System.out.println("3-Quốc gia");
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
            for(int i=0;i<pr.length;i++){
                if(producerCode.equals(pr[i].getProducerCode())){
                    switch(t){
                        case 1:{
                            String newProducerCode;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã nhà sản xuất mới:");
                                newProducerCode=sc.nextLine();
                                for(int j=0;j<pr.length;j++){
                                    if(newProducerCode.equals(pr[j].getProducerCode())){
                                        System.out.println("\n>>Mã nhà sản xuất này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            pr[i].setProducerCode(newProducerCode);
                            break loop;
                        }
                        case 2:{
                            System.out.print("\nNhập vào tên nhà sản xuất mới:");
                            String newProducerName=sc.nextLine();
                            pr[i].setProducerName(newProducerName);
                            break loop;
                        }
                        case 3:{
                            System.out.print("\nNhập vào quốc gia mới:");
                            String newCountry=sc.nextLine();
                            pr[i].setCountry(newCountry);
                            break loop;
                        }
                        
                        default: {
                            System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 3.");
                            continue loop;
                        }
                    }
                }
            }
        }
    }
    public void search(){
        boolean isExisted = false;
        String producerCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã nhà sản xuất cần tìm:");
        producerCode=sc.nextLine();
        for(int i=0;i<pr.length;i++){
            if(producerCode.equals(pr[i].getProducerCode())){
            	System.out.println("o=========================================================o");
                System.out.println("|                   NHÀ SẢN XUẤT CẦN TÌM                  |");
                System.out.println("|=========================================================|");
                System.out.println("| Mã nhà sản xuất |    Tên nhà sản xuất   |    Quốc gia   |");
                System.out.println("|=========================================================|");
                
                System.out.format("|%9s        | ", pr[i].getProducerCode());
                System.out.format("%12s          | ", pr[i].getProducerName());
                System.out.format("%8s      | \n", pr[i].getCountry());
            	System.out.println("o=========================================================o");
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã nhà sản xuất này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public boolean fileNull(){
        if(pr.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("producerList.txt"));
            
            oos.writeObject(pr);
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
            fis = new FileInputStream("producerList.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MangSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Không tìm thấy đường dẫn.");
        }
        ObjectInputStream ois = null;
         try {
             ois = new ObjectInputStream(fis);
         } catch (IOException ex) {
             Logger.getLogger(ArrayProducer.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             pr=(Producer[])ois.readObject();
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