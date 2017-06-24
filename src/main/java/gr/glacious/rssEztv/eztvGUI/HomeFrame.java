package gr.glacious.rssEztv.eztvGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import gr.glacious.rssEztv.RSS.RSSExecute;
import gr.glacious.rssEztv.util.TxtEditor;

public class HomeFrame {
	
	private String favorite;
	List<String> rssList;

	public JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame window = new HomeFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		RSSExecute rssExecute = new RSSExecute();
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				favorite = textField.getText();
				rssList = rssExecute.getRSS();
				TxtEditor favs = new TxtEditor();
				for (String rss : rssList) {
					for (int i = 0; i < favs.readFromFile().size(); i++) {
						if (rss.startsWith(favs.readFromFile().get(i))) {
							listModel.addElement(rss);
						}
					}
					
					
				}
				
			}
		});
        btnUpdate.setBounds(10, 11, 89, 23);
        frame.getContentPane().add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();		
 
        //create the list
        JList<String> rssList = new JList<>(listModel);
        scrollPane.setBounds(10, 40, 414, 190);
        scrollPane.setViewportView(rssList);
        frame.getContentPane().add(scrollPane);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        
        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);
        
        JMenu mnFavorites = new JMenu("Favorites");
        menuBar.add(mnFavorites);
        
        JMenuItem mntmFavoriteList = new JMenuItem("Favorite List");
        mntmFavoriteList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FavoriteList favoriteList = new FavoriteList();
				favoriteList.frame.setVisible(true);
				
			}
		});
        mnFavorites.add(mntmFavoriteList);
        
        JMenuItem mntmAddToFavorite = new JMenuItem("Add to Favorite");
        mnFavorites.add(mntmAddToFavorite);
        
        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        
        JMenuItem mntmAbout = new JMenuItem("About");
        mnHelp.add(mntmAbout);
        
        
        
	}
}
