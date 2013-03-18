package calcul;

public class Vecteur {
	
	public double vectx ;
	public double vecty ;
	public double vectz ;
	public double norme ;
	
	public Vecteur(){
		vectx = 0 ;
		vecty = 0 ;
		vectz = 0 ;
	}
	
	public Vecteur(double x , double y , double z){
		vectx = x ;
		vecty = y ;
		vectz = z ;
	}
	
	public double Norme(double x , double y , double z){
		norme = Math.sqrt(x*x+y*y+z*z) ;
		return norme ;
	}
}
