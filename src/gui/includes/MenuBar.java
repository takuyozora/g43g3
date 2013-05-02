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
	private int lastclicked = 0;
	
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
		if(lastclicked == 0) {
			MainWindow.fenetre.remove(MainWindow.content);
		}
		else if(lastclicked == 2) {
			MainWindow.fenetre.remove(Favoris.panel);
		}
		else if(lastclicked == 3) {
			MainWindow.fenetre.remove(Reglages.panel);
		}
		MainWindow.fenetre.add(AddStep1.panel, MainWindow.layout.CENTER);
		MainWindow.fenetre.reload();
		lastclicked = 1;
	}
	
	public void favsButtonPressed() {
		if(lastclicked == 0) {
			MainWindow.fenetre.remove(MainWindow.content);
		}
		else if(lastclicked == 1) {
			MainWindow.fenetre.remove(AddStep1.panel);
		}
		else if(lastclicked == 3) {
			MainWindow.fenetre.remove(Reglages.panel);
		}
		MainWindow.fenetre.add(Favoris.panel, MainWindow.layout.CENTER);
		MainWindow.fenetre.reload();
		lastclicked = 2;
	}
	
	public void settingsButtonPressed() {
		if(lastclicked == 0) {
			MainWindow.fenetre.remove(MainWindow.content);
		}
		else if(lastclicked == 1) {
			MainWindow.fenetre.remove(AddStep1.panel);
		}
		else if(lastclicked == 2) {
			MainWindow.fenetre.remove(Favoris.panel);
		}
		MainWindow.fenetre.add(Reglages.panel, MainWindow.layout.CENTER);
		MainWindow.fenetre.reload();
		lastclicked = 3;
	}
	
}
	
