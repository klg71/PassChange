package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import account.AccountManager;
import core.Website;

public class MainFrame extends JFrame implements ActionListener,
		TreeSelectionListener, MouseListener, WindowListener, ClipboardOwner {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4987061042842924977L;
	private JMenuBar menuBar;
	private JMenu menuFile, menuAccount, menuSettings, menuInfo;
	private JMenuItem menuItemClose, menuItemOpen, menuItemSave;
	private JPanel mainPanel;
	private JTree websiteTree;
	private JScrollPane treeScrollPane, tableScrollPane;
	private JTable accountTable;
	private HashMap<String, Website> websites;
	private AccountManager accountManager;
	private AccountTableModell accountTableModell;
	private JPopupMenu popupMenu;
	private JMenuItem changePassword, deleteEntry, addEntry;
	private int clickedRow;
	private String activeWebsite;
	private JMenuItem menuItemSaveAs;
	private JMenuItem editEntry, copyClipboardEntry;
	private boolean saved;

	public MainFrame(HashMap<String, Website> websites,
			AccountManager accountManager) {
		this.websites = websites;
		this.accountManager = accountManager;
		activeWebsite = "";

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("PassChange");
		saved = false;
		// Menu initialization
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuAccount = new JMenu("Account");
		menuSettings = new JMenu("Settings");
		menuInfo = new JMenu("Info");
		menuBar.add(menuFile);
		menuBar.add(menuAccount);
		menuBar.add(menuSettings);
		menuBar.add(menuInfo);
		menuItemOpen = new JMenuItem("Open");
		menuItemSaveAs = new JMenuItem("Save as");
		menuItemSave = new JMenuItem("Save");
		menuItemClose = new JMenuItem("Close");
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemSaveAs);
		menuFile.add(menuItemClose);

		menuItemOpen.addActionListener(this);
		menuItemSave.addActionListener(this);
		menuItemSaveAs.addActionListener(this);
		menuItemClose.addActionListener(this);

		this.setJMenuBar(menuBar);

		// Panel initialization
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		// Website tree view initialization
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Websites");

		createNodes(top);
		websiteTree = new JTree(top);
		websiteTree.addTreeSelectionListener(this);
		websiteTree.setPreferredSize(new Dimension(200, 100));

		treeScrollPane = new JScrollPane(websiteTree);
		mainPanel.add(treeScrollPane, BorderLayout.WEST);
		accountTableModell = new AccountTableModell(accountManager);
		accountTable = new JTable(accountTableModell);
		accountTable.setPreferredSize(new Dimension(300, 200));
		accountTable.addMouseListener(this);
		tableScrollPane = new JScrollPane(accountTable);
		mainPanel.add(tableScrollPane, BorderLayout.EAST);
		add(mainPanel);
		pack();

		popupMenu = new JPopupMenu();

		addEntry = new JMenuItem("Add new entry");
		addEntry.addActionListener(this);
		popupMenu.add(addEntry);

		deleteEntry = new JMenuItem("Delete Entry");
		deleteEntry.addActionListener(this);
		popupMenu.add(deleteEntry);

		changePassword = new JMenuItem("Change Password");
		changePassword.addActionListener(this);
		popupMenu.add(changePassword);

		editEntry = new JMenuItem("Edit Entry");
		editEntry.addActionListener(this);
		popupMenu.add(editEntry);

		copyClipboardEntry = new JMenuItem("Copy to Clipboard");
		copyClipboardEntry.addActionListener(this);
		popupMenu.add(copyClipboardEntry);
		this.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == menuItemClose) {
			this.setVisible(false);
			dispose();
		}
		if (arg0.getSource() == menuItemSave) {
			try {
				accountManager.writeToFile();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
			saved = true;
		}

		if (arg0.getSource() == menuItemSaveAs) {
			SaveAccountsDialog dialog = new SaveAccountsDialog(this,
					accountManager);
			dialog.setVisible(true);
			saved = true;
		}
		if (arg0.getSource() == menuItemOpen) {
			OpenAccountDialog dialog = new OpenAccountDialog(websites, this);
			dialog.setVisible(true);
			saved = true;
		}
		if (arg0.getSource() == changePassword) {
			ChangePasswordFrame frame = new ChangePasswordFrame(
					accountManager.findAccount(activeWebsite,
							accountTableModell.getValueAt(0, clickedRow)
									.toString()));
			frame.setVisible(true);
			frame.addWindowListener(this);
			saved = false;
		}
		if (arg0.getSource() == deleteEntry) {
			if (JOptionPane.showConfirmDialog((Component) arg0.getSource(),
					"Do you really want to delete account:"
							+ accountTableModell.getValueAt(0, clickedRow),
					"Warning", 0) == JOptionPane.YES_OPTION) {
				accountManager.removeAccount(activeWebsite, accountTableModell
						.getValueAt(0, clickedRow).toString());
				accountTableModell.fireTableDataChanged();
				accountTableModell.fireTableStructureChanged();
				saved = false;
			}
		}
		if (arg0.getSource() == addEntry) {

			NewEntryFrame entryFrame = new NewEntryFrame(accountManager,
					activeWebsite, websites);
			entryFrame.setVisible(true);
			entryFrame.addWindowListener(this);
			saved = false;
		}

		if (arg0.getSource() == editEntry) {
			EditAccountFrame editAccountFrame = new EditAccountFrame(
					accountManager.findAccount(activeWebsite,
							accountTableModell.getValueAt(0, clickedRow)
									.toString()));
			editAccountFrame.setVisible(true);
			editAccountFrame.addWindowListener(this);
			saved = false;
		}

		if (arg0.getSource() == copyClipboardEntry) {
			Clipboard clip = getToolkit().getSystemClipboard();
			String s = accountManager.findAccount(activeWebsite,
					accountTableModell.getValueAt(0, clickedRow).toString())
					.getActualPassword();
			StringSelection cont = new StringSelection(s);
			clip.setContents(cont, this);
		}

	}

	private void createNodes(DefaultMutableTreeNode top) {

		HashMap<String, ArrayList<Website>> websiteMap = new HashMap<String, ArrayList<Website>>();

		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;
		for (Entry<String, Website> website : websites.entrySet()) {
			if (websiteMap.containsKey(website.getValue().getTopic())) {
				websiteMap.get(website.getValue().getTopic()).add(
						website.getValue());
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
		if (websites.containsKey(node.getUserObject().toString())) {
			accountTableModell.setWebsite(node.getUserObject().toString());
			activeWebsite = node.getUserObject().toString();
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if ((arg0.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			popupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			clickedRow = accountTable.rowAtPoint(new Point(arg0.getX(), arg0
					.getY()));
			if (clickedRow == -1) {
				changePassword.setEnabled(false);
				deleteEntry.setEnabled(false);
				editEntry.setEnabled(false);
			} else {
				changePassword.setEnabled(true);
				deleteEntry.setEnabled(true);
				editEntry.setEnabled(true);

			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		accountTableModell.fireTableDataChanged();

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		accountTableModell.fireTableDataChanged();
		if (arg0.getSource() == this&&!saved) {
			if (JOptionPane.showConfirmDialog((Component) arg0.getSource(),
					"Do you want to save before quitting?", "Warning", 0) == JOptionPane.YES_OPTION) {
				SaveAccountsDialog accountsDialog = new SaveAccountsDialog(
						this, accountManager);
				accountsDialog.setVisible(true);
			}
		}

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;

		accountTableModell = new AccountTableModell(accountManager);
		accountTable.setModel(accountTableModell);

	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		// TODO Auto-generated method stub

	}

}
