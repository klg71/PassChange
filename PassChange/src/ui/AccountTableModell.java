package ui;

import java.text.DateFormat;
import javax.swing.table.AbstractTableModel;

import account.AccountManager;

public class AccountTableModell extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountManager accountManager;
	private String Website;

	public AccountTableModell(AccountManager accountManager) {
		this.accountManager = accountManager;
		Website = "";
	}

	@Override
	public int getColumnCount() {
		if (Website.equals("")) {
			System.out.println("test");
			return 0;
		} else {
			return 4;
		}
	}

	@Override
	public int getRowCount() {
		if (Website.equals("")||!(accountManager.getAccountMap().containsKey(Website))) {
			return 0;
		} else {
			return accountManager.getAccountMap().get(Website).size();
		}
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		DateFormat df;
		df = DateFormat.getDateTimeInstance( /* dateStyle */ DateFormat.FULL,
		                                     /* timeStyle */ DateFormat.MEDIUM );

		switch (arg1) {
		case 0: {
			return accountManager.getAccountMap().get(Website).get(arg0).getUserName();
		}
		case 1: {
			return accountManager.getAccountMap().get(Website).get(arg0).getEmail();
		}
		case 2: {
			return accountManager.getAccountMap().get(Website).get(arg0).getActualPassword();
		}
		case 3: {
			return df.format(accountManager.getAccountMap().get(Website).get(arg0).getLastChangedCalendar().getTime());
		}
		}
		return null;
	}

	public void setWebsite(String Website) {
		this.Website = Website;
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case 0: return "Username";
		case 1: return "Email";
		case 2: return "Actual Password";
		case 3: return "Last Time Changed";
		}
		return super.getColumnName(column);
	}

}
