package gui;

import gui.includes.Content;
import gui.includes.MenuBar;
import gui.includes.Terrain;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;


public class MainWindow extends JFrame {
	
	public MenuBar menuBar = new MenuBar();
	public static Content content = new Content();
	public static MainWindow fenetre;
	public static final String testString = "Mon string";
	public static BorderLayout layout = new BorderLayout();
	
	public MainWindow() {
		super("Squash Pro");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(500, 500));
		this.setLayout(layout);
		// this.setResizable(false);
		
		this.add(menuBar, layout.NORTH);
		this.add(content, layout.CENTER);
		this.validate();
		this.repaint();
	}
	
	public static void main(String[] args) {
		fenetre = new MainWindow();
		Terrain.animator.start();
	}
	
	public void buildWindow(Component component) {
		this.getContentPane().removeAll();
		this.add(menuBar, layout.NORTH);
		this.add(component, layout.CENTER);
		this.validate();
		this.repaint();
	}

}
