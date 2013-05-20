package gui.includes.Reglages;

import gui.MainWindow;
import gui.includes.MenuBar;
import gui.includes.Terrain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Reglages extends JPanel {
	
	private JButton posLanceur = new JButton("Position du lanceur");
	public Reglages() {
		this.add(posLanceur);
		posLanceur.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				posLanceurPressed();
			} 
		});
		
		// ImageIcon image = new ImageIcon(MenuBar.resPath+"terrain_sol.png");
		
		
		
		
	}
	private void posLanceurPressed() {
		MainWindow.fenetre.buildWindow(new PositionLanceur());
	}
}
