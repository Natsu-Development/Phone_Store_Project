package DAL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import Bill.BillDetails;
import Product.Product;
import Supplier.Supplier;
import userService.Cart;

// chưa tối ưu hóa code ở đây
public class productDAL {
	
	databaseConnect dc = new databaseConnect();
	
	public productDAL() {
		
	}
//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<Product> findAll() {
		List<Product> productList = new ArrayList<Product>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from product";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	Product std = new Product(resultSet.getString("PRODUCT_CODE"), 
	                        resultSet.getString("PRODUCT_NAME"), resultSet.getString("PRODUCER_NAME"), 
	                        resultSet.getString("SUPPLIER_NAME"), resultSet.getInt("AMOUNT"), 
	                        resultSet.getInt("IMPORT_PRICE"), resultSet.getInt("PRICE"),resultSet.getString("IMAGE"));
	            	productList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return productList;
	}
	
	public boolean insert(Product p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into product(PRODUCT_CODE, PRODUCT_NAME, PRODUCER_NAME, SUPPLIER_NAME, AMOUNT, IMPORT_PRICE, PRICE,IMAGE) values (?, ?, ?, ?, ?, ?, ?,?)";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, p.getProductCode());
	            statement.setString(2, p.getProductName());
	            statement.setString(3, p.getProducerName());
	            statement.setString(4, p.getSupplierName());
	            statement.setInt(5, p.getAmount());
	            statement.setInt(6, p.getImportPrice());
	            statement.setInt(7, p.getPrice());
	            statement.setString(8, p.getImage());
	            
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
	public boolean update(Product p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "update product set PRODUCT_NAME=?, PRODUCER_NAME=?, SUPPLIER_NAME=?, AMOUNT=?, IMPORT_PRICE=?, PRICE=? , IMAGE = ? where PRODUCT_CODE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, p.getProductName());
	            statement.setString(2, p.getProducerName());
	            statement.setString(3, p.getSupplierName());
	            statement.setInt(4, p.getAmount());
	            statement.setInt(5, p.getImportPrice());
	            statement.setInt(6, p.getPrice());
	            statement.setString(7, p.getImage());
	            statement.setString(8, p.getProductCode());
	            
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
	public boolean delete(String id) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "delete from product where PRODUCT_CODE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, id);
	            
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
	public List<Product> findByFullName(String productName) {
		List<Product> productList = new ArrayList<Product>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from product where PRODUCT_NAME like ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, "%"+productName+"%");
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {    
	            	Product std = new Product(resultSet.getString("PRODUCT_CODE"), 
	                        resultSet.getString("PRODUCT_NAME"), resultSet.getString("PRODUCER_NAME"), 
	                        resultSet.getString("SUPPLIER_NAME"), resultSet.getInt("AMOUNT"), 
	                        resultSet.getInt("IMPORT_PRICE"), resultSet.getInt("PRICE"),resultSet.getString("IMAGE"));
	            	productList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return productList;
	}
	
	public List<String> getSupplierList() {
		List<String> supplierList = new ArrayList<String>();
		
		if(dc.openConnection()) {
			try {
	            
	            //query
	            String sql = "select * from supplier";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	String std = resultSet.getString("SUPPLIER_NAME");
	            	supplierList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return supplierList;
	}
	
	public List<String> getProductList() {
		List<String> productList = new ArrayList<String>();
		
		if(dc.openConnection()) {
			try {
	            
	            //query
	            String sql = "select * from product";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	String std = resultSet.getString("PRODUCT_NAME");
	            	productList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return productList;
	}
	
	 // check trùng mã sản phẩm
	 public boolean hasProductCode(String productCode) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from product where PRODUCT_CODE=" + productCode;
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
	 
	 public boolean checkAmount(int amount, String code) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from product where PRODUCT_CODE=" + code;
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            while(resultSet.next()) {
		            	int amountProduct = resultSet.getInt("AMOUNT");
		            	if(amountProduct>=0 && amountProduct>=amount) {
		            		result = true;
		            	}
		            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
		 
		 return result;
	 }
	 public String showImage() {
		 String sql = "select IMAGE from product where PRODUCT_CODE = ?";
		 return sql;
	 }
	 public String getProduct(String id) {
			String productName = null;
			
			if(dc.openConnection()) {
				try {
		            
		            //query
		            String sql = "select * from product where PRODUCT_CODE="+id;
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            while (resultSet.next()) {                
		            	productName = resultSet.getString("PRODUCT_NAME");
		            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return productName;
		}
	 public boolean handleBillDetails(BillDetails p) {
		 boolean result = false;
		 if(dc.openConnection()) {
				try {
		            
		            //query
		            String sql = "select * from product where PRODUCT_CODE="+p.getProductCode();
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		            int amountProduct = 0;
		            while(resultSet.next()) {
		            	amountProduct = resultSet.getInt("AMOUNT");
		            }
		            
					// xóa số lượng của sản phẩm mà khách hàng đã mua 
					String updateProductSql = "update product SET AMOUNT=? where PRODUCT_CODE=?";
					PreparedStatement statementProduct = dc.connection.prepareCall(updateProductSql);
		            
					statementProduct.setInt(1, amountProduct - p.getAmount());
					statementProduct.setString(2, p.getProductCode());
					
					if(statementProduct.executeUpdate()>=1) {
						result = true;
					}
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return result;
	 }
}
