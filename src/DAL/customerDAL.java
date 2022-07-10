
package DAL;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.*;

import javax.swing.JOptionPane;

import Bill.Bill;
import Bill.BillDetails;
import Product.Product;
import Supplier.Supplier;
import userService.Cart;

// chưa tối ưu hóa code ở đây
public class customerDAL {
	databaseConnect dc = new databaseConnect();
	billDetailsDAL bdDAL = new billDetailsDAL();
	billDAL bDAL = new billDAL();
	
	public customerDAL() {
		
	}
	//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<Cart> findAll(int userCode) {
		List<Cart> cartList = new ArrayList<Cart>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from cart where USER_CODE="+userCode;
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	Cart std = new Cart(resultSet.getInt("CART_CODE"), resultSet.getString("PRODUCT_CODE"), 
	                        resultSet.getString("PRODUCT_NAME"), resultSet.getInt("AMOUNT"), 
	                        resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"));
	            	cartList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return cartList;
	}
	
	public boolean insert(Cart p, int userCode) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into cart(CART_CODE, USER_CODE, PRODUCT_CODE, PRODUCT_NAME, AMOUNT, PRICE, TOTAL) values (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, p.getCartCode());
	            statement.setInt(2, userCode);
	            statement.setString(3, p.getProductCode());
	            statement.setString(4, p.getProductName());
	            statement.setInt(5, p.getAmount());
	            statement.setInt(6, p.getPrice());
	            statement.setInt(7, p.getTotal());
	            
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
	public boolean update(Cart p, int userCode) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "update cart set AMOUNT=? where CART_CODE=? AND USER_CODE=?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, p.getAmount());
	            statement.setInt(2, p.getCartCode());
	            statement.setInt(3, userCode);
	            
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
	public boolean delete(Cart p, int userCode) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "delete from cart where CART_CODE=? AND USER_CODE=?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, p.getCartCode());
	            statement.setInt(2, userCode);
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
	public List<Cart> findByProductName(String productName, int userCode) {
		List<Cart> cartList = new ArrayList<Cart>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from cart where PRODUCT_NAME like ? and USER_CODE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, "%"+productName+"%");
	            statement.setInt(2, userCode);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {                
	            	Cart std = new Cart(resultSet.getInt("CART_CODE"), resultSet.getString("PRODUCT_CODE"), 
	                        resultSet.getString("PRODUCT_NAME"), resultSet.getInt("AMOUNT"), 
	                        resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"));
	            	cartList.add(std);
	            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return cartList;
		}
	
	public boolean handleCartBay(Cart p, int userCode) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
				// lấy ra số lượng sản phẩm đang có
				String sql = "select AMOUNT from product where PRODUCT_CODE=?";
				PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, p.getProductCode());
	            
	            ResultSet resultAmount = statement.executeQuery();
	            
	            int amountProduct = 0;
	            while(resultAmount.next()) {
	            	amountProduct = resultAmount.getInt("AMOUNT");
	            }
	            
				// xóa số lượng của sản phẩm mà khách hàng đã mua 
				String updateProductSql = "update product SET AMOUNT=? where PRODUCT_CODE=?";
				PreparedStatement statementProduct = dc.connection.prepareCall(updateProductSql);
	            
				statementProduct.setInt(1, amountProduct - p.getAmount());
				statementProduct.setString(2, p.getProductCode());
				
	            if(statementProduct.executeUpdate()>=1) {
	            	
	            	// thêm vào hóa đơn chi tiết của ngày đó
	            	Date now = new Date();
    		    	String ft = new SimpleDateFormat("dd/MM/yyyy").format(now);
    		    	String billDate = ft.formatted(now);
    		    	// kiểm tra ngày đó có hay chưa và tạo phiếu chung của ngày đó
    		    	if(!bDAL.hasBillCode(billDate)) {
    		    		int total = 0;
    		    		int amount = 0;
    		    		Bill bill = new Bill(billDate, amount, total);
    		    		bDAL.insert(bill);
    		    	}
    		    	
    		    	// lấy billDetailsCode
	            	String getBillDetailsCodeSql = "select MAX(BILL_DETAILS_CODE) as BILL_DETAILS_CODE from bill_details where BILL_DATE='"+billDate+"'"; 
	            	PreparedStatement statementDetailsCode = dc.connection.prepareCall(getBillDetailsCodeSql);
		            
		            ResultSet resultDetailsCode = statementDetailsCode.executeQuery();
		            
		            int billDetailsCode = 0;
		            while(resultDetailsCode.next()) {
		            	billDetailsCode = resultDetailsCode.getInt("BILL_DETAILS_CODE") + 1;
		            }
    		    	
	            	int customerCode = userCode;
	            	String productCode = p.getProductCode();
	            	int employeeCode = 0;
	            	int amount = p.getAmount();
	            	int price = p.getPrice();
	            	int total = 0;
	            	
	            	BillDetails bd = new BillDetails(billDate, billDetailsCode, customerCode, productCode, employeeCode, amount, price, total, 0);
	            	// thêm thành công sau đó thêm vào đơn hàng của người dùng
	            	if(bdDAL.insert(bd)) {
	            		result = true;
	            		// chưa thêm vào hóa đơn của khách hàng
	            	}
	            	
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
	
	public List<BillDetails> getAllBill(int userCode) {
		List<BillDetails> billList = new ArrayList<BillDetails>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill_details where CUSTOMER_CODE=?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setInt(1, userCode);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {  
	            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
	            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
	            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM")); 
	            	billList.add(std);
	            }
	            
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return billList;
	}
	
	public List<BillDetails> findBillByProductName(String productName, int userCode) {
		List<BillDetails> billList = new ArrayList<BillDetails>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill_details where PRODUCT_NAME like ? and CUSTOMER_CODE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, "%"+productName+"%");
	            statement.setInt(2, userCode);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {  
	            	BillDetails std = new BillDetails(resultSet.getString("BILL_DATE"), resultSet.getInt("BILL_DETAILS_CODE"), resultSet.getInt("CUSTOMER_CODE"), 
	            				resultSet.getString("PRODUCT_CODE"), resultSet.getInt("EMPLOYEE_CODE"), resultSet.getInt("AMOUNT"), 
	            				resultSet.getInt("PRICE"), resultSet.getInt("TOTAL"), resultSet.getInt("CONFIRM")); 
	            	billList.add(std);
	            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return billList;
	}
	
}

