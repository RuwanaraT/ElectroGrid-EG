package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBconnect;

public class pay {
	
		// create object of dbconnect
		DBconnect dbcon = new DBconnect();
		
		
		
		
		//create payment
		public String createPayBill(String payAmount, String cardNumber, String expiry, String CVC) { 
			
		 String output = ""; 
		 try
		 { 
			 
		// create connection object & call the connection method	 
		 Connection con = dbcon.connect(); 
		 
		 if (con == null) {
			 return "Error while connecting to the database"; 
		} 
		 
		 // create a prepared statement
		 String query = " INSERT INTO payment (`paymentID`,`payAmount`,`cardNumber`,`expiry`,`CVC`)" + " VALUES (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, 0);  
		 preparedStmt.setDouble(2, Double.parseDouble(payAmount));
		 preparedStmt.setInt(3, Integer.parseInt(cardNumber)); 
		 preparedStmt.setString(4, expiry); 
		 preparedStmt.setInt(5, Integer.parseInt(CVC)); 
		
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 
		 // close the connection
		 con.close(); 
		 
		 output = "payment successfully done."; 
		 
		 } catch (Exception e) { 
			 
		 output = "Error while processing the payment."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 
		 return output;
		 
		 }
		
		
		
		
		
		// create card details
				public String createCard(String cardNumber, String acntNumber,  String expiry, String CVC) { 
					
				 String output = ""; 
				 try
				 { 
					 
				// create connection object & call the connection method	 
				 Connection con = dbcon.connect(); 
				 
				 if (con == null) {
					 return "Error while connecting to the database"; 
				} 
				 
				 // create a prepared statement
				 String query = " INSERT INTO card (`cardNumber`,`acntNumber`,`expiry`,`CVC`)" + " VALUES (?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setInt(2, Integer.parseInt(cardNumber));
				 preparedStmt.setInt(3, Integer.parseInt(acntNumber));
				 preparedStmt.setString(4, expiry); 
				 preparedStmt.setInt(5, Integer.parseInt(CVC)); 
				
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 
				 // close the connection
				 con.close(); 
				 
				 output = "card successfully inserted"; 
				 
				 } catch (Exception e) { 
					 
				 output = "Error while processing the payment."; 
				 System.err.println(e.getMessage()); 
				 
				 } 
				 
				 return output;
				 
				 }
		
		
		
		
		
		
		
		
		// Retrieve Operation for Display all the payments
		
		public String displayPayments() {
			
		 String output = ""; 
		 
		 try{ 
			
		// create connection object & call the connection method
			 Connection con = dbcon.connect();  
		 
		 if (con == null) {
			 return "Error while connecting to the database"; 
		 } 
		 
		 
		 
		 // prepare the payment to be displayed
		 output = "<center><table border='1' width='100%'><tr><th colspan='2'>Payment History</th>"; 
		 
		 String query = "SELECT * FROM payment"; 
		 Statement statement = con.createStatement(); 
		 ResultSet rset = statement.executeQuery(query); 
		 
		 // iterate through the rows in the result set
		 while (rset.next()) 
		 { 
		 String paymentID = Integer.toString(rset.getInt("paymentID")); 
		 String payAmount = Double.toString(rset.getDouble("payAmount"));
		 String cardNumber = Integer.toString(rset.getInt("cardNumber")); 
		 String expiry =  rset.getString("expiry"); 
		
		 
		 
		 // display body of the e-bill
		 output += "<tr> <td> payment ID </td> <td>" + paymentID + "</td> </tr>"; 
		 output += "<tr> <td> Payment Amount </td> <td>" + payAmount + "</td> </tr>"; 
		 output += "<tr> <td> Card Number </td> <td>" + cardNumber + "</td> </tr>"; 
		 output += "<tr> <td> Expiry Date </td> <td>" + expiry + "</td> </tr>";
		 
	
		 } 
		 
		 con.close(); 
		 
		 // close the e-bill
		 output += "</table> <br/> ***</center>"; 
		 
		 } catch (Exception e) { 
			 
		 output = "Error while Displaying Payments."; 
		 System.err.println(e.getMessage()); 
		 
		 } 
		 return output; 
		 
		 }
		
		
}
