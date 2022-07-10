package DAL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;
import Supplier.Supplier;

// chưa tối ưu hóa code ở đây
public class supplierDAL {
	databaseConnect dc = new databaseConnect();
	private Connection connection; 
	public supplierDAL() {
		
	}
//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<Supplier> findAll() {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		
		if(dc.openConnection()) {
			try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "select * from supplier";
	            Statement statement = connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	Supplier std = new Supplier(resultSet.getString("SUPPLIER_CODE"), 
	                        resultSet.getString("SUPPLIER_NAME"), resultSet.getString("ADDRESS"), 
	                        resultSet.getString("NUMBER_PHONE"));
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
	
	public boolean insert(Supplier p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into supplier(SUPPLIER_CODE, SUPPLIER_NAME, ADDRESS,NUMBER_PHONE) values (?, ?, ?, ?)";
	            PreparedStatement statement = connection.prepareCall(sql);
	            
	            statement = connection.prepareCall(sql);
	            statement.setString(1, p.getSupplierCode());
	            statement.setString(2, p.getSupplierName());
	            statement.setString(3, p.getAddress());
	            statement.setString(4, p.getNumberPhone());	            
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
	public boolean update(Supplier p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "update supplier set SUPPLIER_NAME=?, ADDRESS=?, NUMBER_PHONE=? where SUPPLIER_CODE = ?";
	            PreparedStatement statement = connection.prepareCall(sql);
	            
	            statement.setString(1, p.getSupplierName());
	            statement.setString(2, p.getAddress());
	            statement.setString(3, p.getNumberPhone());
	            statement.setString(4, p.getSupplierCode());
	           
	            
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
				String sql = "delete from supplier where SUPPLIER_CODE = ?";
	            PreparedStatement statement = connection.prepareCall(sql);
	            
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
	public List<Supplier> findByFullName(String supplierName) {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		
		if(dc.openConnection()) {
			try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "select * from supplier where SUPPLIER_NAME like ?";
	            PreparedStatement statement = connection.prepareCall(sql);
	            statement.setString(1, "%"+supplierName+"%");
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {    
	            	Supplier std = new Supplier(resultSet.getString("SUPPLIER_CODE"), 
	                        resultSet.getString("SUPPLIER_NAME"), resultSet.getString("ADDRESS"), 
	                        resultSet.getString("NUMBER_PHONE"));
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
	public List<String> getSupplierList() {
		List<String> supplierList = new ArrayList<String>();
		
		if(dc.openConnection()) {
			try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "select * from supplier";
	            Statement statement = connection.createStatement();
	            
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
	 // check trùng mã nhà cung cấp
	 public boolean hasSupplierCode(String supplierCode) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from supplier where SUPPLIER_CODE=" + supplierCode;
		            Statement statement = connection.createStatement();
		            
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
	
}
