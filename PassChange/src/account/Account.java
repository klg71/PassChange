package account;

import java.util.Date;

import core.Website;

public class Account {
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActualPassword() {
		return actualPassword;
	}
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
	@Override
	public String toString() {
		return "Account [userName=" + userName + ", email=" + email
				+ ", actualPassword=" + actualPassword + ", website=" + website
				+ "]";
	}
	public Account(String userName, String email, String actualPassword,
			Website website) {
		super();
		this.userName = userName;
		this.email = email;
		this.actualPassword = actualPassword;
		this.website = website;
	}
	private String userName;
	private String email;
	private String actualPassword;
	private Date lastChanged;
	private Website website;
}
