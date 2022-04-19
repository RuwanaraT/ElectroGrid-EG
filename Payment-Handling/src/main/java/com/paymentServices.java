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
	public String createPayBill(@FormParam("payAmount") String payAmount, @FormParam("cardNumber") String cardNumber, @FormParam("expiry") String expiry, @FormParam("CVC") String CVC) { 
		
	 String output = paybill.createPayBill(payAmount, cardNumber, expiry, CVC); 
	 return output; 
	
	}
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String displayPayments() 
	 { 
		return paybill.displayPayments(); 
	 }

}
