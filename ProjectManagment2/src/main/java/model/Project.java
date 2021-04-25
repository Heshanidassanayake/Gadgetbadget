package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	//A common method to connect to the DB
		private Connection connect() 
		 { 
			Connection con = null; 
			
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
				
		 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/project_management", "root", ""); 
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
			
			return con; 
		 } 
		
		
		//insert
		public String insertProject( String proCustmerID, String proCustomerName, String proCustomerContact, String projectName, String projectType, String projectDescription, String proHandoverDate ) 
		 { 
			String output = ""; 
			
			try{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for inserting.";
				} 
				
				// create a prepared statement
				String query = "INSERT INTO `projectmanagment`(`ProjectID`, `proCustmerID`, `proCustomerName`, `proCustomerContact`, `projectName`, `projectType`, `projectDescription`, `proHandoverDate`) "
				 		+ " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setInt(2, Integer.parseInt(proCustmerID)); 
				preparedStmt.setString(3, proCustomerName);
				preparedStmt.setString(4, proCustomerContact);
				preparedStmt.setString(5, projectName);
				preparedStmt.setString(4, projectType); 
				preparedStmt.setString(5, projectDescription);
				
				preparedStmt.setString(6, proHandoverDate);
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Inserted successfully"; 
			} 
			
			catch (Exception e) { 
			 
				output = "Error while inserting the project."; 
				System.err.println(e.getMessage()); 
			} 
			
			
		 return output; 
		 
		 
		 } 
		
		
		
		
		 public String readProject() 
		 { 
			
			String output = ""; 
			
			try { 
				
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for reading."; 
				} 
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Project Customer ID</th>" +
						"<th>Customer Name</th>" + 
						"<th>Customer Contact Number</th>" +
						"<th>Project Name</th>" +
						"<th>Project Type</th>" +
						"<th>Project Description</th>" +
						"<th>Project Handover Date</th>" +
						
						"<th>Update</th><th>Remove</th></tr>"; 
		 
				String query = "select * from projectmanagment"; 
				
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				
				// iterate through the rows in the result set
				while (rs.next()){ 
					
					String ProjectID = Integer.toString(rs.getInt("ProjectID")); 
					String proCustmerID = Integer.toString(rs.getInt("proCustmerID")); 
					String proCustomerName  = rs.getString("proCustomerName"); 
				    String proCustomerContact = rs.getString("proCustomerContact"); 
				    String projectName = rs.getString("projectName"); 
				    String projectType = rs.getString("projectType"); 
				    String projectDescription = rs.getString("projectDescription");
				    String proHandoverDate = rs.getString("proHandoverDate"); 
		
					
					// Add into the html table
					output += "<tr><td>" + proCustmerID + "</td>"; 
					output += "<td>" + proCustomerName + "</td>"; 
					output += "<td>" + proCustomerContact + "</td>"; 
					output += "<td>" + projectName + "</td>"; 
					output += "<td>" + projectType + "</td>"; 
					output += "<td>" + projectDescription + "</td>";
					output += "<td>" + proHandoverDate + "</td>"; 
					
					; 
					
					
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='funds.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='fundID' type='hidden' value='" + ProjectID
							+ "'>" + "</form></td></tr>"; 
				} 
				
				
				
			con.close(); 
			
			
		 // Complete the html table
		 output += "</table>"; 
		 
			} 
			
			
			catch (Exception e) 
			{ 
				output = "Error while reading the project."; 
				System.err.println(e.getMessage()); 
			} 
			
				return output; 
				
				
		 }
		 
		 
		 
		 public String updateProject(String ProjectID, String proCustmerID, String proCustomerName,String proCustomerContact, String projectName, String projectType, String projectDescription,String proHandoverDate)
			{ 
				 String output = ""; 
				 
				 try
				 	{ 
					 	Connection con = connect(); 
					 	
					 	if (con == null){
					 		return "Error while connecting to the database for updating."; 
					 	} 
					 	
					 	
					 	// create a prepared statement
					 	String query ="UPDATE `projectmanagment` SET `proCustmerID`=?,`proCustomerName`=?,`proCustomerContact`=?,`projectName`=?,`projectType`=?,`projectDescription`=?,`proHandoverDate`=? WHERE `projectID`=?";
					 	PreparedStatement preparedStmt = con.prepareStatement(query); 
					 	
					 	
					 	// binding values
					 
						preparedStmt.setInt(1, Integer.parseInt(proCustmerID)); 
						preparedStmt.setString(2, proCustomerName); 
						preparedStmt.setInt(3, Integer.parseInt(proCustomerContact)); 
						preparedStmt.setString(4, projectName);
						preparedStmt.setString(5, projectType);
						preparedStmt.setString(6, projectDescription);
						preparedStmt.setString(7, proHandoverDate);
						 
					
					 	// execute the statement
					 	preparedStmt.execute(); 
					 	con.close(); 
					 	output = "Updated successfully";
					 	
				 } 
				 
				 catch (Exception e) 
				 { 
					 output = "Error while updating the project."; 
					 System.err.println(e.getMessage()); 
				 } 
				 	return output; 
			 } 
			
			
			public String deleteProject(String ProjectID)  { 
				String output = ""; 
				
				try { 
					Connection con = connect(); 
					
					if (con == null){
						return "Error while connecting to the database for deleting.";
					} 
					
					// create a prepared statement
					String query = "DELETE FROM `projectmanagment` WHERE `ProjectID `=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(ProjectID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the project."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }



}
