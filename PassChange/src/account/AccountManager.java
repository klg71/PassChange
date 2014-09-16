package account;

import java.util.ArrayList;
import java.util.HashMap;

import core.Website;
import file.XmlParser;

public class AccountManager {
	private ArrayList<Account> accounts;
	private String accountFile;
	private String masterPass;
	private XmlParser xmlParser;
	private HashMap<String,Website> websites;
	
	public AccountManager(String accountFile, String masterPass,HashMap<String,Website> websites){
		accounts=new ArrayList<Account>();
		this.accountFile=accountFile;
		this.masterPass=masterPass;
		this.websites=websites;
		xmlParser=new XmlParser(websites);
		
	}
	
	public void loadFromFile() throws Exception{
		accounts=xmlParser.loadAccountsFromFile(accountFile,masterPass);
	}
	
	public void writeToFile(){
		xmlParser.saveAccountsToFile(accountFile,masterPass,this);
	}
	
	public void exportAccounts(String filename){
		
	}
	
	public void addAccount(Account newAccount){
		accounts.add(newAccount);
	}
	
	public void removeAccount(String website,String accountName){
		int i=-1;
		for(Account account:accounts){
			if(account.getWebsite().toString().equals(website)&&account.getUserName().equals(accountName)){
				i=accounts.indexOf(account);
			}
		}
		accounts.remove(i);
	}
	
	public Account findAccount(String website,String accountName){
		int i=-1;
		for(Account account:accounts){
			if(account.getWebsite().toString().equals(website)&&account.getUserName().equals(accountName)){
				i=accounts.indexOf(account);
			}
		}
		return accounts.get(i);
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	
	public Account getAccount(int index){
		return accounts.get(index);
	}
	
	public HashMap<String,ArrayList<Account>> getAccountMap(){
		HashMap<String,ArrayList<Account>> accountMap= new HashMap<String,ArrayList<Account>>();
		for(Account account:accounts){
			if(accountMap.containsKey(account.getWebsite().toString())){
				accountMap.get(account.getWebsite().toString()).add(account);
			}
			else {
				ArrayList<Account> temp = new ArrayList<Account>();
				temp.add(account);
				accountMap.put(account.getWebsite().toString(), temp);
				
			}
		}
		return accountMap;
		
	}

	public void writeToFile(String string, String file) {

		xmlParser.saveAccountsToFile(file,string,this);
		
	}
}
