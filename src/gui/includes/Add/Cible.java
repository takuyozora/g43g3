package gui.includes.Add;

import gui.Database;
import gui.includes.MenuBar;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cible extends JFrame implements MouseListener, MouseMotionListener {
	
	private int terrainX = 320; // Dimensions du terrain affiché à l'utilisateur
	private int terrainY = 488;
	
	private int width;
	private int height;
	
	private JLabel liveX = new JLabel("x : 0");
	private JLabel liveY = new JLabel("y : 0");
	
	private JLabel labelX = new JLabel("x : 0");
	private JLabel labelY = new JLabel("y : 0");
	private JLabel description = new JLabel("Cliquez sur l'endroit o\u00F9 vous voulez voir la balle retomber");
	
	private JPanel topPanel = new JPanel();
	private JPanel positionPanel = new JPanel();
	private JPanel positionLivePanel = new JPanel();
	private JPanel bottomContainer = new JPanel();
	
	private JPanel container = new JPanel();
	private JButton nextBtn = new JButton("Suivant");
	
	JLabel lblimage = new JLabel();
	Database database = new Database();
	
	public Cible() {
		
		super("Sélectionner une cible");
		this.setSize(700,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(700, 600));

		labelX.setText("x : "+AddStep1.getCibleX());
		labelY.setText("y : "+AddStep1.getCibleY());
		
		this.add(container);
		
		container.setLayout(new BorderLayout());
		topPanel.setLayout(new BorderLayout());
		positionPanel.setLayout(new BorderLayout());
		positionLivePanel.setLayout(new BorderLayout());
		bottomContainer.setLayout(new BorderLayout());
		
		
		topPanel.add(description, BorderLayout.WEST);
		topPanel.add(positionPanel, BorderLayout.EAST);
		container.setBorder( new EmptyBorder(10, 10, 10, 10));
		
		positionPanel.add(labelX, BorderLayout.NORTH);
		positionPanel.add(labelY, BorderLayout.SOUTH);
		
		
		
		positionLivePanel.add(liveX, BorderLayout.NORTH);
		positionLivePanel.add(liveY, BorderLayout.SOUTH);
		
		
		container.add(topPanel, BorderLayout.NORTH);
		container.add(bottomContainer, BorderLayout.SOUTH);
		bottomContainer.add(positionLivePanel, BorderLayout.WEST);
		bottomContainer.add(nextBtn, BorderLayout.EAST);
		
		nextBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				dispose();
				AddStep1.setFocusToSimulate();
			} 
		});
	//	add(labelX, BorderLayout.WEST);
	//	add(labelY, BorderLayout.EAST);
		
		Image image;
		try {
			image = ImageIO.read(new File(MenuBar.resPath+"/textures/sol.png"));
			image = getScaledImage(image, terrainX, terrainY);
			Icon monImg = new ImageIcon(image);
			lblimage = new JLabel(monImg);
			container.add(lblimage, BorderLayout.CENTER);
			lblimage.addMouseListener(this);
			lblimage.addMouseMotionListener(this);
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
		width = 2*arg0.getX() - (lblimage.getWidth() - terrainX);
		height = 2*arg0.getY() - (lblimage.getHeight() - terrainY);
		// System.out.println(arg0.getX());
		// System.out.println(MainWindow.fenetre.getWidth());
		if(width < 0 || width > 640 || height < 0 || height > 975) {
			System.out.println("Vous n'êtes pas dans le terrain");
		}
		else {
			
			
			labelX.setText("x : "+width);
			labelY.setText("y : "+height);
			AddStep1.setCibleX(width);
			AddStep1.setCibleY(height);
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		width = 2*arg0.getX() - (lblimage.getWidth() - terrainX);
		height = 2*arg0.getY() - (lblimage.getHeight() - terrainY);
		
		if(width < 0 || width > 640 || height < 0 || height > 975) {

		}
		else {
			liveX.setText("x : "+width);
			liveY.setText("y : "+height);
		}
	}

}
