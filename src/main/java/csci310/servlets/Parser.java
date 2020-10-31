package csci310.servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.text.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.nio.file.Paths;
import java.util.Date;



@WebServlet(name="parser",urlPatterns={"/parser"})
public class Parser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static ResultSet rs2 = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parser() {
        super();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        BufferedReader br = null;
        System.out.println(fileName);
        String line = "";
        String split = ",";

        try {//

            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] stock = line.split(split);

                System.out.println("Stock [ticker= " + stock[0] + " , amount=" + stock[1] + " , date=" + stock[2] + " , date=" + stock[3] + "]");
                String dateSplit = "/";
                String[] date = stock[2].split(dateSplit);
                if(Integer.parseInt(stock[2]) < 1)
                {
                	stock[2] = "1";
                }
                String[] dateB = stock[2].split(dateSplit);
                if(Integer.parseInt(stock[2]) < 1)
                {
                	stock[2] = "1";
                }
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            	Date d = new Date();
            	System.out.println(dateFormat.format(d));
                if(Integer.parseInt(date[0]) > 12 || Integer.parseInt(date[0]) < 1)
                {
                	
                	stock[1] = dateFormat.format(d);
                	System.out.println(stock[1]);
                }
                if(Integer.parseInt(dateB[0]) > 12 || Integer.parseInt(dateB[0]) < 1)
                {
                	
                	stock[1] = dateFormat.format(d);
                	System.out.println(stock[1]);
                }
                String username = request.getParameter("username");
                String position = stock[0];
                String share_count = stock[1];
                String date_bought = stock[2];
                String date_sold = stock[3];
                
                // * Set default JSON to failed login
             	String jsonStr = "{\"Error\": \"Creating Portfolio Position Failed.\"}";
             	
             	// * Send JSON Object as a response
        		response.setContentType("application/json");
        		
        		// Get the PrintWriter object from response to write the required JSON object to the output stream      
        		PrintWriter out = response.getWriter();
        		
        		boolean invalid_request = false;
        		if(username == null || username.length() == 0 || username.length() > 50) {
        			jsonStr = "{\"Error\": \"Creating Portfolio Position Failed. Invalid username.\"}";
        			invalid_request = true;
                }
                if(position == null || position.length() > 50) {
                	jsonStr = "{\"Error\": \"Creating Portfolio Position Failed. Invalid position.\"}";
        			invalid_request = true;
                }
                if(share_count == null) {
                	jsonStr = "{\"Error\": \"Creating Portfolio Position Failed. Invalid share count.\"}";
        			invalid_request = true;
                }
                if(date_bought == null || date_bought.length() == 0 || date_bought.length() > 200) {
                	jsonStr = "{\"Error\": \"Creating Portfolio Position Failed. Invalid date bought.\"}";
        			invalid_request = true;
                }
                if(date_sold == null || date_sold.length() == 0 || date_sold.length() > 200) {
                	jsonStr = "{\"Error\": \"Creating Portfolio Position Failed. Invalid date sold.\"}";
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
                			ps = conn.prepareStatement("INSERT INTO Portfolio (username, position, share_count, date_bought, date_sold) VALUES ('" + username + "', '" + position + "', '" + share_count + "', STR_TO_DATE('" + date_bought.replace('/', '-') + "', '%m-%d-%Y'), STR_TO_DATE('" + date_sold.replace('/', '-') + "', '%m-%d-%Y'));");
                			ps.executeUpdate();
                			
        					jsonStr = "{\"Success\": \"Successfully added portfolio position.\"}";
                		} else {
                			jsonStr = "{\"Error\": \"Creating Portfolio Position Failed. No user found.\"}";
                		}
                		
                		// * Send Result
                		out.print(jsonStr);
                		out.flush();
                		
                	} catch (SQLException sqle) {
                		System.out.println ("SQLException: " + sqle.getMessage());
                	} catch (ClassNotFoundException cnf) { /* Ignore - invalid project setup */ } 
                	finally { }
                }
                try {

        			//if not valid, it will throw ParseException
                	SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
        			Date da = s.parse(stock[1]);
        			System.out.println(da);

        		} catch (Exception e) {

        			stock[1] = dateFormat.format(d);
        		}
                System.out.println(stock[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
