package gui.includes;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Content extends JPanel {
	
	public JLabel bienvenue = new JLabel("Bienvenue !");
	
	public Content() {
		this.setBackground(Color.WHITE);
		this.add(bienvenue);
	}
	
}
