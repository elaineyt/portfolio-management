package csci310.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

/**
 * Servlet implementation class Profile
 */
@WebServlet(name="Login",urlPatterns={"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
	
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
		
		boolean locked_out = false;
		
		boolean invalid_request = false;
		if(username == null) {
			jsonStr = "{\"Error\": \"Failed to log in user. No username provided.\"}";
			invalid_request = true;
        }
		else if(username.equals("")) {
			jsonStr = "{\"Error\": \"Failed to log in user. No username provided.\"}";
			invalid_request = true;
		}
		else if(password == null) {
        	jsonStr = "{\"Error\": \"Failed to log in user. No password provided.\"}";
			invalid_request = true;
        }
		else if(password.equals("")) {
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
        			
        			//hash password
        			String hash_password = MD5.hash(password);
        			
        			// * Check if user's passwords match
        			if(!rs.getString("Password").equals(hash_password)) {
        				jsonStr = "{\"Error\": \"Failed to log in user. Incorrect password.\"}";
        			}
        			
        			//check not locked out
            		
            		//get curr time 
        			Timestamp curr = new Timestamp(System.currentTimeMillis());
        			Timestamp lockout_time = rs.getTimestamp("lockout_time");
        			
        			if(lockout_time != null) {
    	    			long lockout_diff = curr.getTime() - lockout_time.getTime();
    	    			int lockout_time_diff = (int) lockout_diff;
    	    			
    	    			//if they haven't waited a minute
    	    			if(lockout_time_diff < 60000) {
    	    				locked_out = true;
    	    				jsonStr = "{\"Error\": \"Have not waited full minute since lockout.\"}";
    	    			}
    	    			else {
    	    				locked_out = false;
    	    			}
    	    			
        			}
        			
            		// * Send Result
            		if(jsonStr == "{\"Success\": \"Successfully logged in.\"}") {
            	        session.setAttribute("username", username);
        				
            	        String sqlUpdate = "UPDATE Users " 
            	        		+ "SET failed_login_attempts = ? "
            	                + "WHERE username = ?";
    		 	        ps = conn.prepareStatement(sqlUpdate);
    		 	        ps.setInt(1, 0);
    		 	        ps.setString(2, username);
    		 	        ps.executeUpdate();
            		}
            		//increment failed login attempts
            		else if(locked_out != true){
            			
            			int failed_attempts = rs.getInt("failed_login_attempts");
            			Timestamp time_now = new Timestamp(System.currentTimeMillis());
            			Timestamp first_fail_time = rs.getTimestamp("first_fail_time");
            			
            			//lock out for a minute
            			long diff = 0;
            			int timeDiff = 0;
            			if(first_fail_time != null) {
            				diff = time_now.getTime() - first_fail_time.getTime();
            				timeDiff = (int) diff;
            			}
            			
            			if(failed_attempts >= 2 && timeDiff < 60000) {
            				
            				//set fails back to 0
            				jsonStr = "{\"Error\": \"Exceeded number of login attempts.\"}";
            				String sqlUpdate = "UPDATE Users " 
    		        	        		+ "SET failed_login_attempts = ? "
    		        	                + "WHERE username = ?";
    			 	        ps = conn.prepareStatement(sqlUpdate);
    			 	        ps.setInt(1, 0);
    			 	        ps.setString(2, username);
    			 	        ps.executeUpdate();
    			 	        
    			 	        //set lockout start time
    			 	       java.util.Date utilDate = new Date();
           				 	  sqlUpdate = "UPDATE Users " 
    	        	        		+ "SET lockout_time  = ?"
    	        	                + "WHERE username = ?";
    	 			 	      ps = conn.prepareStatement(sqlUpdate);
    	 			 	      ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
    	 			 	      ps.setString(2, username);
    	 			 	      ps.executeUpdate();
    			 	        
            			}
            			else {
            				
            				//increment failed login attempts
            				failed_attempts = failed_attempts + 1;
            				
            				//add date time if first fail here
            				if(failed_attempts==1) {
            					
            					 java.util.Date utilDate = new Date();
                 				 String sqlUpdate = "UPDATE Users " 
     		        	        		+ "SET first_fail_time = ?"
     		        	                + "WHERE username = ?";
    		 			 	      ps = conn.prepareStatement(sqlUpdate);
    		 			 	      ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
    		 			 	      ps.setString(2, username);
    		 			 	      ps.executeUpdate();
            				}
            				
            				//update number of fails
            				String sqlUpdate = "UPDATE Users " 
    	        	        		+ "SET failed_login_attempts = ? "
    	        	                + "WHERE username = ?";
    				        ps = conn.prepareStatement(sqlUpdate);
    				        ps.setInt(1, failed_attempts);
    				        ps.setString(2, username);
    				        ps.executeUpdate();
    				        
            			}	
            		}
        		} else {
        			jsonStr = "{\"Error\": \"Failed to log in user. User doesn't exist.\"}";
        		}
        		
        		out.print(jsonStr);
        		out.flush();
        	} 
        	catch (SQLException sqle) { /* Ignore */ } 
        	finally {
        		try {
					conn.close();
				} catch (SQLException e) {}
        		
        	}
        }
    }
}