package gui.includes.Reglages;

import gui.Database;
import gui.MainWindow;
import gui.includes.MenuBar;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PositionLanceur extends JPanel implements MouseListener {
	
	private int terrainX = 320; // Dimensions du terrain affiché à l'utilisateur
	private int terrainY = 488;
	
	JLabel lblimage = new JLabel();
	
	public PositionLanceur() {
		setLayout(new BorderLayout());
		
		JLabel lblCliquezSurLendroit = new JLabel("Cliquez sur l'endroit o\u00F9 est plac\u00E9 le lanceur");
		add(lblCliquezSurLendroit, BorderLayout.NORTH);
		
		Image image;
		try {
			image = ImageIO.read(new File(MenuBar.resPath+"terrain_sol.png"));
			image = getScaledImage(image, terrainX, terrainY);
			Icon monImg = new ImageIcon(image);
			lblimage = new JLabel(monImg);
			this.add(lblimage, BorderLayout.CENTER);
			lblimage.addMouseListener(this);
			// this.add(lblimage);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int width = 2*arg0.getX() - (lblimage.getWidth() - terrainX);
		int height = 2*arg0.getY() - (lblimage.getHeight() - terrainY);
		// System.out.println(arg0.getX());
		// System.out.println(MainWindow.fenetre.getWidth());
		if(width < 0 || width > 640 || height < 0 || height > 975) {
			System.out.println("Vous n'êtes pas dans le terrain");
		}
		else {
			/* System.out.println(width);
			System.out.println(height);
			System.out.println(" "); */
			try {
				Database database = new Database();
				database.connect();
				database.setPositionLanceur(width, height);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
