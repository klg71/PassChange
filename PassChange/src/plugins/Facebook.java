package plugins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

import core.Website;

public class Facebook extends Website{

	public Facebook(String username, String pass) {
		super(username, pass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void authenticate() {
		String body = null;
		try {
			body = //"lsd=" + URLEncoder.encode( "AVpnvYPr", "UTF-8" ) + "&" +
			          "email=" + URLEncoder.encode( "klg71@web.de", "UTF-8" ) + "&" +
			          "pass=" + URLEncoder.encode( "***REMOVED***", "UTF-8" ) + "&" +
			          "persistent=" + URLEncoder.encode( "0", "UTF-8" ) + "&" +
			          "default_persistent=" + URLEncoder.encode( "0", "UTF-8" ) + "&" +
			          //"timezone=" + URLEncoder.encode( "-120", "UTF-8" ) + "&" +
			          "email=" + URLEncoder.encode( "klg71@web.de", "UTF-8" ) + "&" +
			          //"lgnrnd=" + URLEncoder.encode( "033327_4jCK", "UTF-8" ) + "&" +
			          //"lgnjs=" + URLEncoder.encode( "1405074848", "UTF-8" ) + "&" +
			          "locale=" + URLEncoder.encode( "de_DE", "UTF-8" );
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	URL url = null;
	try {
		url = new URL( "https://www.facebook.com/home.php" );
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	HttpsURLConnection connection = null;
	try {
		connection = (HttpsURLConnection) url.openConnection();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		connection.setRequestMethod( "GET" );
	} catch (ProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	connection.setDoInput( true );
	connection.setDoOutput( true );
	connection.setUseCaches( false );
	//connection.setRequestProperty( "Content-Type",
	//                               "application/x-www-form-urlencoded" );
	//connection.setRequestProperty( "Content-Length", String.valueOf(body.length()) );
	connection.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:29.0) Gecko/20100101 Firefox/29.0");
	//connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	//connection.setRequestProperty("Cookie", "reg_fb_gate=https%3A%2F%2Fwww.facebook.com%2F; path=/; domain=.facebook.com");
	//connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
	//connection.setRequestProperty("Referer","https://www.facebook.com/?stype=lo&jlou=AfeYW0qfAhA5VoPHThf3lmT4y9WrNAZ0vh_QcG_7vKw096-3l4BaLYQpN_lAKL-MpobsxUxDlkkf_wDLC1jdJGeyQoi-Rw_Ifkwhc4V1c6hWPQ&smuh=13989&lh=Ac-OXGWxILXVZL3u");
	
	OutputStreamWriter writer =null;
	try {
		writer = new OutputStreamWriter( connection.getOutputStream() );
		//writer.write( body );
		writer.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(connection.getHeaderField("Set-Cookie"));
	BufferedReader reader = null;
	if(connection.getHeaderField("Content-Encoding")!=null){
	try {
		reader = new BufferedReader(
		                          new InputStreamReader(new GZIPInputStream(connection.getInputStream())) );
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	} else{
		try {
			reader = new BufferedReader(
			                          new InputStreamReader(connection.getInputStream()) );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	try {
		for ( String line; (line = reader.readLine()) != null; )
		{
		  System.out.println( line );
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		writer.close();
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	@Override
	public void changePassword(String newPass) {
		// TODO Auto-generated method stub
		
	}
	

}
