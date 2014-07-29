package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987061042842924977L;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemClose;
	private JTree websiteTree;
	
	public MainFrame(){
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menuBar=new JMenuBar();
		menuFile=new JMenu("File");
		
		menuBar.add(menuFile);
		menuItemClose=new JMenuItem("Close");
		menuFile.add(menuItemClose);
		menuItemClose.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		DefaultMutableTreeNode top =
		        new DefaultMutableTreeNode("Websites");
		    createNodes(top);
		
		
		websiteTree=new JTree(top);
		add(websiteTree);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==menuItemClose){
			this.setVisible(false);
			dispose();
		}
		
	}
	
	private void createNodes(DefaultMutableTreeNode top) {
	    DefaultMutableTreeNode category = null;
	    DefaultMutableTreeNode book = null;
	    
	    category = new DefaultMutableTreeNode("Books for Java Programmers");
	    top.add(category);
	    
	    //original Tutorial
	    book = new DefaultMutableTreeNode("The Java Tutorial: A Short Course on the Basics"+
	        "tutorial.html");
	    category.add(book);
	}
	
}
