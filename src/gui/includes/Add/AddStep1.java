package gui.includes.Add;

import gui.MainWindow;
import gui.includes.Terrain.Terrain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddStep1 extends JPanel {
	
	public static AddStep1 panel = new AddStep1();
	
	private JLabel title = new JLabel("Step 1", 10);
	private JPanel formContainer = new JPanel();
	private JLabel progNameDescription = new JLabel("Nom du programme : ", 10);
	private JTextField progName = new JTextField(1);
	
	private JButton next = new JButton("Suivant");
	
	
	public AddStep1() {
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(formContainer, BorderLayout.CENTER);
		formContainer.setLayout(new BorderLayout());
		formContainer.add(progNameDescription, BorderLayout.NORTH);
		formContainer.add(progName, BorderLayout.NORTH);
		formContainer.add(next, BorderLayout.CENTER);

	//	this.add(voirterrain); Bouton pour voir le terrain - test uniquement
		
		next.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				nextPressed();
			} 
		});
		
	}
	
	public void nextPressed() {
		MainWindow.fenetre.buildWindow(Terrain.terrain);
		Terrain.terrain.requestFocusInWindow();
	}
}
