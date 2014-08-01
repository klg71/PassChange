package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import core.Crypt;
import core.Website;
import account.AccountManager;

public class OpenAccountDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel passwordPanel;
	private JPanel submitPanel;
	private JPasswordField passwordField;
	private JButton submitButton;
	private String file;
	private HashMap<String, Website> websites;
	private MainFrame mainFrame;

	public OpenAccountDialog(HashMap<String, Website> websites,MainFrame mainFrame) {
		JFileChooser fileChooser = new JFileChooser(".");
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			file=fileChooser.getSelectedFile().getAbsolutePath();
			this.websites=websites;
			System.out.println(file);
			this.mainFrame=mainFrame;
			mainPanel=new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			passwordPanel=new JPanel(new GridLayout(0,2));
			submitPanel=new JPanel();
			
			passwordField= new JPasswordField();
			submitButton=new JButton("Submit");
			submitButton.addActionListener(this);
			passwordPanel.add(new JLabel("Password"));
			passwordPanel.add(passwordField);
			
			submitPanel.add(submitButton);
			mainPanel.add(passwordPanel);
			mainPanel.add(submitPanel);
			add(mainPanel);
			pack();
		} else {
			setVisible(false);
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AccountManager accountManager=new AccountManager(file,new String(passwordField.getPassword()), websites);
		try {
			accountManager.loadFromFile();
			mainFrame.setAccountManager(accountManager);
			setVisible(false);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

}
