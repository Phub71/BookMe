package BookMeFiles;

import java.sql.*;
import javax.swing.*;

public class Connectionsqlite {

	Connection conn = null;
	
	public static Connection ConnectorDB(){
		
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Tobias\\workspace\\Databases\\BookMe");
			
	//		JOptionPane.showMessageDialog(null, "Connection established");
			return conn;
			
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
		
	}
	
	
}
