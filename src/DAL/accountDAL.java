package DAL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import Account.Account;
import GUI.employeeInterfaceGUI;

// chưa tối ưu hóa code ở đây
public class accountDAL {
	databaseConnect dc = new databaseConnect();
	public accountDAL() {
		
	}
//	List<bac_si> bac_siList = new ArrayList<bac_si>();
	// lấy tất cả oke 
	public List<Account> findAll(String permission) {
		List<Account> accountList = new ArrayList<Account>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from account where PERMISSION='"+permission+"'";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	Account std = new Account(resultSet.getInt("ID"), 
	                        resultSet.getString("ACCOUNTNAME"), resultSet.getString("PASSWORD"), 
	                        resultSet.getString("PERMISSION"), resultSet.getString("USERNAME"), resultSet.getString("ADDRESS"), 
	                        resultSet.getString("NUMBERPHONE"), resultSet.getString("BIRTHDAY"));
	            	accountList.add(std);
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return accountList;
	}
	
	public boolean insert(Account p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into account(ID, ACCOUNTNAME, PASSWORD, PERMISSION, USERNAME, ADDRESS, NUMBERPHONE, BIRTHDAY) values (?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, p.getId());
	            statement.setString(2, p.getAccountName());
	            statement.setString(3, p.getPassword());
	            statement.setString(4, p.getPermission());
	            statement.setString(5, p.getUserName());
	            statement.setString(6, p.getAddress());
	            statement.setString(7, p.getNumberPhone());
	            statement.setString(8, p.getBirthday());
	            
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
	public boolean update(Account p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "update account set ACCOUNTNAME=?, PASSWORD=?, PERMISSION=?, USERNAME=?, ADDRESS=?, NUMBERPHONE=?, BIRTHDAY=? where ID=?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, p.getAccountName());
	            statement.setString(2, p.getPassword());
	            statement.setString(3, p.getPermission());
	            statement.setString(4, p.getUserName());
	            statement.setString(5, p.getAddress());
	            statement.setString(6, p.getNumberPhone());
	            statement.setString(7, p.getBirthday());
	            
	            statement.setInt(8, p.getId());
	            
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
	public boolean delete(int id) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "delete from account where ID = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setInt(1, id);
	            
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
	public List<Account> findByUserName(String userName) {
		List<Account> accountList = new ArrayList<Account>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from account where USERNAME like ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, "%"+userName+"%");
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {                
	            	Account std = new Account(resultSet.getInt("ID"), 
	                        resultSet.getString("ACCOUNTNAME"), resultSet.getString("PASSWORD"), 
	                        resultSet.getString("PERMISSION"), resultSet.getString("USERNAME"), resultSet.getString("ADDRESS"), 
	                        resultSet.getString("NUMBERPHONE"), resultSet.getString("BIRTHDAY"));
	            	if(employeeInterfaceGUI.employeeCode!=-1) {
	            		String permission = resultSet.getString("PERMISSION");
		            	if(!permission.equals("admin") && !permission.equals("Nhân viên")) {
		            		accountList.add(std);
		            	}
	            	}
	            	else {
	            		accountList.add(std);
	            	}
	            }
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return accountList;
	}
	 // check trùng mã sản phẩm
	 public boolean hasAccountCode(int code) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from account where ID=" + code;
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
	 
	 public int code(String accountName) {
		 int code = -1;
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select ID from account where ACCOUNTNAME = '"+accountName+"' ";
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            while (resultSet.next()) {                
		            	code = resultSet.getInt("ID");
		            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
		 return code;
	 }
	 
	 public String getPermission(int code) {
		 String permission = "";
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select PERMISSION from account where ID =" + code;
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            while (resultSet.next()) {                
		            	permission = resultSet.getString("PERMISSION");
		            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
		 return permission;
	 }
	 
	 public int getCode(String table, String dataToGet, String name, String col) {
			int data = -1;
			if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select " + dataToGet + " from " + table + " where " + col + " =  "+name+" " ;
		            Statement statement = dc.connection.createStatement();
		            
		            ResultSet resultSet = statement.executeQuery(sql);
		           
		            while (resultSet.next()) {                
		            	data = resultSet.getInt(dataToGet);
		            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			
			return data;
		}
	 // hàm sửa tài khoản của người dùng
	 
	 public boolean updateUser(Account p, int accountCode) {
			boolean result = false;
			if(dc.openConnection()) {
				try {
		            //query account 
					String sql = "update account set PASSWORD=?, USERNAME=?, ADDRESS=?, NUMBERPHONE=?, BIRTHDAY=? where ID=?";
		            PreparedStatement statement = dc.connection.prepareCall(sql);
		            
		            statement.setString(1, p.getPassword());
		            statement.setString(2, p.getUserName());
		            statement.setString(3, p.getAddress());
		            statement.setString(4, p.getNumberPhone());
		            statement.setString(5, p.getBirthday());
		            statement.setInt(6, p.getId());
		            
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
	 
	 public int getAccountId() {
			int accountId = -1;
			
			if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select MAX(ID) from account";
		            PreparedStatement statement = dc.connection.prepareCall(sql);
		            
		            ResultSet resultSet = statement.executeQuery();
		            
		            while (resultSet.next()) {                
		            	accountId = resultSet.getInt("MAX(ID)") + 1; 
		            }
		        } catch(SQLException e) {
		        	System.out.println(e);
		        }
				finally {
					dc.closeConnection();
				}
			}
			return accountId;
		}
	 
	 // get customerList and employeeList
	 public List<String> getList(String request) {
			List<String> resultList = new ArrayList<String>();
			
			if(dc.openConnection()) {
				try {
		            //query
					String sql = "select * from account where PERMISSION = ?";						
		            PreparedStatement statement = dc.connection.prepareCall(sql);
	            	statement.setString(1, request);	
		            
		            ResultSet resultSet = statement.executeQuery();
		            
		            while (resultSet.next()) {                
		            	String userName = resultSet.getString("USERNAME");
		            	resultList.add(userName);
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
