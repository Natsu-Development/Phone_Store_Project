package DAL;

import java.sql.*;

public class databaseConnect {
	public Connection connection;
	public boolean openConnection() {
		 try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_project?useSSL=false", "root", "");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	 }
	 
	 public void closeConnection() {
		 try {
			 if(connection!=null) {
				 connection.close();
			 } 
		 } catch(SQLException e) {
			 System.out.println(e);
		 }
	 }
}
