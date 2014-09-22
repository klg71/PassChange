package plugins;

import java.awt.Image;
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
	}

	public LeagueOfLegends() {
		super();
	}

	@Override
	public void authenticate() throws Exception {
		// /get Cookies
		body = webClient.sendRequest("http://euw.leagueoflegends.com/de/news/",
				RequestType.GET, "", "lolStart", false);

		body = webClient
				.sendRequest(
						"https://www.google.com/recaptcha/api/noscript?k=6LcwdeESAAAAAJg_ltVGdjrqlf7Bmbg449SyUcSW",
						RequestType.GET, "", "lolCaptcha", false);
		getImage();
		System.out.println(solvedTask);
		System.out.println(recaptchaID);
		String post = "";
		post =  "username="
				+ URLEncoder.encode(username, "UTF-8")+"&password=" + URLEncoder.encode(pass, "UTF-8")
				+ "&recaptcha_challenge_field="
				+ URLEncoder.encode(recaptchaID, "UTF-8")
				+ "&recaptcha_response_field="
				+ URLEncoder.encode(solvedTask, "UTF-8") ;
		body = webClient.sendRequest(
				"https://account.leagueoflegends.com/auth", RequestType.POST,
				post, "lollogin", false,"https://account.leagueoflegends.com/pm.html?xdm_e=http%3A%2F%2Feuw.leagueoflegends.com&xdm_c=default3117&xdm_p=1");
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
		// TODO Auto-generated method stub

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

		Pattern urlPattern = Pattern.compile("(image\\?c=[A-Za-z0-9_\\-]+)",
				Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Pattern recaptchaPattern = Pattern
				.compile("(value=\"[0-9A-Za-z_-]+\")");
		Matcher m = urlPattern.matcher(body);
		Matcher k = recaptchaPattern.matcher(body);
		k.find();
		m.find();
		recaptchaID = k.group().substring(7,k.group().length()-1);
		String path = m.group();
		body=webClient.sendRequest("http://www.google.com/recaptcha/api/reload?c="+recaptchaID+"&k=6LcwdeESAAAAAJg_ltVGdjrqlf7Bmbg449SyUcSW&type=image", RequestType.GET, "","reloadCaptcha", false);
		System.out.println(body);
		try {
			captchaImage = ImageIO.read(new URL(
					"https://www.google.com/recaptcha/api/" + path));
			ImageIO.write((RenderedImage) captchaImage, "jpeg", new File(
					"captchaImage.jpeg"));
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
