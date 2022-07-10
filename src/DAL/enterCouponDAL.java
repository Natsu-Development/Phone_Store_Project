package DAL;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import EnterCoupon.EnterCoupon;

// chưa tối ưu hóa code ở đây
public class enterCouponDAL {
	
	databaseConnect dc = new databaseConnect(); 
	public enterCouponDAL() {
		
	}
//	List<Product> productList = new ArrayList<Product>();
	// lấy tất cả oke 
	public List<EnterCoupon> findAll() {
		List<EnterCoupon> enterCouponList = new ArrayList<EnterCoupon>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from enter_coupon";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            // hiển thị toàn bộ phiếu nhập và lấy total của tất cả các cột trong ngày
	            while (resultSet.next()) {  
		            	EnterCoupon std = new EnterCoupon(resultSet.getString("COUPON_DATE"), 
		                        getAmount(resultSet.getString("COUPON_DATE")),
		                        getTotal(resultSet.getString("COUPON_DATE")));
		            	enterCouponList.add(std);
	            }
	            
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return enterCouponList;
	}
	public int getTotal(String code) {
		int total = 0;
		if(dc.openConnection()) {
			try {
	            //query
	            String sqlGetTotal = "select SUM(enter_coupon_details.TOTAL) as TOTAL from enter_coupon_details where enter_coupon_details.COUPON_DATE= '"+code+"'";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultTotal = statement.executeQuery(sqlGetTotal);
	            
	            // hiển thị toàn bộ phiếu nhập và lấy total của tất cả các cột trong ngày
	            while(resultTotal.next()) {
	            	total = resultTotal.getInt("TOTAL");
	            }

	            String sqlTotal = "update enter_coupon set TOTAL = ? where COUPON_DATE = ?";
	            PreparedStatement updateTotal = dc.connection.prepareCall(sqlTotal);
	            
	            updateTotal.setInt(1, total);
	            updateTotal.setString(2, code);
	            
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
	public int getAmount(String code) {
		int amount = 0;
		if(dc.openConnection()) {
			try {
	            //query
	            String sqlGetTotal = "select SUM(enter_coupon_details.AMOUNT) as AMOUNT from enter_coupon_details where enter_coupon_details.COUPON_DATE= '"+code+"' ";
	            Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultTotal = statement.executeQuery(sqlGetTotal);
	            
	            // hiển thị toàn bộ phiếu nhập và lấy total của tất cả các cột trong ngày
	            while(resultTotal.next()) {
	            	amount = resultTotal.getInt("AMOUNT");
	            }

	            String sqlTotal = "update enter_coupon set AMOUNT = ? where COUPON_DATE = ?";
	            PreparedStatement updateTotal = dc.connection.prepareCall(sqlTotal);
	            
	            updateTotal.setInt(1, amount);
	            updateTotal.setString(2, code);
	            
	            updateTotal.executeUpdate();
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return amount;
	}
	
	public boolean insert(EnterCoupon p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "insert into enter_coupon(COUPON_DATE, AMOUNT, TOTAL) values (?, ?, ?)";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(1, p.getCouponDate());
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
	public boolean update(EnterCoupon p) {
		boolean result = false;
		if(dc.openConnection()) {
			try {
	            //query
				String sql = "update enter_coupon set EMPLOYEE_CODE=? where COUPON_DATE = ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
	            statement.setString(2, p.getCouponDate());
	            
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
				String sql = "delete from enter_coupon where COUPON_DATE = '"+id+"'";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            
//	            statement.setString(1, id);
	            
	            String sqlDetails = "delete from enter_coupon_details where COUPON_DATE = '"+id+"'";
	            PreparedStatement statementDetails = dc.connection.prepareCall(sqlDetails);
	            
//	            statementDetails.setString(1, id);
	            
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
	public List<EnterCoupon> findByDate(String date) {
		List<EnterCoupon> enterCouponList = new ArrayList<EnterCoupon>();
		
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select * from enter_coupon where COUPON_DATE like ?";
	            PreparedStatement statement = dc.connection.prepareCall(sql);
	            statement.setString(1, "%"+date+"%");
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {  
	            	EnterCoupon std = new EnterCoupon(resultSet.getString("COUPON_DATE"), 
	                        getAmount(resultSet.getString("COUPON_DATE")),
	                        getTotal(resultSet.getString("COUPON_DATE")));
	            	enterCouponList.add(std);
            }
	            
	        } catch(SQLException e) {
	        	System.out.println(e);
	        }
			finally {
				dc.closeConnection();
			}
		}
		return enterCouponList;
	}
	 // check trùng mã phiếu nhập chung
	 public boolean hasCouponDate(String couponDate) {
		 boolean result = false;
		 
		 if(dc.openConnection()) {
				try {
		            //query
		            String sql = "select * from enter_coupon where COUPON_DATE = '"+couponDate+"' ";
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
