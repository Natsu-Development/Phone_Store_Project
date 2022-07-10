package database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import DAL.databaseConnect;

public class databaseHelp {
	databaseConnect dc = new databaseConnect();
	public int changeNameToCode(String table, String dataToGet, String name, String col) {
		int data = 0;
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select " + dataToGet + " from " + table + " where " + col + " = '"+name+"' ";
	            java.sql.Statement statement = dc.connection.createStatement();
	            
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
	
	public String changeCodeToName(String table, String dataToGet, int name, String col) {
		String data = "";
		if(dc.openConnection()) {
			try {
	            //query
	            String sql = "select " + dataToGet + " from " + table + " where " + col + " =  "+name+" " ;
	            java.sql.Statement statement = dc.connection.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	           
	            while (resultSet.next()) {                
	            	data = resultSet.getString(dataToGet);
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
}
