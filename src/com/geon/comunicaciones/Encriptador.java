package com.geon.comunicaciones;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {

	
	public String GeneraSHAHash(String s)
    {
		 MessageDigest digest = null;

         try {

                 digest = MessageDigest.getInstance("SHA-1");

         } catch (NoSuchAlgorithmException e) {

                 // TODO Auto-generated catch block

                 e.printStackTrace();

         }

         digest.reset();

         byte[] data = digest.digest(s.getBytes());

         return String.format("%0" + (data.length*2) + "X", new BigInteger(1, data));
 
 
      }
   
   
  public String GeneraMD5Hash(String password)
  {

      try {
          // Create MD5 Hash
          MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
          digest.update(password.getBytes());
          byte messageDigest[] = digest.digest();
    
          StringBuffer MD5Hash = new StringBuffer();
          for (int i = 0; i < messageDigest.length; i++)
          {
              String h = Integer.toHexString(0xFF & messageDigest[i]);
              while (h.length() < 2)
                  h = "0" + h;
              MD5Hash.append(h);
          }
                
          return (MD5Hash).toString();
           
          } 
          catch (NoSuchAlgorithmException e) 
          {
          e.printStackTrace();
          }
	
      return "";
      
       
  }
  
  
}
