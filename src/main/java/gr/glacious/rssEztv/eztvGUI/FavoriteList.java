package gr.glacious.rssEztv.eztvGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gr.glacious.rssEztv.util.TxtEditor;

public class FavoriteList {

	File file = new File("C:\\Users\\Glacious\\Documents\\favorites.txt");
	public JFrame frame;
	DefaultListModel<String> listModel = new DefaultListModel<>();
	TxtEditor txtEditor;
	List<String> favoriteList;



	/**
	 * Create the application.
	 */
	public FavoriteList(JMenuBar menuBar) {
		
		initialize(menuBar);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JMenuBar menuBar) {
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HomeFrame homeFrame = new HomeFrame();
				
				frame.setVisible(false);
				homeFrame.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(190, 227, 80, 23);
		frame.getContentPane().add(btnCancel);

		JList<String> list = new JList<>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDelete.setEnabled(true);
				
			}
		});
		txtEditor = new TxtEditor();
		favoriteList = txtEditor.readFromFile(file);

		for (String favorite : favoriteList) {
			listModel.addElement(favorite);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 414, 177);
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = JOptionPane.showInputDialog(frame, "Enter the title of the tv series you want to save");

				txtEditor = new TxtEditor();
				try {
					if (txtEditor.writeToFile(title,file).equals("Already Exists")) {
						JOptionPane.showMessageDialog(frame, "Series already in the list", "Warning",
						        JOptionPane.WARNING_MESSAGE);
					} else {

					}

				} catch (IOException e) {
					e.printStackTrace();
				}

				favoriteList = txtEditor.readFromFile(file);
				listModel.clear();
				for (String favorite : favoriteList) {
					listModel.addElement(favorite);
				}
			}
		});
		btnAdd.setBounds(10, 227, 80, 23);
		frame.getContentPane().add(btnAdd);

		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = list.getSelectedValue();
				txtEditor = new TxtEditor();

				try {
					txtEditor.deleteFromFile(selected,file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				favoriteList = txtEditor.readFromFile(file);
				listModel.clear();
				for (String favorite : favoriteList) {
					listModel.addElement(favorite);
				}

			}
		});
		btnDelete.setBounds(100, 227, 80, 23);
		frame.getContentPane().add(btnDelete);
		
		frame.add(menuBar);
	}

}
