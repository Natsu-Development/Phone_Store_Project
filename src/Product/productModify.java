package Product;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import Supplier.Supplier;

// chưa tối ưu hóa code ở đây
public class productModify {
	
	public productModify() {
		
	}
//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<Product> findAll() {
		List<Product> productList = new ArrayList<Product>();
		
		Connection connection = null;
		Statement statement = null;
		
		try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "select * from product";
	            statement = connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	Product std = new Product(resultSet.getString("PRODUCT_CODE"), 
	                        resultSet.getString("PRODUCT_NAME"), resultSet.getString("SUPPLIER_NAME"), 
	                        resultSet.getString("SUPPLIER_NAME"), resultSet.getInt("AMOUNT"), 
	                        resultSet.getInt("IMPORT_PRICE"), resultSet.getInt("PRICE"));
	            	productList.add(std);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
		
		return productList;
	}
	// thêm oke
	 public void insert(Product p) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        if(check(p.getProductCode())) {
		        try {
		        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
		            
		            //query
		            String sql = "insert into product(PRODUCT_CODE, PRODUCT_NAME, PRODUCER_NAME, SUPPLIER_NAME, AMOUNT, IMPORT_PRICE, PRICE) values (?, ?, ?, ?, ?, ?, ?)";
		            statement = connection.prepareCall(sql);
		            
		            statement.setString(1, p.getProductCode());
		            statement.setString(2, p.getProductName());
		            statement.setString(3, p.getProducerName());
		            statement.setString(4, p.getSupplierName());
		            statement.setInt(5, p.getAmount());
		            statement.setInt(6, p.getImportPrice());
		            statement.setInt(7, p.getPrice());
		            
		            statement.execute();
		            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
		        } catch (SQLException ex) {
		            Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		        } finally {
		            if(statement != null) {
		                try {
		                    statement.close();
		                } catch (SQLException ex) {
		                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		            
		            if (connection != null) {
		                try {
		                    connection.close();
		                } catch (SQLException ex) {
		                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		        }
	        }
	        else {
	        	JOptionPane.showMessageDialog(null, "Mã sản phẩm này đã tồn tại. Vui lòng kiểm tra lại!");
	        }
	    }
	 
	 // sửa oke
	 public void update(Product p) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            //lay tat ca danh sach sinh vien
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "update product set PRODUCT_NAME=?, PRODUCER_NAME=?, SUPPLIER_NAME=?, AMOUNT=?, IMPORT_PRICE=?, PRICE=? where PRODUCT_CODE = ?";
	            statement = connection.prepareCall(sql);
	            
	            statement.setString(1, p.getProductName());
	            statement.setString(2, p.getProducerName());
	            statement.setString(3, p.getSupplierName());
	            statement.setInt(4, p.getAmount());
	            statement.setInt(5, p.getImportPrice());
	            statement.setInt(6, p.getPrice());
	            statement.setString(7, p.getProductCode());
	            
	            statement.execute();
	            JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công");
	        } catch (SQLException ex) {
	            Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	    }
	 // xóa oke
	 public void delete(String id) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            //lay tat ca danh sach sinh vien
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "delete from product where PRODUCT_CODE = ?";
	            statement = connection.prepareCall(sql);
	            
	            statement.setString(1, id);
	            
	            statement.execute();
	        } catch (SQLException ex) {
	            Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	    }
	 // đang lỗi câu lệnh sql
	 public List<Product> findByFullName(String productName) {
		 
		 	List<Product> productList = new ArrayList<Product>();
			Connection connection = null;
			PreparedStatement statement = null;
			
			try {
		            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
		            
		            //query
		            String sql = "select * from product where PRODUCT_NAME like ?";
		            statement = connection.prepareCall(sql);
		            statement.setString(1, "%"+productName+"%");
		            
		            ResultSet resultSet = statement.executeQuery();
		            
		            while (resultSet.next()) {    
		            	Product std = new Product(resultSet.getString("PRODUCT_CODE"), 
		                        resultSet.getString("PRODUCT_NAME"), resultSet.getString("SUPPLIER_NAME"), 
		                        resultSet.getString("SUPPLIER_NAME"), resultSet.getInt("AMOUNT"), 
		                        resultSet.getInt("IMPORT_PRICE"), resultSet.getInt("PRICE"));
		            	productList.add(std);
		            }
		            
		        } catch (SQLException ex) {
		            Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		        } finally {
		            if(statement != null) {
		                try {
		                    statement.close();
		                } catch (SQLException ex) {
		                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		            
		            if (connection != null) {
		                try {
		                    connection.close();
		                } catch (SQLException ex) {
		                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		        }
			
			return productList;
		}
	 
	 public List<String> getSupplierList() {
		 
		 	List<String> supplierList = new ArrayList<String>();
			
			Connection connection = null;
			Statement statement = null;
			
			try {
		            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
		            
		            //query
		            String sql = "select * from supplier";
		            statement = connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            while (resultSet.next()) {                
		            	String std = resultSet.getString("SUPPLIER_NAME");
		            	supplierList.add(std);
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		        } finally {
		            if(statement != null) {
		                try {
		                    statement.close();
		                } catch (SQLException ex) {
		                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		            
		            if (connection != null) {
		                try {
		                    connection.close();
		                } catch (SQLException ex) {
		                    Logger.getLogger(productModify.class.getName()).log(Level.SEVERE, null, ex);
		                }
		            }
		        }
			
			return supplierList;
		 
	 }
	 // check trùng mã sản phẩm
	 public boolean check(String productCode) {
		 int i=0;
		 List<Product> productList = new ArrayList<Product>();
		 productList = findAll();
		 while(i<productList.size() && productList.size()>0) {
			 Product s = productList.get(i);
			 if(productCode.equals(s.getProductCode())) {
				 return false;
			 }
			 i++;
		 }
		 
		 return true;
	 }
}
