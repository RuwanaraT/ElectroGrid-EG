package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.dbconnect;

public class interrupt {
	
	// create object of dbconnect
		dbconnect dbconn = new dbconnect();
		
		// Insert Operation
		public String createInterrupt(String iCode, String Date, String start_time, String end_time, String duration, String region, String reason) { 
			
			 String output = ""; 
			 try
			 { 
				 
			// create connection object & call the connection method	 
			 Connection con = dbconn.connect(); 
			 
			 if (con == null) {
				 return "Error while connecting to the database for creating interrupt."; 
			} 
			 
			 // create a prepared statement
			 String query = " INSERT INTO interrupt ('InterruptID', 'interruptCode', 'Date', 'Start_time', 'End_time', 'Duration', 'Region', 'Reason')" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setInt(2, Integer.parseInt(iCode));
			 preparedStmt.setString(3, Date); 
			 preparedStmt.setString(4, start_time); 
			 preparedStmt.setString(5, end_time); 
			 preparedStmt.setInt(6, Integer.parseInt(duration));
			 preparedStmt.setString(7, region);
			 preparedStmt.setString(8, reason);
			 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 
			 // close the connection
			 con.close(); 
			 
			 output = "Interrupt Created Successfully."; 
			 
			 } catch (Exception e) { 
				 
			 output = "Error while Creating Interrupt."; 
			 System.err.println(e.getMessage()); 
			 
			 } 
		 
		 return output;
		 
		 }
		// Retrieve Operation
		public String DisplayInterrupt() {
			
		 String output = ""; 
		 
		 try{ 
			
		// create connection object & call the connection method
		 Connection con = dbconn.connect(); 
		 
		 if (con == null) {
			 return "Error while connecting to the database for display interrupts."; 
		 } 
		 
		 // prepare the e-bill to be displayed
		 output = "<center><table border='1' width='100%'><tr><th colspan='2'>Interrupt Information</th>"; 
		 
		 String query = "SELECT * FROM interrupt"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String interruptID = Integer.toString(rs.getInt("interruptID")); 
			 String iCode = Integer.toString(rs.getInt("iCode"));
			 String date = rs.getString("date"); 
			 String start_time =  rs.getString("start_time"); 
			 String end_time = rs.getString("end_time"); 
			 String duration = Integer.toString(rs.getInt("duration")); 
			 String region = rs.getString("region"); 
			 String reason = rs.getString("reason"); 
		 
		 // display body of the e-bill
		 output += "<tr> <td> Interrupt Code </td> <td>" + iCode + "</td> </tr>"; 
		 output += "<tr> <td> Date </td> <td>" + date + "</td> </tr>"; 
		 output += "<tr> <td> Start time </td> <td>" + start_time + "</td> </tr>"; 
		 output += "<tr> <td> End time </td> <td>" + end_time + "</td> </tr>";
		 output += "<tr> <td> Duration </td> <td>" + duration + "</td> </tr>";
		 output += "<tr> <td> Region </td> <td>" + region + "</td> </tr>";
		 output += "<tr> <td> Reason </td> <td>" + reason + "</td> </tr>";
		 
		 
		 // buttons
		 output += "<tr> <td colspan='2'><center><br/><input name='update' type='button' value='Update Interrupt'> <br/><br/>"
		 + "<form method='post' action='#'>"
		 + "<input name='remove' type='submit' value='Remove Interrupt'>"
		 + "<input name='itemID' type='hidden' value='" + interruptID 
		 + "'>" + "</form></center></td> </tr>"; 
		 } 
		 
		 con.close(); 
		 
		 // close the interrupt
		 output += "</table> <br/> ***</center>"; 
		 
		 } catch (Exception e) { 
			 
		 output = "Error while Displaying interrupts."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 return output; 
		 
		 }
		
		// Update Operation
		public String updateInterrupt(String iterruptID, String iCode, String Date, String start_time, String end_time, String duration, String region, String reason) {
			
		 String output = ""; 
		 
		 try { 
			 
		 // create connection object & call the connection method	 
		 Connection con = dbconn.connect(); 
		 
		 if (con == null) {
			 return "Error while connecting to the database for updating interrupt."; 
		 } 
		 
		 // create a prepared statement
		 String query = "UPDATE interrupt SET interruptCode=?,Date=?,Start_time=?,End_time=?, Duration=?, Region=?, Reason=? WHERE InterruptID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(iCode)); 
		 preparedStmt.setString(2, Date);
		 preparedStmt.setString(3, start_time);
		 preparedStmt.setString(4, end_time); 
		 preparedStmt.setInt(5, Integer.parseInt(duration));
		 preparedStmt.setString(6, region); 
		 preparedStmt.setString(7, reason); 
		  

		 // execute the statement
		 preparedStmt.execute(); 
		 
		 // close the connection
		 con.close(); 
		 
		 output = "Interrupt Updated successfully"; 
		 } 
		 
		 catch (Exception e) { 
		 output = "Error while Updating the Interrupt."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 
		 return output; 
		 
		 }
		// Delete Operation
		public String deleteInterrupt(String interruptID) { 
			
		 String output = ""; 
		 try { 
		 // create connection object & call the connection method
		 Connection con = dbconn.connect();
		 
		 if (con == null) {
			 return "Error while connecting to the database for deleting interrupt."; 
		 } 
		 
		 // create a prepared statement
		 String query = "DELETE FROM interrupt WHERE InterruptID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(interruptID));
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 
		 // close the connection
		 con.close(); 
		 
		 output = "Interrupt Deleted successfully"; 
		 
		 } 
		 catch (Exception e) { 
		 output = "Error while Deleting the Interrupt."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 
		 return output; 
		 
		 }

}
