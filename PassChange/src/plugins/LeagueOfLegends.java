package plugins;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.RequestType;
import core.WebClient;
import core.Website;

public class LeagueOfLegends extends Website {
	private WebClient webClient;
	private String body;
	private Image captchaImage;
	private String solvedTask;
	private String recaptchaID;

	public LeagueOfLegends(String username, String password) {
		super();
		initialize(username, password);
	}

	public void initialize(String username, String password) {
		super.initialize(username, password);
		body = "";
		webClient = new WebClient();
		solvedTask="";
		recaptchaID="";
	}

	public LeagueOfLegends() {
		super();
	}

	@Override
	public void authenticate() throws Exception {
		// /get Cookies
		
		webClient.sendRequest("http://euw.leagueoflegends.com/de/news/",
				RequestType.GET, "", "lolStart", false);
		 webClient.sendRequest("https://account.leagueoflegends.com/login", RequestType.GET,"", "lologin",false);

		body=webClient
				.sendRequest(
						"https://www.google.com/recaptcha/api/challenge?k=6LcwdeESAAAAAJg_ltVGdjrqlf7Bmbg449SyUcSW&ajax=1&lang=de",
						RequestType.GET, "", "lolCaptcha", false);
		getImage();
		System.out.println(solvedTask);
		System.out.println(recaptchaID);
		String post = "";
		post = "username=" + URLEncoder.encode(username, "UTF-8")
				+ "&password=" + URLEncoder.encode(pass, "UTF-8")
				+ "&recaptcha_challenge_field="
				+ URLEncoder.encode(recaptchaID, "UTF-8")
				+ "&recaptcha_response_field="
				+ URLEncoder.encode(solvedTask, "UTF-8");
		webClient.setCookie("leagueoflegends.com", "PVPNET_REGION","euw");
		webClient.setCookie("leagueoflegends.com", "PVPNET_LANG","de_DE");
		body = webClient
				.sendRequest(
						"https://account.leagueoflegends.com/auth",
						RequestType.POST,
						post,
						"lollogin",
						false,
						"https://account.leagueoflegends.com/pm.html?xdm_e=http%3A%2F%2Feuw.leagueoflegends.com&xdm_c=default3117&xdm_p=1");
	System.out.println(body);
		validateAuthentification();
	}

	@Override
	public void changePassword(String newPass) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validatePassword(String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void validateAuthentification() throws Exception {
	if(!body.contains("\"success\":true")){
		throw new Exception("Login unsuccesful! Maybe you entered a wrong captcha or wrong username/password!");
	}

	}

	@Override
	protected void validatePasswordChange() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "League of Legends";
	}

	@Override
	public String getTopic() {
		// TODO Auto-generated method stub
		return "Games";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "League of Legends";
	}

	private void getImage() {

		Pattern urlPattern = Pattern.compile("'([0-9A-Za-z_-]{200,250}')",
				Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Pattern reloadPattern = Pattern.compile("([0-9A-Za-z_-]{206,})");

		Matcher m = urlPattern.matcher(body);
		m.find();
		recaptchaID = m.group().substring(1, m.group().length() - 1);
		String path = m.group();
		body = webClient
				.sendRequest(
						"https://www.google.com/recaptcha/api/reload?c="
								+ recaptchaID
								+ "&k=6LcwdeESAAAAAJg_ltVGdjrqlf7Bmbg449SyUcSW&type=image&reason=i&lang=de",
						RequestType.GET, "", "reloadCaptcha", false);
		
		Matcher r = reloadPattern.matcher(body);
		r.find();
		recaptchaID = r.group();
		
		try {
			captchaImage = ImageIO.read(new URL(
					"https://www.google.com/recaptcha/api/image?c=" + recaptchaID));
			
			JLabel picLabel = new JLabel(new ImageIcon(captchaImage));
			JTextField captchaField = new JTextField();
			JPanel dialogPanel = new JPanel();
			dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
			dialogPanel.add(picLabel);
			dialogPanel.add(captchaField);
			JOptionPane.showMessageDialog(null, dialogPanel, "About",
					JOptionPane.PLAIN_MESSAGE, null);
			solvedTask = captchaField.getText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
