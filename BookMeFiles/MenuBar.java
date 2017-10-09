package BookMeFiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

public class MenuBar extends JFrame {
	
	public MenuBar() {

		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setSize(400, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
	//	fileMenu.addMenuListener(new MenuListener());
		menuBar.add(fileMenu);
		JMenu editMenu = new JMenu("Edit");
	//	editMenu.addMenuListener(new MenuListener());
		menuBar.add(editMenu);
		
		JMenuItem libraryAction = new JMenuItem("Library");
		JMenuItem closeAction = new JMenuItem("Close");
		fileMenu.add(libraryAction);
		fileMenu.addSeparator();
		fileMenu.add(closeAction);
		
		JMenuItem infoAction = new JMenuItem("Info");
		JMenuItem helpAction = new JMenuItem("Help");
		JMenuItem aboutAction = new JMenuItem("About");
		editMenu.add(infoAction);
		editMenu.addSeparator();
		editMenu.add(helpAction);
		editMenu.addSeparator();
		editMenu.add(aboutAction);
		
		this.setJMenuBar(menuBar);
		
		libraryAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("You have clicked an action button!");
			}
		});
	}
		
		public void ManuBar(MenuBar menuBar) {
			MenuBar mb = new MenuBar();
			mb.setVisible(true);
			
		}
		
      
}