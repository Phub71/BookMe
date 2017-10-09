package BookMeFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class UserFrame extends JFrame {
	
	FrameGenerator frameGenerator;
	int userID;
	Connection conn;
	PreparedStatement pst, pstn;
	ResultSet rs, rsn;
	Object userN;
	
	
	public UserFrame(FrameGenerator frameGenerator, int userID) {
		
		conn = Connectionsqlite.ConnectorDB();
		
		try{
			String nameqry = "select FullName from user where UserID='"+userID+"'";
			pstn = conn.prepareStatement(nameqry);
			rsn = pstn.executeQuery();
			
			userN = rsn.getObject(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				pstn.close();
				rsn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.userID = userID;
		this.setBounds(10, 10, 1000, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle(userN+"'s Homepage");
		BorderLayout homeLayout = new BorderLayout();
		this.setLayout(homeLayout);
		
		TitlePanel titlePanel = new TitlePanel();
		this.add(titlePanel, BorderLayout.NORTH);
		
		UserHomePanel homePagePanel = new UserHomePanel(frameGenerator, userID);
		this.add(homePagePanel, BorderLayout.CENTER);
		
		JPanel bottomUserPanel = new JPanel();
		GridBagLayout userGbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		bottomUserPanel.setLayout(userGbl);
		
		JButton myReserve = new JButton("My Reservations");
		myReserve.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
				
				
				String query = "select * from TesTable where exists(select * from UserBook where TesTable.BookID = UserBook.BookID AND UserBook.UserID='"+userID+"')";
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				
				JTable table = new JTable();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				ReservedBooks rsB = new ReservedBooks(table, userID);
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					pst.close();
					rs.close();
										
				}catch(Exception e){
					e.printStackTrace();
				}
			}
				}
		});
		
		JButton userLogout = new JButton("Logout");
		userLogout.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent ae){
				
				JOptionPane.showMessageDialog(null, "You have logged out.");	
				FrameGenerator fg = new FrameGenerator();		
				disposeOlympus();
			}
		});
	
		gbc.gridx = 0;
		gbc.gridy = 0;
		bottomUserPanel.add(myReserve, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		bottomUserPanel.add(userLogout, gbc);
		
		this.add(bottomUserPanel, BorderLayout.SOUTH);
		
		
	}
	public void disposeOlympus() {
		this.dispose();
	}

}