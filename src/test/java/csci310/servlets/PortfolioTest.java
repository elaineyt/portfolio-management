package csci310.servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import java.io.*;
import javax.servlet.http.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;

public class PortfolioTest extends Mockito {
	String long_string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	String longer_string = long_string + long_string + long_string + long_string;
	
	// ------------ doPost Method Testing ----------------
	
	@Test
    public void testLongUsername() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn(long_string);
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid username."));
    }
	 
	@Test
    public void testZeroUsername() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid username."));
    }
	
	@Test
    public void testNoUsername() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid username."));
    }
	
	@Test
    public void testLongPosition() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn(long_string);
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid position."));
    }
	
	@Test
    public void testNoPosition() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        verify(request, atLeast(1)).getParameter("position"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid position."));
    }
	
	@Test
    public void testNoShareCount() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("tsla");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid share count."));
    }
	
	@Test
    public void testLongDateBought() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn(longer_string);
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid date bought."));
    }
	
	@Test
    public void testZeroDateBought() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid date bought."));
    }
	
	@Test
    public void testNoDateBought() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid date bought."));
    }
	
	@Test
    public void testLongDateSold() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn(longer_string);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid date sold."));
    }
	
	@Test
    public void testZeroDateSold() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid date sold."));
    }
	
	@Test
    public void testNoDateSold() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. Invalid date sold."));
    }
	
	@Test
    public void testNoUserFound() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user_94628596032742364885323");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. No user found."));
    }
	
	@Test
    public void testUserNoExist() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user_94628596032742364885323");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("today");
        when(request.getParameter("date_sold")).thenReturn("today");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        
        assertTrue(stringWriter.toString().contains("Creating Portfolio Position Failed. No user found."));
    }
	
	@Test
    public void testSuccessfulDoPost() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");
        when(request.getParameter("share_count")).thenReturn("10");
        when(request.getParameter("date_bought")).thenReturn("10/1/2019");
        when(request.getParameter("date_sold")).thenReturn("10/1/2020");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doPost(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Successfully added portfolio position."));
    }
	
	
	// ----------------- doGet Method Testing ---------------------
	
	@Test
    public void testLongUsernameGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doGet(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Profile Fetch Failed. Could not find user with provided username."));
    }
	
	@Test
    public void testMissingUsernameGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_Us3R_9236523129asc87b43ifew");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doGet(request, response);

        writer.flush();
       
        assertTrue(stringWriter.toString().contains("Profile Fetch Failed. Could not find user with provided username."));
    }
	
	@Test
    public void testBadSQLGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test'; SELECT * from Users; SELECT * from Users where username='");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doGet(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().equals(""));
    }
	
	@Test
    public void testSuccessfulDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doGet(request, response);

        writer.flush();
       
        assertTrue(stringWriter.toString().contains("positions"));
    }
	
	
	// ------------------ doDelete Method Testing ------------------
	
	@Test
    public void testLongUsernameDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn(long_string);
        when(request.getParameter("position")).thenReturn("aapl");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush(); 
        
        assertTrue(stringWriter.toString().contains("Deleting Position Failed. Invalid username."));
    }
	 
	@Test
    public void testZeroUsernameDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("position")).thenReturn("aapl");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Deleting Position Failed. Invalid username."));
    }
	
	@Test
    public void testNoUsernameDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("position")).thenReturn("aapl");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Deleting Position Failed. Invalid username."));
    }
	
	@Test
    public void testLongPositionDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn(long_string);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Deleting Position Failed. Invalid position."));
    }
	
	@Test
    public void testNoPositionDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Deleting Position Failed. Invalid position."));
    }
	
	@Test
    public void testMissingUsernameDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_Us3R_9236523129asc87b43ifew");
        when(request.getParameter("position")).thenReturn("aapl");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush();
       
        assertTrue(stringWriter.toString().contains("Deleting Position Failed. No user found."));
    }
	
	@Test
    public void testBadSQLDoDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test'; SELECT * from Users;");
        when(request.getParameter("position")).thenReturn("aapl");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().equals(""));
    }
	
	@Test
    public void testSuccessfulDoDelete() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("position")).thenReturn("aapl");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Portfolio().doDelete(request, response);

        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Successfully deleted portfolio position."));
    }
	
	
}