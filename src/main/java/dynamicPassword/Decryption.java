package dynamicPassword;

import java.nio.ByteBuffer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

//import org.apache.commons.codec.binary.Base64;

public class Decryption {
	  @SuppressWarnings("static-access")
	  public String decrypt(String encryptedText) throws Exception {
	    String password="veloxsol";
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    System.out.println("password in dycrpy"+encryptedText);
	    //strip off the salt and iv
	    ByteBuffer buffer = ByteBuffer.wrap(new Base64().decode(encryptedText));
	    byte[] saltBytes = new byte[20];
	    buffer.get(saltBytes, 0, saltBytes.length);
	    byte[] ivBytes1 = new byte[cipher.getBlockSize()];
	    buffer.get(ivBytes1, 0, ivBytes1.length);
	    byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes1.length];
	  
	    buffer.get(encryptedTextBytes);
	    // Deriving the key
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65556, 256);
	    SecretKey secretKey = factory.generateSecret(spec);
	    SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes1));
	    byte[] decryptedTextBytes = null;
	    try {
	      decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
	    } catch (IllegalBlockSizeException e) {
	        System.out.println("Decrypt Excption1:"+e);
	    } catch (BadPaddingException e) {
	         System.out.println("Decrypt Excption2:"+e);
	    }
	    System.out.println("another password:"+decryptedTextBytes);
	   
	    return new String(decryptedTextBytes);
	  }
}
