package com;

import model.Fund;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundService {

Fund FundObj = new Fund(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readBuyerDiscounts() 
	{ 
		return FundObj.readFund(); 
	 }
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	 
	public String insertFund( @FormParam("RecieverID") String RecieverID, @FormParam("ProjectID") String ProjectID,  @FormParam("ProjectName") String ProjectName, @FormParam("TimeRange") String TimeRange, @FormParam("TotalAmount") String TotalAmount, @FormParam("Status") String Status)
	{ 
				String output = FundObj.insertFund(RecieverID, ProjectID, ProjectName, TimeRange, TotalAmount, Status  );
				return output;
			}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFund(String Funddata) 
	{ 
//tring FundData;
	//Convert the input string to a JSON object 
	 JsonObject FundObject = new JsonParser().parse(Funddata).getAsJsonObject(); 
	//Read the values from the JSON object
	 int FundID = FundObject.get("FundID").getAsInt(); 
	 String RecieverID = FundObject.get("RecieverID").getAsString(); 
	 String ProjectID= FundObject.get("ProjectID").getAsString(); 
	 String ProjectName = FundObject.get("ProjectName").getAsString(); 
	 String TimeRange = FundObject.get("TimeRange").getAsString();
	 String TotalAmount = FundObject.get("TotalAmount").getAsString();
	 String Status = FundObject.get("Status").getAsString();

	 String output = FundObj.updateFund(FundID, RecieverID, ProjectID, ProjectName, TimeRange, TotalAmount, Status); 
			 
			 
			 
	return output; 
	}
	
	
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBuyerDiscounts(String FundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(FundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <buyerDiscountID>
	 String buyerDiscountID = doc.select("FundID").text(); 
	 String output = FundObj.deleteFund(buyerDiscountID); 
	return output; 
	}

}
