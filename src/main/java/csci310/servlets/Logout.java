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
@WebServlet(name="Logout",urlPatterns={"/logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param username | String
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username", "");
        
        // * Set default JSON to failed login
     	String jsonStr = "{\"Success\": \"Successfully logged out.\"}";
     	
     	// * Send JSON Object as a response
		response.setContentType("application/json");
		
		// Get the PrintWriter object from response to write the required JSON object to the output stream      
		PrintWriter out = response.getWriter();
		
		out.print(jsonStr);
		out.flush();
    }
}