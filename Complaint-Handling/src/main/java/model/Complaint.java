package model;
import java.sql.*;

import util.dbconnect;
public class Complaint
{ //A common method to connect to the DB
	dbconnect dbconn = new dbconnect();
	
	public String insertComplaint(String num, String telNo, String type, String detail, String date, String status)
	 {
		 String output = "";
		 try
		 {
			 Connection con = dbconn.connect(); 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into items_backup (`ComplaintID`,`accountNumber`,`ContactNo`,`ComplaintType`,`Details`,`Date`,`Status`)"
			 + " values (?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, num);
			 preparedStmt.setString(3,telNo);
			 preparedStmt.setString(4, type);
			 preparedStmt.setString(5, detail);
			 preparedStmt.setString(6, date);
			 preparedStmt.setString(7, status);
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the Complaint.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String displayAllComplaint()
	 {
		 String output = "";
		 try
		 {
			 Connection con = dbconn.connect(); 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Account Number</th>" +
			 "<th>Contact No</th>" + 
			 "<th>Complaint Type</th>" +
			 "<th>Details</th><th>Date</th>" +
			 "<th>Status</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
			
			 String query = "select * from items_backup";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String ComplaintID = Integer.toString(rs.getInt("ComplaintID"));
			 String accountNumber = rs.getString("accountNumber");
			 String ContactNo = rs.getString("ContactNo");
			 String ComplaintType = rs.getString("ComplaintType");
			 String Details = rs.getString("Details");
			 String Date = rs.getString("Date");
			 String Status = rs.getString("Status");
			 // Add into the html table
			 output += "<tr><td>" + accountNumber + "</td>";
			 output += "<td>" + ContactNo + "</td>";
			 output += "<td>" + ComplaintType + "</td>";
			 output += "<td>" + Details + "</td>";
			 output += "<td>" + Date + "</td>";
			 output += "<td>" + Status + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>" 
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' value='" + ComplaintID
			 + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the Complaints.";
		 System.err.println(e.getMessage());
		 }
		 return output;
	 }
	
	public String DisplayComplaint(String ComplaintID) {
		
		 String output = ""; 
		 
		 try{ 
			
			 Connection con = dbconn.connect(); 
			 if (con == null)
			 {return "Error while connecting to the database for reading single Complaint."; }
		 
		 // prepare the e-bill to be displayed
		 output = "<center><table border='1' width='100%'><tr><th colspan='2'>Complaint</th>"; 
		 
		 String query = "SELECT * FROM items_backup WHERE ComplaintID=?"; 

		// create a prepared statement
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ComplaintID));
		 
		 // execute the statement
		 ResultSet rs = preparedStmt.executeQuery(); 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String accountNumber = rs.getString("accountNumber");
			 String ContactNo = rs.getString("ContactNo");
			 String ComplaintType = rs.getString("ComplaintType");
			 String Details = rs.getString("Details");
			 String Date = rs.getString("Date");
			 String Status = rs.getString("Status");
			 
		 // display body of the e-bill
		 output += "<tr> <td> Account Number </td> <td>" + accountNumber + "</td> </tr>"; 
		 output += "<tr> <td> Contact Number </td> <td>" + ContactNo + "</td> </tr>"; 
		 output += "<tr> <td> Complaint Type </td> <td>" + ComplaintType + "</td> </tr>";
		 output += "<tr> <td> Details </td> <td>" + Details + "</td> </tr>";
		 output += "<tr> <td> Date </td> <td>" + Date + "</td> </tr>"; 
		 output += "<tr> <td> Status </td> <td>" + Status + "</td> </tr>";
		 
		 // buttons
		 output += "<tr> <td colspan='2'><center><br/><input name='update' type='button' value='Update'> <br/><br/></td>";
		 output += "<tr> <td colspan='2'><center><br/><input name='delete' type='button' value='Remove'> <br/><br/></td></tr>";
		 
		 } 
		 
		 con.close(); 
		 
		 // close the e-bill
		 output += "</table> <br/> ***</center>"; 
		 
		 } catch (Exception e) { 
			 
		 output = "Error while Displaying single Complaint."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 return output; 
		 
		 }
	
	public String updateComplaint(String ID, String num, String telNo, String type, String detail, String date, String status)
	{
			 String output = "";
			 try
			 {
				 Connection con = dbconn.connect(); 
				 if (con == null)
				 {return "Error while connecting to the database for updating."; }
				 // create a prepared statement
				 String query = "UPDATE items_backup SET accountNumber=?,ContactNo=?,ComplaintType=?,Details=?,Date=?,Status=? WHERE ComplaintID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, num);
				 preparedStmt.setString(2, telNo);
				 preparedStmt.setString(3, type);
				 preparedStmt.setString(4, detail);
				 preparedStmt.setString(5, date);
				 preparedStmt.setString(6, status);
				 preparedStmt.setInt(7, Integer.parseInt(ID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the Complaint.";
			 System.err.println(e.getMessage());
			 }
			 return output;
		 }
		public String deleteComplaint (String ID)
		 {
			 String output = "";
			 try
			 {
				 Connection con = dbconn.connect(); 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				 // create a prepared statement
				 String query = "delete from items_backup where ComplaintID=?";
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
			 output = "Error while deleting the Complaint.";
			 System.err.println(e.getMessage());
			 }
			 return output;
		 }
	} 