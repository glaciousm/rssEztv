package gr.glacious.rssEztv.eztvGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import gr.glacious.rssEztv.util.TxtEditor;

public class FavoriteList {

	public JFrame frame;
	DefaultListModel<String> listModel = new DefaultListModel<>();
	TxtEditor txtEditor = new TxtEditor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FavoriteList window = new FavoriteList();
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
	public FavoriteList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeFrame homeFrame = new HomeFrame();
								
				frame.setVisible(false);
				
				homeFrame.frame.setVisible(true);
			}
		});
		btnSave.setBounds(161, 227, 65, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeFrame homeFrame = new HomeFrame();
								
				frame.setVisible(false);
				
				homeFrame.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(236, 227, 65, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeFrame homeFrame = new HomeFrame();
								
				frame.setVisible(false);
				
				homeFrame.frame.setVisible(true);
			}
		});
		btnBack.setBounds(311, 227, 65, 23);
		frame.getContentPane().add(btnBack);
		
		JList<String> list = new JList<>(listModel);
		List<String> favoriteList = txtEditor.readFromFile();
		
		for (String favorite : favoriteList) {
			listModel.addElement(favorite);
		}
		
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setBounds(10, 10, 414, 177);
        scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					System.out.println(txtEditor.writeToFile());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(10, 227, 65, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(85, 227, 65, 23);
		frame.getContentPane().add(btnDelete);
	}

}
