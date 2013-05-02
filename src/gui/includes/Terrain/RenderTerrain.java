package gui.includes.Terrain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;


public class RenderTerrain {
	// Define constants for the top-level container
	   private static String TITLE = "OpenGL dans JFrame";  // window's title
	   private static final int CANVAS_WIDTH = 640;  // width of the drawable
	   private static final int CANVAS_HEIGHT = 480; // height of the drawable
	   private static final int FPS = 60; // animator's target frames per second
	   
	   
	   public RenderTerrain() {
		   // Create the OpenGL rendering canvas
           GLJPanel canvas = new Terrain();
           canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

           // Create a animator that drives canvas' display() at the specified FPS.
           final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

           // Create the top-level container
           final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
           frame.setLayout(new BorderLayout());
           frame.add(new JLabel("Ceci est un JLabel",10), BorderLayout.NORTH);
           frame.add(canvas, BorderLayout.CENTER);
           frame.setTitle(TITLE);
           frame.pack();
           frame.setVisible(true);
           animator.start(); // start the animation loop
        
	   }
	   
	/** The entry main() method to setup the top-level container and animator */   
	   public static void main(String[] args) {
	      // Run the GUI codes in the event-dispatching thread for thread safety
	      
	      RenderTerrain terrain = new RenderTerrain();  
	      
	   }
}