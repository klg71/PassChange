package ui;

import plugins.Facebook;

public class TaskMain {

	public static void main(String[] args) {
		Facebook facebook= new Facebook("","");
		facebook.authenticate();

	}

}
