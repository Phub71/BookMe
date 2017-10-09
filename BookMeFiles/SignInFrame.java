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
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignInFrame extends JFrame {
	
	Connection conn = null;
	FrameGenerator frameGenerator;
	PreparedStatement pst;
	ResultSet rs;
	
	public SignInFrame(FrameGenerator frameGenerator) {
		
		
		
		this.setBounds(10, 10, 450, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Sign In");
		this.setLayout(new BorderLayout());
		
		JPanel signInPanel = new JPanel();
		TitlePanel titlePanel = new TitlePanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		
		JLabel userName1 = new JLabel("Username:");
		JLabel passWord1 = new JLabel("Password:");
		
		JTextField userName = new JTextField();
		Dimension dimension = new Dimension(180, 30);
		userName.setPreferredSize(dimension);
		
		JPasswordField passWord = new JPasswordField();
		passWord.setPreferredSize(dimension);
		
		JButton signIn = new JButton("Sign In");
		signIn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				
				
				try{
					conn = Connectionsqlite.ConnectorDB();
					String userText = userName.getText();
					String passwordText = passWord.getText();
					String query = ("SELECT * FROM user WHERE UserName ='"+userText+"' AND Password ='"+passwordText+"'");
			    	PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs2 = pst.executeQuery();					
					
					if(rs2.next()){
						JOptionPane.showMessageDialog(null, "You have logged in!");
						pst = conn.prepareStatement("select UserID from user where UserName= '"+userText+"'");
						rs = pst.executeQuery();
						UserFrame userFrame = new UserFrame(frameGenerator, rs.getInt("UserID"));
						frameGenerator.hpf.dispose();
						disposeMontBlanc();
						
						rs.close();
						conn.close();
						pst.close();
						
					}else{
						
						JOptionPane.showMessageDialog(null, "Username or Password incorrect!");
						
					}
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			});
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(signInPanel, BorderLayout.CENTER);
		
		signInPanel.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		signInPanel.add(userName1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		signInPanel.add(userName, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		signInPanel.add(passWord1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		signInPanel.add(passWord, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		signInPanel.add(signIn, gbc);
		
		
	}
	public void disposeMontBlanc() {
		this.dispose();
	}
}
