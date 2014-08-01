package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import account.Account;

public class ChangePasswordFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -117706966870003512L;
	private JPanel mainPanel;
	private JPanel changePanel;
	private JPanel submitPanel;
	
	private JButton submitButton;
	private JPasswordField passwordField;
	
	private Account account;
	
	public ChangePasswordFrame(Account account){
		setTitle("New Password");
		this.account=account;
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		changePanel=new JPanel(new GridLayout(0,2));
		submitPanel=new JPanel();
		
		passwordField=new JPasswordField();
		submitButton=new JButton("Submit");
		submitButton.addActionListener(this);
		changePanel.add(new JLabel("New Password"));
		changePanel.add(passwordField);
		submitPanel.add(submitButton);
		
		mainPanel.add(changePanel);
		mainPanel.add(submitPanel);
		
		add(mainPanel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			account.getWebsite().changePassword(new String(passwordField.getPassword()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this,e1.getMessage());
			e1.printStackTrace();
		}
		
	}
}
