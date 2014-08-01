package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.Website;
import account.Account;
import account.AccountManager;

public class NewEntryFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AccountManager accountManager;

	private JPanel gridPanel;
	private JPanel submitPanel;
	private JPanel mainPanel;
	
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JButton submitButton;
	private GridLayout gridLayout;
	
	private String website;
	private HashMap<String, Website> websites;
	
	public NewEntryFrame(AccountManager accountManager,String website,HashMap<String, Website> websites){
		this.accountManager=accountManager;
		this.website=website;
		this.websites=websites;
		setTitle("Create new Entry");
		setSize(300, 200);
		
		gridLayout=new GridLayout(0,2);
		gridLayout.setVgap(10);
		gridPanel=new JPanel(gridLayout);
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		gridPanel.add(new JLabel("Username:"));
		usernameTextField=new JTextField();
		gridPanel.add(usernameTextField);
		
		gridPanel.add(new JLabel("email:"));
		emailTextField=new JTextField();
		gridPanel.add(emailTextField);
		
		gridPanel.add(new JLabel("Password:"));
		passwordTextField=new JPasswordField();
		gridPanel.add(passwordTextField);
		
		mainPanel.add(gridPanel);
		
		submitPanel=new JPanel();
		submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
		submitButton=new JButton("Submit");
		submitButton.addActionListener(this);
		submitPanel.add(submitButton);
		
		mainPanel.add(submitPanel);
		
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(submitButton)){
			accountManager.addAccount(new Account(usernameTextField.getText(), emailTextField.getText(),new String( passwordTextField.getPassword()), websites.get(website)));
			this.setVisible(false);
			dispose();
		}
		
	}
}
