package csci310.servlets;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

public class MD5 {
	
	public static String hash(String input) 
    { 
        try { 
        	
        	if(input == null) {
        		Exception e = new Exception();
        		throw e;
        	}
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
            
            
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
                    
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (Exception e) { 
//            throw new RuntimeException(e);
        	return "*";
        } 
    } 
}
