package gui.includes;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuBar extends JPanel {

	
	public MenuBar() {
		this.setLayout(new FlowLayout());
		this.add(new JButton("Nouveau"));
		this.add(new JButton("Favoris"));
		this.add(new JButton("Aide"));
		
		this.setBackground(Color.BLACK);
	}
	
}
