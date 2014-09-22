package generator;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class Crypt {
	
	static public String generateMd5(String key){
		byte[] bytesOfMessage = null;
		bytesOfMessage = key.getBytes();

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return new String(md.digest(bytesOfMessage));
	}
	
	
	static public String generateKey(String pass,String salt) throws Exception{
		String key="";
		char c=0;
		int i=0;
		for(;i<pass.length()&i<12;i++){
			if(key.length()>10)
				break;
			key+=(pass.charAt(i) ^ salt.charAt(i*2));
		}
		if(key.length()<12){
			key+=salt.substring(i,i+(12-key.length()));
		}
		System.out.println(key);
		Encoder encoder=Base64.getEncoder();
		key=new String(encoder.encode(key.getBytes()));
		return key;
	}
	
	static public void encode( byte[] bytes, OutputStream out, String pass ) throws Exception
	  {
	    Cipher c = Cipher.getInstance("AES");

	    Key k = new SecretKeySpec( pass.getBytes("UTF-8"), "AES" );
	    System.out.println(pass);
	    c.init( Cipher.ENCRYPT_MODE, k );

	    OutputStream cos = new CipherOutputStream( out, c );
	    cos.write( bytes );
	    cos.close();
	  }

	  static public byte[] decode( InputStream is, String pass ) throws Exception
	  {

		    Cipher c = Cipher.getInstance("AES");


		    Key k = new SecretKeySpec( pass.getBytes("UTF-8"), "AES" );
		    System.out.println(pass);
		    c.init( Cipher.DECRYPT_MODE, k );

	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    CipherInputStream cis = new CipherInputStream( is, c );

	    for ( int b; (b = cis.read()) != -1; )
	      bos.write( b );

	    cis.close();
	    return bos.toByteArray();
	  }
}
