package com;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;


import model.Device;

@Path("/EDevice")
public class DeviceServices {
	
	Device deviceObject = new Device();
	
	   //read
	    @GET
	    @Path("/")
	    @Produces(MediaType.TEXT_HTML)
	    public String readdevice() {
		return deviceObject.readDevices();
	   }
	
	
	
	
	//insert
	   
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String inserdevice(@FormParam("device") String device, 
		 @FormParam("powerUsage") String powerUsage, 
		 @FormParam("hours") String hours,
		 @FormParam("noOfdevices")String noOfdevices) 
		{ 
		 String output = deviceObject.insertDevice(device, powerUsage, hours,noOfdevices); 
		return output; 
		}

		
		
		
		//delete
		
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deletedevice(String dData) 
		{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(dData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String deviceID = doc.select("deviceID").text(); 
		 String output = deviceObject.deleteDevice(deviceID); 
		return output; 
		}
		
		
		//update
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updatedevice(String dData) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject dObject = new JsonParser().parse(dData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String deviceID = dObject.get("deviceID").getAsString(); 
		 String device = dObject.get("device").getAsString(); 
		 String powerUsage = dObject.get("powerUsage").getAsString(); 
		 String hours = dObject.get("hours").getAsString(); 
		 String noOfdevices =dObject.get("noOfdevices").getAsString();  
		 
		 String output = deviceObject.UpdateDevice(deviceID,device,powerUsage,hours,noOfdevices);
		 return output;
		}
}
