package plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.activation.URLDataSource;
import javax.net.ssl.HttpsURLConnection;

import core.RequestType;
import core.WebClient;
import core.Website;

public class Facebook extends Website {
	private WebClient webClient;
	private String password;
	private String passwordNew;
	private String fb_dtsg;
	private String charset_test;

	public Facebook(String username, String pass) {
		super(username, pass);
		webClient = new WebClient();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("passwords"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		fb_dtsg = "";
		charset_test = "";
		password = scanner.nextLine();
		passwordNew = scanner.nextLine();

	}

	@Override
	public void authenticate() {
		webClient.sendRequest("https://m.facebook.com/", RequestType.GET, "",
				"home1", false);
		System.out.println("");
		System.out.println("");
		try {
			webClient.sendRequest("https://m.facebook.com/login.php",
					RequestType.POST,
					"email=" + URLEncoder.encode("klg71@web.de", "UTF-8") + "&"
							+ "pass=" + URLEncoder.encode(password, "UTF-8"),
					"login", false);
			System.out.println("");
			System.out.println("");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.sendRequest(
				"https://m.facebook.com/settings/account/?password",
				RequestType.GET, "", "home2", false);

		try {
			webClient.sendRequest(
					"https://m.facebook.com/password/change/",
					RequestType.POST,
					"confirm_password="
							+ URLEncoder.encode(passwordNew, "UTF-8") + "&"
							+ "new_password="
							+ URLEncoder.encode(passwordNew, "UTF-8") + "&"
							+ "old_password="
							+ URLEncoder.encode(password, "UTF-8") + "&"
							+ "save="
							+ URLEncoder.encode("Passwort ändern", "UTF-8")
							+ "charset_test="
							+ URLEncoder.encode(charset_test, "UTF-8")
							+ "fb_dtsg="
							+ URLEncoder.encode(fb_dtsg, "UTF-8")
							+ "&change_password", "pwchange", false);
			System.out.println("");
			System.out.println("");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		parsingPassChangeForm();
	}

	@Override
	public void changePassword(String newPass) {
		// TODO Auto-generated method stub

	}

	public void parsingPassChangeForm() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("home2.html"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String body = "";
		while (scanner.hasNextLine()) {
			body += scanner.nextLine();
		}
		fb_dtsg = body.substring(body.indexOf("fb_dtsg") + 16,
				body.indexOf("fb_dtsg") + 28);
		charset_test = body.substring(body.indexOf("charset_test") + 21);
		try {
			charset_test = URLDecoder.decode(charset_test.substring(0, charset_test.indexOf("\"")),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(charset_test);
	}

}
