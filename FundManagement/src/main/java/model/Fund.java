package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Path;


@Path("/Fund")
public class Fund {

	//A common method to connect to the DB
	private Connection connect() 
	 { 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/foundmanagement", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		return con; 
	 } 
	
	
	
	
	public String insertFund( String RecieverID, String ProjectID, String ProjectName, String TimeRange, String TotalAmount, String Status) 
	 { 
		String output = ""; 
		
		try{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting.";
			} 
			
			// create a prepared statement
			String query = "INSERT INTO `Fund`(`FundID`, `RecieverID`, `ProjectID`, `ProjectName`, `TimeRange`, `TotalAmount`, `Status` )"
			 		+ " values (?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			//String fundID = Integer.toString(rs.getInt("fundID")); 
		
			preparedStmt.setInt(2, Integer.parseInt(RecieverID));
			preparedStmt.setInt(3, Integer.parseInt(ProjectID)); 
			preparedStmt.setInt(4, Integer.parseInt(ProjectName));
			
			preparedStmt.setInt(5, Integer.parseInt(TimeRange));
			preparedStmt.setInt(6, Integer.parseInt(TotalAmount));
			preparedStmt.setInt(7, Integer.parseInt(Status));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		
		catch (Exception e) { 
		 
			output = "Error while inserting the discount."; 
			System.err.println(e.getMessage()); 
		} 
		
		
	 return output; 
	 
	 
	 } 
	
	
	
	 public String readFund() 
	 { 
		
		String output = ""; 
		
		try { 
			
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Reciever ID</th>" +
					"<th>Project ID</th>" + 
					"<th>Project Name</th>" +
					"<th>Total Time Range</th>" +
					"<th>Total Amount</th>" +
					
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from fundmanagment"; 
			
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()){ 
				
				 
				String FundID = Integer.toString(rs.getInt("FundID")); 
				String RecieverID  = Integer.toString(rs.getInt("ReceiverID")); 
			    String ProjectID = Integer.toString(rs.getInt("ProjectID")); 
			    String ProjectName  = Integer.toString(rs.getInt("ProjectName")); 
			    String TimeRange = Integer.toString(rs.getInt("TimeRange")); 
			    String TotalAmount = Integer.toString(rs.getInt("TotalAmount")); 
			    String Status = Integer.toString(rs.getInt("Status")); 
				
				// Add into the html table
				
				output += "<tr><td>" + RecieverID + "</td>"; 
				output += "<td>" + ProjectID + "</td>"; 
				output += "<td>" + ProjectName + "</td>"; 
				output += "<td>" + TimeRange + "</td>"; 
				output += "<td>" + TotalAmount + "</td>"; 
				
				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='fund.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='FundID' type='hidden' value='" + FundID 
						+ "<input name='Status' type='hidden' value='" + Status 
						+ "'>" + "</form></td></tr>"; 
			} 
			
			
			
		con.close(); 
		
		
	 // Complete the html table
	 output += "</table>"; 
	 
		} 
		
		
		catch (Exception e) 
		{ 
			output = "Error while reading the fund."; 
			System.err.println(e.getMessage()); 
		} 
		
			return output; 
			
			
	 }
	 
	 
	 
	 
	 
		public String updateFund(int FundID, String RecieverID, String ProjectID, String ProjectName, String TimeRange, String TotalAmount, String Status)
		{ 
			 String output = ""; 
			 
			 try
			 	{ 
				 	Connection con = connect(); 
				 	
				 	if (con == null){
				 		return "Error while connecting to the database for updating."; 
				 	} 
				 	
				 	
				 	// create a prepared statement
				 	String query = "UPDATE fundmanagment SET ReceiverID=?,ProjectiD=?,ProjectName=?,TimeRange=?,TotalAmount=?,Status=? WHERE FundID=?"; 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
				 	
				 	
				 	// binding values
				 
					//preparedStmt.setInt(1, Integer.parseInt(FundID)); 
					preparedStmt.setInt(1, Integer.parseInt(RecieverID)); 
					preparedStmt.setInt(2, Integer.parseInt(ProjectID)); 
					preparedStmt.setString(3,(ProjectName));
					preparedStmt.setString(4,(TimeRange));
					preparedStmt.setDouble(5, Double.parseDouble(TotalAmount));
					preparedStmt.setString(6,(Status));
					preparedStmt.setInt(7, (FundID)); 
				 	
				 	// execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
				 	output = "Updated successfully";
				 	
			 } 
			 
			 catch (Exception e) 
			 { 
				 output = "Error while updating the sss."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
		 } 
		
		
		
		
		
		public String deleteFund(String FundID)  { 
			String output = ""; 
			
			try { 
				Connection con = connect(); 
				
				if (con == null){
					return "Error while connecting to the database for deleting.";
				} 
				
				// create a prepared statement
				String query = "DELETE FROM fundmanagment WHERE FundID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(FundID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the discount."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }




		

	 
	
}
