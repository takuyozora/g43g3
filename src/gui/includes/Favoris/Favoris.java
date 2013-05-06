package gui.includes.Favoris;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Favoris extends JPanel {
	
	public static Favoris panel = new Favoris();
	
	public Favoris() {
		this.add(new JLabel("Favoris", 10));
		
	}
}
