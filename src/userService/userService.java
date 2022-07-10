package userService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Account.Account;
import Account.ArrayAccount;
import Bill.ArrayBill;
import Bill.Bill;
import Bill.BillDetails;
import Dungchung.Abstract;
import Dungchung.Tester;
import Product.Product;  
import Users.Customer;

public class userService extends Abstract{
	
	private static final String NULL = null;
	private Customer [] customerInfo;
	private Product [] productInfo;
	private Cart [] cart;
	private Account [] accounts;
	private BillDetails [] ar;
	private Bill [] bills;
	ArrayAccount aa = new ArrayAccount();
	private int length = 0, n=0, amount = 0;; // Đây là độ dài của cart
    Scanner sc=new Scanner(System.in);
    
    public userService() {
    	
    }
    public String getCustomerCode(String x) {
    	String customerCode=null;
    	for(int i=0;i<accounts.length;i++) {
    		if(x.equals(accounts[i].getAccountName())) {
    			customerCode = accounts[i].getCustomerCode();
    		}
    	}
    	return customerCode;
    }
    public boolean fileNull() {
    	if(cart.length==0) return true;
    	return false;
    }
    public void productDisplay(){
    	System.out.println("o===================================================================================================o");
        System.out.println("|                                    DANH SÁCH HÀNG HÓA                                             |");
        System.out.println("|===================================================================================================|");
        System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng còn |     Giá bán     |"); 
        System.out.println("|===================================================================================================|");
        for(int i=0; i<productInfo.length;i++)
        {
        	System.out.format("|%6s     | ", productInfo[i].getProductCode());
            System.out.format("%14s     |", productInfo[i].getProductName());
            System.out.format("%9s |", productInfo[i].getProducerName());
            System.out.format("%13s         | ", productInfo[i].getSupplierName());
            System.out.format("%6s       | ", productInfo[i].getAmount());
            System.out.format("%11s     |\n", productInfo[i].getPrice());
        }
        System.out.println("o===================================================================================================o");
    }
    
    public void paymentCart(String accountName) throws IOException {
    	int amount =0;
    	payment:
    		while(true) {
    			// Kiểm tra giỏ hàng có hàng hay không
    			if(cart.length==0) {
    				System.out.println("Giỏ hàng của bạn đang trống. Vui lòng kiểm tra lại giỏ hàng của bạn");
    				return;
    			}
    			// hiển thị đơn hàng ra cho người dùng 
    			displayCart();
		    	System.out.println("Nhập vào mã đơn hàng bạn cần thanh toán : ");
		    	String x = sc.nextLine();
		    	for(int i=0; i<cart.length; i++) {
		    		// Kiểm tra đơn này có trong cart hay không
		    		if(x.equals(cart[i].getOrderCode())) {
		    			for(int k=0; k<productInfo.length; k++) {
		    				// Nếu có lấy mã sản phẩm ra
		    				if(cart[i].getProductCode().equals(productInfo[k].getProductCode())) {
		    					amount = cart[i].getAmount();
		    					// Nếu số lượng sản phẩm đã hết thì thông báo cho người dùng
		    					if(productInfo[k].getAmount()==0 || productInfo[k].getAmount()<amount) {
		    						System.out.println("Sản phẩm này đã hết hàng. Bạn vui lòng chọn sản phẩm khác");
		    						break payment;
		    					}
		    					// nếu có thì trừ số lượng của sản phẩm
		    					productInfo[k].setAmount(productInfo[k].getAmount()-cart[i].getAmount());
		    					break;
		    				}
		    			}
		    			// thêm vào billDetails
//						int oldLength = ar.length;
//						ar = Arrays.copyOf(ar, ar.length+1);
	    				setBillCode:
	    					while(true) {
//				    			for(int b = 0 ;b< ar.length; b++) {
//				    						ar[b] = new BillDetails();
//				    				ar[oldLength] = new BillDetails();
				    				// Nếu cùng ngày đặt hàng 
				    						int max = 1;
						    				Date now = new Date();
						    		    	String ft = new SimpleDateFormat("dd/MM/yyyy").format(now);
						    		    	String dayNow = ft.formatted(now);
						    		    	check:
						    		    	for(int bill = 0 ; bill<bills.length; bill++) {
						    				if(bills[bill].getBillDate().equals(dayNow)) {
						    					int oldLength = ar.length;
												ar = Arrays.copyOf(ar, ar.length+1);
												ar[oldLength] = new BillDetails();
						    					ar[oldLength].setBillDate(dayNow);
						    					for(int b = 0 ;b< ar.length; b++) {						    						
						    					// Bắt đầu so sánh lấy billCode so sánh với billCode có trong ngày nếu không có nữa thì set cái tiếp theo
						    					//if(ar[b].getBillCode().equals(ar[b].getBillCode()) && ar[b].getBillDate().equals(ar[b].getBillDate())){
						    					billCodeDetails:
						    					while(true) {
						    						if((ar[b].getBillDate().equals(dayNow))==false) {
						    							System.out.println("Test billDate:" + ar[b].getBillDate());
						    							b++;
						    							continue billCodeDetails;
						    						}
						    						test:
						    						if(b<oldLength) {
							    					if((ar[b].getBillCode()).equals(String.valueOf(max)) && ar[b].getBillDate().equals(dayNow)) {
							    						//max = Integer.parseInt(ar[b].getBillCode());
							    						max++;
							    						b++;
							    						continue billCodeDetails; 
							    					}
							    					}
							    					else
							    						ar[oldLength].setBillCode(String.valueOf(max));
							    						//ar[oldLength].setBillDate(dayNow);
							    						// Lấy thông tin người dùng để gắn cho billDetails
							    						for(int c = 0; c<customerInfo.length; c++) {
							    							for(int k= 0; k<accounts.length; k++) {
							    					    		if(accountName.equals(accounts[k].getAccountName())) {
							    					    			x=accounts[k].getCustomerCode();
							    					    		}	
							    							}
							    							if(x.equals(customerInfo[c].getCustomerCode())){
								    							ar[oldLength].setCustomerCode(customerInfo[c].getCustomerCode());
								    							ar[oldLength].setName(customerInfo[c].getName());
							    							}
							    						}
							    						// Lấy thông tin về đơn hàng để set cho billDetails
							    						ar[oldLength].setProductCode(cart[i].getProductCode());
					    								ar[oldLength].setProductName(cart[i].getProductName());
					    								ar[oldLength].setAmount(cart[i].getAmount());
					    								ar[oldLength].setPrice(cart[i].getPrice());
					    								ar[oldLength].setTotal(cart[i].getAmount() * cart[i].getPrice());
							    						break setBillCode;
						    					}
						    				}
						    				}
				    			}
						        int check = bills.length;
					    		bills = Arrays.copyOf(bills, bills.length+1);
					    		bills[check] = new Bill(String.valueOf(bills.length), dayNow, "2", amount, 0);   
					    		//ar[oldLength].setBillDate(dayNow);
					    		continue setBillCode;
				    			//}
	    					}
		    			// xóa khỏi cart
						 //if(cart[i].getOrderCode().equals(x)) {
			    			 for(int k=i;k<cart.length-1;k++)
			    		        	cart[k]=cart[k+1];
			    		        cart = Arrays.copyOf(cart,cart.length-1);
//						 }
		    		     System.out.println("Thanh toán sản phẩm thành công");
		    		     break payment;
		    			
		    		}
		    	}
	    			System.out.println("Mã đơn hàng này tồn tại. Vui lòng kiểm tra lại đơn hàng của bạn");
	    			continue payment;
    		}	
    	// cho người dùng chọn hàng để thanh toán ư
    	// trừ số lượng trong sản phẩm và xóa sản phẩm đó trong cart và thêm cái đó vào billDetails
    }
    public void searchRequest() {
		System.out.println("___________________________________________");
		System.out.println("Bạn muốn tìm kiếm như thế nào?");
		System.out.println("1- Tìm kiếm theo mã sản phẩm");
		System.out.println("2- Tìm kiếm theo tên sản phẩm");
		System.out.println("3- Tìm kiếm theo hãng");
		System.out.println("4- Tìm kiếm theo khoảng giá");
		System.out.println("___________________________________________");
		sc: while (true) {
			int b = 0;
			System.out.print("Nhập vào sự lựa chọn của bạn:");
			try {
				b = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("\n>>Lựa chọn phải là số!");
			}
			sc.nextLine();
			switch (b) {
			case 1: {
				searchCode();
				break sc;
			}
			case 2: {
				searchName();
				searchCode();
				break sc;
			}
			case 3: {
				searchProducer();
				searchCode();
				break sc;
			}
			case 4: {
				searchPriceRange();
				searchCode();
				break sc;
			}
			default: {
				System.out.println(
						"\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 4.\n");
				continue sc;
			}
			}
		}
    }
    public void searchPriceRange(){
        int flag = 0;
        int beginPrice, endPrice;
        System.out.print("\nNhập vào giá bắt đầu của sản phẩm mà bạn cần tìm:");
        beginPrice=Integer.parseInt(sc.nextLine());     
        System.out.print("\n Nhập vào giá kết thúc của sản phẩm mà bạn cần tìm");
        endPrice=Integer.parseInt(sc.nextLine());
        System.out.println("o===================================================================================================o");
        System.out.println("|                                 THÔNG TIN SẢN PHẨM CẦN TÌM                                        |");
        System.out.println("|===================================================================================================|");
        System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng còn |     Giá bán     |"); 
        System.out.println("|===================================================================================================|"); 
        for(int i=0;i<productInfo.length;i++){
            if(productInfo[i].getPrice()>(beginPrice) && productInfo[i].getPrice()<(endPrice)){ 
            	System.out.format("|%6s     | ", productInfo[i].getProductCode());
                System.out.format("%14s     |", productInfo[i].getProductName());
                System.out.format("%9s |", productInfo[i].getProducerName());
                System.out.format("%13s         | ", productInfo[i].getSupplierName());
                System.out.format("%6s       | ", productInfo[i].getAmount());
                System.out.format("%11s     |\n", productInfo[i].getPrice());
                flag = 1;
            }
        }
        if(flag==0) {
        	System.out.println(">>>>Không có giá của sản phẩm bạn cần tìm");
        	return ;
        }
        System.out.println("o===================================================================================================o");
    }
    public void searchProducer(){
        int flag = 0;
        String producer;
        System.out.print("\nNhập hãng của sản phẩm mà bạn cần tìm:");
        producer=sc.nextLine();                
        System.out.println("o===================================================================================================o");
        System.out.println("|                                 THÔNG TIN SẢN PHẨM CẦN TÌM                                        |");
        System.out.println("|===================================================================================================|");
        System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng còn |     Giá bán     |"); 
        System.out.println("|===================================================================================================|");
        for(int i=0;i<productInfo.length;i++){
            if(producer.equals(productInfo[i].getProducerName())){ 
            	System.out.format("|%6s     | ", productInfo[i].getProductCode());
                System.out.format("%14s     |", productInfo[i].getProductName());
                System.out.format("%9s |", productInfo[i].getProducerName());
                System.out.format("%13s         | ", productInfo[i].getSupplierName());
                System.out.format("%6s       | ", productInfo[i].getAmount());
                System.out.format("%11s     |\n", productInfo[i].getPrice());
                flag = 1;
            }
        }
        if(flag==0) {
        	System.out.println(">>>>Không có hãng bạn cần tìm");
        	return;
        }
        System.out.println("o===================================================================================================o");
    }
    public void searchName(){
        int flag = 0;
        String productName;
        System.out.print("\nNhập tên của sản phẩm mà bạn cần tìm:");
        productName=sc.nextLine();
        System.out.println("o===================================================================================================o");
        System.out.println("|                                 THÔNG TIN SẢN PHẨM CẦN TÌM                                        |");
        System.out.println("|===================================================================================================|");
        System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng còn |     Giá bán     |"); 
        System.out.println("|===================================================================================================|");
        for(int i=0;i<productInfo.length;i++){
            if((productInfo[i].getProductName()).contains(productName)){ 
            	System.out.format("|%6s     | ", productInfo[i].getProductCode());
                System.out.format("%14s     |", productInfo[i].getProductName());
                System.out.format("%9s |", productInfo[i].getProducerName());
                System.out.format("%13s         | ", productInfo[i].getSupplierName());
                System.out.format("%6s       | ", productInfo[i].getAmount());
                System.out.format("%11s     |\n", productInfo[i].getPrice());
                flag = 1;
            }
        }
        if(flag==0) {
        	System.out.println(">>>>Không có sản phẩm bạn cần tìm");
        	return;
        }
        System.out.println("o===================================================================================================o");
    }
    public void searchCode(){
        boolean isExisted = false;
        String productCode;
        input:
        while(true){
        System.out.print("\nNhập mã hàng cần mua:");
        productCode=sc.nextLine();
        System.out.println("o===================================================================================================o");
        System.out.println("|                                 THÔNG TIN SẢN PHẨM CẦN TÌM                                        |");
        System.out.println("|===================================================================================================|");
        System.out.println("|  Mã hàng  |      Tên hàng      |   Hãng   |   Tên nhà cung cấp   | Số lượng còn |     Giá bán     |"); 
        System.out.println("|===================================================================================================|");
        for(int i=0;i<productInfo.length;i++){
            if(productCode.equals(productInfo[i].getProductCode())){
                System.out.format("|%6s     | ", productInfo[i].getProductCode());
                System.out.format("%14s     |", productInfo[i].getProductName());
                System.out.format("%9s |", productInfo[i].getProducerName());
                System.out.format("%13s         | ", productInfo[i].getSupplierName());
                System.out.format("%6s       | ", productInfo[i].getAmount());
                System.out.format("%11s     |\n", productInfo[i].getPrice());
                isExisted = true;
                if(productInfo[i].getAmount()==0) {
                	System.out.println("Sản phẩm này đã hết hàng. Bạn vui lòng chọn sản phẩm khác.");
    				break input;
    			}
                System.out.println("o===================================================================================================o");
                System.out.println("_________________________________________");
                System.out.println("Bạn có muốn mua sản phẩm này hay không ? ");
        		System.out.println("1.Có ");
        		System.out.println("2.Không ");
        		System.out.println("_________________________________________");
        		choise:
        			while(true) {
		        		int choise = 0;
		        		try {
		        			System.out.print("Mời bạn chọn : ");
		        			choise = sc.nextInt();
		        		}
		        		catch(InputMismatchException ex){
		                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
		                    sc.nextLine();
		                }       
		        		switch (choise) {
			        		case 1:{
			        			amount:
			        				while(true) {
					        			amount = 0;
					        			try {
					        				System.out.print("Số lượng sản phẩm bạn muốn mua:");
					        				amount = sc.nextInt();
					        			}
					        			catch(InputMismatchException ex){
						                    System.out.print("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
						                    sc.nextLine();
						                } 
					        			if(amount > productInfo[i].getAmount()) {
					        				System.out.println("Số lượng sản phẩm không đủ. Sản phẩm chỉ còn:" + productInfo[i].getAmount());
					        				continue amount;	
					        			}
					        			//productInfo[i].setAmount(productInfo[i].getAmount()-amount); khi mua hàng sẽ không trừ số lượng sản phẩm chỉ khi tạo hóa đơn mới trừ số lượng sản phẩm 
					        			break amount;
			        				}	
			        		    addCart(productCode); // thêm sản phẩm vào giỏ hàng
			        			System.out.print("Sản phẩm đã được thêm vào giỏ hàng\n");
			        			sc.nextLine();
			        			break input;
			        		}
			        		case 2:
			        			sc.nextLine();
			        			break input;
				            default: {
								System.out.print("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 2.\n");
								continue choise;
							}
		        		}
        			}
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã hàng này không tồn tại. Mời bạn nhập lại.");
            continue input;
        }
        System.out.println("o===================================================================================================o");
        }
    }
    public void addCart(String x) {
		for(int i=0; i<productInfo.length; i++) {
			if(x.equals(productInfo[i].getProductCode())) {  
		        if(length == 0) // nếu ban đầu mảng không có phần tử gọi cấp phát động cho phần tử của mảng
		        	cart = new Cart[length];
				int oldLength=cart.length;
	            n=cart.length+1;// 1 2 
	            cart = Arrays.copyOf(cart, n); // sau đó mảng đc tự động cập nhật bằng arrayCopy
	            for(int k = oldLength; k<n; k++) {
	            	cart[k] = new Cart();
	            	cart[k].setOrderCode(String.valueOf(cart.length));
	            	cart[k].setProductCode(x);
	            	cart[k].setProductName(productInfo[i].getProductName());
	            	cart[k].setAmount(amount);
	            	cart[k].setPrice(productInfo[i].getPrice()*amount);
	            } 
	            length++;
			}
	}
    }
    public void myBillDisplay(String accountName) {
    	String x=null;
    	System.out.println("o====================================================================o");
        System.out.println("|                            HÓA ĐƠN CỦA TÔI                         |");
        System.out.println("|====================================================================|");
        System.out.println("|     Tên hàng     | Số lượng |     Giá bán     |     Thành tiền     |"); 
        System.out.println("|====================================================================|");
    	for(int k=2; k<accounts.length; k++) {
    		if(accountName.equals(accounts[k].getAccountName())) {
    			x=accounts[k].getCustomerCode();
    			for(int i=0; i<ar.length; i++) {
    				if(x.equals(ar[i].getCustomerCode())) {
    		             System.out.format("|%14s    |", ar[i].getProductName());
    		             System.out.format("%6s    | ", ar[i].getAmount());
    		             System.out.format("%11s     |", ar[i].getPrice());
    		             System.out.format("%14d      |\n", (int)ar[i].getTotal());
    				}
    			}
    		}	
    	}
    	System.out.println("o====================================================================o");
    	
    }



public void showInfo(String accountName)
	{
    	String x = null;
		for(int i=0; i<customerInfo.length; i++)
		{
			for(int k=2; k<accounts.length; k++) {
	    		if(accountName.equals(accounts[k].getAccountName())) {
	    			x=accounts[k].getCustomerCode();
	    		}	
	    	}
			if(x.equals(customerInfo[i].getCustomerCode())) {
		        System.out.println("o===============================================================================o");
		        System.out.println("|  Mã khách hàng  |    Tên khách hàng     |     Địa chỉ     |   Số điện thoại   |");
		        System.out.println("|===============================================================================|");
				if(customerInfo[i].getAddress()==NULL || customerInfo[i].getNumberPhone()==NULL ) {
					System.out.println("Thông tin của bạn đang trống. Vui lòng nhập thông tin cá nhân của bạn");
					return ;
				}
				customerInfo[i].display();
		        System.out.println("o===============================================================================o");
				System.out.println("_________________________________________");
				System.out.println("Bạn có muốn thay đổi thông tin tài khoản của bạn không?");
		 		System.out.println("1.Có ");
		 		System.out.println("2.Không ");
		 		System.out.println("_________________________________________");
		 		choice:
		 			while(true) {
			        		int choise = 0;
			        		try {
			        			System.out.print("Mời bạn chọn : ");
			        			choise = sc.nextInt();
			        		}
			        		catch(InputMismatchException ex){
			                    System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
			                    sc.nextLine();
			                }       
			        		switch (choise) {
				        		case 1:{
										System.out.println("_______________________________________");
										System.out.println("\nBạn muốn sửa thông tin nào");        
										System.out.println("1-Địa chỉ");
										System.out.println("2-Số điện thoại");
										System.out.println("_______________________________________");
										loop:
											while(true){
												int t=0;           
												System.out.print("Nhập vào lựa chọn:");                
												try{
													t=sc.nextInt();                   
												}
												catch(InputMismatchException ex){
													System.out.println("\nLựa chọn phải là số!");
												}
												switch(t){
								                    case 1:{
								                         System.out.print("\nNhập vào địa chỉ mới:");
								                         String newAddress=sc.nextLine();
								                         customerInfo[i].setAddress(newAddress);
								                         break loop;
								                    }
								                    case 2:{
								                         String newNumberPhone=null;                    
								                         sdt:
								                         	while(true){                                           
								                         		System.out.print("\nNhập vào số điện thoại mới");                
								                         		newNumberPhone=sc.nextLine();
								                         		if(!Tester.numberPhone(newNumberPhone)){
								                         			System.out.println("\nSố điện thoại phải có 10 chữ số");
								                         			continue sdt;
								                         		}
								                         		else {
								                         			customerInfo[i].setNumberPhone(newNumberPhone);
								                         			break sdt;
								                         		}                        
								                         	}
								                         break loop;
								                    } 
								                    default: {
								                    	System.out.println("\nNhập sai mời bạn nhập lại !!");
								                        continue loop;
								                    }
											    }
											}
				        		}
				        		case 2:{
				        			sc.nextLine();
				        			break choice;
				        		}
			        		}
		 			}
			}
		}
}
    public void writeFile() throws IOException {
        ObjectOutputStream oos = null;
       try {
            oos = new ObjectOutputStream(new FileOutputStream("productList.txt"));
            
            oos.writeObject(productInfo);
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
             Logger.getLogger(ArrayAccount.class.getName()).log(Level.SEVERE, null, ex);
         }
           
         try {
             productInfo=(Product[])ois.readObject();
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
            
            oos.writeObject(customerInfo);
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
             customerInfo=(Customer[])ois.readObject();
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
    
    // Đây là giỏ hàng (xóa, sửa sản phẩm)
    public void removeCart() {
    	boolean isExisted = false;
        int m = 0;
        nhapma:
        while(true){
        if(cart.length==0) {
        	System.out.println("\n>>Giỏ hàng hiện tại của bạn đang trống!!!");
        	return ;
        }
        System.out.print("\nNhập mã sản phẩm cần xóa:");
        String productCode=sc.nextLine();
        for(int i=0; i<cart.length; i++){
            if(productCode.equals(cart[i].getProductCode())){
                m=i;
                isExisted = true;
                for(int p = 0; p<productInfo.length; p++) {
                	if(productCode.equals(productInfo[p].getProductCode())) {
                		productInfo[p].setAmount(productInfo[p].getAmount() + cart[m].getAmount()); // xóa trả số lượng của sản phẩm về cho sản phẩm
                	}
                }
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Tên tài khoản này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<cart.length-1;i++)
        	cart[i]=cart[i+1];
        cart = Arrays.copyOf(cart,cart.length-1);
        System.out.println("Xóa sản phẩm thành công");
    }
    public void editCart(){
        boolean isExisted = false;
        String productCode;
        nhapma:
        while(true){
        if(cart.length==0) {
            System.out.println("\n>>Giỏ hàng hiện tại của bạn đang trống!!!");
            return ;
        }        	
        System.out.print("\nNhập mã sản phẩm cần sửa:");
        productCode=sc.nextLine();
        for(int i=0;i<cart.length;i++){
            if(productCode.equals(cart[i].getProductCode())){
                isExisted = true;
                for(int p = 0; p<productInfo.length; p++) {
            		if(productCode.equals(productInfo[p].getProductCode())) {
                    	amount:
                    		while(true) {
	                            System.out.print("\nNhập vào số lượng mới:");
	                            int newAmount=sc.nextInt();
	                            if(newAmount > cart[i].getAmount() && newAmount > productInfo[p].getAmount()) {
	                            	System.out.println("Sản phẩm của mặt hàng này còn ít hơn số lượng bạn nhập!!!");
	                            	continue amount;
	                            	}
	                            if(newAmount < cart[i].getAmount())
	                            productInfo[p].setAmount(productInfo[p].getAmount() + (cart[i].getAmount() - newAmount));
	                            else
	                            	productInfo[p].setAmount(productInfo[p].getAmount()-(newAmount - cart[i].getAmount()));
	                            cart[i].setAmount(newAmount);
	                            cart[i].setPrice(newAmount * productInfo[p].getPrice());
	                            break amount;
                    		}
            		}
            }
            break nhapma;
        }
        
        if(!isExisted ){
            System.out.println("\n>>Tên sản phẩm này không có trong giỏ hàng của bạn. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("Sửa sản phẩm thành công");
        }
    }
    public void searchCart()
    {
        boolean isExisted = false;
        String productCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã sản phẩm cần tìm:");
        productCode=sc.nextLine();
        System.out.println("o================================================================o");
        System.out.println("|                    THÔNG TIN SẢN PHẨM CẦN TÌM                  |");
        System.out.println("|================================================================|");
        System.out.println("| Mã sản phẩm |     Tên hàng     | Số lượng |     Thành tiền     |"); 
        System.out.println("|================================================================|");
         for(int i=0; i<cart.length;i++){
             if(productCode.equals(cart[i].getProductCode()))
             {
            	 System.out.format("|%7s      | ", cart[i].getProductCode());  
                 System.out.format("%14s   |", cart[i].getProductName());
                 System.out.format("%6s    | ", cart[i].getAmount());             
                 System.out.format("%11s        |\n", cart[i].getPrice());
                 System.out.println("o================================================================o");
                 isExisted = true;
                 break nhapma;
             }
         }
        if(!isExisted ){
            System.out.println("\n>>Mã sản phẩm không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
    }
    public void displayCart() {
        System.out.println("o==============================================================================o");
        System.out.println("|                                 GIỎ HÀNG CỦA TÔI                             |");
        System.out.println("|==============================================================================|");
        System.out.println("| Mã đơn hàng | Mã sản phẩm |     Tên hàng     | Số lượng |     Thành tiền     |"); 
        System.out.println("|==============================================================================|");
         for(int i=0; i<cart.length;i++){
        	 System.out.format("|%6s       | ", cart[i].getOrderCode());
        	 System.out.format("%6s      | ", cart[i].getProductCode());  
             System.out.format("%14s   |", cart[i].getProductName());
             System.out.format("%6s    | ", cart[i].getAmount());             
             System.out.format("%13s      |\n", cart[i].getPrice());
         }
         System.out.println("o==============================================================================o");
    }
    // Đọc ghi file Cart
    public void writeFileCart(String x) throws IOException {
        ObjectOutputStream oos = null;
       try {
    	    if(x.equals("3")) {
    	    	oos = new ObjectOutputStream(new FileOutputStream("cartList.txt"));
    	    }
    	    if(x.equals("4")) {
    	    	oos = new ObjectOutputStream(new FileOutputStream("cartList1.txt"));
    	    }
    	    if(x.equals("5")) {
    	    	oos = new ObjectOutputStream(new FileOutputStream("cartList2.txt"));
    	    }
            oos.writeObject(cart);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            oos.close();
            
        }
    }
    public void readFileCart(String x) {
        //-------------------B1. Tao doi tuong luong va lien ket luong-----------
        FileInputStream fis = null;
        try {
        	if(x.equals("3")) {
        		fis = new FileInputStream("cartList.txt");
        	}
        	if(x.equals("4")) {
        		fis = new FileInputStream("cartList1.txt");
        	}
        	if(x.equals("5")) {
        		fis = new FileInputStream("cartList2.txt");
        	}
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
             cart=(Cart[])ois.readObject();
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
    // Đây là phương thức đổi trả của người dùng
    public void removeCart(String x) {
    	boolean isExisted = false;
        int m = 0;
        nhapma:
        while(true){
        for(int i=0; i<cart.length; i++){
            if(x.equals(cart[i].getProductCode())){
                m=i;
                isExisted = true;
                for(int p = 0; p<productInfo.length; p++) {
                	if(x.equals(productInfo[p].getProductCode())) {
                		productInfo[p].setAmount(productInfo[p].getAmount() + cart[m].getAmount()); // xóa trả số lượng của sản phẩm về cho sản phẩm
                	}
                }
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã sản phẩm này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        for(int i=m;i<cart.length-1;i++)
        	cart[i]=cart[i+1];
        cart = Arrays.copyOf(cart,cart.length-1);
    }
    public void changeProduct() {
    	boolean isExisted = false, isExistedChange = false;
        String productCode, changeProductCode;
        input:
        while(true){
        if(cart.length==0) {
             System.out.println("\n>>Giỏ hàng hiện tại của bạn đang trống!!!");
             break input;
        }	
        System.out.print("\nNhập mã sản phẩm bạn đã mua:");
        productCode=sc.nextLine();
        for(int i=0;i<cart.length;i++){
            if(productCode.equals(cart[i].getProductCode())){
                isExisted = true;
                inputChange:
                while(true) {
	                System.out.print("\nNhập vào mã sản phẩm bạn muốn đổi:");
	                changeProductCode = sc.nextLine();
	                for(int p=0; p<productInfo.length; p++) {
	                	if(changeProductCode.equals(productInfo[p].getProductCode())) {
	                		isExistedChange = true;
	                		amount:
		        				while(true) {
				        			amount = 0;
				        			try {
				        				System.out.print("Số lượng sản phẩm bạn muốn mua:");
				        				amount = sc.nextInt();
				        			}
				        			catch(InputMismatchException ex){
					                    System.out.print("\n>>Lựa chọn phải là số! Mời bạn nhập lại");
					                    sc.nextLine();
					                } 
				        			if(amount > productInfo[p].getAmount()) {
				        				System.out.println("Số lượng sản phẩm không đủ. Sản phẩm chỉ còn:" + productInfo[p].getAmount());
				        				continue amount;	
				        			}
				        			break amount;
		        				}	
	                		removeCart(productCode);
	                		// xóa sản phẩm bị thay đổi
		        		    addCart(changeProductCode); // thêm sản phẩm được cập nhật vào giỏ hàng
		        			System.out.print("Đổi sản phẩm thành công\n");
		        			sc.nextLine();
	                		break inputChange;
	                	}
	                }
	                if(!isExistedChange) {
                		System.out.println("Mã sản phẩm không tồn tại!!");
                		continue inputChange;
                	}
                }    
                break input;
        }
        
        }
        if(!isExisted ){
            System.out.println("\n>>Mã sản phẩm này không có trong giỏ hàng của bạn. Mời bạn nhập lại.");
            continue input;
        }
        }
    }
    public void writeFileAccount() throws IOException {
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
    
    public void readFileAccount() {
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
    
    public void writeFileBillDetails() throws IOException {
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
    
    public void readFileBillDetails() {
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
