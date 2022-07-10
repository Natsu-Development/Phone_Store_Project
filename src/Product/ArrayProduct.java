package Product;
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
import Dungchung.Mang;
import Product.Xiaomi;
import Product.Iphone;
import Product.Realme;
import Product.Samsung;
public class ArrayProduct implements Mang{
	public Product[] p;
    private  int n,k;
    public static Scanner sc=new Scanner(System.in);
    public void creatData() {
    	int i=0;
    	p = new Product[3];
    	p[i] = new Product("1", "redmiNote8", "Xiaomi", "China", 10, 3900000, 4000000);
    	p[i+1] = new Product("2", "iphone 8", "Iphone", "America", 10, 7900000, 8200000);
    	p[i+2] = new Product("3", "Samsung note 7", "Samsung", "China", 10, 6900000, 7500000);
    }
    public ArrayProduct(){
        
    }
    public void inputProduct(){
        while(true){
        System.out.print("\nNhập số lượng hàng hóa: ");
        try{
            n=sc.nextInt(); 
            p = new Product[n];
            int count = 1;
            for(int i=0;i<n;i++){
                System.out.println("SẢN PHẨM THỨ "+count);
                System.out.println("___________________________________________");
                System.out.println("\nBạn muốn chọn hãng nào?");
                System.out.println("1-Xiaomi");
                System.out.println("2-Iphone");
                System.out.println("3-Samsung");
                System.out.println("4-Realme");
                System.out.println("___________________________________________");
                choice:
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
                switch (t){
                case 1:{
                    p[i] = new Xiaomi();
                    String productCode;
                    input:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode())){
                                System.out.println("\n>>Mã hàng này đã tồn tại. Mời bạn nhập lại.");
                                continue input;
                            }
                        }
                        break input;
                    }
                    p[i].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[i].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nMã nhà cung cấp:");
                    p[i].setSupplierName(sc.nextLine());
                    int x=0;
                    amount:
                    while(true){                                           
                        System.out.print("\nSố lượng:");                
                        try{
                            x=sc.nextInt();
                            break amount;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setAmount(x);
                    int importPrice=0;
                    importPrice:
                    while(true){                                           
                        System.out.print("\nGiá nhập:");                
                        try{
                            importPrice=sc.nextInt();
                            break importPrice;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setImportPrice(importPrice);;
                    int price=0;
                    price:
                    while(true){                                           
                        System.out.print("\nGiá bán:");                
                        try{
                            price=sc.nextInt();
                            break price;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setPrice(price);
                    sc.nextLine();
                    ///////////
                    break choice;
                }
                case 2:{
                    p[i] = new Iphone();
                    String productCode;
                    input:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode())){
                                System.out.println("\n>>Mã hàng này đã tồn tại. Mời bạn nhập lại.");
                                continue input;
                            }
                        }
                        break input;
                    }
                    p[i].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[i].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nMã nhà cung cấp:");
                    p[i].setSupplierName(sc.nextLine());
                    int x=0;
                    amount:
                    while(true){                                           
                        System.out.print("\nSố lượng:");                
                        try{
                            x=sc.nextInt();
                            break amount;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setAmount(x);
                    int importPrice=0;
                    importPrice:
                    while(true){                                           
                        System.out.print("\nGiá nhập:");                
                        try{
                            importPrice=sc.nextInt();
                            break importPrice;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setImportPrice(importPrice);;
                    int price=0;
                    price:
                    while(true){                                           
                        System.out.print("\nGiá bán:");                
                        try{
                            price=sc.nextInt();
                            break price;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setPrice(price);
                    sc.nextLine();
                    ///////////
                    break choice;
                }
                case 3:{
                    p[i] = new Samsung();
                    String productCode;
                    input:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode())){
                                System.out.println("\n>>Mã hàng này đã tồn tại. Mời bạn nhập lại.");
                                continue input;
                            }
                        }
                        break input;
                    }
                    p[i].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[i].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nMã nhà cung cấp:");
                    p[i].setSupplierName(sc.nextLine());
                    int x=0;
                    amount:
                    while(true){                                           
                        System.out.print("\nSố lượng:");                
                        try{
                            x=sc.nextInt();
                            break amount;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setAmount(x);
                    int importPrice=0;
                    importPrice:
                    while(true){                                           
                        System.out.print("\nGiá nhập:");                
                        try{
                            importPrice=sc.nextInt();
                            break importPrice;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setImportPrice(importPrice);;
                    int price=0;
                    price:
                    while(true){                                           
                        System.out.print("\nGiá bán:");                
                        try{
                            price=sc.nextInt();
                            break price;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setPrice(price);
                    sc.nextLine();
                    ///////////
                    break choice;
                }
                case 4:{
                    p[i] = new Realme();
                    String productCode;
                    input:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode())){
                                System.out.println("\n>>Mã hàng này đã tồn tại. Mời bạn nhập lại.");
                                continue input;
                            }
                        }
                        break input;
                    }
                    p[i].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[i].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nMã nhà cung cấp:");
                    p[i].setSupplierName(sc.nextLine());
                    int x=0;
                    amount:
                    while(true){                                           
                        System.out.print("\nSố lượng:");                
                        try{
                            x=sc.nextInt();
                            break amount;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setAmount(x);
                    int importPrice=0;
                    importPrice:
                    while(true){                                           
                        System.out.print("\nGiá nhập:");                
                        try{
                            importPrice=sc.nextInt();
                            break importPrice;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setImportPrice(importPrice);;
                    int price=0;
                    price:
                    while(true){                                           
                        System.out.print("\nGiá bán:");                
                        try{
                            price=sc.nextInt();
                            break price;
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                            sc.nextLine();
                        }                           
                    }
                    p[i].setPrice(price);
                    sc.nextLine();
                    ///////////
                    break choice;
                }
                }
                }
                }
            }
                catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Vui lòng nhập lại.");
            }
        catch(InputMismatchException ex){
            System.out.println("\n>>Lựa chọn phải là số! Vui lòng nhập lại.");
        }
        }
    }
    public void display(){
    	System.out.println("o==================================================================================================================o");
        System.out.println("|                                              DANH SÁCH HÀNG HÓA                                                  |");
        System.out.println("|==================================================================================================================|");
        System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng |     Giá nhập     |     Giá bán     |"); 
        System.out.println("|==================================================================================================================|");
        for(int i=0; i<p.length;i++)
        {
        	System.out.format("|%6s     | ", p[i].getProductCode());
            System.out.format("%14s     |", p[i].getProductName());
            System.out.format("%9s |", p[i].getProducerName());
            System.out.format("%13s         | ", p[i].getSupplierName());
            System.out.format("%5s    | ", p[i].getAmount());
            System.out.format("%11s      | ", p[i].getImportPrice());
            System.out.format("%11s     |\n", p[i].getPrice());
        }
        System.out.println("o==================================================================================================================o");
    }
    public void add(){
    	int flag = 0;
        while(true){
            System.out.print("\nNhập số lượng hàng cần thêm vào:");
            try{
            k=Integer.parseInt(sc.nextLine());
            int giatricu=p.length;
            n=p.length+k;
            p = Arrays.copyOf(p, n);
            int count=1;
            set:
            for(int i=giatricu;i<n;i++){ 
            	int check = i;
            	if(flag==1) {
            		check=i-1;
            	}
                System.out.println("HÀNG HÓA THỨ "+ count);
                System.out.println("___________________________________________");
                System.out.println("\nBạn muốn chọn loại hàng hóa nào?");
                System.out.println("1-Xiaomi");
                System.out.println("2-Iphone");
                System.out.println("3-Samsung");
                System.out.println("4-Realme");
                System.out.println("___________________________________________");
                choice:
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
                switch (t){
                case 1:{
                    p[check]=new Xiaomi();
                    String productCode;
                    nhapma:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode()) && p[j].getProducerName().equals("Xiaomi")){
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
                            	flag=1;
                            	p[j].setAmount(p[j].getAmount()+ amount);
                            	p = Arrays.copyOf(p, p.length-1);
                            	count++;
                                continue set;
                            }
                            if(productCode.equals(p[j].getProductCode())) {
                            	System.out.println("Sản phẩm đã bị trùng mã. Vui lòng nhập lại");
                            	continue nhapma;
                            }
                        }
                        break nhapma;
                    }
                    p[i].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[i].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nTên nhà cung cấp:");
                    p[i].setSupplierName(sc.nextLine());
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
                    p[i].setAmount(amount);
                    int price=0, importPrice=0;
                    check:
                    	while(true) {
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
		                    p[i].setImportPrice(importPrice);
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
		                    p[i].setPrice(price);
		                    if(importPrice>price) {
		                    	System.out.println(">>>Giá bán phải lớn hơn giá nhập. Vui lòng nhập lại");
		                    	continue check;
		                    }
		                    break check;
		                    }
                    sc.nextLine();
                    break choice;
                }
                case 2:{
                    p[check]=new Iphone();
                    String productCode;
                    nhapma:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode()) && p[j].getProducerName().equals("Iphone")){
                            	int amount = 0;
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
                            	flag=1;
                            	p = Arrays.copyOf(p, p.length-1);
                            	p[j].setAmount(p[j].getAmount()+ amount);
                            	count++;
                                continue set;
                            }
                            if(productCode.equals(p[j].getProductCode())) {
                            	System.out.println("Sản phẩm đã bị trùng mã. Vui lòng nhập lại");
                            	continue nhapma;
                            }
                        }
                        break nhapma;
                    }
                    p[check].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[check].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nTên nhà cung cấp:");
                    p[check].setSupplierName(sc.nextLine());
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
                    p[check].setAmount(amount);
                    int price=0, importPrice=0;
                    check:
                    	while(true) {
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
		                    p[i].setImportPrice(importPrice);
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
		                    p[i].setPrice(price);
		                    if(importPrice>price) {
		                    	System.out.println(">>>Giá bán phải lớn hơn giá nhập. Vui lòng nhập lại");
		                    	continue check;
		                    }
		                    break check;
		                    }
                    sc.nextLine();
                    break choice;
                }
                case 3:{
                    p[check]=new Samsung();
                    String productCode;
                    nhapma:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode()) && p[j].getProducerName().equals("Samsung")){
                            	int amount = 0;
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
                            	flag=1;
                            	p = Arrays.copyOf(p, p.length-1);
                            	p[j].setAmount(p[j].getAmount()+ amount);
                            	count++;
                                continue set;
                            }
                            if(productCode.equals(p[j].getProductCode())) {
                            	System.out.println("Sản phẩm đã bị trùng mã. Vui lòng nhập lại");
                            	continue nhapma;
                            }
                        }
                        break nhapma;
                    }
                    p[check].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[check].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nTên nhà cung cấp:");
                    p[check].setSupplierName(sc.nextLine());
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
                    p[check].setAmount(amount);
                    int price=0, importPrice=0;
                    check:
                    	while(true) {
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
		                    p[i].setImportPrice(importPrice);
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
		                    p[i].setPrice(price);
		                    if(importPrice>price) {
		                    	System.out.println(">>>Giá bán phải lớn hơn giá nhập. Vui lòng nhập lại");
		                    	continue check;
		                    }
		                    break check;
		                    }
                    sc.nextLine();
                    break choice;
                }
                case 4:{
                	p[check]=new Realme();
                    String productCode;
                    nhapma:
                    while(true){
                        System.out.print("\nMã hàng hóa: ");
                        productCode=sc.nextLine();
                        for(int j=0;j<i;j++){
                            if(productCode.equals(p[j].getProductCode()) && p[j].getProducerName().equals("Realme")){
                            	int amount = 0;
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
                            	flag=1;
                            	p = Arrays.copyOf(p, p.length-1);
                            	p[j].setAmount(p[j].getAmount()+ amount);
                            	count++;
                                continue set;
                            }
                            if(productCode.equals(p[j].getProductCode())) {
                            	System.out.println("Sản phẩm đã bị trùng mã. Vui lòng nhập lại");
                            	continue nhapma;
                            }
                        }
                        break nhapma;
                    }
                    p[check].setProductCode(productCode);
                    System.out.print("\nTên hàng:");
                    p[check].setProductName(sc.nextLine());
                    p[i].setProducerName(p[i].getProducerName());
                    System.out.print("\nTên nhà cung cấp:");
                    p[check].setSupplierName(sc.nextLine());
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
                    p[check].setAmount(amount);
                    int price=0, importPrice=0;
                    check:
                    	while(true) {
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
		                    p[i].setImportPrice(importPrice);
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
		                    p[i].setPrice(price);
		                    if(importPrice>price) {
		                    	System.out.println(">>>Giá bán phải lớn hơn giá nhập. Vui lòng nhập lại");
		                    	continue check;
		                    }
		                    break check;
		                    }
                    sc.nextLine();
                    break choice;
                }
                default:{
                    System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 3.\n");
                    continue choice;
                }
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
        System.out.print("\nNhập mã hàng cần xóa:");
        String productCode=sc.nextLine();
        for(int i=0;i<p.length;i++){
            if(productCode.equals(p[i].getProductCode())){
                m=i;
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã hàng này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<p.length-1;i++)
        	p[i]=p[i+1];
        p = Arrays.copyOf(p,p.length-1);
    }    
    public void edit(){
        boolean isExisted = false;
        String productCode;
        input:
        while(true){
        System.out.print("\nNhập mã hàng cần sửa:");
        productCode=sc.nextLine();
        for(int i=0;i<p.length;i++){
            if(productCode.equals(p[i].getProductCode())){
                isExisted = true;
                break input;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã hàng này không tồn tại. Mời bạn nhập lại.");
            continue input;
        }
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");
        System.out.println("1-Mã hàng");
        System.out.println("2-Tên hàng");
        System.out.println("3-Mã nhà sản xuất");
        System.out.println("4-Mã nhà cung cấp");
        System.out.println("5-Số lượng");
        System.out.println("6-Giá nhập");
        System.out.println("7-Giá bán");
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
            for(int i=0;i<p.length;i++){
                if(productCode.equals(p[i].getProductCode())){
                    switch(t){
                        case 1:{
                            String newProductCode;
                            input:
                            while(true){
                                System.out.print("\nNhập vào mã hàng mới:");
                                newProductCode=sc.nextLine();
                                for(int j=0;j<p.length;j++){
                                    if(newProductCode.equals(p[j].getProductCode())){
                                        System.out.println("\n>>Mã hàng này đã tồn tại. Mời bạn nhập lại.");
                                        continue input;
                                    }
                                }
                                break input;
                            }
                            p[i].setProductCode(newProductCode);
                            break loop;
                        }
                        case 2:{
                            System.out.print("\nNhập vào tên hàng mới:");
                            String newProductName=sc.nextLine();
                            p[i].setProductName(newProductName);
                            break loop;
                        }
                        case 3:{
                            System.out.print("\nNhập vào mã nhà sản xuất mới:");
                            String newProducerName=sc.nextLine();
                            p[i].setProducerName(newProducerName);
                            break loop;
                        }
                        case 4:{
                            System.out.print("\nNhập vào mã nhà cung cấp mới:");
                            String newSupplierName=sc.nextLine();
                            p[i].setSupplierName(newSupplierName);
                            break loop;
                        }
                        case 5:{
                            int newAmount=0;
                            amount:
                            while(true){                                           
                                System.out.print("\nNhập vào số lượng mới:");                
                                try{
                                    newAmount=sc.nextInt();
                                    break amount;
                                }
                                catch(InputMismatchException ex){
                                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                                    sc.nextLine();
                                }                           
                            }
                            p[i].setAmount(newAmount);
                            break loop;
                        }
                        case 6:{
                            int newImportPrice=0;
                            importPrice:
                            while(true){                                           
                                System.out.print("\nNhập vào giá nhập mới:");                
                                try{
                                    newImportPrice=sc.nextInt();
                                    break importPrice;
                                }
                                catch(InputMismatchException ex){
                                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                                    sc.nextLine();
                                }                           
                            }
                            p[i].setPrice(newImportPrice);
                            break loop;
                        }
                        case 7:{
                            int newPrice=0;
                            price:
                            while(true){                                           
                                System.out.print("\nNhập vào giá bán mới:");                
                                try{
                                    newPrice=sc.nextInt();
                                    break price;
                                }
                                catch(InputMismatchException ex){
                                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
                                    sc.nextLine();
                                }                           
                            }
                            p[i].setPrice(newPrice);
                            break loop;
                        }
                        default: {
                            System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 7.");
                            continue loop;
                        }
                    }
                }
            }
        }
    }
    public void search(){
        boolean isExisted = false;
        String productCode;
        input:
        while(true){
        System.out.print("\nNhập mã hàng cần tìm:");
        productCode=sc.nextLine();
        for(int i=0;i<p.length;i++){
            if(productCode.equals(p[i].getProductCode())){
            	System.out.println("o==================================================================================================================o");
                System.out.println("|                                           THÔNG TIN HÀNG HÓA CẦN TÌM                                             |");
                System.out.println("|==================================================================================================================|");
                System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng |     Giá nhập     |     Giá bán     |"); 
                System.out.println("|==================================================================================================================|");
                System.out.format("|%6s     | ", p[i].getProductCode());
                System.out.format("%14s     |", p[i].getProductName());
                System.out.format("%9s |", p[i].getProducerName());
                System.out.format("%13s         | ", p[i].getSupplierName());
                System.out.format("%5s    | ", p[i].getAmount());
                System.out.format("%11s      | ", p[i].getImportPrice());
                System.out.format("%11s     |\n", p[i].getPrice());
                System.out.println("o==================================================================================================================o");
                isExisted = true;
                break input;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã hàng này không tồn tại. Mời bạn nhập lại.");
            continue input;
        }
        }
    }
    public boolean fileNull(){
        if(p.length==0) return true;
        return false;
    }
    public void writeFile() throws IOException {
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
    
    public void readFile() {
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
    
}