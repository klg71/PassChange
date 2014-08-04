package file;

import generator.Crypt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import account.Account;
import account.AccountManager;
import core.Website;

public class XmlParser {
	private HashMap<String, Website> websites;
	public XmlParser(HashMap<String, Website> websites) {
		this.websites = websites;
	}

	public ArrayList<Account> loadAccountsFromFile(String filename,
			String password) throws Exception {
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
			e.printStackTrace();
		} catch (IOException e) {
			throw new Exception("You maybe entered a wrong password. Please try again.");
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
			AccountManager accountManager) {
		 try {
			 
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		 
				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("websites");
				doc.appendChild(rootElement);
		 
				// staff elements
				for(Entry<String, ArrayList<Account>> entry:accountManager.getAccountMap().entrySet()){
					Element website = doc.createElement("website");
					website.setAttribute("name", entry.getKey());
					for(Account account:entry.getValue()){
						Element accountElement=doc.createElement("account");
						accountElement.setAttribute("name", account.getUserName());
						accountElement.setAttribute("email", account.getEmail());
						accountElement.setAttribute("pass", account.getActualPassword());
						website.appendChild(accountElement);
					}
					rootElement.appendChild(website);
				}
				
		 
		 
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				System.out.println(source.toString());
				StringWriter stringWriter=new StringWriter();
				StreamResult result = new StreamResult(stringWriter);
			
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
	
				transformer.transform(source, result);
				try {
					Crypt.encode(stringWriter.toString().getBytes(), new FileOutputStream(new File(filename)),Crypt.generateMd5( password));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
	}

}
