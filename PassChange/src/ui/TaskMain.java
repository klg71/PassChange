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

import core.Crypt;
import core.PluginClassLoader;
import core.PluginManager;
import core.Website;

public class TaskMain {

	public static void main(String[] args) {
		//for (Provider p : Security.getProviders()) System.out.println(p.getName());
		
		String key="123457678";
		
		String md5= Crypt.generateMd5(key);
		System.out.println(md5);
		OutputStream file = null;
		try {
			file = new FileOutputStream(new File("test.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Crypt.encode("Hello World".getBytes(), file,md5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(new String(Crypt.decode(new FileInputStream(new File("test.txt")), md5)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PluginManager pluginManager=new PluginManager();
		ArrayList<Website> websites=pluginManager.getPlugins();

		pluginManager.runPlugins();
		
		MainFrame mainFrame=new MainFrame(websites);
		mainFrame.setVisible(true);
		// mysqlManager=new MysqlManager("", "");
		
	}

}
