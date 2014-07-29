package core;

public abstract class Website {
	protected String username;
	protected String pass;

	public Website(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}

	public abstract void authenticate() throws Exception;

	public abstract void changePassword(String newPass) throws Exception;
	
	protected abstract void validateAuthentification() throws Exception;
	
	protected abstract void validatePasswordChange() throws Exception;

	protected abstract void validatePasswordChange(String newPass) throws Exception ;

	public abstract String getName() ;
}
