package plugins;

import core.Website;

public class Twitter extends Website {

	@Override
	public void authenticate() throws Exception {
		// TODO Auto-generated method stub

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

}
