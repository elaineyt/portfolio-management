package csci310.servlets;

import static org.junit.Assert.*;
import org.mockito.Mockito;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest extends Mockito {

	@Test
    public void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        UUID username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(username.toString());
        when(request.getParameter("password")).thenReturn("test_password");
        when(request.getParameter("confirm")).thenReturn("test_password");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Successfully created user."));
    }
	
	@Test
    public void testNoUsernamePost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("password")).thenReturn("test_password");
        when(request.getParameter("confirm")).thenReturn("test_password");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Creating User. Invalid username."));
    }
	
	@Test
    public void testNoPasswordPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        UUID username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(username.toString());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Creating User. Invalid password."));
    }
	
	@Test
    public void testNoConfirmPasswordPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        UUID username = UUID.randomUUID();
        when(request.getParameter("username")).thenReturn(username.toString());
        when(request.getParameter("password")).thenReturn("test-password");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Creating User. Invalid confirm password."));
    }
	
	@Test
    public void testUserExistsPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test_user");
        when(request.getParameter("password")).thenReturn("test_password");
        when(request.getParameter("confirm")).thenReturn("test_password");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("User already exists."));
    }
	
	@Test
    public void testBadSQLPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    

        when(request.getParameter("username")).thenReturn("test'; SELECT * from Users; SELECT * from Users where username='");
        when(request.getParameter("password")).thenReturn("test_password");
        when(request.getParameter("confirm")).thenReturn("test_password");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().equals(""));
    }
	
	@Test
    public void testDifferentPasswords() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("test_username");
        when(request.getParameter("password")).thenReturn("test_password");
        when(request.getParameter("confirm")).thenReturn("test_confirm");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new User().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Creating User. Passwords don't match"));
    }

}
