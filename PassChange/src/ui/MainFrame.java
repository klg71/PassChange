package ui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import account.Account;
import account.AccountManager;
import core.Website;

public class MainFrame extends JFrame implements ActionListener,
		TreeSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987061042842924977L;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemClose;
	private JPanel mainPanel;
	private JTree websiteTree;
	private JScrollPane treeScrollPane,tableScrollPane;
	private JTable accountTable;
	private HashMap<String,Website> websites;
	private AccountManager accountManager;
	private AccountTableModell accountTableModell;

	public MainFrame(HashMap<String,Website> websites,AccountManager accountManager) {
		this.websites = websites;
		this.accountManager = accountManager;

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Menu initialization
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");

		menuBar.add(menuFile);
		menuItemClose = new JMenuItem("Close");
		menuFile.add(menuItemClose);
		menuItemClose.addActionListener(this);

		this.setJMenuBar(menuBar);
		
		//Panel initialization
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		//Website tree view initialization
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Websites");

		createNodes(top);
		websiteTree = new JTree(top);
		websiteTree.addTreeSelectionListener(this);
		websiteTree.setPreferredSize(new Dimension(200,100));
		
		treeScrollPane=new JScrollPane(websiteTree);
		mainPanel.add(treeScrollPane,BorderLayout.WEST);
		accountTableModell=new AccountTableModell(accountManager);
		accountTable=new JTable(accountTableModell);
		accountTable.setPreferredSize(new Dimension(300,200));

		tableScrollPane=new JScrollPane(accountTable);
		mainPanel.add(tableScrollPane,BorderLayout.EAST);
		add(mainPanel);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == menuItemClose) {
			this.setVisible(false);
			dispose();
		}

	}

	private void createNodes(DefaultMutableTreeNode top) {

		HashMap<String, ArrayList<Website>> websiteMap = new HashMap<String, ArrayList<Website>>();

		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;
		for (Entry<String,Website> website : websites.entrySet()) {
			if (websiteMap.containsKey(website.getValue().getTopic())) {
				websiteMap.get(website.getValue().getTopic()).add(website.getValue());
			} else {
				ArrayList<Website> temp = new ArrayList<Website>();
				temp.add(website.getValue());
				websiteMap.put(website.getValue().getTopic(), temp);
			}
		}

		for (Entry<String, ArrayList<Website>> entry : websiteMap.entrySet()) {
			category = new DefaultMutableTreeNode(entry.getKey());
			top.add(category);
			for (Website site : entry.getValue()) {
				category.add(new DefaultMutableTreeNode(site));
			}
		}

	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) websiteTree
				.getLastSelectedPathComponent();
		if(websites.containsKey(node.getUserObject().toString())){
					accountTableModell.setWebsite(node.getUserObject().toString());
			
		}

	}

}
