package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class EditAccountFrame extends JFrame implements KeyListener, ActionListener {
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
	
	private Account account;
	
	public EditAccountFrame(Account account){
		this.account=account;
		setTitle("Edit Entry");
		setSize(300, 200);
		
		gridLayout=new GridLayout(0,2);
		gridLayout.setVgap(10);
		gridPanel=new JPanel(gridLayout);
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		gridPanel.add(new JLabel("Username:"));
		usernameTextField=new JTextField(account.getUserName());
		gridPanel.add(usernameTextField);
		
		gridPanel.add(new JLabel("Email:"));
		emailTextField=new JTextField(account.getEmail());
		gridPanel.add(emailTextField);
		
		gridPanel.add(new JLabel("Password:"));
		passwordTextField=new JPasswordField(account.getActualPassword());
		gridPanel.add(passwordTextField);
		
		gridPanel.add(new JLabel("Expire:"));
		expireTextField=new JTextField(Integer.toString(account.getExpire()));
		gridPanel.add(expireTextField);
		expireTextField.addKeyListener(this);
		
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
			account.setUserName(usernameTextField.getText().toString());
			account.setEmail(emailTextField.getText().toString());
			account.setActualPassword(new String(passwordTextField.getPassword()));
			account.setExpire(Integer.parseInt(expireTextField.getText().toString()));
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
