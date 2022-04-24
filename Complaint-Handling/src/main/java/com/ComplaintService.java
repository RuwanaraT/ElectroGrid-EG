package com;
import model.Complaint;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Complaint")
public class ComplaintService
{
	Complaint ComplaintObj = new Complaint();
	 
	//Retrieve all
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readComplaint()
	  {
		 return ComplaintObj.displayAllComplaint();
	 }
	 
	 //Retrieve by ID
	 @GET
		@Path("/{id}") 
		@Produces(MediaType.TEXT_HTML) 
		public String DisplayComplaint(@PathParam("id") String ComplaintID) 
		 { 
		 return ComplaintObj.DisplayComplaint(ComplaintID); 
		}
	 
	 
	 //Create new complaint
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertComplaint(@FormParam("accountNumber") String accountNumber,
		  @FormParam("ContactNo") String ContactNo,
	 		@FormParam("ComplaintType") String ComplaintType,
	 		@FormParam("Details") String Details,
	 		@FormParam("Date") String Date)
		 {
		  String output = ComplaintObj.insertComplaint(accountNumber, ContactNo, ComplaintType, Details, Date);
		  return output;
	 }
	 
	 //Update Complaint
	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateComplaint(String complaintData)
	 {
		 //Convert the input string to a JSON object
		  JsonObject itemObject = new JsonParser().parse(complaintData).getAsJsonObject();
		 //Read the values from the JSON object
		  String ComplaintID = itemObject.get("ComplaintID").getAsString();
		  String accountNumber = itemObject.get("accountNumber").getAsString();
		  String ContactNo = itemObject.get("ContactNo").getAsString();
		  String ComplaintType = itemObject.get("ComplaintType").getAsString();
		  String Details = itemObject.get("Details").getAsString();
		  String Status = itemObject.get("Status").getAsString();
		  
		  String output = ComplaintObj.updateComplaint(ComplaintID, accountNumber, ContactNo, ComplaintType, Details, Status);
		 return output;
	 }
	 
	 //Delete Complaint
	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteComplaint(String complaintData)
	 {
		 //Convert the input string to an XML document
		  Document doc = Jsoup.parse(complaintData, "", Parser.xmlParser());
	
		 //Read the value from the element <complaintID>
		  String ComplaintID = doc.select("ComplaintID").text();
		  String output = ComplaintObj.deleteComplaint(ComplaintID);
		 return output;
	 }



}

