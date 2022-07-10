package Employee;
import Dungchung.Abstract;
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

import Account.Account;
public class ArrayEmployee extends Abstract implements Mang{
    Scanner sc =new Scanner(System.in);
    private int n,k;
    private Employee[] e;
    public void creatData() {
    	e = new Employee[2];
    	int i=0;
    	e[i]= new Employee("Le Cat", "0924714551", "Thành phố Hồ Chí Minh", "1", "15/12/2000", "Quản lí chi nhánh");
    	e[i+1]= new Employee("Quoc Bao", "0924714552", "Thành phố Hồ Chí Minh", "2", "28/12/2001", "Nhân viên điều hành");
    }
    public void inputEmployee(){
        while(true){
            System.out.print("\nNhập số lượng nhân viên: ");
            try{
                n=Integer.parseInt(sc.nextLine());
                e = new Employee[n];
                int count =1;
                for(int i=0;i<e.length;i++)
                {
                    System.out.println("\nNHÂN VIÊN THỨ "+count);
                    e[i]=new Employee();
                    String employeeCode;
                    nhapma:
                    while(true)
                    {
                        System.out.print("\nMã nhân viên:");
                        employeeCode=sc.nextLine();

                        for(int j=0;j<e.length;j++)
                        {
                            if(employeeCode.equals(e[j].getEmployeeCode()))
                            {
                                System.out.println("\n>>Mã nhân viên này đã tồn tại. Mời bạn nhập lại.");
                                continue nhapma;
                            }
                        }
                        break nhapma;
                    }
                    e[i].setEmployeeCode(employeeCode);;
                    System.out.print("\nTên nhân viên:");
                    e[i].setEmployeeName(sc.nextLine());
                    String birthday=null;
                    date:
                    while(true){                                           
                        System.out.print("\nNgày sinh:");                
                        birthday=sc.nextLine();
                        if(!Tester.day(birthday)){
                            System.out.println("\n>>Ngày sinh phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue date;
                        }
                        else {
                            e[i].setBirthday(birthday);
                            break date;
                        }                        
                    }
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
                            e[i].setNumberPhone(numberPhone);
                            break sdt;
                        }                        
                    }
                    System.out.print("\nĐịa chỉ:");
                    e[i].setAddress(sc.nextLine());
                    System.out.print("\nChức vụ:");
                    e[i].setPosition(sc.nextLine());
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
            System.out.print("\nNhập số lượng nhân viên thêm vào:");
            try{
                k=Integer.parseInt(sc.nextLine());
                int oldLength=e.length;
                n=e.length+k;
                e = Arrays.copyOf(e, n);
                int count=1;
                for(int i=oldLength;i<n;i++){          

                    System.out.println("\nNHÂN VIÊN THỨ "+count);
                    e[i]=new Employee();
                    String employeeCode;
                    nhapma:
                    while(true)
                    {
                        System.out.print("\nMã nhân viên:");
                        employeeCode=sc.nextLine();
                        e[i].setEmployeeCode(employeeCode);
                        for(int j=0;j<oldLength;j++)
                        {
                            if(employeeCode.equals(e[j].getEmployeeCode()))
                            {
                                System.out.println("\n>>Mã nhân viên này đã tồn tại. Mời bạn nhập lại.");
                                continue nhapma;
                            }

                    }
                    break nhapma;
                    }
                    e[i].setEmployeeCode(employeeCode);
                    System.out.print("\nTên nhân viên:");
                    e[i].setEmployeeName(sc.nextLine());
                    String x=null;
                    date:
                    while(true){                                           
                        System.out.print("\nNgày sinh:");                
                        x=sc.nextLine();
                        if(!Tester.day(x)){
                            System.out.println("\n>>Ngày sinh phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                            continue date;
                        }
                        else {
                            e[i].setBirthday(x);
                            break date;
                        }                        
                    }
                    String y=null;                    
                    sdt:
                    while(true){                                           
                        System.out.print("\nSố điện thoại:");                
                        y=sc.nextLine();
                        if(!Tester.numberPhone(y)){
                            System.out.println("\n>>Số điện thoại phải có 10 chữ số và không được chứa kí tự. Mời bạn nhập lại.");
                            continue sdt;
                        }
                        else {
                            e[i].setNumberPhone(y);
                            break sdt;
                        }                        
                    }
                    System.out.print("\nĐịa chỉ:");
                    e[i].setAddress(sc.nextLine());
                    System.out.print("\nChức vụ:");
                    e[i].setPosition(sc.nextLine());
                    count++;
                }
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                //mNV.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số! Mời bạn nhập lại.");
                //mNV.nextLine();
            }
        }
    }
    public void display()
    {
    	System.out.println("o============================================================================================================================================o");
        System.out.println("|                                                      DANH SÁCH NHÂN VIÊN                                                                   |");
        System.out.println("|============================================================================================================================================|");
        System.out.println("| Mã nhân viên |    Tên nhân viên     |     Ngày sinh     |     Số điện thoại     |          Địa chỉ           |           Chức vụ           |"); 
        System.out.println("|============================================================================================================================================|");
        //System.out.println(pn.length);
        for(int i=0;i<e.length;i++) 
            e[i].xuat();
        System.out.println("o============================================================================================================================================o");
    }      
    public void search() {
		System.out.println("___________________________________________");
		System.out.println("Bạn muốn tìm kiếm như thế nào?");
		System.out.println("1- Tìm kiếm theo mã");
		System.out.println("2- Tìm kiếm theo tên");
		System.out.println("3- Tìm kiếm theo số điện thoại");
		System.out.println("4- Tìm kiếm theo địa chỉ");
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
				break sc;
			}
			case 3: {
				searchPhone();
				break sc;
			}
			case 4: {
				searchAddress();
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
    public void searchCode()
    {
        boolean isExisted = false;
        String employeeCode;
        nhapma:
        while(true){
        System.out.print("\nNhập mã nhân viên cần tìm:");
        employeeCode=sc.nextLine();
    	System.out.println("o============================================================================================================================================o");
        System.out.println("|                                                     THÔNG TIN NHÂN VIÊN CẦN TÌM                                                            |");
        System.out.println("|============================================================================================================================================|");
        System.out.println("| Mã nhân viên |    Tên nhân viên     |     Ngày sinh     |     Số điện thoại     |          Địa chỉ           |           Chức vụ           |"); 
        System.out.println("|============================================================================================================================================|");
        for(int i=0;i<e.length;i++){
            if(employeeCode.equals(e[i].getEmployeeCode()))
            {
                System.out.format("|%7s       |  ", e[i].getEmployeeCode());
                System.out.format("%10s          |  ", e[i].getEmployeeName());
                System.out.format("%12s     |  ", e[i].getBirthDay());
                System.out.format("%13s        |  ", e[i].getNumberPhone());
                System.out.format("%23s   |   ", e[i].getAddress());
                System.out.format("%21s     |\n", e[i].getPosition());
                isExisted = true;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Mã Nhân viên này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("o============================================================================================================================================o");
    }
    public void searchName()
    {
    	int flag = 0;
    	System.out.print("\nNhập vào tên nhân viên cần tìm:");
    	String input=sc.nextLine();
    	System.out.println("o============================================================================================================================================o");
        System.out.println("|                                                     THÔNG TIN NHÂN VIÊN CẦN TÌM                                                            |");
        System.out.println("|============================================================================================================================================|");
        System.out.println("| Mã nhân viên |    Tên nhân viên     |     Ngày sinh     |     Số điện thoại     |          Địa chỉ           |           Chức vụ           |"); 
        System.out.println("|============================================================================================================================================|");
    	for(int i=0; i<e.length; i++) {
    		if(e[i].getName().toLowerCase().contains(input)) { 
    	        System.out.format("|%7s       |  ", e[i].getEmployeeCode());
    	        System.out.format("%10s          |  ", e[i].getEmployeeName());
    	        System.out.format("%12s     |  ", e[i].getBirthDay());
    	        System.out.format("%13s        |  ", e[i].getNumberPhone());
    	        System.out.format("%23s   |   ", e[i].getAddress());
    	        System.out.format("%21s     |\n", e[i].getPosition());
                flag  =1;
    		}
    	}
    	if(flag == 0) {
    		System.out.println("|                                                                                                                                            |");
    		System.out.println("|                                                   Không có nhân viên cần tìm                                                               |");
    		System.out.println("|                                                                                                                                            |");
    	}
        System.out.println("o============================================================================================================================================o");
    }    
    public void searchPhone()
    {
        boolean isExisted = false;
        int flag = 0;
        String employeePhone;
        nhapma:
        while(true){
        System.out.print("\nNhập số điện thoại nhân viên cần tìm:");
        employeePhone=sc.nextLine();
        for(int i=0; i<e.length; i++) {
        	if(e[i].getNumberPhone().equals(employeePhone)==false) {
        		System.out.println(">>>>Số điện thoại này không có trong danh sách nhân viên");
        		return;
        	}
        }
       	System.out.println("o============================================================================================================================================o");
        System.out.println("|                                                     THÔNG TIN NHÂN VIÊN CẦN TÌM                                                            |");
        System.out.println("|============================================================================================================================================|");
        System.out.println("| Mã nhân viên |    Tên nhân viên     |     Ngày sinh     |     Số điện thoại     |          Địa chỉ           |           Chức vụ           |"); 
        System.out.println("|============================================================================================================================================|");
        for(int i=0;i<e.length;i++){
            if(e[i].getNumberPhone().contains(employeePhone))
            { 
    	        System.out.format("|%7s       |  ", e[i].getEmployeeCode());
    	        System.out.format("%10s          |  ", e[i].getEmployeeName());
    	        System.out.format("%12s     |  ", e[i].getBirthDay());
    	        System.out.format("%13s        |  ", e[i].getNumberPhone());
    	        System.out.format("%23s   |   ", e[i].getAddress());
    	        System.out.format("%21s     |\n", e[i].getPosition());
                isExisted = true;
                flag = 1;
                break nhapma;
            }
        }
        if(!isExisted ){
            System.out.println("\n>>Số điện thoại này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("o============================================================================================================================================o");
    }
    public void searchAddress()
    {
        boolean isExisted = false;
        int flag =0;
        String employeeAddress;
        nhapma:
        while(true){
        System.out.print("\nNhập địa chỉ nhân viên cần tìm:");
        employeeAddress=sc.nextLine();
        System.out.println("o============================================================================================================================================o");
        System.out.println("|                                                     THÔNG TIN NHÂN VIÊN CẦN TÌM                                                            |");
        System.out.println("|============================================================================================================================================|");
        System.out.println("| Mã nhân viên |    Tên nhân viên     |     Ngày sinh     |     Số điện thoại     |          Địa chỉ           |           Chức vụ           |"); 
        System.out.println("|============================================================================================================================================|");
        for(int i=0;i<e.length;i++){
            if(e[i].getAddress().contains(employeeAddress))
            {
    	        System.out.format("|%7s       |  ", e[i].getEmployeeCode());
    	        System.out.format("%10s          |  ", e[i].getEmployeeName());
    	        System.out.format("%12s     |  ", e[i].getBirthDay());
    	        System.out.format("%13s        |  ", e[i].getNumberPhone());
    	        System.out.format("%23s   |   ", e[i].getAddress());
    	        System.out.format("%21s     |\n", e[i].getPosition());
                isExisted = true;
                flag=1;
            }
        }
        if(flag==1) {
        	break nhapma;
        }	
        if(!isExisted ){
            System.out.println("\nĐịa chỉ này không tồn tại. Mời bạn nhập lại.");
            continue nhapma;
        }
        }
        System.out.println("o============================================================================================================================================o");
    }
    public void remove()
    {
    	 boolean isExisted = false;
         int m;
         nhapma:
         while(true)
         {
         System.out.print("\nNhập mã nhân viên cần xóa:");
         String employeeCode=sc.nextLine();
         for(int i=0;i<e.length;i++)
         {
             if(employeeCode.equals(e[i].getEmployeeCode()))
             {
                 m=i;
                 isExisted = true;
                 break nhapma;
             }
         }
         if(!isExisted )
         {
             System.out.println("\n>>Mã nhân viên này không tồn tại. Mời bạn nhập lại.");
             continue nhapma;
         }
         }
         for(int i=m;i<e.length-1;i++)
        	 e[i]=e[i+1];
         e = Arrays.copyOf(e,e.length-1);
    }
    
    public void edit()
    {
        boolean isExisted = false;
        String employeeCode;
        nhapma:
        while(true)
        {
            System.out.print("\nNhập mã Nhân viên cần sửa:");
            employeeCode=sc.nextLine();
            for(int i=0;i<e.length;i++)
            {
                if(employeeCode.equals(e[i].getEmployeeCode()))
                {
                    isExisted = true;
                    break nhapma;
                }
            }
            if(!isExisted)
            {
                System.out.println("\n>>Mã nhân viên này không tồn tại. Mời bạn nhập lại.");
                continue nhapma;
            } 
        }
        System.out.println("___________________________________________");
        System.out.println("\nBạn muốn sửa thông tin nào?");        
        System.out.println("1-Mã nhân viên                       ");
        System.out.println("2-Tên nhân viên                      ");
        System.out.println("3-Ngày sinh                          ");
        System.out.println("4-Số điện thoại                      ");
        System.out.println("5-Địa chỉ                      ");
        System.out.println("6-Chức vụ                            ");
        System.out.println("___________________________________________");
        loop:
        while(true)
        {
            int t=0;           
            System.out.print("Nhập vào sự lựa chọn của bạn:");                
            try{
                t=sc.nextInt();                   
            }
            catch(InputMismatchException ex){
                System.out.println("\n>>Lựa chọn phải là số!");
            }
            sc.nextLine();//dung de bo ki tu xuong dong bi thua truoc khi nhap String
                for(int i=0;i<e.length;i++)
                {
                    if(employeeCode.equals(e[i].getEmployeeCode()))
                    {
                        switch(t)
                        {
                            case 1:
                            {
                            String newEmployeeCode;
                            nhapma:
                            while(true){
                                System.out.print("\nNhập vào mã nhân viên mới:");
                                newEmployeeCode=sc.nextLine();
                                for(int j=0;j<e.length;j++){
                                    if(employeeCode.equals(e[j].getEmployeeCode())){
                                        System.out.println("\nMã nhân viên này đã tồn tại. Mời bạn nhập lại.");
                                        continue nhapma;
                                    }
                                }
                                break nhapma;
                            }
                            e[i].setEmployeeCode(newEmployeeCode);
                            break loop;
                        }
                            case 2:
                            {
                                System.out.print("\nNhập vào tên nhân viên mới:");
                                String newEmployeeName=sc.nextLine();
                                e[i].setEmployeeName(newEmployeeName);
                                break loop;                        
                            }                            
                            case 3:
                            {
                                String newBirthday=null;
                                date:
                                while(true){                                           
                                    System.out.print("\nNhập vào ngày sinh mới:");                
                                    newBirthday=sc.nextLine();
                                    if(!Tester.day(newBirthday)){
                                        System.out.println("\n>>Ngày sinh phải theo định dạng dd/mm/yyyy. Mời bạn nhập lại.");
                                        continue date;
                                    }
                                    else {
                                        e[i].setBirthday(newBirthday);
                                        break date;
                                    }                        
                                }
                                break loop;
                            }                           
                            case 4:
                            {
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
                                        e[i].setNumberPhone(newNumberPhone);
                                        break sdt;
                                    }                        
                                } 
                                break loop;
                            }
                            case 5:
                            {
                                System.out.print("\nNhập vào địa chỉ mới:");
                                String newAddress=sc.nextLine();
                                e[i].setAddress(newAddress);
                                break loop;                        
                            }                            
                            case 6:
                            {
                                System.out.print("\nNhập vào chức vụ mới:");
                                String newPosition=sc.nextLine();
                                e[i].setPosition(newPosition);
                                break loop;
                            }                            
                            default: 
                            {
                                System.out.println("\n>>Không hợp lệ. Mời bạn nhập lại giá trị từ 1 đến 6.");
                                continue loop;
                            }
                        }
                    } 
                }
        }
    }  
    public boolean fileNull(){
        if(e.length==0) return true;
        return false;
    }
    public void readFile()
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
    public void writeFile()throws IOException {
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
}
