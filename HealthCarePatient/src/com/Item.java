package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {

	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		String dbURL = "jdbc:mysql://127.0.0.1:3306/healthcare_db" ;
		String dbUsername = "root" ;
		String dbPassword = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 
	
	

	// reading an items -------------------------
	public String readItems()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table class='table'><thead class='thead-dark'><tr><th>Last Name</th> <th>Last Name</th><th>Phone Number</th>"+ "<th>Email</th><th>Age</th><th>Address</th><th>Password</th><th>Update</th><th>Remove</th></tr> </thead>";
			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String PatientID = Integer.toString(rs.getInt("PatientID"));
				String Fname = rs.getString("Fname");
				String Lname = rs.getString("Lname");
				String Pnumber = Integer.toString(rs.getInt("Pnumber"));
				String Email = rs.getString("Email"); 
				String Age = rs.getString("Age");
				String Address = rs.getString("Address");
				String Password = rs.getString("Password");

				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate' type='hidden'value='" + PatientID + "'>" + Fname + "</td>";output += "<td>" + Lname + "</td>";output += "<td>" + Pnumber + "</td>";output += "<td>" + Email + "</td>";output += "<td>" + Age + "</td>";output += "<td>" + Address + "</td>";output += "<td>" + Password + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button'"+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"+"<td><input name='btnRemove' type='button'"+" value='Remove'"+"class='btnRemove btn btn-danger' data-itemid='"+ PatientID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//inserting---------------------
	public String insertItem(String code, String name,String price, String desc, String Age, String Address, String Password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into patient(`PatientID`,`Fname`,`Lname`,`Pnumber`,`Email`, `Age`,`Address`,`Password`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setInt(4, Integer.parseInt(price));
			preparedStmt.setString(5, desc);
			preparedStmt.setInt(6, Integer.parseInt(Age));
			preparedStmt.setString(7, Address);
			preparedStmt.setString(8, Password);
			// execute the statement
			preparedStmt.execute();
			
			 System.out.print("successfuly inserted");
			 
			con.close();
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update items
	public String updateItem(String ID, String code, String name,String price, String desc, String Age, String Address,String Password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE patient SET Fname=?,Lname=?,Pnumber=?,Email=?,Age=?,Address=?,Password=? WHERE PatientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setInt(3, Integer.parseInt(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(Age));
			preparedStmt.setString(6, Password);
			preparedStmt.setString(7, Address);
			preparedStmt.setInt(8, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete items------------------------
	public String deleteItem(String PatientID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patient where PatientID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PatientID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			//output = "Error while deleting the item.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			
			System.err.println(e.getMessage());
		}
		return output;
	}
}
