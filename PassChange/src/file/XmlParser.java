package file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import account.Account;
import core.Crypt;
import core.Website;

public class XmlParser {
	private HashMap<String, Website> websites;

	public XmlParser(HashMap<String, Website> websites) {
		this.websites = websites;
	}

	public ArrayList<Account> loadAccountsFromFile(String filename,
			String password) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String content = null;
		try {
			content = new String(Crypt.decode(new FileInputStream(new File(
					filename)), Crypt.generateMd5(password)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document document = null;
		try {
			document = builder.parse(new ByteArrayInputStream(content
					.getBytes()));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(document.getFirstChild().getNodeName());
		NodeList nodeList = document.getFirstChild().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeName().equals("website")) {
				NodeList accountList = nodeList.item(i).getChildNodes();
				for (int k = 0; k < accountList.getLength(); k++) {
					if (accountList.item(k).getNodeName().equals("account")) {
						System.out.println(nodeList.item(i)
								.getAttributes().getNamedItem("name")
								.getNodeValue());
						accounts.add(new Account(accountList.item(k)
								.getAttributes().getNamedItem("name")
								.getNodeValue(), accountList.item(k)
								.getAttributes().getNamedItem("email")
								.getNodeValue(), accountList.item(k)
								.getAttributes().getNamedItem("pass")
								.getNodeValue(), websites.get(nodeList.item(i)
								.getAttributes().getNamedItem("name")
								.getNodeValue())));
					}
				}
			}

		}
		for(Account account:accounts){
			System.out.println(account);
		}
		return accounts;
	}

	public void saveAccountsToFile(String filename, String password,
			ArrayList<Account> accounts) {

	}

}
