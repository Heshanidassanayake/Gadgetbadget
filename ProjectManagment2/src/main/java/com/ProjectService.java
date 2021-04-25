package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Project;
@Path("/project") 

public class ProjectService {

	Project projectObj = new Project(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readProject() 
	{ 
	
		return projectObj.readProject();  
	 }
	
	
	@POST
	@Path("/postproject")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	 
	public String insertProject( @FormParam("CustomerID") String CustomerID, @FormParam("CustomerName") String CustomerName, @FormParam("ContactNumber") String ContactNumber,  @FormParam("ProjectName") String ProjectName, @FormParam("ProjectType") String ProjectType,  @FormParam("ProjectDescription") String ProjectDescription, @FormParam("HandOverTime") String HandOverTime)
	{ 
			
		
			String output = projectObj.insertProject(CustomerID, CustomerName, ContactNumber, ProjectName, ProjectType, ProjectDescription, HandOverTime);
			return output;
			}
	

	@PUT
	@Path("/putproject") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateProject(String ProjectID) 
	
	
	{ 
	String Project;
	//Convert the input string to a JSON object 
	 JsonObject ProjectObject = new JsonParser().parse(ProjectID).getAsJsonObject(); 
	//Read the values from the JSON object
	 String ProjectID1 = ProjectObject.get("ProjectID").getAsString(); 
	 String CustomerID = ProjectObject.get("CustomerID").getAsString(); 
	 String CustomerName = ProjectObject.get("ProjectName").getAsString(); 
	 String ContactNumber = ProjectObject.get("ContactNumber").getAsString();
	 String ProjectName = ProjectObject.get("ProjectName").getAsString();
	 String ProjectType = ProjectObject.get("ProjectType").getAsString();
	 String ProjectDescription = ProjectObject.get("ProjectDescription").getAsString();
	 String HandOverTime = ProjectObject.get("HandOverTime").getAsString();


	 String output = projectObj.updateProject(ProjectID1, CustomerID, CustomerName, ContactNumber, ProjectName, ProjectType, ProjectDescription, HandOverTime); 
			 
			 
			 
	return output; 
	}
	
	
	
	@DELETE
	@Path("/Deleteproject") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProject(String projectData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <fundID>
	 String ProjectID = doc.select("ProjectID").text(); 
	 String output = projectObj.deleteProject(ProjectID); 
	return output; 
	}
}
