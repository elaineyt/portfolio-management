package csci310.servlets;

import static org.junit.Assert.*;
import org.mockito.Mockito;

import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;

public class ParserTest extends Mockito {
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
    public void testDoPost() throws Exception {
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
}
