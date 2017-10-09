package BookMeFiles;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{

	public TitlePanel(){
		
		ImageIcon image = new ImageIcon("BookMe2.png");
		
		GridBagLayout thisLayout = new GridBagLayout();
		this.setLayout(thisLayout);
		
		JButton titleButton = new JButton();
		titleButton.setIcon(image);
		titleButton.setOpaque(false);
		titleButton.setContentAreaFilled(false);
		titleButton.setBorderPainted(false);
		titleButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent a){
				System.out.println("You pressed the HOME button!");
			}
		});
		
		this.add(titleButton);
		
	}
}
