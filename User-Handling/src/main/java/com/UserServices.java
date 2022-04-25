package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;

@Path("/Users")
public class UserServices{
	
	
	User user = new User();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createUser(@FormParam ("acntNumber") String acntNumber, @FormParam ("fullName") String fullName, @FormParam ("email") String email, @FormParam ("NIC") String NIC,
			@FormParam ("address") String address, @FormParam ("mobileNumber") String mobileNumber, @FormParam ("landpNumber") String landpNumber, @FormParam ("userName") String userName,
			@FormParam ("password") String password, @FormParam ("confirmPassword") String confirmPassword) {
		
		String output = user.createUser(acntNumber, fullName, email, NIC, address, mobileNumber, landpNumber, userName, password, confirmPassword);
		return output;
		
	    }
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String displayUsers() 
	 { 
	 return user.displayUsers(); 
	}
	
	@GET
	@Path("/{id}") 
	@Produces(MediaType.TEXT_HTML) 
	public String displayUser(@PathParam("id") String userName) 
	 { 
	 return user.displayUser(userName); 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUser(String userData) { 
	//Convert the input string to a JSON object 
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String acntNumber = userObject.get("acntNumber").getAsString(); 
	 String fullName = userObject.get("fullName").getAsString(); 
	 String email = userObject.get("email").getAsString(); 
	 String NIC = userObject.get("NIC").getAsString(); 
	 String address = userObject.get("address").getAsString(); 
	 String mobileNumber = userObject.get("mobileNumber").getAsString();
	 String landpNumber = userObject.get("landpNumber").getAsString();
	 String userName = userObject.get("userName").getAsString();
	 
	 String output = user.updateUser(acntNumber, fullName, email, NIC, address, mobileNumber, landpNumber, userName); 
	 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteUser(String userData) {
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String userName = doc.select("userName").text(); 
	 String output = user.deleteUser(userName); 
	return output; 
	}
	
	
	
}
