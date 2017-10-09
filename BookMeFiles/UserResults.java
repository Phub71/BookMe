package BookMeFiles;

import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class UserResults extends JFrame{
	
	int userID;
	JTable table;
	Connection conn= null;
	PreparedStatement pst = null;
	ResultSet rs;
	

	public UserResults(JTable t, int userID, String text) {
		
	
		conn = Connectionsqlite.ConnectorDB();	
		
		table = t;
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		table.setRowHeight(60);
		table.setSize(this.getSize());
		
		
		
		JScrollPane tablePane = new JScrollPane(table);
		
		this.setBounds(10, 10, 700, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Your Search Results");
		this.setLayout(new BorderLayout());
		
		JPanel tablePanel = new JPanel();
		tablePanel.add(tablePane);
		this.add(tablePanel, BorderLayout.CENTER);
		
		
		JPanel buttonPanel = new JPanel();
		GridBagLayout reserveLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(7, 7, 7, 7);
		buttonPanel.setLayout(reserveLayout);
		
		JLabel reserveLabel = new JLabel("Select the book you wish to reserve");
		
	
		JButton button  = new JButton("Reserve");
		button.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{				
					Object selected = table.getModel().getValueAt(table.getSelectedRow(), 6);
					Object compare = table.getModel().getValueAt(table.getSelectedRow(), 5);				
				
					if("No".compareTo(compare.toString())!=0){
					
						String qry = "update TesTable set Available= 'No' where BookID='"+selected+"'";
						pst = conn.prepareStatement(qry);
						pst.executeUpdate();
					
						
						String qry1 = "insert into UserBook values('"+userID+"', '"+selected+"')";
						pst = conn.prepareStatement(qry1);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Your book has been reserved.");
				
						String query = "select * from TesTable where Title LIKE '"+"%"+text+"%"+"' OR Author LIKE '"+"%"+text+"%"+"' OR Genre LIKE '"+"%"+text+"%"+"'";
						pst = conn.prepareStatement(query);
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					
						UserResults results = new UserResults(table, userID, text);
						disposePlease();
					}else{
						JOptionPane.showMessageDialog(null, "That book is not available for reservation");
					}
				
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
		});
						
			gbc.gridx = 0;
			gbc.gridy = 0;
			buttonPanel.add(reserveLabel, gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			buttonPanel.add(button, gbc);
			
			
			this.add(buttonPanel, BorderLayout.EAST);
		}
	public void disposePlease(){
		this.dispose();
	}
}
