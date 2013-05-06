package gui.includes;

import gui.MainWindow;
import gui.includes.Add.*;
import gui.includes.Favoris.*;
import gui.includes.Reglages.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;

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
		this.setSize(700, 300); // Not working ...
		this.add(addButton);
		this.add(favsButton);
		this.add(settingsButton);
		this.setBackground(Color.WHITE);
		
		addButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				addButtonPressed();
			} 
		});
		favsButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				favsButtonPressed();
			} 
		});
		settingsButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				settingsButtonPressed();
			} 
		});
	}
	
	public void addButtonPressed() {
		MainWindow.fenetre.buildWindow(AddStep1.panel);
	}
	
	public void favsButtonPressed() {
		MainWindow.fenetre.buildWindow(Favoris.panel);
	}
	
	public void settingsButtonPressed() {
		MainWindow.fenetre.buildWindow(Reglages.panel);
	}
	
}
	
