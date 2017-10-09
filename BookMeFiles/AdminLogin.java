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

import net.proteanit.sql.DbUtils;

public class AdminLogin extends JFrame {
	
	Connection conn = null;
	FrameGenerator frameGenerator;
	
	public AdminLogin(FrameGenerator frameGenerator) {
		
		conn = Connectionsqlite.ConnectorDB();
		
		this.setBounds(10, 10, 450, 320);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Admin Login");
		this.setLayout(new BorderLayout());
		
		
		TitlePanel titlePanel = new TitlePanel();
		Dimension dimension = new Dimension(150, 30);
		
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(dimension);
		
		JButton adlog = new JButton("AdLog");
		adlog.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
							
				try{
					String text = password.getText();
					String query1 = "SELECT * FROM AdminPass WHERE Password ='"+text+"'";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					ResultSet rs1 = pst1.executeQuery();

					if (rs1.next()) {
						JOptionPane.showMessageDialog(null, "Password entered correctly!");
						AdminFrame adframe = new AdminFrame(frameGenerator);						
						frameGenerator.hpf.dispose();
						disposeEverest();
						
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect Password!");
					}
					
				}catch (Exception e){
					e.printStackTrace();
				}finally{
					try{
						conn.close();
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
		});
		
		JLabel passWord = new JLabel("Password:");
		
		
		this.add(titlePanel, BorderLayout.NORTH);
		
		JPanel jpl = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		jpl.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		jpl.add(passWord, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		jpl.add(password, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		jpl.add(adlog, gbc);
	
		this.add(jpl, BorderLayout.CENTER);
	    this.revalidate();
	
	}
	public void disposeEverest() {
		this.dispose();
	}
}
