package gui.includes;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Content extends JPanel {
	
	
	
	public void paintComponent(Graphics g){                        
	    // j'ai cr�er les textes, ce qui est police etc .. jusqu'� la fin
		g.drawString("Bienvenu dans l'application SquashPro ", 230, 30);  
		g.drawString("New : cr�er une nouvelle simulation ", 150, 100);  
	    g.drawString("Favoris : Utiliser les donn�es d�j� enregistr�es ", 150, 220);
	    g.drawString("Settings : aller dans les param�tres", 150, 340);
	    this.setBackground(Color.white);
	    // j'ai cr�er les images 
	    try {
	        Image img = ImageIO.read(new File("./src/gui/res/add.png"));
	        g.drawImage(img, 110, 80, this);
	     
	        Image img2 = ImageIO.read(new File("./src/gui/res/favs.png"));
	        g.drawImage(img2, 110, 200, this);
	        
	        Image img3 = ImageIO.read(new File("./src/gui/res/settings.png"));
	        g.drawImage(img3, 110, 320, this);
	      } catch (IOException e) {
	        e.printStackTrace();
	      }                
	      }                
	  }               

	

