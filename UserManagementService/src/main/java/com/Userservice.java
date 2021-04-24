package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")

public class Userservice {

	
	User UserObj = new User();
	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	{
		return UserObj.readUsers();
	}
 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("UserFirstName") String UserFirstName,
	 @FormParam("UserLastName") String UserLastName,
	 @FormParam("UserAddress") String UserAddress,
	 @FormParam("UserNIC") String UserNIC,
	 @FormParam("UserPhoneNumber") String UserPhoneNumber,
	 @FormParam("UserEmail") String UserEmail,
	 @FormParam("UserName") String UserName,
	 @FormParam("UserPassword") String UserPassword)
	{
	 String output = UserObj.insertUser(UserFirstName, UserLastName, UserAddress, UserNIC, UserPhoneNumber, UserEmail, UserName, UserPassword);
	return output;
	}
	
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String UserId = userObject.get("UserId").getAsString();
	 String UserFirstName = userObject.get("UserFirstName").getAsString();
	 String UserLastName = userObject.get("UserLastName").getAsString();
	 String UserAddress= userObject.get("UserAddress").getAsString();
	 String UserNIC = userObject.get("UserNIC").getAsString();
	 String UserPhoneNumber = userObject.get("UserPhoneNumber").getAsString();
	 String UserEmail = userObject.get("UserEmail").getAsString();
	 String UserName = userObject.get("UserName").getAsString();
	 String UserPassword = userObject.get("UserPassword").getAsString();
	 //String output = UserObj.updateUser(UserFirstName, UserLastName, UserAddress, UserNIC, UserPhoneNumber, UserEmail, UserName, UserPassword);
		String output = UserObj.updateUser(UserId, UserFirstName, UserLastName, UserAddress, UserNIC, UserPhoneNumber, UserEmail, UserName, UserPassword);
	 return output;
	}

	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String UserId = doc.select("UserId").text();
	 String output = UserObj.deleteUser(UserId);
	return output;
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String getlogin(String loging) {
	Document doc = Jsoup.parse(loging, "", Parser.xmlParser());
	String UserName = doc.select("UserName").text();
	String UserPassword = doc.select("UserPassword").text();
	String output = UserObj.UserLogin(UserName,UserPassword);
	return output;

	}
	
	
	
}
