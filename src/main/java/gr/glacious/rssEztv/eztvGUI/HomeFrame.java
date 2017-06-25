package gr.glacious.rssEztv.eztvGUI;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
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
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gr.glacious.rssEztv.RSS.RSSExecute;
import gr.glacious.rssEztv.model.FeedMessage;
import gr.glacious.rssEztv.util.OpenPage;
import gr.glacious.rssEztv.util.TxtEditor;

public class HomeFrame {

	private static final long serialVersionUID = 5078700827741826934L;
	
	File file = new File("C:\\Users\\Glacious\\Documents\\favorites.txt");
	File dFile = new File("C:\\Users\\Glacious\\Documents\\downloaded.txt");

	List<FeedMessage> rssList;
	String selected;
	FeedMessage series;
	TxtEditor txtEditor;
	TrayIcon trayIcon;
    SystemTray tray;

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
		btnDownload.setEnabled(false);
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);

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
				btnDelete.setEnabled(true);

			}
		});
		scrollPane.setBounds(10, 10, 414, 190);
		scrollPane.setViewportView(rssJList);
		frame.getContentPane().add(scrollPane);

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

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = rssJList.getSelectedValue();
				txtEditor = new TxtEditor();

				try {
					txtEditor.writeToFile(selected, dFile);
				} catch (IOException e1) {
					e1.printStackTrace();
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

		});
		btnDelete.setBounds(109, 211, 89, 23);
		frame.getContentPane().add(btnDelete);

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
		
		//Hide to tray
		
		System.out.println("creating instance");
        try{
            System.out.println("setting look and feel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println("Unable to set LookAndFeel");
        }
        if(SystemTray.isSupported()){
            System.out.println("system tray supported");
            tray=SystemTray.getSystemTray();

            Image image=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Glacious\\Pictures\\favicon.png");
            ActionListener exitListener=new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting....");
                    System.exit(0);
                }
            };
            PopupMenu popup=new PopupMenu();
            MenuItem defaultItem=new MenuItem("Exit");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem=new MenuItem("Open");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	frame.setVisible(true);
                	frame.setExtendedState(JFrame.NORMAL);
                }
            });
            popup.add(defaultItem);
            trayIcon=new TrayIcon(image, "SystemTray Demo", popup);
            trayIcon.setImageAutoSize(true);
        }else{
            System.out.println("system tray not supported");
        }
        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if(e.getNewState()==JFrame.ICONIFIED){
                    try {
                        tray.add(trayIcon);
                        frame.setVisible(false);
                        System.out.println("added to SystemTray");
                    } catch (AWTException ex) {
                        System.out.println("unable to add to tray");
                    }
                }
        if(e.getNewState()==7){
                    try{
            tray.add(trayIcon);
            frame.setVisible(false);
            System.out.println("added to SystemTray");
            }catch(AWTException ex){
            System.out.println("unable to add to system tray");
        }
            }
        if(e.getNewState()==JFrame.MAXIMIZED_BOTH){
                    tray.remove(trayIcon);
                    frame.setVisible(true);
                    System.out.println("Tray icon removed");
                }
                if(e.getNewState()==JFrame.NORMAL){
                    tray.remove(trayIcon);
                    frame.setVisible(true);
                    System.out.println("Tray icon removed");
                }
            }
        });
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Duke256.png"));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
