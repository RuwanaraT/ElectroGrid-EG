package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBconnect;

public class pay {

	// create object of dbconnect
	DBconnect dbcon = new DBconnect();

	// implementing insert payment
	public String createPayBill(String acntNumber, String billID, String payAmount, String cardNumber, String expiry, String CVC) {

		String output = "";
		try {

			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO pay (`paymentID`,`acntNumber`,`billID`,`payAmount`,`cardNumber`,`expiry`,`CVC`,`date`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ? , ? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(acntNumber));
			preparedStmt.setInt(3, Integer.parseInt(billID));
			preparedStmt.setDouble(4, Double.parseDouble(payAmount));
			preparedStmt.setInt(5, Integer.parseInt(cardNumber));
			preparedStmt.setString(6, expiry);
			preparedStmt.setInt(7, Integer.parseInt(CVC));
			preparedStmt.setTimestamp(8, null);

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

	// implementing create card details
	public String createCard(String cardNumber, String acntNumber, String expiry, String CVC) {

		String output = "";
		try {

			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " INSERT INTO card (`cardNumber`,`acntNumber`,`expiry`,`CVC`)" + " VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cardNumber));
			preparedStmt.setInt(2, Integer.parseInt(acntNumber));
			preparedStmt.setString(3, expiry);
			preparedStmt.setInt(4, Integer.parseInt(CVC));

			// execute the statement
			preparedStmt.execute();

			// close the connection
			con.close();

			output = "card successfully inserted";

		} catch (Exception e) {

			output = "Error while processing the card details.";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// Retrieve Operation for Display all the payments

	public String displayPayments() {

		String output = "";

		try {

			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// prepare the payment to be displayed
			output = "<center><table border='1' width='100%'><tr><th colspan='8'>Transaction History</th></tr><tr><td>Payment ID</td>"
					+ "<td>Account Number</td><td>Bill ID</td><td>Payment Amount</td><td>Card Number</td><td>Expiry Date</td>"
					+ "<td>CVC</td><td>Payment Done On</td></tr>";

			String query = "SELECT * FROM pay";
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery(query);

			// iterate through the rows in the result set
			while (rset.next()) {
				String paymentID = Integer.toString(rset.getInt("paymentID"));
				String acntNumber = Integer.toString(rset.getInt("acntNumber"));
				String billID = Integer.toString(rset.getInt("billID"));
				String payAmount = Double.toString(rset.getDouble("payAmount"));
				String cardNumber = Integer.toString(rset.getInt("cardNumber"));
				String expiry = rset.getString("expiry");
				String CVC = Integer.toString(rset.getInt("CVC"));
				String date = rset.getString("date");

				// display payments in a tabular manner
				output += "<tr> <td> " + paymentID + "</td><td>" + acntNumber + "</td><td>" + billID + "</td><td>" + payAmount + "</td><td>"
						+ cardNumber + "</td><td>" + expiry + "</td><td>" + CVC + "</td><td>" + date + "</td></tr>";

			}

			con.close();

			output += "</table> <br/> ***</center>";

		} catch (Exception e) {

			output = "Error while Displaying Payments.";
			System.err.println(e.getMessage());

		}
		return output;

	}

	// Retrieve Operation for display payments related to specific account
	public String displayPayment(String acntNumber) {

		String output = "";

		try {

			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database.";
			}

			// prepare the payment to display
			output = "<center><table border='1' width='100%'><tr><th colspan='8'>My Payments</th>"
					+ "<tr><td>Account Number</td><td>Bill ID</td><td>Pay Amount</td><td>Payment Done On</td>";

			String query = "SELECT acntNumber, billID , payAmount,date FROM pay WHERE acntNumber=?";

			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(acntNumber));

			// execute the statement
			ResultSet rs = preparedStmt.executeQuery();

			// iterate through the rows in the result set
			while (rs.next()) {

				String acNumber = Integer.toString(rs.getInt("acntNumber"));
				String billID = Integer.toString(rs.getInt("billID"));
				String payAmount = Double.toString(rs.getDouble("payAmount"));
				String date = rs.getString("date");

				// show details in a tabular manner
				output += "<tr> <td> " + acNumber + "</td><td>" + billID + "</td><td>" + payAmount + "</td><td>" + date + "</td></tr>";

			}

			con.close();

			// close the displaying
			output += "</table> <br/> ***</center>";

		} catch (Exception e) {

			output = "Error while Displaying Payments.";
			System.err.println(e.getMessage());

		}
		return output;

	}

	// Retrieve Operation for display card
	public String displayCard(String cardNumber) {

		String output = "";

		try {

			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database.";
			}

			// prepare the payment to display
			output = "<center><table border='1' width='100%'><tr><th colspan='2'>My Card</th>";

			String query = "SELECT * FROM card WHERE cardNumber=?";

			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cardNumber));

			// execute the statement
			ResultSet rs = preparedStmt.executeQuery();

			// iterate through the rows in the result set
			while (rs.next()) {

				String cNumber = Integer.toString(rs.getInt("cardNumber"));
				String acntNumber = Integer.toString(rs.getInt("acntNumber"));
				String expiry = rs.getString("expiry");
				String CVC = Integer.toString(rs.getInt("CVC"));

				// show details in a tabular manner
				output += "<tr> <td> Card Number</td> <td>" + cNumber + "</td> </tr>";
				output += "<tr> <td> Account Number </td> <td>" + acntNumber + "</td> </tr>";
				output += "<tr> <td> Expiry Date </td> <td>" + expiry + "</td> </tr>";
				output += "<tr> <td> CVC </td> <td>" + CVC + "</td> </tr>";

				// buttons
				output += "<tr> <td colspan='2'><center><br/><input name='update' type='button' value='updateCard'> <br/><br/>"
						+ "<form method='post' action='#'>" + "<input name='remove' type='submit' value='deleteCard'>"
						+ "<input name='cid' type='hidden' value='" + cardNumber + "'>" + "</form></center></td> </tr>";
			}

			con.close();

			// close the displaying
			output += "</table> <br/> ***</center>";

		} catch (Exception e) {

			output = "Error while Displaying Card.";
			System.err.println(e.getMessage());

		}
		return output;

	}

	// implementing Update card method
	public String updateCard(String acntNumber, String expiry, String CVC, String cardNumber) {

		String output = "";

		try {

			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database.";
			}

			// create a prepared statement
			String query = "UPDATE card SET acntNumber=?, expiry=?, CVC=? WHERE cardNumber=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(acntNumber));
			preparedStmt.setString(2, expiry);
			preparedStmt.setInt(3, Integer.parseInt(CVC));
			preparedStmt.setInt(4, Integer.parseInt(cardNumber));

			// execute the statement
			preparedStmt.execute();

			// close the connection
			con.close();

			output = "card Updated successfully";
		}

		catch (Exception e) {
			output = "Error while Updating the card.";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// implementing Delete card method
	public String deleteCard(String cardNumber) {

		String output = "";
		try {
			// create connection object & call the connection method
			Connection con = dbcon.connect();

			if (con == null) {
				return "Error while connecting to the database.";
			}

			// create a prepared statement
			String query = "DELETE FROM card WHERE cardNumber=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cardNumber));

			// execute the statement
			preparedStmt.execute();

			// close the connection
			con.close();

			output = "Card deleted successfully";

		} catch (Exception e) {
			output = "Error while deleting the Card.";
			System.err.println(e.getMessage());

		}

		return output;

	}

}
