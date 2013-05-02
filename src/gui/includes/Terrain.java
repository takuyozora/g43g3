package gui.includes;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.*;
 

@SuppressWarnings("serial")
public class Terrain extends GLJPanel implements GLEventListener {
 
   private GLU glu;
   
   // Couleurs R,G,B du terrain
   private byte r = (byte)255;
   private byte g = (byte)255;
   private byte b = (byte)255;
   private byte alpha = (byte)155; // Transparence du terrain
   
   private float angleCube = 0;
   private float speedCube = -1.5f;
   
   public Terrain() {
      this.addGLEventListener(this);
   }
 
   
   /**
    * Initialisation OpenGL
    */
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      glu = new GLU();
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
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
      glu.gluPerspective(45.0, aspect, 0.1, 100.0);

      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity();
   }
 
   /**
    * Méthode appelée par l'animateur pour effectuer le rendu 3D.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glClearColor(0.95f, 0.95f, 0.95f, 1.0f);
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
      gl.glLoadIdentity();
 
      gl.glTranslated(-3.2, -2.82, -30); // Placement au centre du terrain
    //  gl.glRotated(30,1,1,1);
      
      
    //  gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
      
      gl.glBegin(GL_QUADS); // Début de dessin du terrain
      gl.glColor4ub(r,g,b, alpha);
      
      gl.glVertex3d(0, 0, 0);
      gl.glVertex3d(0, 5.640, 0);
      gl.glVertex3d(6.400, 5.640, 0);
      gl.glVertex3d(6.400, 0, 0);
      
      gl.glVertex3d(0, 0, 0);
      gl.glVertex3d(0, 5.640, 0);
      gl.glVertex3d(0, 5.640, 9.750);
      gl.glVertex3d(0, 0, 9.750);
      
      gl.glVertex3d(6.400, 0, 0);
      gl.glVertex3d(6.400, 5.640, 0);
      gl.glVertex3d(6.400, 5.640, 9.750);
      gl.glVertex3d(6.400, 0, 9.750);

      gl.glVertex3d(0, 0, 9.750);
      gl.glVertex3d(0, 5.640, 9.750);
      gl.glVertex3d(6.400, 5.640, 9.750);
      gl.glVertex3d(6.400, 0, 9.750);

      gl.glVertex3d(0, 0, 0);
      gl.glVertex3d(0, 0, 9.750);
      gl.glVertex3d(6.400, 0, 9.750);
      gl.glVertex3d(6.400, 0, 0);

      gl.glVertex3d(0, 5.640, 0);
      gl.glVertex3d(0, 5.640, 9.750);
      gl.glVertex3d(6.400, 5.640, 9.750);
      gl.glVertex3d(6.400, 5.640, 0);

      gl.glEnd(); // Fin de dessin
      
      angleCube += speedCube; // Incrémentation angle de rotation du terrain (utile en test uniquement).
   }
 
   @Override
   public void dispose(GLAutoDrawable drawable) { }
}