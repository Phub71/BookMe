package BookMeFiles;

import java.awt.*;
import java.sql.Connection;

import javax.swing.*;


public class AdminRemoveFrame extends JFrame{
	
	Connection conn = null;
	
	public AdminRemoveFrame(){	
		
		conn = Connectionsqlite.ConnectorDB();
		
		this.setBounds(10, 10, 450, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Remove a book");
		this.setLayout(new BorderLayout());
		
		TitlePanel titlePanel = new TitlePanel();
		
		
		JPanel removePanel = new JPanel();
		GridBagLayout gridBag = new GridBagLayout();	
		removePanel.setLayout(gridBag);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		JLabel removeLabel = new JLabel("Book Name:");
		JTextField removeField = new JTextField();
		JButton removeButton = new JButton("Remove");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		removePanel.add(removeLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		removePanel.add(removeField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		removePanel.add(removeButton, gbc);
		
		
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(removePanel, BorderLayout.CENTER);
		
		
		
		
	}

}
