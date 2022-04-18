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


import model.interrupt;

@Path("/interrupts") 
public class InterruptService{
	
	interrupt interrupt = new interrupt();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String createInterrupt(@FormParam("iCode") String iCode, @FormParam("date") String date, @FormParam("start_time") String start_time, @FormParam("end_time") String end_time, @FormParam("duration") String duration, @FormParam("region") String region, @FormParam("reason") String reason) { 
		
	 String output = interrupt.createInterrupt(iCode, date, start_time, end_time, duration, region, reason); 
	return output; 
	
	}
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String Displayinterrupt() 
	 { 
		return interrupt.DisplayInterrupt(); 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateinterrupt(String interruptData) { 
	//Convert the input string to a JSON object 
	 JsonObject ebillObject = new JsonParser().parse(interruptData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String interruptID = ebillObject.get("interruptID").getAsString(); 
	 String iCode = ebillObject.get("iCode").getAsString(); 
	 String date = ebillObject.get("date").getAsString(); 
	 String start_time = ebillObject.get("start_time").getAsString(); 
	 String end_time = ebillObject.get("end_time").getAsString(); 
	 String duration = ebillObject.get("duration").getAsString(); 
	 String region = ebillObject.get("region").getAsString(); 
	 String reason = ebillObject.get("reason").getAsString(); 
	 
	 String output = interrupt.updateInterrupt(interruptID, iCode, date, start_time, end_time, duration, region, reason); 
	 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteInterrupt(String interruptData) {
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(interruptData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String interruptID = doc.select("interruptID").text(); 
	 String output = interrupt.deleteInterrupt(interruptID); 
	return output; 
	}
}