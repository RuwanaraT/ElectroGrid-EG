package model;
import java.sql.*;

import util.dbconnect;
public class Interrupt
{ //A common method to connect to the DB
	dbconnect dbconn = new dbconnect();
	
	public String insertInterrupt(String code, String date, String duration, String start, String end, String region, String reason, String adminID)
	 {
		 String output = "";
		 try
		 {
			 Connection con = dbconn.connect(); 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into interrupt (`interruptID`,`interruptCode`,`Date`,`Duration`,`Start_time`,`End_time`,`Region`,`Reason`,`AdminID`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, code);
			 preparedStmt.setString(3, date);
			 preparedStmt.setDouble(4, Double.parseDouble(duration));
			 preparedStmt.setString(5, start);
			 preparedStmt.setString(6, end);
			 preparedStmt.setString(7, region);
			 preparedStmt.setString(8, reason);
			 preparedStmt.setString(9, adminID);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String displayAllInterrupt()
	 {
		 String output = "";
		 try
		 {
			 Connection con = dbconn.connect(); 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Interrupt Code</th><th>Date</th>" +
			 "<th>Duration</th>" +
			 "<th>Start time</th>" +
			 "<th>End time</th>" +
			 "<th>Region</th>" +
			 "<th>Reason</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
			
			 String query = "select * from interrupt";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String interruptID = Integer.toString(rs.getInt("interruptID"));
			 String interruptCode = rs.getString("interruptCode");
			 String Date = rs.getString("Date");
			 String Duration = Double.toString(rs.getDouble("Duration"));
			 String Start_time = rs.getString("Start_time");
			 String End_time = rs.getString("End_time");
			 String Region = rs.getString("Region");
			 String Reason = rs.getString("Reason");
			 // Add into the html table
			 output += "<tr><td>" + interruptCode + "</td>";
			 output += "<td>" + Date + "</td>";
			 output += "<td>" + Duration + "</td>";
			 output += "<td>" + Start_time + "</td>";
			 output += "<td>" + End_time + "</td>";
			 output += "<td>" + Region + "</td>";
			 output += "<td>" + Reason + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 +"<td><input name='btnUpdate' type='button' value='Delete' class='btn btn-secondary'></td>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the interrupt notice.";
		 System.err.println(e.getMessage());
		 }
		 return output;
	 }
	
	public String DisplayInterrupt(String Region) {
		
		 String output = ""; 
		 
		 try{ 
			
			 Connection con = dbconn.connect(); 
			 if (con == null)
			 {return "Error while connecting to the database for reading single interrupt."; }
		 
			 output = "<table border='1'><tr><th>Interrupt Code</th><th>Date</th>" +
					 "<th>Duration</th>" +
					 "<th>Start time</th>" +
					 "<th>End time</th>" +
					 "<th>Region</th>" +
					 "<th>Reason</th>" +
					 "<th>Update</th><th>Remove</th></tr>";
		 
		 String query = "SELECT interruptCode, Date, Duration, Start_time, End_time, Region, Reason FROM interrupt WHERE Region=?"; 

		// create a prepared statement
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1,Region);
		 
		 // execute the statement
		 ResultSet rs = preparedStmt.executeQuery(); 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 		
			 
			 String interruptCode = rs.getString("interruptCode");
			 String Date = rs.getString("Date");
			 String Duration = Double.toString(rs.getDouble("Duration"));
			 String Start_time = rs.getString("Start_time");
			 String End_time = rs.getString("End_time");
			 String Reason = rs.getString("Reason");
			 // Add into the html table
			 output += "<tr><td>" + interruptCode + "</td>";
			 output += "<td>" + Date + "</td>";
			 output += "<td>" + Duration + "</td>";
			 output += "<td>" + Start_time + "</td>";
			 output += "<td>" + End_time + "</td>";
			 output += "<td>" + Region + "</td>";
			 output += "<td>" + Reason + "</td>"; 
		
		 
		 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
					 +"<td><input name='btnUpdate' type='button' value='Delete' class='btn btn-secondary'></td>";
					 }
		 
		 
		 con.close(); 
		 
		 // close the e-bill
		 output += "</table> <br/></center>"; 
		 
		 } catch (Exception e) { 
			 
		 output = "Error while Displaying interrupt based on region."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 return output; 
		 
		 }
	
	
	
	public String updateInterrupt(String ID, String code, String date, String duration, String start, String end, String region, String reason)
	{
			 String output = "";
			 try
			 {
				 Connection con = dbconn.connect(); 
				 if (con == null)
				 {return "Error while connecting to the database for updating."; }
				 // create a prepared statement
				 String query = "UPDATE interrupt SET interruptCode=?,Date=?,Duration=?,Start_time=?,End_time=?,Region=?,Reason=? WHERE interruptID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, code);
				 preparedStmt.setString(2, date);
				 preparedStmt.setDouble(3, Double.parseDouble(duration));
				 preparedStmt.setString(4, start);
				 preparedStmt.setString(5, end);
				 preparedStmt.setString(6, region);
				 preparedStmt.setString(7, reason);
				 preparedStmt.setInt(8, Integer.parseInt(ID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the interrupt notice.";
			 System.err.println(e.getMessage());
			 }
			 return output;
		 }
		public String deleteInterrupt(String ID)
		 {
			 String output = "";
			 try
			 {
				 Connection con = dbconn.connect(); 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				 // create a prepared statement
				 String query = "delete from interrupt where interruptID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(ID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the interrupt notice.";
			 System.err.println(e.getMessage());
			 }
			 return output;
		 }
	} 