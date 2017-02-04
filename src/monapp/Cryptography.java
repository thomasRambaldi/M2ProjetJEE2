package monapp;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	public static String crypt(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		byte[] bytesOfMessage = s.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage); 

		BigInteger bigInt = new BigInteger(1,thedigest);
		String hashtext = bigInt.toString(16);
		while(hashtext.length() < 32 ){
			hashtext = "0"+hashtext;
		}
		return hashtext;
	}
}
