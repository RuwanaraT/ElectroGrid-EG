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
	public String createEBill(@FormParam("eaNumber") String eaNumber, @FormParam("cusName") String cusName, @FormParam("address") String address, @FormParam("billingDate") String billingDate, @FormParam("amount") String amount) { 
		
	 String output = ebill.createEBill(eaNumber, cusName, address, billingDate, amount); 
	return output; 
	
	}
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String DisplayEBill() 
	 { 
	 return ebill.DisplayEBill(); 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateEBill(String ebillData) { 
	//Convert the input string to a JSON object 
	 JsonObject ebillObject = new JsonParser().parse(ebillData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String billID = ebillObject.get("billID").getAsString(); 
	 String eaNumber = ebillObject.get("eaNumber").getAsString(); 
	 String cusName = ebillObject.get("cusName").getAsString(); 
	 String address = ebillObject.get("address").getAsString(); 
	 String billingDate = ebillObject.get("billingDate").getAsString(); 
	 String amount = ebillObject.get("amount").getAsString(); 
	 
	 String output = ebill.updateEBill(billID, eaNumber, cusName, address, billingDate, amount); 
	 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String ebillData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(ebillData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String billID = doc.select("billID").text(); 
	 String output = ebill.deleteEBill(billID); 
	return output; 
	}



}
