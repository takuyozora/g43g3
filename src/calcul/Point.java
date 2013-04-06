package calcul;

public class Point {
	
	public double x ;
	public double y ;
	public double z ;
	
	public Point (){
		this.x = 0 ;
		this.y = 0 ;
		this.z = 0 ;
	}
	
	public Point (double x , double y , double z){
		this.x = x ;
		this.y = y ;
		this.z = z ;
	}
	
	public Vecteur getVecteur(){
		return new Vecteur(this.x,this.y,this.z);
	}

}
