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

import model.EBill;

@Path("/EBills") 
public class EBillServices {
	
	EBill ebill = new EBill();
		
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String createEBill(@FormParam("eaNumber") int eaNumber, @FormParam("cusName") String cusName, @FormParam("address") String address, @FormParam("billingDate") String billingDate, @FormParam("tType") String tType, @FormParam("dDates") String dDates, @FormParam("conn") String conn, @FormParam("amount") double amount) { 
		
	 String output = ebill.createEBill(eaNumber, cusName, address, billingDate, tType, dDates, conn,  amount); 
	return output; 
	
	}
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String DisplayEBill() 
	 { 
	 return ebill.DisplayEBill(); 
	}


}
