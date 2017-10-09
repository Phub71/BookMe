package BookMeFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;




public class HomePagePanel extends JPanel{	

	Connection conn = null;
	FrameGenerator frameGenerator;
	
	public HomePagePanel(FrameGenerator frameGenerator){		
		
		conn = Connectionsqlite.ConnectorDB();
		
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
					String text = searchField.getText();
					String query = "select * from TesTable where Title LIKE '"+"%"+text+"%"+"' OR Author LIKE '"+"%"+text+"%"+"' OR Genre LIKE '"+"%"+text+"%"+"'";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				
					ResultsTable results = new ResultsTable(table);
					
				}catch (Exception e){
					e.printStackTrace();
				}
								
			}
		});
		
		JButton adminButton = new JButton("Admin Login");
		adminButton.setOpaque(false);
		adminButton.setContentAreaFilled(false);
		adminButton.setBorderPainted(false);
		adminButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				AdminLogin adminLogin = new AdminLogin(frameGenerator);				
								
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
					String query1 = "select * from TesTable where Genre='Novel'";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					ResultSet rs1 = pst1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs1));
				
					ResultsTable results = new ResultsTable(table);
					
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
		});
		
		JButton optionSciFi = new JButton("Sci-Fi");
		optionSciFi.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					String query2 = "select * from TesTable where Genre='Sci-Fi'";
					PreparedStatement pst2 = conn.prepareStatement(query2);
					ResultSet rs2 = pst2.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs2));
				
					ResultsTable results = new ResultsTable(table);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionFood = new JButton("Cuisine");
		optionFood.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					String query3 = "select * from TesTable where Genre='Cuisine'";
					PreparedStatement pst3 = conn.prepareStatement(query3);
					ResultSet rs3 = pst3.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs3));
				
					ResultsTable results = new ResultsTable(table);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionFantasy = new JButton("Fantasy");
		optionFantasy.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					String query3 = "select * from TesTable where Genre='Fantasy'";
					PreparedStatement pst3 = conn.prepareStatement(query3);
					ResultSet rs3 = pst3.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs3));
				
					ResultsTable results = new ResultsTable(table);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionHistory = new JButton("History");
		optionHistory.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					String query3 = "select * from TesTable where Genre='History'";
					PreparedStatement pst3 = conn.prepareStatement(query3);
					ResultSet rs3 = pst3.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs3));
				
					ResultsTable results = new ResultsTable(table);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton optionBusiness = new JButton("Business");
		optionBusiness.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{				
					String query3 = "select * from TesTable where Genre='Business'";
					PreparedStatement pst3 = conn.prepareStatement(query3);
					ResultSet rs3 = pst3.executeQuery();
					
					
					table.setModel(DbUtils.resultSetToTableModel(rs3));			
					ResultsTable results = new ResultsTable(table);
					
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JButton signIn = new JButton("Sign In");
		signIn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				SignInFrame newSignIn = new SignInFrame(frameGenerator);
			}
		});
		
		JButton signUp = new JButton("Sign Up");
		signUp.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){		
				SignUpFrame newSignUp = new SignUpFrame();
			}
		});
		
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
		this.add(signIn, gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 0;
		this.add(signUp, gbc);
		
		
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
		
		gbc.gridx = 6;
		gbc.gridy = 3;
		this.add(adminButton, gbc);
			
	}
}
