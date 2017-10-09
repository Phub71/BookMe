package BookMeFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class SignUpFrame extends JFrame {

	Connection conn = null;
	PreparedStatement statement = null;
	PreparedStatement pst = null;
	
	public SignUpFrame(){
		
		conn = Connectionsqlite.ConnectorDB();
				
		this.setBounds(10, 10, 450, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Sign Up");
		this.setLayout(new BorderLayout());
		
		JPanel signUpPanel = new JPanel();
		TitlePanel titleP = new TitlePanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		JLabel userNamel = new JLabel("Username:");
		JLabel passwordl = new JLabel("Password:");
		JLabel fullNamel = new JLabel("Full Name:");
		
		JTextField userName = new JTextField();
		Dimension dimension = new Dimension(200, 30);	
		userName.setPreferredSize(dimension);		
		
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(dimension);		
		
		JTextField fullName = new JTextField();
		fullName.setPreferredSize(dimension);		
		
		JButton signUpbtn = new JButton("Sign Up");
		signUpbtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				try{
					
					String userText = userName.getText();
					String passwordText = password.getText();
					String fullNameText = fullName.getText();
														 
					
			    	pst = conn.prepareStatement("select UserName from user where UserName='"+userText+"'");
					ResultSet rs = pst.executeQuery();					
					
					
					if(rs.next() == false){
						
						statement = conn.prepareStatement("INSERT INTO user (FullName,UserName,Password)VALUES(?,?,?)");           
						statement.setString(1,fullNameText);
						statement.setString(2,userText);
						statement.setString(3,passwordText);
						statement.executeUpdate();
						
						
						
						
						JOptionPane.showMessageDialog(null, "User successfully added!");						
						closeFrame();
					}else{															
						JOptionPane.showMessageDialog(null, "That username already exists");
						SignUpFrame nFrame = new SignUpFrame();
						disposeThis();
					}
					
				}catch (Exception e){
					e.printStackTrace();
				}finally{
					try{
						conn.close();
					//	statement.close();
					//	pst.close();
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			});
		
		
		this.add(titleP, BorderLayout.NORTH);
		this.add(signUpPanel, BorderLayout.CENTER);
		
		signUpPanel.setLayout(new GridBagLayout());
		

		gbc.gridx = 0;
		gbc.gridy = 0;
		signUpPanel.add(fullNamel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		signUpPanel.add(fullName, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		signUpPanel.add(userNamel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		signUpPanel.add(userName, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		signUpPanel.add(passwordl, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		signUpPanel.add(password, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		signUpPanel.add(signUpbtn, gbc);
	}
	
	public void closeFrame(){
		this.dispose();
	}
	
	public void disposeThis(){
		this.dispose();
	}

}

