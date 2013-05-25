package gui.includes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Content extends JPanel {
	
	// Attributs
	private JPanel panbvn = new JPanel();
	private JPanel pan2 = new JPanel(); 
	
	private JPanel pannew = new JPanel(); 
	private JPanel panfav = new JPanel(); 
	private JPanel panpar = new JPanel(); 
	
	private JLabel textbvn = new JLabel("Bienvenue dans l'application SquashPro !");
	private JLabel textnew = new JLabel( "Créer une nouvelle simulation");
	private JLabel textfav = new JLabel("Accéder aux simulations existantes");
	private JLabel textpar = new JLabel("Régler la position du lanceur");
	private JLabel textnewgras = new JLabel("Nouveau :");
	private JLabel textfavgras = new JLabel("Favoris :");
	private JLabel textpargras = new JLabel("Paramètres :");
	
	
	private Image imgnew;
	private Image imgfav;
	private Image imgpar;
	Icon iconnew;
	Icon iconfav;
	Icon iconpar;
	
	
	
	
	public Content() {
		try {
			imgnew =  ImageIO.read(new File("./src/gui/res/add.png"));
			iconnew = new ImageIcon(imgnew);
			imgfav =  ImageIO.read(new File("./src/gui/res/favs.png"));
			iconfav= new ImageIcon(imgfav);
			imgpar =  ImageIO.read(new File("./src/gui/res/settings.png"));
			iconpar = new ImageIcon(imgpar);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setLayout(new BorderLayout());
		this.add(panbvn,BorderLayout.NORTH);
		this.add(pan2,BorderLayout.CENTER);
		
		JLabel labelnew = new JLabel(iconnew);
		JLabel labelfav = new JLabel(iconfav);
		JLabel labelpar = new JLabel(iconpar);
		
		textnewgras.setFont(textnewgras.getFont().deriveFont(Font.BOLD));
		textfavgras.setFont(textfavgras.getFont().deriveFont(Font.BOLD));
		textpargras.setFont(textpargras.getFont().deriveFont(Font.BOLD));
		textbvn.setFont(textbvn.getFont().deriveFont(Font.ITALIC));
		
		panbvn.setLayout(new FlowLayout());
		panbvn.add(textbvn);
		panbvn.setPreferredSize(new Dimension(500,100));
		
		pannew.setLayout(new FlowLayout());
		pannew.add(labelnew);
		pannew.add(textnewgras);
		pannew.add(textnew);
		pannew.setPreferredSize(new Dimension(500,100));
		
		panfav.setLayout(new FlowLayout());
		panfav.add(labelfav);
		panfav.add(textfavgras);
		panfav.add(textfav);
		panfav.setPreferredSize(new Dimension(500,100));
		
		panpar.setLayout(new FlowLayout());
		panpar.add(labelpar);
		panpar.add(textpargras);
		panpar.add(textpar);
		panpar.setPreferredSize(new Dimension(500,270));
		
		pan2.setLayout(new BorderLayout());
		pan2.add(pannew,BorderLayout.NORTH);
		pan2.add(panfav,BorderLayout.CENTER);
		pan2.add(panpar,BorderLayout.SOUTH);
		
	}
	
}               

	

