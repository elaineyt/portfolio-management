package csci310.servlets;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

public class MD5Test extends Mockito{

	//tests the hash function
	@Test
	public void testHash() {
		String test = "Hello";
		assertTrue(test != MD5.hash(test));
	}
}
