package gui.includes.Programmes;

import gui.Database;
import gui.MainWindow;
import gui.includes.Terrain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class Programmes extends JPanel {
	
	private JPanel topContainer = new JPanel();
	private JPanel progContainer = new JPanel();
	private JPanel bottomContainer = new JPanel();
	private JPanel nextContainer = new JPanel();
	private JPanel deleteContainer = new JPanel();
	private Database database = new Database();
	JPanel radioPanel = new JPanel();
	
	private JButton simuler = new JButton("Simuler");
	private JButton supprimerBtn = new JButton("Supprimer");
	
	private int clickedRadio = 0;
	
	public Programmes() {
		this.setLayout(new BorderLayout());
		nextContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottomContainer.setLayout(new BorderLayout());
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(progContainer, BorderLayout.CENTER);
		this.add(bottomContainer, BorderLayout.SOUTH);
		
		bottomContainer.add(deleteContainer, BorderLayout.WEST);
		bottomContainer.add(nextContainer, BorderLayout.EAST);
		
		deleteContainer.add(supprimerBtn);
		
		
		
		JScrollPane scroller = new JScrollPane(progContainer);
		scroller.setBorder(null);
		this.add(scroller); 
		
		topContainer.add(new JLabel("SŽlectionner un programme pour le simuler", 10));
		nextContainer.add(simuler);
		showProgramme();
		progContainer.add(radioPanel);
		
		simuler.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				// Database.getProgramsNb();
				if(clickedRadio != 0) {
					database.connect();
					int x = database.getProgramById(clickedRadio)[0];
					int y = database.getProgramById(clickedRadio)[1];
					int mur = database.getProgramById(clickedRadio)[2];
					database.logout();
					
					if(mur != 0 && x != 0 && y != 0) {
						Terrain terrain2 = new Terrain(x, y, mur);
						terrain2.requestFocusInWindow();
						MainWindow.fenetre.buildWindow(terrain2);
						terrain2.requestFocusInWindow();
					}
				}
			} 
		});
		
		supprimerBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if(clickedRadio != 0) {					
					database.connect();
					database.deleteProgram(clickedRadio);
				//	System.out.println(clickedRadio);
					database.logout();
					MainWindow.fenetre.buildWindow(new Programmes());
				}
			} 
		});
	}
	
	public void showProgramme() {
		database.connect();
		radioPanel.setLayout(new GridLayout(database.getProgram().size(), 1));
		ButtonGroup bgroup = new ButtonGroup();
	
		for(final String s:database.getProgram()) {
			JRadioButton radioBtn = new JRadioButton(s , false);
			bgroup.add(radioBtn);
			final int row = database.getProgram().indexOf(s) +1;
			radioPanel.add(radioBtn);
			radioBtn.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					clickedRadio = row;
					// System.out.println(row);
				} 
			});
			// System.out.println(database.getProgram().indexOf(s));
		}
		database.logout();
	}
}
