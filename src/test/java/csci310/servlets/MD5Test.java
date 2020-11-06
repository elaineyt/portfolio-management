package csci310.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

public class MD5Test extends Mockito{

	//tests the hash function
	@Test
	public void testHash() {
		new MD5();
		String test = "Hello";
		assertTrue(test != MD5.hash(test));
	}
	
	@Test
	public void testFunc() throws Exception { 
		 
		 MessageDigest mock = org.mockito.Mockito.mock(MessageDigest.class);
		 
		 when(mock.digest("in".getBytes())).thenReturn(null);

	     assertEquals("*", MD5.hash(null));
	} 
	
}
