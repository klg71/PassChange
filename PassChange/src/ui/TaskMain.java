package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Scanner;

import core.RequestType;
import core.WebClient;
import plugins.Facebook;

public class TaskMain {

	public static void main(String[] args) {
//		WebClient webClient = new WebClient();
//		Scanner scanner = null;
//		try {
//			scanner = new Scanner(new File("passwords"));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		String password = scanner.nextLine();
//		String passwordNew=scanner.nextLine();
//
//		webClient.sendRequest("https://m.facebook.com/", RequestType.GET, "",
//				"home1",false);
//		System.out.println("");
//		System.out.println("");
//		try {
//			webClient.sendRequest("https://m.facebook.com/login.php",
//					RequestType.POST,
//					"email=" + URLEncoder.encode("klg71@web.de", "UTF-8") + "&"
//							+ "pass=" + URLEncoder.encode(password, "UTF-8"),
//					"login",false);
//			System.out.println("");
//			System.out.println("");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		webClient.sendRequest("https://m.facebook.com/settings/account/?password",
//				RequestType.GET, "", "home2",false);
//
//		try {
//			webClient.sendRequest("https://m.facebook.com/password/change/",
//					RequestType.POST,
//					"confirm_password=" + URLEncoder.encode(passwordNew, "UTF-8") + "&"
//							+ "new_password=" + URLEncoder.encode(passwordNew, "UTF-8") + "&" + "old_password="+URLEncoder.encode(password, "UTF-8")+"&"+"save="+URLEncoder.encode("Passwort ändern", "UTF-8")+"&change_password",
//					"pwchange",false);
//			System.out.println("");
//			System.out.println("");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Facebook facebook=new Facebook("", "");
		facebook.authenticate();
	}

}
