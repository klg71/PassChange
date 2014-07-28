package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

import core.RequestType;
import core.WebClient;
import plugins.Facebook;

public class TaskMain {

	public static void main(String[] args) {
		WebClient webClient=new WebClient();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("passwords"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String password=scanner.nextLine();
		
		webClient.sendRequest("https://www.facebook.com/home.php", RequestType.GET,"","","home1");
		System.out.println(password);
		//System.out.println(webClient.getCookies());
		try {
			webClient.sendRequest("https://login.facebook.com/login.php", RequestType.POST,"email=" + URLEncoder.encode( "klg71@web.de", "UTF-8" ) + "&" +
				          "pass=" + URLEncoder.encode( password, "UTF-8" ),"","login");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.sendRequest("https://www.facebook.com/home.php", RequestType.GET,"","","home2");
		

	}

}
