package gui.includes.Programmes;

import gui.Database;

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
	private JPanel nextContainer = new JPanel();
	private Database database = new Database();
	JPanel radioPanel = new JPanel();
	
	private JButton simuler = new JButton("Simuler");
	
	public Programmes() {
		this.setLayout(new BorderLayout());
		nextContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.add(topContainer, BorderLayout.NORTH);
		this.add(progContainer, BorderLayout.CENTER);
		this.add(nextContainer, BorderLayout.SOUTH);
		
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
			} 
		});
	}
	
	public void showProgramme() {
		database.connect();
		radioPanel.setLayout(new GridLayout(database.getProgram().size(), 1));
		ButtonGroup bgroup = new ButtonGroup();
	
		for(String s:database.getProgram()) {
			JRadioButton radioBtn = new JRadioButton(s , false);
			bgroup.add(radioBtn);
			radioPanel.add(radioBtn);
		}
		database.logout();
	}
}
