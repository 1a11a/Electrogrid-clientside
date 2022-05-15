package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consumption {
	
		public Connection connect() {
			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
				// For  testing
				System.out.print("Successfully connected");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return con;
		}
		
		//insert method
		public String insertConsumption(
				String consumptionID, 
				String customerName, 
				String customerUsage, 
				String price, 
				String customerType) {
			Connection con = connect();
			String output = "";
			if (con == null) {
				return "Error while connecting to the database";

			}

			// create a prepared statement
			String query = " insert into consumptions (`ConsumptionID`,`CustomerName`,`CustomerUsage`,`Price`,`CustomerType`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt;
			try {
				preparedStmt = con.prepareStatement(query);

				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, consumptionID);
				preparedStmt.setString(3, customerName);
				preparedStmt.setString(4, customerUsage);
				preparedStmt.setString(5, price);
				preparedStmt.setString(6, customerType);


				
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (SQLException e) {
				output = "Error while inserting";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		//read method
		public String readConsumption() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Counsumption ID</th><th>Customer Name</th><th>Customer Usage</th>" + "<th>Price</th>"
						+ "<th>Customer Type</th></tr>";

				String query = "select * from consumptions";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String consumptionID = Integer.toString(rs.getInt("consumptionID"));
					String customerName = rs.getString("customerName");
					String customerUsage = rs.getString("customerUsage");
					String price = rs.getString("price");
					String customerType = rs.getString("customerType");

					// Add into the html table
					output += "<tr><td>" + consumptionID + "</td>";
					//output += "<td>" + consumptionID + "</td>";
					output += "<td>" + customerName + "</td>";
					output += "<td>" + customerUsage + "</td>";
					output += "<td>" + price + "</td>";
					output += "<td>" + customerType + "</td>";
					// buttons 
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-consumptionID='"
							+ consumptionID + "'>" + "</td></tr>";

				}
				con.close();

				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the consumption Details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//update method
		public String updateConsumption(
				String consumptionID, 
				String customerName, 
				String customerUsage, 
				String price, 
				String customerType, String string)
				

		{
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				String query = " update consumptions set customerName= ? , customerUsage = ? , price = ? , customerType = ?  where consumptionID = ? ";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				
				preparedStmt.setString(2, customerName);
				preparedStmt.setString(3, customerUsage);
				preparedStmt.setString(4, price);
				preparedStmt.setString(4, customerType);
				preparedStmt.setInt(5, Integer.parseInt(consumptionID));

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the consumption Details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		// delete method
				public String deleteConsumption(String consumptionID) {
					String output = "";
					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}
						// create a prepared statement
						String query = "delete from consumptions where consumptionID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						preparedStmt.setInt(1, Integer.parseInt(consumptionID));

						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Deleted successfully";
					} catch (Exception e) {
						output = "Error while deleting the consumption Details.";
						System.err.println(e.getMessage());
					}
					return output;
				}
}
