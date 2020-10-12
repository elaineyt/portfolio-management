package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param username | String
	 * @param password | String
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        
        // * Set default JSON to failed login
     	String jsonStr = "{\"Success\": \"Successfully logged in.\"}";
     	
     	// * Send JSON Object as a response
		response.setContentType("application/json");
		
		// Get the PrintWriter object from response to write the required JSON object to the output stream      
		PrintWriter out = response.getWriter();
		
		boolean invalid_request = false;
		if(username == null || username.equals("")) {
			jsonStr = "{\"Error\": \"Failed to log in user. No username provided.\"}";
			invalid_request = true;
        }
		else if(password == null || password.equals("")) {
        	jsonStr = "{\"Error\": \"Failed to log in user. No password provided.\"}";
			invalid_request = true;
        }
        
        // ! Returns JSON Object in a String format due to bad request
        if(invalid_request) {
    		out.print(jsonStr);
    		out.flush();
        } else {
        	try {
        		//Class.forName("com.mysql.cj.jdbc.Driver");
        		// * Connection string for DO DB
        		conn = DriverManager.getConnection("jdbc:mysql://165.232.50.26:3306/group_30?useSSL=false", "group30", "6HUTzqpAv#SL2kafG#!p");
        		ps = conn.prepareStatement("SELECT * from Users where username='" + username + "';");
        		rs = ps.executeQuery();
        		
        		// * Cannot log in user that doesn't exist
        		if(rs.next()) {
        			// * Check if user's passwords match
        			if(!rs.getString("Password").equals(password)) {
        				jsonStr = "{\"Error\": \"Failed to log in user. Incorrect password.\"}";
        			}
        		} else {
        			jsonStr = "{\"Error\": \"Failed to log in user. User doesn't exist.\"}";
        		}
        		
        		// * Send Result
        		if(jsonStr == "{\"Success\": \"Successfully logged in.\"}") {
        	        session.setAttribute("username", username);
        		}
        		out.print(jsonStr);
        		out.flush();
        	} 
        	catch (SQLException sqle) { /* Ignore */ } 
        	finally { }
        }
    }
}