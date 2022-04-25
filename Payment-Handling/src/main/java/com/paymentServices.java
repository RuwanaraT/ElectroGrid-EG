package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.pay;

@Path("/payment")
public class paymentServices {
	pay paybill = new pay();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPayBill(@FormParam("acntNumber") String acntNumber, @FormParam("billID") String billID, @FormParam("payAmount") String payAmount,
			@FormParam("cardNumber") String cardNumber, @FormParam("expiry") String expiry,
			@FormParam("CVC") String CVC) {

		String output = paybill.createPayBill(acntNumber, billID ,payAmount, cardNumber, expiry, CVC);
		return output;

	}

	@POST
	@Path("/card")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createCard(@FormParam("cardNumber") String cardNumber, @FormParam("acntNumber") String acntNumber,
			@FormParam("expiry") String expiry, @FormParam("CVC") String CVC) {

		String output = paybill.createCard(cardNumber, acntNumber, expiry, CVC);
		return output;

	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String displayPayments() {
		return paybill.displayPayments();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String displayCard(@PathParam("id") String cardNumber) {
		return paybill.displayCard(cardNumber);
	}

	@GET
	@Path("/pay/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String displayPayment(@PathParam("id") String acntNumber) {
		return paybill.displayPayment(acntNumber);
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCard(String cardData) {

		// Convert the input string to a JSON object
		JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject();

		// Read the values from the JSON object
		String acntNumber = cardObject.get("acntNumber").getAsString();
		String expiry = cardObject.get("expiry").getAsString();
		String CVC = cardObject.get("CVC").getAsString();
		String cardNumber = cardObject.get("cardNumber").getAsString();

		String output = paybill.updateCard(acntNumber, expiry, CVC, cardNumber);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCard(String cardData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(cardData, "", Parser.xmlParser());

		// Read the value from the element <cardNumber>
		String cardNumber = doc.select("cardNumber").text();
		String output = paybill.deleteCard(cardNumber);

		return output;
	}

}
