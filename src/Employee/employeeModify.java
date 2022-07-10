package Employee;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class employeeModify {
	
	List<Employee> employeeList = new ArrayList<Employee>();
	
	public employeeModify() {
		
	}
	
	public List<Employee> findAll() {
		Connection connection = null;
		Statement statement = null;
		
		try {
	            //lay tat ca danh sach sinh vien
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "select * from employee";
	            statement = connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            
	            while (resultSet.next()) {                
	            	Employee std = new Employee(resultSet.getString("NAME"), 
	                        resultSet.getString("NUMBER_PHONE"), resultSet.getString("ADDRESS"), 
	                        resultSet.getString("EMPLOYEE_CODE"), resultSet.getString("BIRTHDAY"), 
	                        resultSet.getString("POSITION"));
	                employeeList.add(std);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
		
		return employeeList;
	}
	
	 public void insert(Employee e) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            //lay tat ca danh sach sinh vien
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "insert into employee(EMPLOYEE_CODE, NAME, BIRTHDAY, NUMBER_PHONE, ADDRESS, POSITION) values(?, ?, ?, ?, ?, ?)";
	            statement = connection.prepareCall(sql);
	            
	            statement.setString(1, e.getEmployeeCode());
	            statement.setString(2, e.getEmployeeName());
	            statement.setString(3, e.getBirthDay());
	            statement.setString(4, e.getNumberPhone());
	            statement.setString(5, e.getAddress());
	            statement.setString(6, e.getPosition());
	            
	            statement.execute();
	        } catch (SQLException ex) {
	            Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	    }
	 
	 public void update(Employee e) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            //lay tat ca danh sach sinh vien
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "update student set NAME=?, BIRTHDAY=?, NUMBER_PHONE=?, ADDRESS=?, POSITION=? where EMPLOYEE_CODE = ?";
	            statement = connection.prepareCall(sql);
	            
	            statement.setString(1, e.getEmployeeName());
	            statement.setString(2, e.getBirthDay());
	            statement.setString(3, e.getNumberPhone());
	            statement.setString(4, e.getAddress());
	            statement.setString(5, e.getPosition());
	            statement.setString(6, e.getEmployeeCode());
	            
	            statement.execute();
	        } catch (SQLException ex) {
	            Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	    }
	 public void delete(String id) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            //lay tat ca danh sach sinh vien
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "delete from employee where EMPLOYEE_CODE = ?";
	            statement = connection.prepareCall(sql);
	            
	            statement.setString(1, id);
	            
	            statement.execute();
	        } catch (SQLException ex) {
	            Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        //ket thuc.
	    }
	 public List<Employee> findByFullname(String fullname) {	        
	        Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            //lay tat ca danh sach sinh vien
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
	            
	            //query
	            String sql = "select * from employee where NAME like ?";
	            statement = connection.prepareCall(sql);
	            statement.setString(1, "%"+fullname+"%");
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            while (resultSet.next()) {                
	            	Employee std = new Employee(resultSet.getString("NAME"), 
	                        resultSet.getString("NUMBER_PHONE"), resultSet.getString("ADDRESS"), 
	                        resultSet.getString("EMPLOYEE_CODE"), resultSet.getString("BIRTHDAY"), 
	                        resultSet.getString("POSITION"));
	                employeeList.add(std);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            if(statement != null) {
	                try {
	                    statement.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	            
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(employeeModify.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        //ket thuc.
	        
	        return employeeList;
	    }
	 
}
