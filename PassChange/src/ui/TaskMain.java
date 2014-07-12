package ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import core.RequestType;
import core.WebClient;
import plugins.Facebook;

public class TaskMain {

	public static void main(String[] args) {
		WebClient webClient=new WebClient();
		webClient.sendRequest("https://www.facebook.com/home.php", RequestType.GET,"","","home1");
		//System.out.println(webClient.getCookies());
		try {
			webClient.sendRequest("https://login.facebook.com/login.php", RequestType.POST,"email=" + URLEncoder.encode( "klg71@web.de", "UTF-8" ) + "&" +
				          "pass=" + URLEncoder.encode( "***REMOVED***", "UTF-8" ),"","login");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.sendRequest("https://www.facebook.com/home.php", RequestType.GET,"","","home2");
		

	}

}
