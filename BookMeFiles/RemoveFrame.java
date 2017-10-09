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
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class RemoveFrame extends JFrame {
	
	Connection conn = null;
	
	public RemoveFrame() {
		
		conn = Connectionsqlite.ConnectorDB();
		
		this.setBounds(10, 10, 450, 320);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Admin Remove Book");
		this.setLayout(new BorderLayout());
		
		TitlePanel tpr = new TitlePanel();
		this.add(tpr, BorderLayout.NORTH);
		
		JLabel removeLabel = new JLabel("Book ID:");
		
		JPanel removePanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		this.add(removePanel, BorderLayout.CENTER);
		removePanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10 ,10);
		
		
		JTextField removeTextField = new JTextField();
		Dimension dimension = new Dimension(150, 20);
		removeTextField.setPreferredSize(dimension);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar){
				
			
				try{
					String text = removeTextField.getText();
					String query1 = ("DELETE FROM TesTable WHERE BookID ='"+text+"'");
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Book deleted");
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			
				
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		removePanel.add(removeLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		removePanel.add(removeTextField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		removePanel.add(removeButton, gbc);
		
		this.revalidate();
	
	}
	public void addPanel(JPanel panel) {
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
}