package BookMeFiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddFrame extends JFrame {
	
	Connection conn = null;

	public AddFrame() {
		conn = Connectionsqlite.ConnectorDB();

		this.setBounds(10, 10, 500, 550);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		TitlePanel titlePanel = new TitlePanel();
		this.add(titlePanel, BorderLayout.NORTH);

		JPanel addPanel = new JPanel();
		this.add(addPanel);
		GridBagLayout gbl = new GridBagLayout();
		addPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		Dimension dimension = new Dimension(150, 20);
		Dimension dimension1 = new Dimension(80, 20);

//		Text field one
		
		JTextField addTextField1 = new JTextField();
		addTextField1.setPreferredSize(dimension);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 1;
		gbc.gridy = 0;
		addPanel.add(addTextField1, gbc);
			
//		Text field two
		
		JTextField addTextField2 = new JTextField();
		addTextField2.setPreferredSize(dimension);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 1;
		gbc.gridy = 1;
		addPanel.add(addTextField2, gbc);
						  
				    
//		Text field three
		String[] genrestring = { "Fantasy", "Sci-Fi", "Novel",
			      "Cuisine", "Business", "History" };
		JComboBox genreList = new JComboBox(genrestring);
		JTextField addTextField3 = new JTextField();
		addTextField3.setPreferredSize(dimension);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 1;
		gbc.gridy = 2;
		addPanel.add(genreList, gbc);
			
//		Text field four
		JTextField addTextField4 = new JTextField();
		addTextField4.setPreferredSize(dimension);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 1;
		gbc.gridy = 3;
		addPanel.add(addTextField4, gbc);
			
//		Text field five
		JTextField addTextField5 = new JTextField();
		addTextField5.setPreferredSize(dimension);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 1;
		gbc.gridy = 4;
		addPanel.add(addTextField5, gbc);
			
			
//		attribute name 1
		JLabel label1 = new JLabel("Title");
		label1.setPreferredSize(dimension1);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		addPanel.add(label1, gbc);
			
//		attribute name 2
		JLabel label2 = new JLabel("Author");
		label2.setPreferredSize(dimension1);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 1;
		addPanel.add(label2, gbc);
			
//		attribute name 3
		JLabel label3 = new JLabel("Genre");
		label3.setPreferredSize(dimension1);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 2;
		addPanel.add(label3, gbc);
			
//		attribute name 4
		JLabel label4 = new JLabel("Year");
		label4.setPreferredSize(dimension1);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 3;
		addPanel.add(label4, gbc);
		
//		attribute name 5
		JLabel label5 = new JLabel("About");
		label5.setPreferredSize(dimension1);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 4;
		addPanel.add(label5, gbc);
			
			

		JButton addButton = new JButton("Add to database");
		gbc.gridx = 1;
		gbc.gridy = 7;
		addPanel.add(addButton, gbc);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				try {
					String addQuery = "INSERT INTO TesTable (Title, Author, Genre, Year, About, Available, BookID) VALUES(?,?,?,?,?,?,?)";
					PreparedStatement statement = conn
							.prepareStatement(addQuery);
					statement.setString(1, addTextField1.getText());
					statement.setString(2, addTextField2.getText());
					statement.setObject(3, genreList.getSelectedItem());
					statement.setString(4, addTextField4.getText());
					statement.setString(5, addTextField5.getText());
					statement.setString(6, "Yes");
					statement.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Book added successfully");
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

}
