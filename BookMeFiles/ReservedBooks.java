package BookMeFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class ReservedBooks extends JFrame{
	
	FrameGenerator fgReserved;
	Dimension d = new Dimension(70, 30);
	JTable table;
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public ReservedBooks(JTable t, int UserID){
		
		
		conn = Connectionsqlite.ConnectorDB();	
		
		table = t;
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		table.setRowHeight(60);
		table.setSize(this.getSize());
		
		
		
		JScrollPane tablePane = new JScrollPane(table);
		
		this.setBounds(10, 10, 700, 650);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Your Search Results");
		this.setLayout(new BorderLayout());
		
		
		JPanel tablePanel = new JPanel();
		tablePanel.add(tablePane);
		this.add(tablePanel, BorderLayout.CENTER);
		
		TitlePanel tp = new TitlePanel();
		
		JPanel buttonPanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(7, 7, 7, 7);
		buttonPanel.setLayout(gbl);
		
		JLabel returnLabel = new JLabel("Select the book you want to return");
		
		JButton returnButton = new JButton("Return book");
		returnButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
				Object selected = table.getModel().getValueAt(table.getSelectedRow(), 6);
				
				String query = "delete from UserBook where BookID='"+selected+"'";
				pst = conn.prepareStatement(query);
				pst.executeUpdate();
				
				String query1 = "update TesTable set Available= 'Yes' where TesTable.BookID='"+selected+"'";
				pst = conn.prepareStatement(query1);
				pst.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Book returned successfully");
				
				String query3 = "select * from TesTable where exists(select * from UserBook where TesTable.BookID = UserBook.BookID AND UserBook.UserID='"+UserID+"')";
				pst = conn.prepareStatement(query3);
				rs = pst.executeQuery();
				
				JTable table = new JTable();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				ReservedBooks rsB = new ReservedBooks(table, UserID);
				
				disposePlease();
				
				}catch(Exception e){
					
				}
			}
			});

		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(returnLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		buttonPanel.add(returnButton, gbc);
		
		this.add(buttonPanel, BorderLayout.EAST);
		this.add(tp, BorderLayout.NORTH);
		
	}
			
	
	
		
		
	public void disposePlease(){
		this.dispose();
	}
}
