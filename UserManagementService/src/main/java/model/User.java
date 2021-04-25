package model;

import java.sql.*;


public class User {

	
	
	
	
	//A common method to connect to the DB
			private Connection connect() 
			{
					Connection con = null;
					
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						
						//Provide the correct details: DBServer/DBName, username, password 
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", ""); 
					}
					catch(Exception e)
					{e.printStackTrace();} 
					
					return con; 
			
			}
			
			public String  insertUser(String UserFirstName, String UserLastName, String UserAddress, String UserNIC, String UserPhoneNumber, String UserEmail, String UserName, String UserPassword) 
			{
					String output = "";
					String phone = "^[0-9]{10}$";
					String NIC = "^[0-9]{9}[vV]$";
					String NIC2 = "^[0-9]{12}$";
					String email = "^(.+)@(.+)$";
					
					if (UserFirstName.isEmpty() || UserLastName.isEmpty() || UserAddress.isEmpty() || UserNIC.isEmpty() || UserPhoneNumber.isEmpty() || UserEmail.isEmpty() || UserName.isEmpty() || UserPassword.isEmpty()) {
						output = "Please Insert Empty Values!";
					}
						
					else if(!(UserPhoneNumber.matches(phone))) {
						output = "Phone Number is Incorrect!";
					}
					
					else if(!(UserNIC.matches(NIC) || UserNIC.matches(NIC2))) {
						output = "Please insert valid NIC Number!";
					}
					
					else if(!(UserEmail.matches(email))) {
						output = "Please enter correct email address!";
					}
					
					else if (UserPassword.length() < 6) {
						output = "Password must be greater than 6 digits!";
					}
					
					else {
							 
							
						
						try 
						{
							Connection con = connect();
							
							if(con == null)
							{return "Error while connecting to the database for inserting."; } 
							
							// create a prepared statement
							String query = "insert into user(`UserId`, `UserFirstName`, `UserLastName`, `UserAddress`, `UserNIC`, `UserPhoneNumber`, `UserEmail`, `UserName`, `UserPassword`)" 
									+ "values(?,?,?,?,?,?,?,?,?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							
							// binding values
							 preparedStmt.setInt(1, 0);
							 preparedStmt.setString(2, UserFirstName);
							 preparedStmt.setString(3, UserLastName);
							 preparedStmt.setString(4, UserAddress);
							 preparedStmt.setString(5, UserNIC);
							 preparedStmt.setString(6, UserPhoneNumber);
							 preparedStmt.setString(7, UserEmail);
							 preparedStmt.setString(8, UserName);
							 preparedStmt.setString(9, UserPassword);
							 
							 
							// execute the statement
							
							 preparedStmt.execute();
							 con.close();
							 output = "Inserted successfully";
						 }
							catch(Exception e)
							{
									output = "Error while inserting the User.";
									System.err.println(e.getMessage());
							}
					
					}
					
						return output;
					
							
			}
	
	
			
			public String readUsers()
			{
				String output = "";
				
				try 
				{
						Connection con = connect();
						
						if(con == null)
						{return "Error while connecting to the database for reading."; } 
						
						
						//Prepare the html table to be displayed
						output = "<table border='1'><tr><th>First Name</th>"
								+ "<th>Last Name</th>"
								+ "<th>Address</th>"
								+ "<th>NIC</th>"
								+ "<th>Phone Number</th>"
								+ "<th>E mail</th>"
								+ "<th>User Name</th>"
								+ "<th>User Password</th>";
						
						String query = "select * from User";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						
						// iterate through the rows in the result set
						while (rs.next())
						{
						 String UserId = Integer.toString(rs.getInt("UserId"));
						 String UserFirstName = rs.getString("UserFirstName");
						 String UserLastName = rs.getString("UserLastName");
						 String UserAddress =rs.getString("UserAddress");
						 String UserNIC = rs.getString("UserNIC");
						 String UserPhoneNumber = rs.getString("UserPhoneNumber");
						 String UserEmail = rs.getString("UserEmail");
						 String UserName = rs.getString("UserName");
						 String UserPassword = rs.getString("UserPassword");
						
						 // Add into the html table
						 output += "<tr><td>" + UserFirstName + "</td>";
						 output += "<td>" + UserLastName + "</td>";
						 output += "<td>" + UserAddress + "</td>";
						 output += "<td>" + UserNIC + "</td>";
						 output += "<td>" + UserPhoneNumber + "</td>";
						 output += "<td>" + UserEmail + "</td>";
						 output += "<td>" + UserName + "</td>";
						 output += "<td>" + UserPassword + "</td>";
						
						 
						// buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td><td><form method='post' action='items.jsp'><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='UserId' type='hidden' value='" + UserId
						 + "'>" + "</form></td></tr>";
						} 
						
						con.close();
						 // Complete the html table
						 output += "</table>";
						 
				}
			   catch (Exception e)
				{
				 output = "Error while reading the Users.";
				 System.err.println(e.getMessage());
			    }
				 return output;
		     } 
								
								
				public String updateUser(String UserId, String UserFirstName, String UserLastName, String UserAddress, String UserNIC, String UserPhoneNumber, String UserEmail, String UserName, String UserPassword) 
				{
					String output = "";
					String phone = "^[0-9]{10}$";
					String NIC = "^[0-9]{9}[vV]$";
					String NIC2 = "^[0-9]{12}$";
					String email = "^(.+)@(.+)$";
					
					if (UserFirstName.isEmpty() || UserLastName.isEmpty() || UserAddress.isEmpty() || UserNIC.isEmpty() || UserPhoneNumber.isEmpty() || UserEmail.isEmpty() || UserName.isEmpty() || UserPassword.isEmpty()) {
						output = "Please Insert Empty Values!";
					}
						
					else if(!(UserPhoneNumber.matches(phone))) {
						output = "Phone Number is Incorrect!";
					}
					
					else if(!(UserNIC.matches(NIC) || UserNIC.matches(NIC2))) {
						output = "Please insert valid NIC Number!";
					}
					
					else if(!(UserEmail.matches(email))) {
						output = "Please enter correct email address!";
					}
					
					else if (UserPassword.length() < 6) {
						output = "Password must be greater than 6 digits!";
					}
					
					else {
					
					
							 try
							 {
								 Connection con = connect();
								 if (con == null)
								 {return "Error while connecting to the database for updating."; }
								 
								// create a prepared statement
								 String query = "UPDATE user SET UserFirstName=?,UserLastName=?,UserAddress=?,UserNIC=?,UserPhoneNumber=?,UserEmail=?,UserName=?,UserPassword=? WHERE UserId=?";
									
								 PreparedStatement preparedStmt = con.prepareStatement(query); 
								 
								 //binding values
								
								 preparedStmt.setString(1, UserFirstName);
								 preparedStmt.setString(2, UserLastName);
								 preparedStmt.setString(3, UserAddress);
								 preparedStmt.setString(4, UserNIC);
								 preparedStmt.setString(5, UserPhoneNumber);
								 preparedStmt.setString(6, UserEmail);
								 preparedStmt.setString(7, UserName);
								 preparedStmt.setString(8, UserPassword);
								 preparedStmt.setInt(9,Integer.parseInt(UserId));
								 
								 
								// execute the statement
								
								 preparedStmt.execute();
								 con.close();
									
								 output = "Updated successfully";
							 }
							 catch (Exception e)
							 {
								 output = "Error while updating the user.";
								 System.err.println(e.getMessage());
							 }
					}
					
					 return output;
			    } 
						
				
				
				public String deleteUser(String UserId) 
				{
					String output = "";
					
					 try 
					 {
						 Connection con = connect(); 
						 
						 if (con == null)
						 {return "Error while connecting to the database for deleting."; } 
						 
						// create a prepared statement
						 String query = "delete from user where UserId=?";
						 
						 PreparedStatement preparedStmt = con.prepareStatement(query); 
						 
						// binding values
						 preparedStmt.setInt(1, Integer.parseInt(UserId)); 
						 
						// execute the statement
						 preparedStmt.execute();
						 con.close();
						 output = "Deleted successfully"; 
					 }
						
					 catch (Exception e)
					 {
					 output = "Error while deleting the user.";
					 System.err.println(e.getMessage());
					 }	
						
					 return output; 
									
				
				  }
				
				
				
				public String UserLogin(String UserName, String UserPassword) {

					 try {
					Connection con = connect();
					if (con == null)
					{
					return "Error while connecting to the database for Loging.";
					}

					 //System.out.println("wor");
					String query = "select `UserName`,`UserPassword` from `user` where `UserName` = ? and `UserPassword` = ?";
					PreparedStatement preparedStat = con.prepareStatement(query);
					System.out.println(preparedStat);
					System.out.println(UserName);
					System.out.println(UserPassword);
					preparedStat.setString(1, UserName);
					preparedStat.setString(2, UserPassword);
					ResultSet rs = preparedStat.executeQuery();

					if(rs.next()) {
					con.close();
					return "Welcome "+ UserName +". "+"Login is Susseccfully!";
					}
					else {
					con.close();
					
					if(UserName.equals("Admin") && UserName.equals("Admin")) 
					{
						return "Welcome Admin!";				}
					
					else if(UserName.equals("")) {
					return "Username cannot be empty";
					}
					else if(UserPassword.equals("")) {
					return "Password cannot be empty";
					}
					else {
					return "Incorrect Username or Password ! ";
					}
					}

					 }
					catch(Exception e)
					{
					System.out.println(e);
					return "Error while connecting to the database for login.";

					 }

					 }
				
	
}
