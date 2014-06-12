package core;

public abstract class Website {
	protected String username;
	protected String pass;

	public Website(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}

	public abstract void authenticate();

	public abstract void changePassword(String newPass);
}
