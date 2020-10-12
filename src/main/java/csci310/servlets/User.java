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
@WebServlet("/User")
public class User extends HttpServlet {
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
        String confirm = request.getParameter("confirm");
        
           
        // * Set default JSON to failed login
     	String jsonStr = "{\"Error\": \"Creating User Failed.\"}";
     	
     	// * Send JSON Object as a response
		response.setContentType("application/json");
		
		// Get the PrintWriter object from response to write the required JSON object to the output stream      
		PrintWriter out = response.getWriter();
		
		boolean invalid_request = false;
		if(username == null || username.equals("")) {
			jsonStr = "{\"Error\": \"Creating User. Invalid username.\"}";
			invalid_request = true;
        }
		else if(password == null || password.equals("")) {
        	jsonStr = "{\"Error\": \"Creating User. Invalid password.\"}";
			invalid_request = true;
        }
        else if(confirm == null || confirm.equals("")) {
        	jsonStr = "{\"Error\": \"Creating User. Invalid confirm password.\"}";
			invalid_request = true;
        }
        else if(!password.equals(confirm)) {
            jsonStr = "{\"Error\": \"Creating User. Passwords don't match.\"}";
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
        		
        		// * Cannot create duplicate users
        		if(rs.next()) {
        			jsonStr = "{\"Error\": \"User already exists.\"}";
        		} else {
        			// * Create User
        			ps = conn.prepareStatement("INSERT INTO Users (username, password) VALUES ('" + username + "', '" + password + "' );");
					ps.executeUpdate();
					jsonStr = "{\"Success\": \"Successfully created user.\"}";
        		}
        		
        		// * Send Result
        		out.print(jsonStr);
        		out.flush();
        	} 
        	catch (SQLException sqle) { /* Ignore */ } 
        	finally { }
        }
    }
}