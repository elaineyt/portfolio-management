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

public class LogoutTest extends Mockito {

	@Test
    public void testDoPost() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class); 
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new Logout().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Successfully logged out."));
    }
	

}
