package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnect {
	
	 // database connection method
	public Connection connect() { 
		
	 Connection con = null; 
	 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //provide the correct details: DBServer/DBName, UserName, Password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electroGrid", "root", ""); 
	 
	 } catch (Exception e) {
		 e.printStackTrace();
	 } 
	 
	 return con; 
	 
	 }

}

