package BookMeFiles;

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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class UserHomePanel extends JPanel{
	
	Connection conn = null;
	FrameGenerator frameGenerator;
	int UserID;
	Object userN;
	PreparedStatement pst;
	ResultSet rs;
	
	public UserHomePanel(FrameGenerator frameGenerator, int UserID){

		
		
		try{
			conn = Connectionsqlite.ConnectorDB();
			String nameqry = "select FullName from user where UserID='"+UserID+"'";
			pst = conn.prepareStatement(nameqry);
			rs = pst.executeQuery();
			
			userN = rs.getObject(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		GridBagLayout homeLayout = new GridBagLayout();
		this.setLayout(homeLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		JTable table = new JTable();
		
		JTextField searchField = new JTextField();
		Dimension dimension = new Dimension(200, 30);	
		searchField.setPreferredSize(dimension);
		
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String text = searchField.getText();
					String query = "select * from TesTable where Title LIKE '"+"%"+text+"%"+"' OR Author LIKE '"+"%"+text+"%"+"' OR Genre LIKE '"+"%"+text+"%"+"'";
					pst = conn.prepareStatement(query);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					UserResults results = new UserResults(table, UserID, text);
					
				}catch (Exception e){
					e.printStackTrace();
				}finally{
					try{
						pst.close();
						rs.close();
						conn.close();
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
								
			}
		});
		
		
		
		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		JLabel orLabel = new JLabel("OR");
		orLabel.setFont(new Font("Serif", Font.ITALIC, 25));
		
		JLabel optionLabel = new JLabel("Search by Genre:");
		optionLabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		JButton optionNovel = new JButton("Novel");
		optionNovel.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String query1 = "select * from TesTable where Genre='Novel'";
					pst = conn.prepareStatement(query1);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					UserResults results = new UserResults(table, UserID, "Novel");
					
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
		});
		
		JButton optionSciFi = new JButton("Sci-Fi");
		optionSciFi.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String query2 = "select * from TesTable where Genre='Sci-Fi'";
					pst = conn.prepareStatement(query2);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					UserResults results = new UserResults(table, UserID, "Sci-Fi");
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionFood = new JButton("Cuisine");
		optionFood.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String query3 = "select * from TesTable where Genre='Cuisine'";
					pst = conn.prepareStatement(query3);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					UserResults results = new UserResults(table, UserID, "Cuisine");
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionFantasy = new JButton("Fantasy");
		optionFantasy.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String query3 = "select * from TesTable where Genre='Fantasy'";
					pst = conn.prepareStatement(query3);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					UserResults results = new UserResults(table, UserID, "Fantasy");
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionHistory = new JButton("History");
		optionHistory.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String query3 = "select * from TesTable where Genre='History'";
					pst = conn.prepareStatement(query3);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					UserResults results = new UserResults(table, UserID, "History");
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionBusiness = new JButton("Business");
		optionBusiness.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{	
					conn = Connectionsqlite.ConnectorDB();
					String query3 = "select * from TesTable where Genre='Business'";
					pst = conn.prepareStatement(query3);
					rs = pst.executeQuery();
					
					
					table.setModel(DbUtils.resultSetToTableModel(rs));			
					UserResults results = new UserResults(table, UserID, "Business");
					
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JLabel welcome = new JLabel("Welcome, "+userN);
		welcome.setFont(new Font("Serif", Font.BOLD, 22));
		
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(searchLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(searchField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(searchButton, gbc);
		
		
		gbc.gridx = 5;
		gbc.gridy = 0;
		this.add(welcome, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(orLabel, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(optionLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(optionNovel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(optionSciFi, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(optionFood, gbc);	
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(optionFantasy, gbc);	
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.add(optionHistory, gbc);	
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		this.add(optionBusiness, gbc);	
		
	}

}
