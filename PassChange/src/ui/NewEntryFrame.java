package ui;

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
	private AccountManager accountManager;

	private JPanel usernamePanel;
	private JPanel emailPanel;
	private JPanel passwordPanel;
	private JPanel submitPanel;
	private JPanel mainPanel;
	
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JButton submitButton;
	
	private String website;
	private HashMap<String, Website> websites;
	
	public NewEntryFrame(AccountManager accountManager,String website,HashMap<String, Website> websites){
		this.accountManager=accountManager;
		this.website=website;
		this.websites=websites;
		setTitle("Create new Entry");
		setSize(300, 200);
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		usernamePanel=new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
		usernamePanel.add(new JLabel("Username:"));
		usernameTextField=new JTextField();
		usernamePanel.add(usernameTextField);
		
		mainPanel.add(usernamePanel);
		
		emailPanel=new JPanel();
		emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
		emailPanel.add(new JLabel("email:"));
		emailTextField=new JTextField();
		emailPanel.add(emailTextField);
		
		mainPanel.add(emailPanel);
		
		passwordPanel=new JPanel();
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
		passwordPanel.add(new JLabel("Password:"));
		passwordTextField=new JPasswordField();
		passwordPanel.add(passwordTextField);
		
		mainPanel.add(passwordPanel);
		
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
			accountManager.addAccount(new Account(usernameTextField.getText(), emailTextField.getText(), passwordTextField.getPassword().toString(), websites.get(website)));
			this.setVisible(false);
			dispose();
		}
		
	}
}
