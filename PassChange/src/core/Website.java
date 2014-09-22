package core;

public abstract class Website {
	protected String username;
	protected String pass;

	
	
	public void initialize(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}

	public abstract void authenticate() throws Exception;

	public abstract void changePassword(String newPass) throws Exception;
	
	public abstract boolean validatePassword(String pass);
	
	protected abstract void validateAuthentification() throws Exception;
	
	protected abstract void validatePasswordChange() throws Exception;

	public abstract String getName() ;
	
	public abstract String getTopic();
	
	public abstract String toString();
}
