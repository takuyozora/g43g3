package gui;

import gui.includes.Content;
import gui.includes.MenuBar;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainWindow extends JFrame {
	
	public MenuBar menuBar = new MenuBar();
	public Content content = new Content();
	
	public MainWindow() {
		super("Squash Pro");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(500, 500));;
		this.setLayout(new BorderLayout());
		// this.setResizable(false);
		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	public static void main(String[] args) {
		MainWindow fenetre = new MainWindow();
	}

}
