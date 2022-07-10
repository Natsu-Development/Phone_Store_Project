package DAL;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.*;

import javax.swing.JOptionPane;

import Bill.BillDetails;
import Dungchung.statis;
import GUI.billGUI;
import GUI.employeeInterfaceGUI;
import Product.Product;
import Supplier.Supplier;

// chưa tối ưu hóa code ở đây
public class billDetailsDAL {
	
	databaseConnect dc = new databaseConnect();
	public billDetailsDAL() {
		
	}
//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<BillDetails> findAll(String date) {
		List<BillDetails> billDetailsList = new ArrayList<BillDetails>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill_details where BILL_DATE='"+date+"' and CONFIRM=1";
	            
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            
	            while (resultSet.next()) {  
	            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
	            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
	            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM")); 
	            	billDetailsList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return billDetailsList;
	}
	
	public boolean insert(BillDetails p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into bill_details(BILL_DATE, BILL_DETAILS_CODE, CUSTOMER_CODE, PRODUCT_CODE, EMPLOYEE_CODE , AMOUNT, PRICE, TOTAL, CONFIRM) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, p.getBillDate());
	            statement.setInt(2, p.getBillDetailsCode());
	            statement.setInt(3, p.getCustomerCode());
	            statement.setString(4, p.getProductCode());
	            statement.setInt(5, p.getEmployeeCode());
	            statement.setInt(6, p.getAmount());
	            statement.setInt(7, p.getPrice());
	            statement.setInt(8, p.getTotal());
	            statement.setInt(9, p.getConfirm());
	            
	            if(statement.executeUpdate()>=1) {
	            	result = true;
	            }
	        } catch (SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return result;
	}
	
	 // sửa oke
	public boolean update(BillDetails p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            // phiếu chi tiết
				String sqlDetails = "update bill_details set CUSTOMER_CODE=?, PRODUCT_CODE=?, EMPLOYEE_CODE=?, AMOUNT=?, PRICE=? where BILL_DETAILS_CODE = ? AND BILL_DATE=?";
	            PreparedStatement statementDetails = dc.connection.prepareCall(sqlDetails);
	            
	            statementDetails.setInt(1, p.getCustomerCode());
	            statementDetails.setString(2, p.getProductCode());
	            statementDetails.setInt(3, p.getEmployeeCode());
	            statementDetails.setInt(4, p.getAmount());
	            statementDetails.setInt(5, p.getPrice());
	            statementDetails.setInt(6, p.getBillDetailsCode());
	            statementDetails.setString(7, p.getBillDate());
	            
	            if(statementDetails.executeUpdate()>=1) {
	            	result = true;
	            }
	        } catch (SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return result;
	}
	public boolean delete(String id, String date) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "delete from bill_details where BILL_DETAILS_CODE = ? and BILL_DATE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, id);
	            statement.setString(2, date);
	            
	            if(statement.executeUpdate()>=1) {
	            	result = true;
	            }
	        } catch (SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return result;
	}
	public List<BillDetails> findByCode(String code, String col) {
		List<BillDetails> billDetailsList = new ArrayList<BillDetails>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill_details where "+col+" = ? and CONFIRM=1";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, code);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {  
	            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM")); 
	            	
	            	if(billGUI.date!=null && billGUI.date.equals(std.getBillDate())) {
	            		billDetailsList.add(std);
	            	}
	            	if(employeeInterfaceGUI.employeeCode!=-1) {
	            		billDetailsList.add(std);
	            	}
	            }
	            
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return billDetailsList;
	}
	
	// get order not confirmed by order online
	public List<BillDetails> getOrderNotConfirmed() {
		List<BillDetails> orderList = new ArrayList<BillDetails>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill_details where CONFIRM = '0'";
	            
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            
	            while (resultSet.next()) {  
	            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM")); 
	            	orderList.add(std);
	            }
	            
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return orderList;
	}
	
	//get order not confirm by cus code
	public List<BillDetails> getOrderNotConfirmByCusCode(String code) {
		List<BillDetails> orderList = new ArrayList<BillDetails>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill_details where CONFIRM = '0' and CUSTOMER_CODE = " + code;
	            
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            
	            while (resultSet.next()) {  
	            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM")); 
	            	orderList.add(std);
	            }
	            
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return orderList;
	}
	
	// delete Order not Confirm
	public boolean deleteOrderNotConfirmed(BillDetails p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "delete from bill_details where BILL_DETAILS_CODE = ? and BILL_DATE = ? and CONFIRM = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, p.getBillDetailsCode());
	            statement.setString(2, p.getBillDate());
	            statement.setInt(3, 0);
	            
	            if(statement.executeUpdate()>=1) {
	            	result = true;
	            }
	        } catch (SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return result;
	}
	
	public boolean confirmOrder(BillDetails p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "update bill_details set CONFIRM=?, EMPLOYEE_CODE=? where BILL_DETAILS_CODE=? AND BILL_DATE='"+p.getBillDate()+"'";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, 1);
	            statement.setInt(2, p.getEmployeeCode());
	            statement.setInt(3, p.getBillDetailsCode());
	            
	            if(statement.executeUpdate()>=1) {
	            	result = true;
	            }
	        } catch (SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return result;
	}
	 // check trùng mã phiếu xuất chung
	 public boolean hasBillDetailsCode(String billDate, int billDetailsCode) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
					String sql = "select * from bill_details where BILL_DATE= '"+billDate+"' and BILL_DETAILS_CODE=" + billDetailsCode;
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            result = resultSet.next();
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
		 
		 return result;
	 }
	 
	 // statistic
	 public List<statis> statistic(List<Supplier> listSupllier, List<Product> listProduct, String monthNow) throws ParseException {
			List<statis> resultList = new ArrayList<statis>();
			String brand = "";
        	
			if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from bill_details where CONFIRM = '1'";
		            
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		            
		            while (resultSet.next()) {  
		            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
	            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
	            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM"));
		            	
		            	// BILL DATE
		            	String date1 = std.getBillDate();
		            	// chuyển String về ngày
		            	Date format = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
		            	String ft1 = new SimpleDateFormat("MM/yyyy").format(format);
		            	String billDate = ft1.formatted(format);
		            	
		            	if(monthNow.equals(billDate)) {
		            		int amount = 0, flag = 0;
		            		long total = 0; 
		            		// lấy hãng của sản phẩm
		            		for(int i = 0; i<listProduct.size(); i++) {
		            			if(listProduct.get(i).getProductCode().equals(resultSet.getString("PRODUCT_CODE"))) {
		            				brand = listProduct.get(i).getSupplierName();
		            			}
		            		}
		            		
		            		// check brand have existed in resultList
		            		for(int i=0; i<resultList.size(); i++) {
		            			if(brand.equals(resultList.get(i).getName())) {
		            				total = resultList.get(i).getTotal() + resultSet.getInt("TOTAL");
		            				amount = resultList.get(i).getAmount() + resultSet.getInt("AMOUNT");
		            				resultList.get(i).setAmount(amount);
		            				resultList.get(i).setTotal(total);
		            				flag = 1;
		            			}
		            		}
		            		
		            		// set data for sta brand
		            		for(int i = 0; i<listSupllier.size(); i++) {
		            			if(brand.equals(listSupllier.get(i).getSupplierName())) {
		            				total += resultSet.getInt("TOTAL");
		            				amount += resultSet.getInt("AMOUNT");
		            			}
		            		}
		            		
		            		if(flag == 0) {
		            			statis s = new statis(brand, amount, total);
		            			resultList.add(s);
		            		}	
		            	}
		            }
		            
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return resultList;
		} 
	 
	 public List<statis> statisticByProduct(List<Product> listProduct, String monthNow) throws ParseException {
			List<statis> resultList = new ArrayList<statis>();
	     	
	     	String productName = "";
     	
			if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from bill_details where CONFIRM = '1'";
		            
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		            
		            while (resultSet.next()) {  
		            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
	            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
	            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM"));
		            	
		            	// BILL DATE
		            	String date1 = std.getBillDate();
		            	// chuyển String về ngày
		            	Date format = new SimpleDateFormat("dd/MM/yyyy").parse(date1);
		            	String ft1 = new SimpleDateFormat("MM/yyyy").format(format);
		            	String billDate = ft1.formatted(format);
		            	
		            	if(monthNow.equals(billDate)) {
		            		int flag = 0, amount = 0;
		            		for(int i=0; i<listProduct.size(); i++) {
		            			if(std.getProductCode().equals(listProduct.get(i).getProductCode())) {
		            				productName = listProduct.get(i).getProductName(); 
		            			}
		            		}
		            		
		            		for(int i=0; i<resultList.size(); i++) {
		            			if(productName.equals(resultList.get(i).getName())) {
		            				amount = resultList.get(i).getAmount() + resultSet.getInt("AMOUNT");
		            				resultList.get(i).setAmount(amount);
		            				flag = 1;
		            			}
		            		}
		            		
		            		if(flag==0) {
		            			amount = resultSet.getInt("AMOUNT");
		            			statis s = new statis(productName, amount);
		            			resultList.add(s);
		            		}
		            	}
		            }
		            
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return resultList;
		} 
}
