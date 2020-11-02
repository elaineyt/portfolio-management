package csci310.servlets;

import static org.junit.Assert.*;
import org.mockito.Mockito;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest extends Mockito {

	String username = "cs310_test_user";
	String password = "test_password";
			
	@Test
    public void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class); 
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Successfully logged in."));
    }
	
	@Test
    public void testNoUsernamePost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Failed to log in user. No username provided."));
    }
	
	@Test
    public void testEmptyUsernamePost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Failed to log in user. No username provided."));
    }
	
	@Test
    public void testNoPasswordPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UUID username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(username.toString());
        
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Failed to log in user. No password provided."));
    }
	
	@Test
    public void testEmptyPasswordPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UUID username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(username.toString());
        when(request.getParameter("password")).thenReturn("");
        
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Failed to log in user. No password provided."));
    }
	
	@Test
    public void testNoUserPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UUID temp_username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(temp_username.toString());
        when(request.getParameter("password")).thenReturn(temp_username.toString());
        when(request.getSession()).thenReturn(session);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Failed to log in user. User doesn't exist."));
    }
	
	@Test
    public void testBadPasswordPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        UUID temp_username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(temp_username.toString()); // * Should be "test_password"
        when(request.getSession()).thenReturn(session);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Failed to log in user. Incorrect password."));
    }
	
	@Test
    public void testBadSQLPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn("test'; SELECT * from Users; SELECT * from Users where username='");
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Login().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().equals(""));
    }

}
