package BookMeFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class AdminPanel extends JPanel {
	
	 Connection conn = null;
	
	 public AdminPanel(){
		 
		 JTable adminTable = new JTable();
		 
		 conn = Connectionsqlite.ConnectorDB();
												
		 try{
			 String query = "select * from TesTable";
			 PreparedStatement pst = conn.prepareStatement(query);
			 ResultSet rs = pst.executeQuery();
			 adminTable.setModel(DbUtils.resultSetToTableModel(rs));
					
			 ResultsTable results = new ResultsTable(adminTable);
						
		 	}catch (Exception e){
				e.printStackTrace();
		 	}
									
	 }
			
}
	
	


