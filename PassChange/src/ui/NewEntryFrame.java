package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.print.attribute.standard.JobName;
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

public class NewEntryFrame extends JFrame implements ActionListener, KeyListener {
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
	private JTextField expireTextField;
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
		
		gridPanel.add(new JLabel("Email:"));
		emailTextField=new JTextField();
		gridPanel.add(emailTextField);
		
		gridPanel.add(new JLabel("Password:"));
		passwordTextField=new JPasswordField();
		gridPanel.add(passwordTextField);
		
		
		gridPanel.add(new JLabel("Expire:"));
		expireTextField=new JTextField();
		expireTextField.addKeyListener(this);
		gridPanel.add(expireTextField);
		
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
			Calendar tempCalendar = Calendar.getInstance();
				Date tempDate = new Date();
				tempCalendar.setTime(tempDate);
			accountManager.addAccount(new Account(usernameTextField.getText(), emailTextField.getText(),new String( passwordTextField.getPassword()),tempCalendar ,websites.get(website),Integer.parseInt(expireTextField.getText().toString())));
			this.setVisible(false);
			dispose();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		try {

			Integer.parseInt(expireTextField.getText().toString());
		} catch (Exception e) {
			expireTextField.setText("");
		}
		
	}
}
