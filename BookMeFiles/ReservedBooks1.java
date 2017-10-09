package BookMeFiles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ReservedBooks1 extends JFrame {

	FrameGenerator fgReserved;
	Dimension d = new Dimension(70, 30);
	int userID;
	JTable table;
	Connection conn;
	
	public ReservedBooks1(JTable table, int userID) {
		
		conn = Connectionsqlite.ConnectorDB();
		this.table = table;
		table.setFont(new Font("Serif", Font.PLAIN, 20));
		table.setRowHeight(60);
		table.setSize(this.getSize());
		this.userID = userID;
		
		
		this.setBounds(0, 0, 700, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Your Reserved Books");
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		

		TitlePanel tp = new TitlePanel();
		this.add(tp, BorderLayout.NORTH);

		JPanel ejp = new JPanel();

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		ejp.setLayout(gbl);

		

		JLabel jl = new JLabel("Select which book you want to return.");
		jl.setLayout(bl);
		gbc.gridx = 0;
		gbc.gridy = 0;
		ejp.add(jl, gbc);

		JButton jb = new JButton("Return book");
		jb.setLayout(bl);
		gbc.gridx = 0;
		gbc.gridy = 1;
		ejp.add(jb, gbc);
		
		
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				try{
					Object selected = table.getModel().getValueAt(table.getSelectedRow(), 6);
					
					String query = "delete from UserBook where UserID='"+userID+"'";
					PreparedStatement pst1 = conn.prepareStatement(query);
					pst1.executeUpdate();
					
					String query1 = "update TesTable set Available= 'Yes' where TesTable.BookID='"+selected+"'";
					PreparedStatement pst = conn.prepareStatement(query1);
					pst.executeUpdate();
					
				}catch (Exception e){
					e.printStackTrace();
			}
			}
		});
		
	
		
		
		this.add(table, BorderLayout.CENTER);
		this.add(ejp, BorderLayout.EAST);
		
		this.revalidate();
	}

}
