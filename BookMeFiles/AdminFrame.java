package BookMeFiles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminFrame extends HomePageFrame {
	
	FrameGenerator frameGenerator;
	
	public AdminFrame (FrameGenerator frameGenerator) {
		super(frameGenerator);
		
		this.setTitle("BookMe Admin Homepage");
		this.setBounds(10, 10, 1000, 500);		
		
		JPanel addRemovePanel = new JPanel();
		
		GridBagLayout GBL1 = new GridBagLayout();
		addRemovePanel.setLayout(GBL1);
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.insets = new Insets(10, 10, 10, 10);

		JButton addAdmin = new JButton("Add Books");
		addAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				AddFrame AFAdd = new AddFrame();
				
			}

			
		});
		
		
		JButton removeAdmin = new JButton("Remove Books");
		removeAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				AdminPanel adPan = new AdminPanel();
				RemoveFrame AFAdd = new RemoveFrame();
			}
	});
		
		JButton adlogout = new JButton("Admin Logout");
		adlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				JOptionPane.showMessageDialog(null, "You have logged out as admin!");	
				FrameGenerator fg = new FrameGenerator();		
				disposeK2();				
			}
		});
		
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		addRemovePanel.add(addAdmin, gbc1);

		gbc1.gridx = 2;
		gbc1.gridy = 0;
		addRemovePanel.add(removeAdmin, gbc1);
		
		gbc1.gridx = 3;
		gbc1.gridy = 0;
		addRemovePanel.add(new JLabel(), gbc1);

		gbc1.gridx = 4;
		gbc1.gridy = 0;
		addRemovePanel.add(new JLabel(), gbc1);
		
		gbc1.gridx = 5;
		gbc1.gridy = 0;
		addRemovePanel.add(adlogout, gbc1);
		
		
		this.add(addRemovePanel, BorderLayout.SOUTH);
		
		}
		
		public void disposeK2(){
			this.dispose();
		}
}
