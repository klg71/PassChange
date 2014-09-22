package ui;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import account.AccountManager;
import core.Website;

public class SaveAccountsDialog extends JDialog implements KeyListener, ActionListener {

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
	private AccountManager accountManager;

	public SaveAccountsDialog(JFrame frame,AccountManager accountManager) {
		super(frame,true);
		JFileChooser fileChooser = new JFileChooser(".");
		//fileChooser.setApproveButtonText("Save");
		this.accountManager=accountManager;
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			file=fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println(file);
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
		try {
			accountManager.writeToFile(new String(passwordField.getPassword()),file);
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
			try {
				accountManager.writeToFile(new String(passwordField.getPassword()),file);
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
