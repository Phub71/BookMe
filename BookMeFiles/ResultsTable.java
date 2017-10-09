package BookMeFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class ResultsTable extends JFrame{
	
	JTable table;
	Connection conn= null;
	PreparedStatement pst = null;
	
	
	public ResultsTable(JTable t){
		
		
		conn = Connectionsqlite.ConnectorDB();	
		
		table = t;
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		table.setRowHeight(60);
		table.setSize(this.getSize());
		
		
		
		JScrollPane tablePane = new JScrollPane(table);
		
		this.setBounds(10, 10, 800, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Your Search Results");
		this.setLayout(new BorderLayout());
		
		
		JPanel tablePanel = new JPanel();
		tablePanel.add(tablePane);
		this.add(tablePanel, BorderLayout.CENTER);
				
		}
			
	
		
		
	
}
