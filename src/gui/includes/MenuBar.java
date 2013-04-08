package gui.includes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuBar extends JPanel {

	String resPath = "./src/gui/res/"; // Path to resources
	
	/* MenuBar buttons definition w/ icon */
	ImageIcon addIcon = new ImageIcon(resPath+"add.png");
	JButton addButton = new JButton("Nouveau", addIcon);
	
	ImageIcon favsIcon = new ImageIcon(resPath+"favs.png");
	JButton favsButton = new JButton("Favoris", favsIcon);

	ImageIcon settingsIcon = new ImageIcon(resPath+"settings.png");
	JButton settingsButton = new JButton("Paramètres", settingsIcon);

	public MenuBar() {
		
		
		this.setLayout(new FlowLayout());
		this.add(addButton);
		this.add(favsButton);
		this.add(settingsButton);
		this.setBackground(Color.BLACK);
	}
	
	
	
}
	
