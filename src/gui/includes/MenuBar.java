package gui.includes;

import gui.MainWindow;
import gui.includes.Add.*;
import gui.includes.Programmes.*;
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
		this.setSize(700, 300); // Not working ... Of course, the layout manager decides what to do
		this.add(addButton);
		this.add(favsButton);
		this.add(settingsButton);
		this.setBackground(new Color(245, 245, 245));
		
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
		MainWindow.fenetre.buildWindow(new AddStep1());
	}
	
	public void favsButtonPressed() {
		MainWindow.fenetre.buildWindow(new Programmes());
	}
	
	public void settingsButtonPressed() {
		MainWindow.fenetre.buildWindow(new Reglages());
	}
	
}
	
