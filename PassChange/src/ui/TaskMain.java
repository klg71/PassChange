package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import account.AccountManager;
import core.Crypt;
import core.PluginClassLoader;
import core.PluginManager;
import core.Website;
import file.XmlParser;

public class TaskMain {

	public static void main(String[] args) {
		
		String key="Password";
		
		String md5= Crypt.generateMd5(key);
		System.out.println(md5);
		OutputStream file = null;
		try {
			file = new FileOutputStream(new File("accounts.xmls"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("accounts.xml"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String content="";
		while(scanner.hasNextLine())
			content+=scanner.nextLine();
		try {
			Crypt.encode(content.getBytes(), file,md5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(new String(Crypt.decode(new FileInputStream(new File("accounts.xmls")), md5)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PluginManager pluginManager=new PluginManager();
		HashMap<String,Website> websites=pluginManager.getPlugins();

		
		AccountManager accountManager=new AccountManager("accounts.xmls", key, websites);
		accountManager.loadFromFile();
		
		MainFrame mainFrame=new MainFrame(websites,accountManager);
		mainFrame.setVisible(true);
		// mysqlManager=new MysqlManager("", "");
		
	}

}
