package gui.includes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.*;
 

@SuppressWarnings("serial")
public class Terrain extends GLJPanel implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
 
   private GLU glu;
   
   // Couleurs R,G,B du terrain
   private byte r = (byte)240;
   private byte g = (byte)240;
   private byte b = (byte)240;
   private byte alpha = (byte)100; // Transparence du terrain
   
   // Paramètres de rotation de la camera
   private double rotation_x; // Angle de 10 par défaut
   private double rotation_y;
   private double previous_x;
   private double previous_y;
   private double r_factor = 1;
   private int mouseInitPosX = 0;
   private int mouseInitPosY = 0;
   
   // Mouvement de la balle
   private double ballMotionX = 1;
   private double ballMotionY = 1;
   private double ballMotionZ = 1;
   int posInTableau = 0;
   
   // Paramètres pour tourner le terrain en mode automatique (Test uniquement)
   private float angleCube = 0;
   private float speedCube = -1.5f;
   
   public static Terrain terrain = new Terrain();
   public static FPSAnimator animator = new FPSAnimator(terrain, 60, true);
   
   private int pos_x = 0;
   private int pos_y = 0;
   
   public Terrain() {
	   this.addGLEventListener(this);
	   this.addKeyListener(this);
	   this.addMouseListener(this);
	   this.addMouseMotionListener(this);
	   this.setFocusable(true);
   }
 
   
   /**
    * Initialisation OpenGL
    */
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      glu = new GLU();
      gl.glClearDepth(1.0f);
      gl.glEnable(GL_DEPTH_TEST);
      gl.glEnable(GL_BLEND); // Active le mode BLEND
      gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA) ; // Utilisation du mode BLEND en transparence
      gl.glDepthFunc(GL_LEQUAL);
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
      gl.glShadeModel(GL_SMOOTH);
   }
 

   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();
 
      if (height == 0) height = 1;
      float aspect = (float)width / height;
 
      gl.glViewport(0, 0, width, height);
 
      gl.glMatrixMode(GL_PROJECTION);
      gl.glLoadIdentity();
      glu.gluPerspective(30.0, aspect, 0.1, 100.0);

      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity();
   }
 
   /**
    * Méthode appelée par l'animateur pour effectuer le rendu 3D.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glClearColor(0.929f, 0.929f, 0.929f, 1.0f);
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
      gl.glLoadIdentity();
 
      gl.glTranslated(0, -2.82, -12); // Placement au centre du terrain
      gl.glRotated(rotation_x,1,0,0);
      gl.glRotated(rotation_y,0,1,0);
      
      gl.glTranslated(-3.2, 0, -5.2);  // -7,875
      
      // Rotation de la caméra
   //   gl.glRotated(rotation_x,1,0,0);
   //   gl.glRotated(rotation_y,0,1,0);
      
      
   //   gl.glTranslated(-3.2, -2.82, -20);
   //   gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
      
      angleCube += speedCube; // Incrémentation angle de rotation du terrain (utile en test uniquement).
      
      // Contours du terrain
      gl.glLineWidth(1);
      gl.glBegin(GL_LINES);
      gl.glColor4ub((byte)50,(byte)50,(byte)50, (byte)255);
      
      gl.glVertex3d(0, 0, 0);
      gl.glVertex3d(0, 5.640, 0);
      
      gl.glVertex3d(0, 0, 9.750);
      gl.glVertex3d(0, 5.640, 9.750);
      
      gl.glVertex3d(6.400, 0, 0);
      gl.glVertex3d(6.400, 5.640, 0);
      
      gl.glVertex3d(6.400, 0, 9.750);
      gl.glVertex3d(6.400, 5.640, 9.750);
      
      gl.glVertex3d(0, 0, 0);
      gl.glVertex3d(0, 0, 9.750);
      
      gl.glVertex3d(0, 5.640, 0);
      gl.glVertex3d(0, 5.640, 9.750);
      
      gl.glVertex3d(6.400, 0, 0);
      gl.glVertex3d(6.400, 0, 9.750);
      
      gl.glVertex3d(6.400, 5.640, 0);
      gl.glVertex3d(6.400, 5.640, 9.750);
      
      gl.glVertex3d(0, 5.640, 9.750);
      gl.glVertex3d(6.400, 5.640, 9.750);
      
      gl.glVertex3d(0, 0, 9.750);
      gl.glVertex3d(6.400, 0, 9.750);
      
      gl.glVertex3d(0, 5.640, 0);
      gl.glVertex3d(6.400, 5.640, 0);
      
      gl.glVertex3d(0, 0, 0);
      gl.glVertex3d(6.400, 0, 0);
      
      gl.glVertex3d(0, 4.570, 0);
      gl.glVertex3d(6.400, 4.570, 0);
      
      gl.glVertex3d(0, 4.570, 0);
      gl.glVertex3d(0, 2.130, 9.750);
      
      gl.glVertex3d(6.400, 4.570, 0);
      gl.glVertex3d(6.400, 2.130, 9.750);
      
      gl.glVertex3d(0, 0, 5.490);
      gl.glVertex3d(6.400, 0, 5.490);
      
      gl.glVertex3d(0, 0, 7.140);
      gl.glVertex3d(1.600, 0, 7.140);
      
      gl.glVertex3d(4.800, 0, 7.140);
      gl.glVertex3d(6.400, 0, 7.140);
      
      gl.glVertex3d(1.600, 0, 5.490);
      gl.glVertex3d(1.600, 0, 7.140);
      
      gl.glVertex3d(4.800, 0, 5.490);
      gl.glVertex3d(4.800, 0, 7.140);
      
      gl.glVertex3d(3.200, 0, 5.490);
      gl.glVertex3d(3.200, 0, 9.750);
      
      gl.glVertex3d(0, 1.780, 0);
      gl.glVertex3d(6.400, 1.780, 0);
      
      gl.glVertex3d(0, 0.430, 0);
      gl.glVertex3d(6.400, 0.430, 0);
      
      gl.glEnd();
      
      ballMotionX = tabX()[posInTableau];
      if(posInTableau < tabX().length-1) {
    	  posInTableau++;
      }
      else {
    	  ballMotionX = tabX()[tabX().length-1];
      }
      
      gl.glTranslated(ballMotionX, ballMotionY, ballMotionZ);
      
   //   ballMotionX += 0.01;
   //   ballMotionY += 0.01;
      ballMotionZ += 0.02;
      
      
      // Balle de Squash
      GLUquadric sphere2 = glu.gluNewQuadric();
      glu.gluSphere(sphere2, 0.04, 20, 20);
      glu.gluDeleteQuadric(sphere2);
   }
 
   @Override
   public void dispose(GLAutoDrawable drawable) { }
   
   public void keyTyped(KeyEvent key)
   {
	   // Rien à faire (pour le moment).
   }
   
   // Gestion des évènements liés au clavier
   public void keyPressed(KeyEvent key)
   {
     if(key.getKeyCode() == KeyEvent.VK_UP) {
    	 rotation_x=rotation_x+r_factor;
    	// pos_y = pos_y + 1;
     }
     if(key.getKeyCode() == KeyEvent.VK_DOWN) {
    	 rotation_x=rotation_x-r_factor;
    	// pos_y = pos_y - 1;
     }
     if(key.getKeyCode() == KeyEvent.VK_LEFT) {
    	 rotation_y=rotation_y+r_factor;
    	// pos_x = pos_x - 1;
    	 
     }
     if(key.getKeyCode() == KeyEvent.VK_RIGHT) {
    	 rotation_y=rotation_y-r_factor;
    	// pos_x = pos_x + 1;
     }
     if(key.getKeyCode() == KeyEvent.VK_ESCAPE) {
         System.exit(0);
       }
   }

   public void keyReleased(KeyEvent key)
   {
	 // Rien à faire (pour le moment) 
   }
   
   
  public double[] tabX() {
	  double[] tab = new double[200];
	  
	  for(int i =0; i<tab.length; i++) {
		  if(i == 0) {
			  tab[i] = 1;
		  }
		  else {
			  tab[i] = tab[i-1]+0.01;
		  }
		  
	  }

	  
	  return tab;
  }
  
  public double[] tabY() {
	  double[] tab = new double[5];
	  
	  tab[0] = 1;
	  tab[1] = 1.1;
	  tab[2] = 1.2;
	  tab[3] = 1.3;
	  tab[4] = 1.4;
	  
	  return tab;
  }
  
  public double[] tabZ() {
	  double[] tab = new double[5];
	  
	  tab[0] = 1;
	  tab[1] = 1.1;
	  tab[2] = 1.2;
	  tab[3] = 1.3;
	  tab[4] = 1.4;
	  
	  return tab;
  }
   
   
  /* 
   public float[][][] tableauPositions() {
	   float[][][] tab = new float[20][20][20];
	   float x =  5;
	   
	   tab[0][0][0] = 5;
	   
	   for(int i=0; i<19; i++) {
		   for(int j=0; j<19; j++) {
			   for(int k=0; k<19; k++) {
				   tab[i][i][i] = x;
				   tab[i][i][i] = x;
				   x=x-0.3f;
			   }
		   }
		   tab[i][i][i] = x;
		   x=x-0.3f;
	   } 
	   
	   return tab;
   } 
   */


	@Override
	public void mouseDragged(MouseEvent e) {
		
		rotation_y = previous_y + e.getX() - mouseInitPosX;
		rotation_x = previous_x + e.getY() - mouseInitPosY;
	
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	//	System.out.println(rotation_x);
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}
	
	
	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		mouseInitPosX = e.getX();
		mouseInitPosY = e.getY();
		// System.out.println("Position initiale : "+mouseInitPosX);
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		previous_y = previous_y + e.getX() - mouseInitPosX;
		previous_x = previous_x + e.getY() - mouseInitPosY;
	}
   
}