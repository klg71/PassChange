package ui;

import java.util.ArrayList;

import core.PluginClassLoader;
import core.PluginManager;
import core.Website;

public class TaskMain {

	public static void main(String[] args) {
//		Scanner scanner = null;
//		try {
//			scanner = new Scanner(new File("passwords"));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String password1=scanner.nextLine();
//		String password2=scanner.nextLine();
//		scanner.close();
//		Facebook facebook=new Facebook("klg71@web.de", password1);
//		try {
//			facebook.authenticate();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			facebook.changePassword(password2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		PluginManager pluginManager=new PluginManager();
		ArrayList<Website> websites=pluginManager.getPlugins();
//		try {
//			websites.get(0).authenticate();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		pluginManager.runPlugins();
		
		MainFrame mainFrame=new MainFrame(websites);
		mainFrame.setVisible(true);
		// mysqlManager=new MysqlManager("", "");
		
	}

}
