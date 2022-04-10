package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.dbconnect;

public class EBill {
	
	// create object of dbconnect
	dbconnect dbconn = new dbconnect();
	
	// Insert Operation
	public String createEBill(int eaNumber, String cusName, String address, String billingDate, String tType, String dDates, String conn, double amount) { 
		
	 String output = ""; 
	 try
	 { 
		 
	// create connection object & call the connection method	 
	 Connection con = dbconn.connect(); 
	 
	 if (con == null) {
		 return "Error while connecting to the database for creating ebill."; 
	} 
	 
	 // create a prepared statement
	 String query = " insert into ebill (`billID`,`eaNumber`,`cusName`,`address`,`billingDate`, `tType`, `dDates`, `conn`, `amount`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setInt(2, eaNumber); 
	 preparedStmt.setString(3, cusName); 
	 preparedStmt.setString(4, address); 
	 preparedStmt.setString(5, billingDate); 
	 preparedStmt.setString(6, tType); 
	 preparedStmt.setString(7, dDates); 
	 preparedStmt.setString(8, conn); 
	 preparedStmt.setDouble(9, amount);
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 
	 // close the connection
	 con.close(); 
	 
	 output = "E-Bill Created Successfully."; 
	 
	 } catch (Exception e) { 
		 
	 output = "Error while Creating E-Bill."; 
	 System.err.println(e.getMessage()); 
	 
	 } 
	 
	 return output;
	 
	 }

}
