package DAL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import Bill.Bill;

// chưa tối ưu hóa code ở đây
public class billDAL {
	
	databaseConnect dc = new databaseConnect();
	public billDAL() {
		
	}
//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<Bill> findAll() {
		List<Bill> billList = new ArrayList<Bill>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            // hiển thị toàn bộ phiếu nhập và lấy total của tất cả các cột trong ngày
	            while (resultSet.next()) {  
	            	Bill std = new Bill(resultSet.getString("BILL_DATE"), 
		                        getAmount(resultSet.getString("BILL_DATE")), getTotal(resultSet.getString("BILL_DATE")) );
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
	
	public int getTotal(String date) {
		int total = 0;
		if(dc.openConnection()) {
			try {
	            //query
	            String sqlGetTotal = "select SUM(bill_details.TOTAL) as TOTAL from bill_details where bill_details.BILL_DATE= '"+date+"' ";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultTotal = statement.executeQuery(sqlGetTotal);
	            
	            // hiển thị toàn bộ phiếu nhập và lấy total của tất cả các cột trong ngày
	            while(resultTotal.next()) {
	            	total = resultTotal.getInt("TOTAL");
	            }

	            String sqlTotal = "update bill set TOTAL = ? where BILL_DATE = ?";
	            PreparedStatement updateTotal = dc.connection.prepareCall(sqlTotal);
	            
	            updateTotal.setInt(1, total);
	            updateTotal.setString(2, date);
	            
	            updateTotal.executeUpdate();
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return total;
	}
	
	public int getAmount(String date) {
		int total = 0;
		if(dc.openConnection()) {
			try {
	            //query
	            String sqlGetTotal = "select SUM(bill_details.AMOUNT) as AMOUNT from bill_details where bill_details.BILL_DATE= '"+date+"' ";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultTotal = statement.executeQuery(sqlGetTotal);
	            
	            // hiển thị toàn bộ phiếu nhập và lấy total của tất cả các cột trong ngày
	            while(resultTotal.next()) {
	            	total = resultTotal.getInt("AMOUNT");
	            }

	            String sqlTotal = "update bill set AMOUNT = ? where BILL_DATE = ?";
	            PreparedStatement updateTotal = dc.connection.prepareCall(sqlTotal);
	            
	            updateTotal.setInt(1, total);
	            updateTotal.setString(2, date);
	            
	            updateTotal.executeUpdate();
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return total;
	}
	
	public boolean insert(Bill p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into bill(BILL_DATE, AMOUNT, TOTAL) values (?, ?, ?)";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, p.getBillDate());
	            statement.setInt(2, p.getAmount());
	            statement.setInt(3, p.getTotal());
	            
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
	public boolean update(Bill p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
				
	            // phiếu chung
	            String sql = "update bill set EMPLOYEE_CODE=? where BILL_DATE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(2, p.getBillDate());
	            
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
				String sql = "delete from bill where BILL_DATE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, id);
	            
	            String sqlDetails = "delete from bill_details where BILL_DATE = ?";
	            PreparedStatement statementDetails = dc.connection.prepareCall(sqlDetails);
	            
	            statementDetails.setString(1, id);
	            
	            // lỗi
	            if(statementDetails.executeUpdate()>=1 && statement.executeUpdate()>=1) {
	            	result = true;
	            }
	            
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
	public List<Bill> findByDate(String date) {
		List<Bill> billList = new ArrayList<Bill>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from bill where BILL_DATE like ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, "%"+date+"%");
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {  
	            	Bill std = new Bill(resultSet.getString("BILL_DATE"), 
		                        resultSet.getInt("AMOUNT"), resultSet.getInt("TOTAL"));
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
	 // check trùng mã phiếu nhập chung
	 public boolean hasBillCode(String billDate) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from bill where BILL_DATE= '"+billDate+"' ";
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
	 
	 public boolean billNotExist(String billDate) {
		 boolean result = false;
		 if(dc.openConnection()) {
			 try {
		            //query
					String sql = "select * from bill where BILL_DATE= '"+billDate+"'";
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
}
