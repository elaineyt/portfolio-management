package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.text.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Historical")
public class Historical extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static ResultSet rs2 = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Historical() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param username | String
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
     	String jsonStr = "{\"Error\": \"Profile Fetch Failed. Could not find user with provided username.\"}";
		
     	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// * Connection string for digital ocean db
			conn = DriverManager.getConnection("jdbc:mysql://165.232.50.26:3306/group_30?useSSL=false", "group30", "6HUTzqpAv#SL2kafG#!p");
			ps = conn.prepareStatement("SELECT * from Users where username='" + username + "';");
			rs = ps.executeQuery();
			
			if(rs.next()) {
				// * User successfully found
				ps = conn.prepareStatement("SELECT * from Historical where username='" + username + "';");
        		rs2 = ps.executeQuery();
        		
        		jsonStr = "{\"positions\": [ ";
        		while(rs2.next()) {
        			jsonStr += "{"
    						+ "\"id\": \"" + rs2.getInt("ID") + "\","
    						+ "\"username\": \"" + username+ "\","
    						+ "\"position\": \"" + rs2.getString("position") + "\","
    						+ "\"share_count\": \"" + rs2.getInt("share_count") + "\","
    						+ "\"date_bought\": \"" + rs2.getDate("date_bought") + "\","
    						+ "\"date_sold\": \"" + rs2.getDate("date_sold") + "\" },";
        		}
        		
        		// * Remove last comma after last post object and add bracket closing array of post objects and curly brace ending [jsonStr]
    			jsonStr = jsonStr.substring(0, jsonStr.length() - 1) + "]}";
			}
			
			// * Send JSON Object as a response
			response.setContentType("application/json");
			
			// Get the PrintWriter object from response to write the required JSON object to the output stream      
			PrintWriter out = response.getWriter();
			
			// Returns JSON Object in a String format
			out.print(jsonStr);
			out.flush();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param username | String
	 * @param position | String
	 * @param share_count | Int
	 * @param date_bought | date time
	 * @param date_sold | date time
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String position = request.getParameter("position");
        String share_count = request.getParameter("share_count");
        String date_bought = request.getParameter("date_bought");
        String date_sold = request.getParameter("date_sold");
        
        // * Set default JSON to failed login
     	String jsonStr = "{\"Error\": \"Creating Historical Position Failed.\"}";
     	
     	// * Send JSON Object as a response
		response.setContentType("application/json");
		
		// Get the PrintWriter object from response to write the required JSON object to the output stream      
		PrintWriter out = response.getWriter();
		
		boolean invalid_request = false;
		if(username == null || username.length() == 0 || username.length() > 50) {
			jsonStr = "{\"Error\": \"Creating Historical Position Failed. Invalid username.\"}";
			invalid_request = true;
        }
        if(position == null || position.length() > 50) {
        	jsonStr = "{\"Error\": \"Creating Historical Position Failed. Invalid position.\"}";
			invalid_request = true;
        }
        if(share_count == null) {
        	jsonStr = "{\"Error\": \"Creating Historical Position Failed. Invalid share count.\"}";
			invalid_request = true;
        }
        if(date_bought == null || date_bought.length() == 0 || date_bought.length() > 200) {
        	jsonStr = "{\"Error\": \"Creating Historical Position Failed. Invalid date bought.\"}";
			invalid_request = true;
        }
        if(date_sold == null || date_sold.length() == 0 || date_sold.length() > 200) {
        	jsonStr = "{\"Error\": \"Creating Historical Position Failed. Invalid date sold.\"}";
			invalid_request = true;
        }
        
        // ! Returns JSON Object in a String format due to bad request
        if(invalid_request) {
    		out.print(jsonStr);
    		out.flush();
        } else {
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		// * Connection string for DO DB
        		conn = DriverManager.getConnection("jdbc:mysql://165.232.50.26:3306/group_30?useSSL=false", "group30", "6HUTzqpAv#SL2kafG#!p");
        		ps = conn.prepareStatement("SELECT * from Users where username='" + username + "';");
        		rs = ps.executeQuery();
        		
        		// * Only update profile if user exists
        		if(rs.next()) {
        			// * Create Portfolio Position
        			ps = conn.prepareStatement("INSERT INTO Historical (username, position, share_count, date_bought, date_sold) VALUES ('" + username + "', '" + position + "', '" + share_count + "', STR_TO_DATE('" + date_bought.replace('/', '-') + "', '%m-%d-%Y'), STR_TO_DATE('" + date_sold.replace('/', '-') + "', '%m-%d-%Y'));");
        			ps.executeUpdate();
        			
					jsonStr = "{\"Success\": \"Successfully added historical position.\"}";
        		} else {
        			jsonStr = "{\"Error\": \"Creating Historical Position Failed. No user found.\"}";
        		}
        		
        		// * Send Result
        		out.print(jsonStr);
        		out.flush();
        		
        	} catch (SQLException sqle) {
        		System.out.println ("SQLException: " + sqle.getMessage());
        	} catch (ClassNotFoundException cnf) { /* Ignore - invalid project setup */ } 
        	finally { }
        }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param username | String
	 * @param position | String
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String position = request.getParameter("position");
        
        // * Set default JSON to failed login
     	String jsonStr = "{\"Error\": \"Deleting Historical Position Failed.\"}";
     	
     	// * Send JSON Object as a response
		response.setContentType("application/json");
		
		// Get the PrintWriter object from response to write the required JSON object to the output stream      
		PrintWriter out = response.getWriter();
		
		boolean invalid_request = false;
		if(username == null || username.length() == 0 || username.length() > 50) {
			jsonStr = "{\"Error\": \"Deleting Historical Position Failed. Invalid username.\"}";
			invalid_request = true;
        }
        if(position == null || position.length() > 50) {
        	jsonStr = "{\"Error\": \"Deleting Historical Position Failed. Invalid position.\"}";
			invalid_request = true;
        }
        
        // ! Returns JSON Object in a String format due to bad request
        if(invalid_request) {
    		out.print(jsonStr);
    		out.flush();
        } else {
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		// * Connection string for DO DB
        		conn = DriverManager.getConnection("jdbc:mysql://165.232.50.26:3306/group_30?useSSL=false", "group30", "6HUTzqpAv#SL2kafG#!p");
        		ps = conn.prepareStatement("SELECT * from Users where username='" + username + "';");
        		rs = ps.executeQuery();
        		
        		// * Only update profile if user exists
        		if(rs.next()) {
        			// * Delete Historical Position
        			ps = conn.prepareStatement("DELETE FROM Historical WHERE username='" + username + "' AND position='" + position + "';");
            		ps.executeUpdate();
        			
					jsonStr = "{\"Success\": \"Successfully deleted historical position.\"}";
        		} else {
        			jsonStr = "{\"Error\": \"Deleting Historical Position Failed. No user found.\"}";
        		}
        		
        		// * Send Result
        		out.print(jsonStr);
        		out.flush();
        		
        	} catch (SQLException sqle) {
        		System.out.println ("SQLException: " + sqle.getMessage());
        	} catch (ClassNotFoundException cnf) { /* Ignore - invalid project setup */ } 
        	finally { }
        }
	}

}