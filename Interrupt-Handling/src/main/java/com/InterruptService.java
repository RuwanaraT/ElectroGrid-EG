package com;
import model.Interrupt;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/interrupts")
public class InterruptService
{
	 Interrupt interruptObj = new Interrupt();
	 
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readInterrupt()
	  {
		 return interruptObj.displayAllInterrupt();
	 }
	 
	 @GET
		@Path("/{id}") 
		@Produces(MediaType.TEXT_HTML) 
		public String DisplayInterrupt(@PathParam("id") String interruptID) 
		 { 
		 return interruptObj.DisplayInterrupt(interruptID); 
		}
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertInterrupt(@FormParam("interruptCode") String interruptCode,
		  @FormParam("Date") String Date,
		  @FormParam("Duration") String Duration,
		  @FormParam("Start_time") String Start_time,
	 		@FormParam("End_time") String End_time,
	 		@FormParam("Region") String Region,
	 		@FormParam("Reason") String Reason)
		 {
		  String output = interruptObj.insertInterrupt(interruptCode, Date, Duration, Start_time, End_time, Region, Reason);
		  return output;
	 }
	 
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateInterrupt(String interruptData)
	 {
		 //Convert the input string to a JSON object
		  JsonObject itemObject = new JsonParser().parse(interruptData).getAsJsonObject();
		 //Read the values from the JSON object
		  String interruptID = itemObject.get("interruptID").getAsString();
		  String interruptCode = itemObject.get("interruptCode").getAsString();
		  String Date = itemObject.get("Date").getAsString();
		  String Duration = itemObject.get("Duration").getAsString();
		  String Start_time = itemObject.get("Start_time").getAsString();
		  String End_time = itemObject.get("End_time").getAsString();
		  String Region = itemObject.get("Region").getAsString();
		  String Reason = itemObject.get("Reason").getAsString();
		  String output = interruptObj.updateInterrupt(interruptID, interruptCode, Date, Duration, Start_time, End_time, Region, Reason);
		 return output;
	 }
	 
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteInterrupt(String interruptData)
	 {
		 //Convert the input string to an XML document
		  Document doc = Jsoup.parse(interruptData, "", Parser.xmlParser());
	
		 //Read the value from the element <itemID>
		  String interruptID = doc.select("interruptID").text();
		  String output = interruptObj.deleteInterrupt(interruptID);
		 return output;
	 }



}