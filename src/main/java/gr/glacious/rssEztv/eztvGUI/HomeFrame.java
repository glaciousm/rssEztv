package gr.glacious.rssEztv.eztvGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gr.glacious.rssEztv.RSS.RSSExecute;
import gr.glacious.rssEztv.model.FeedMessage;
import gr.glacious.rssEztv.util.OpenPage;
import gr.glacious.rssEztv.util.TxtEditor;

public class HomeFrame {

	File file = new File("C:\\Users\\Glacious\\Documents\\favorites.txt");
	File dFile = new File("C:\\Users\\Glacious\\Documents\\downloaded.txt");

	List<FeedMessage> rssList;
	String selected;
	FeedMessage series;
	TxtEditor txtEditor;

	public JFrame frame;

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
		JList<String> rssJList = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane();
		JButton btnDownload = new JButton("Download");

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		

		rssList = rssExecute.getRSS();
		txtEditor = new TxtEditor();
		for (FeedMessage rss : rssList) {
			for (int i = 0; i < txtEditor.readFromFile(file).size(); i++) {
				if (rss.getTitle().startsWith(txtEditor.readFromFile(file).get(i))) {
					if (!txtEditor.readFromFile(dFile).contains(rss.getTitle())) {

						listModel.addElement(rss.getTitle());

					}

				}
			}

		}

		// create the list
		
		rssJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDownload.setEnabled(true);
				
			}
		});
		scrollPane.setBounds(10, 10, 414, 190);
		scrollPane.setViewportView(rssJList);
		frame.getContentPane().add(scrollPane);

		
		btnDownload.setEnabled(false);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (FeedMessage feedMessage : rssList) {

					if (rssJList.getSelectedValuesList().contains(feedMessage.getTitle())) {

						try {
							OpenPage.openWebpage(new URI(feedMessage.getMagnetURI()));
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							txtEditor.writeToFile(feedMessage.getTitle(), dFile);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						listModel.clear();
						txtEditor = new TxtEditor();
						for (FeedMessage rss : rssList) {
							for (int i = 0; i < txtEditor.readFromFile(file).size(); i++) {
								if (rss.getTitle().startsWith(txtEditor.readFromFile(file).get(i))) {
									if (!txtEditor.readFromFile(dFile).contains(rss.getTitle())) {
										listModel.addElement(rss.getTitle());

									}

								}
							}

						}
						
					}

				}
			}
		});

		btnDownload.setBounds(10, 211, 89, 23);
		frame.getContentPane().add(btnDownload);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnFavorites = new JMenu("TV Series");
		menuBar.add(mnFavorites);

		JMenuItem mntmFavoriteList = new JMenuItem("Favorite List");
		mntmFavoriteList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FavoriteList favoriteList = new FavoriteList(menuBar);
				frame.setVisible(false);
				favoriteList.frame.setVisible(true);

			}
		});
		mnFavorites.add(mntmFavoriteList);
		
		JMenuItem mntmHistoryList = new JMenuItem("History List");
		
		mntmHistoryList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HistoryList historyList = new HistoryList(menuBar);
				frame.setVisible(false);
				historyList.frame.setVisible(true);
				
			}
		});
		
		mnFavorites.add(mntmHistoryList);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

	}
}
