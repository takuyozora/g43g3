package gui.includes.Add;

import gui.Database;
import gui.MainWindow;
import gui.includes.Terrain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddStep1 extends JPanel {
	
//	public static AddStep1 panel = new AddStep1();
	
	// private JLabel title = new JLabel("Step 1", 10);
	private JPanel formContainer1 = new JPanel();
	private JPanel formContainer = new JPanel();
	private JPanel nextContainer = new JPanel();
	private JPanel nameContainer = new JPanel();
	private JPanel murContainer = new JPanel();
	private JPanel pointContainer = new JPanel();
	
	private JLabel progNameDescription = new JLabel("Nom du programme : ", 10);
	private JTextField progName = new JTextField("",10);
	private JButton mur_nord = new JButton("Nord");
	private JButton mur_sud = new JButton("Sud");
	private JButton mur_est = new JButton("Est");
	private JButton mur_ouest = new JButton("Ouest");
	private JButton choixCible = new JButton("Choisir une cible");
	
	private JLabel erreurDeValidation = new JLabel("", 10);
	private JButton save = new JButton("Ajouter aux favoris");
	private static JButton next = new JButton("Simuler la trajectoire");
	private int mur = 0;
	private static int cibleX = 0;
	private static int cibleY = 0;
	
	public AddStep1() {
		this.setLayout(new BorderLayout());
		formContainer1.add(formContainer);
		
		formContainer.setLayout(new BorderLayout());
		nextContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		nameContainer.setLayout(new FlowLayout());
		murContainer.setLayout(new FlowLayout());
		pointContainer.setLayout(new FlowLayout());
		
		this.add(formContainer1, BorderLayout.CENTER);
		this.add(nextContainer, BorderLayout.SOUTH);
		
		formContainer.add(nameContainer, BorderLayout.NORTH);
		formContainer.add(murContainer, BorderLayout.CENTER);
		formContainer.add(pointContainer, BorderLayout.SOUTH);
		formContainer.setPreferredSize(new Dimension(500,330));
		formContainer.setBorder( new EmptyBorder(200, 10, 10, 10));
		
		nameContainer.add(progNameDescription);
		nameContainer.add(progName);
		murContainer.add(new JLabel("Sélectionnez un mur"));
		murContainer.add(mur_nord);
		murContainer.add(mur_sud);
		murContainer.add(mur_est);
		murContainer.add(mur_ouest);
		pointContainer.add(choixCible);		
		
		nextContainer.add(erreurDeValidation);
		nextContainer.add(save);
		nextContainer.add(next);
		
		/*
		this.add(title, BorderLayout.NORTH);
		this.add(formContainer, BorderLayout.CENTER);
		formContainer.setLayout(new BorderLayout());
		formContainer.add(progNameDescription, BorderLayout.WEST);
		formContainer.add(progName, BorderLayout.CENTER);
		formContainer.add(next, BorderLayout.SOUTH); */

	//	this.add(voirterrain); Bouton pour voir le terrain - test uniquement
		mur_nord.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				mur = 10;
			} 
		});
		mur_sud.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				mur = 11;
			} 
		});
		mur_est.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				mur = 12;
			} 
		});
		mur_ouest.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				mur = 13;
			} 
		});
		
		next.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				nextPressed();
			} 
		});
		
		save.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				savePressed();
			} 
		});
		
		choixCible.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				Cible macible = new Cible();
			} 
		});
		
	}
	
	public void nextPressed() {
		MainWindow.fenetre.buildWindow(Terrain.terrain);
	//	MainWindow.fenetre.buildWindow(new FirstPersonCam(Terrain.terrain));
		Terrain.terrain.requestFocusInWindow();
	}
	
	public void savePressed() {
		if(progName.getText().length() > 2 && mur != 0 && cibleX != 0 && cibleY != 0) {
			Database database = new Database();
			database.connect();
			database.addProgram(progName.getText(), mur, cibleX, cibleY);
			database.logout();
			erreurDeValidation.setText("Programme sauvegardé");
		}
		else {
		//	System.out.println("Veuillez remplir tous les champs");
			erreurDeValidation.setText("Veuillez remplir tous les champs");
		}
	}

	public static int getCibleX() {
		return cibleX;
	}
	
	public static void setCibleX(int i) {
		cibleX = i;
	}

	public static int getCibleY() {
		return cibleY;
	}
	
	public static void setCibleY(int i) {
		cibleY = i;
	}
	public static void setFocusToSimulate() {
		next.requestFocusInWindow();
	}
}
