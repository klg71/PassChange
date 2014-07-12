package core;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class WebClient {
	private RequestType type;
	private String body;
	private String cookies;
	private HttpsURLConnection connection;
	private CookieManager cookieManager;
	private CookieStore cookieStore;
	private URL url;

	public WebClient() {
		super();
		cookies = "";
		cookieManager=new CookieManager();
		CookieHandler.setDefault(cookieManager);
		cookieStore = cookieManager.getCookieStore();
	}

	private void initConnection() {
		try {
			connection = (HttpsURLConnection) url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (type == RequestType.GET) {
			try {
				connection.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				connection.setRequestMethod("POST");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("host",url.getHost());
		if (type == RequestType.POST) {
			connection.setRequestProperty("Content-Length",
					String.valueOf(body.length()));
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		}
		connection
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:29.0) Gecko/20100101 Firefox/29.0");
		connection.setRequestProperty("Accept", "*/*");
		//connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//			try {
//				connection.setRequestProperty("Cookie",
//						URLEncoder.encode(cookies, "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

	}

	public void sendRequest(String url, RequestType type, String body,
			String cookies, String filename) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filename + ".html");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.type = type;
		this.body = body;
		if (cookies != "")
			this.cookies = cookies;
		initConnection();

		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(connection.getOutputStream());
			if (type == RequestType.POST) {
				writer.write(body);
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Map.Entry<String, List<String>> entry : connection
				.getHeaderFields().entrySet()) {
//			 System.out.println(entry.getKey()+":");
//			 for(String value:entry.getValue()){
//			 System.out.print(value);
//			 }
//			 System.out.println();
		}
		//setCookies();

		 List<HttpCookie> cks = cookieStore.getCookies();
		 for (HttpCookie ck : cks) {
		 System.out.println(ck);
		 }
		BufferedReader reader = null;
		if (connection.getHeaderField("Content-Encoding") != null) {
			try {
				reader = new BufferedReader(new InputStreamReader(
						new GZIPInputStream(connection.getInputStream())));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			for (String line; (line = reader.readLine()) != null;) {
				// System.out.println( line );
				fileWriter.write(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getCookies() {
		return cookies;
	}

	

}
