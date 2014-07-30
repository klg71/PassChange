package plugins;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.RequestType;
import core.WebClient;
import core.Website;

public class Twitter extends Website {

	private WebClient webClient;
	private String token;
	private String body;

	public Twitter(String username, String pass) {
		initialize(username, pass);
		webClient = new WebClient();
		token = "";
	}

	@Override
	public void authenticate() throws Exception {
		webClient.sendRequest("https://twitter.com/", RequestType.GET, "",
				"home2", false);
		body = webClient.sendRequest("https://twitter.com/login/",
				RequestType.GET, "", "home1", true);
		System.out.println(URLEncoder.encode(pass, "UTF-8"));
		String post=URLEncoder.encode("session[username_or_email]", "UTF-8") + "="
				+ URLEncoder.encode(username, "UTF-8") + "&"
				+ URLEncoder.encode("session[password]", "UTF-8") + "="
				+ URLEncoder.encode(pass, "UTF-8")
				+ "&authenticity_token="
				+ URLEncoder.encode(token, "UTF-8")
				+ "&scribe_log=&redirect_after_login="
				+ "&authenticity_token="
				+ URLEncoder.encode(token, "UTF-8");
		body = webClient.sendRequest(
				"https://twitter.com/sessions",
				RequestType.POST,post, "twitter1", true);
		
		System.out.println(post);
		webClient.sendRequest("https://twitter.com/", RequestType.GET, "",
				"home2", false);

	}

	@Override
	public void changePassword(String newPass) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateAuthentification() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validatePasswordChange() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validatePasswordChange(String newPass) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Twitter";
	}

	@Override
	public String getTopic() {

		return "Social";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	private void getToken() {
		Pattern pattern = Pattern.compile("([a-f0-9]{40})");
		Matcher m = pattern.matcher(body);
		while (m.find()) {
			token = m.group(1);
			// s now contains "BAR"
		}
		System.out.println(token);
	}

}
