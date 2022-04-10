package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
		 return "Error while connecting to the database for creating e-bill."; 
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
	
	// Retrieve Operation
	public String DisplayEBill() {
		
	 String output = ""; 
	 
	 try{ 
		 
	 Connection con = dbconn.connect(); 
	 if (con == null) {
		 return "Error while connecting to the database for display e-bill."; 
	 } 
	 
	 // prepare the e-bill to be displayed
	 output = "<center><table border='1' width='100%'><tr><th colspan='2'>Statement of Electricity Account</th>"; 
	 
	 String query = "select * from ebill"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String billID = Integer.toString(rs.getInt("billID")); 
	 String eaNumber = Integer.toString(rs.getInt("eaNumber"));
	 String cusName = rs.getString("cusName"); 
	 String address =  rs.getString("address"); 
	 String billingDate = rs.getString("billingDate"); 
	 String tType = rs.getString("tType"); 
	 String dDates = rs.getString("dDates"); 
	 String conn = rs.getString("conn"); 
	 String amount = Double.toString(rs.getDouble("amount"));
	 
	 // display body of the e-bill
	 output += "<tr> <td> Electricity Account No </td> <td>" + eaNumber + "</td> </tr>"; 
	 output += "<tr> <td> Customer Name </td> <td>" + cusName + "</td> </tr>"; 
	 output += "<tr> <td> Home Address </td> <td>" + address + "</td> </tr>"; 
	 output += "<tr> <td> Billing Date </td> <td>" + billingDate + "</td> </tr>";
	 output += "<tr> <td> Tariff Type </td> <td>" + tType + "</td> </tr>";
	 output += "<tr> <td> Duration </td> <td>" + dDates + "</td> </tr>";
	 output += "<tr> <td> Connection </td> <td>" + conn + "</td> </tr>";
	 output += "<tr> <td> Total Bill Amount </td> <td>" + amount + "</td> </tr>";
	 
	 // buttons
	 output += "<tr> <td colspan='2'><center><br/><input name='update' type='button' value='Update Bill'> <br/><br/>"
	 + "<form method='post' action='#'>"
	 + "<input name='remove' type='submit' value='Remove Bill'>"
	 + "<input name='itemID' type='hidden' value='" + billID 
	 + "'>" + "</form></center></td> </tr>"; 
	 } 
	 
	 con.close(); 
	 
	 // close the e-bill
	 output += "</table> <br/> ***</center>"; 
	 
	 } catch (Exception e) { 
		 
	 output = "Error while Displaying E-Bill."; 
	 System.err.println(e.getMessage()); 
	 
	 } 
	 return output; 
	 
	 } 

}
