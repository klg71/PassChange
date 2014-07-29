package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import core.Website;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987061042842924977L;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemClose;
	private JTree websiteTree;
	private ArrayList<Website> websites;

	public MainFrame(ArrayList<Website> websites) {
		this.websites = websites;

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		menuFile = new JMenu("File");

		menuBar.add(menuFile);
		menuItemClose = new JMenuItem("Close");
		menuFile.add(menuItemClose);
		menuItemClose.addActionListener(this);

		this.setJMenuBar(menuBar);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Websites");
		createNodes(top);

		websiteTree = new JTree(top);
		add(websiteTree);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == menuItemClose) {
			this.setVisible(false);
			dispose();
		}

	}

	private void createNodes(DefaultMutableTreeNode top) {
		
		
		HashMap<String,ArrayList<Website>> websiteMap=new HashMap<String,ArrayList<Website>>();
		
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;
		for (Website website : websites) {
			if(websiteMap.containsKey(website.getTopic())){
				websiteMap.get(website.getTopic()).add(website);
			} else {
				ArrayList<Website> temp=new ArrayList<Website>();
				temp.add(website);
				websiteMap.put(website.getTopic(), temp);
			}
		}
		

		for(Entry<String, ArrayList<Website>> entry:websiteMap.entrySet()){
			category = new DefaultMutableTreeNode(entry.getKey());
			top.add(category);
			for(Website site : entry.getValue()){
				category.add(new DefaultMutableTreeNode(site));
			}
		}

	}

}
