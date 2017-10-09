package BookMeFiles;

import java.awt.*;
import java.sql.Connection;
import javax.swing.*;

public class HomePageFrame extends JFrame {
	
	FrameGenerator frameGenerator;
	
	public HomePageFrame(FrameGenerator frameGenerator){
		
		this.setBounds(10, 10, 930, 430);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("BookMe Homepage");
		BorderLayout homeLayout = new BorderLayout();
		this.setLayout(homeLayout);
		this.setBackground(Color.RED);
		
		TitlePanel titlePanel = new TitlePanel();
		this.add(titlePanel, BorderLayout.NORTH);
		
		HomePagePanel homePagePanel = new HomePagePanel(frameGenerator);
		this.add(homePagePanel, BorderLayout.CENTER);
	
	
		
		this.revalidate();
	}
		
	
	public void disposeHomePageFrame(HomePageFrame hpf) {
		hpf.dispose();
	}
}