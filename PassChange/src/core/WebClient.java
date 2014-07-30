package core;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class WebClient {
	private RequestType type;
	private String body;
	private HttpsURLConnection connection;
	private CookieManager cookieManager;
	private CookieStore cookieStore;
	private URL url;
	boolean ref;

	public WebClient() {
		super();
		ref = false;
		cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
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
				connection.setDoOutput(false);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		connection.setDoInput(true);

		connection.setUseCaches(false);
		connection.setRequestProperty("host", url.getHost());
		if (ref) {
			 connection.setRequestProperty("Referer","	https://twitter.com/login");
		}
		if (type == RequestType.POST) {
			connection.setRequestProperty("Content-Length",
					String.valueOf(body.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
		}
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("DNT", "1");
		connection.setRequestProperty("Accept-Language",
				"de-de,de;q=0.8,en-us;q=0.5,en;q=0.3");
		connection
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:29.0) Gecko/20100101 Firefox/29.0");
		connection
				.setRequestProperty("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
		// try {
		// connection.setRequestProperty("Cookie",
		// URLEncoder.encode(cookies, "UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public String sendRequest(String url, RequestType type, String body,
			String filename, Boolean ref) {
		String ret="";
		System.out.println(filename);
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
		this.ref = ref;
		initConnection();

		OutputStreamWriter writer = null;
		try {
			if (type == RequestType.POST) {
				writer = new OutputStreamWriter(connection.getOutputStream());
				writer.write(body);
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(connection.getHeaderField("Status"));
//		for (Map.Entry<String, List<String>> entry : connection
//				.getHeaderFields().entrySet()) {
//			// System.out.println(entry.getKey()+":");
//			// for(String value:entry.getValue()){
//			// System.out.print(value);
//			// }
//			// System.out.println();
//		}
		// setCookies();s
		List<HttpCookie> cks = cookieStore.getCookies();
		for (HttpCookie ck : cks) {
			try {
				System.out.print(URLDecoder.decode(ck.getName(), "UTF-8")
						+ ": ");
				System.out.println(URLDecoder.decode(ck.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				ret+=line;
				fileWriter.write(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			reader.close();
			if (writer != null)
				writer.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

	}

}
