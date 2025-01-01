package dynamicPassword;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class Encryption {

    public String encrypt(String word)  {
        try {
            System.out.println("####data:"+word);
            byte[] ivBytes;
            String password = "veloxsol";
            /*
             * you can give whatever you want for password. This is for testing
             * purpose
             */
            SecureRandom random = new SecureRandom();
            System.out.println("1");
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            byte[] saltBytes = bytes;
            // Derive the key
            System.out.println("2");
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            System.out.println("3");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65556, 256);
            System.out.println("4");
            SecretKey secretKey = factory.generateSecret(spec);
            System.out.println("5");
            SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
            //encrypting the word
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();
            ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
            byte[] encryptedTextBytes = cipher.doFinal(word.getBytes("UTF-8"));
            //prepend salt and vi
            byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
            System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
            System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
            System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);
            return new Base64().encodeToString(buffer);
        }
        //    public String encryptInt(int word) throws Exception {
        //
        //        String str_word = String.valueOf(word);
        //        byte[] ivBytes;
        //        String password = "veloxsol";
        //        /*
        //         * you can give whatever you want for password. This is for testing
        //         * purpose
        //         */
        //        SecureRandom random = new SecureRandom();
        //        byte bytes[] = new byte[20];
        //        random.nextBytes(bytes);
        //        byte[] saltBytes = bytes;
        //        // Derive the key
        //        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        //        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65556, 256);
        //        SecretKey secretKey = factory.generateSecret(spec);
        //        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //        //encrypting the word
        //        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //        cipher.init(Cipher.ENCRYPT_MODE, secret);
        //        AlgorithmParameters params = cipher.getParameters();
        //        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        //        byte[] encryptedTextBytes = cipher.doFinal(str_word.getBytes("UTF-8"));
        //        //prepend salt and vi
        //        byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
        //        System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
        //        System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
        //        System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);
        //       String encrypt_str = new Base64().encodeToString(buffer);
        //       int encrypt_int=Integer.parseInt(encrypt_str);
        //        return  encrypt_int;
        //    }
        catch (Exception ex) {
            System.out.println("encryption exception:"+ex);
            return null;
        }
    }


//    public String encryptInt(int word) throws Exception {
//
//        String str_word = String.valueOf(word);
//        byte[] ivBytes;
//        String password = "veloxsol";
//        /*
//         * you can give whatever you want for password. This is for testing
//         * purpose
//         */
//        SecureRandom random = new SecureRandom();
//        byte bytes[] = new byte[20];
//        random.nextBytes(bytes);
//        byte[] saltBytes = bytes;
//        // Derive the key
//        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65556, 256);
//        SecretKey secretKey = factory.generateSecret(spec);
//        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
//        //encrypting the word
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, secret);
//        AlgorithmParameters params = cipher.getParameters();
//        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
//        byte[] encryptedTextBytes = cipher.doFinal(str_word.getBytes("UTF-8"));
//        //prepend salt and vi
//        byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
//        System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
//        System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
//        System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);
//       String encrypt_str = new Base64().encodeToString(buffer);
//       int encrypt_int=Integer.parseInt(encrypt_str);
//        return  encrypt_int;
//    }
}
