package ui;

import generator.Crypt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import core.Website;
import account.AccountManager;

public class OpenAccountDialog extends JDialog implements ActionListener, KeyListener{

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
			setSize(200, 100);
			setTitle("Enter Password");
			mainPanel=new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			passwordPanel=new JPanel(new GridLayout(0,2));
			submitPanel=new JPanel();
			
			passwordField= new JPasswordField();
			passwordField.addKeyListener(this);
			submitButton=new JButton("Submit");
			submitButton.addActionListener(this);
			passwordPanel.add(new JLabel("Password"));
			passwordPanel.add(passwordField);
			
			submitPanel.add(submitButton);
			mainPanel.add(passwordPanel);
			mainPanel.add(submitPanel);
			add(mainPanel);
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

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_ENTER){
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
