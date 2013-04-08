package calcul;

public class PointMateriel{
	
	public double masse = 0 ;
	
	public Vecteur vitesse = new Vecteur(0,0,0);
	public Vecteur position = new Vecteur(0,0,0);

	public PointMateriel(){
		this.masse = 0;
	}
	
	public PointMateriel(double masse){
		this.masse = masse;
	}

	public PointMateriel(double masse, double x , double y , double z){
		this.masse = masse;
		this.position = new Vecteur(x,y,z);
	}
	
	public void subirForce(Vecteur force, double t){
		/*
		 * Cette méthode utilise le PFD pour modifier le vecteur vitesse du point en fonction de la 
		 * froce subit et du temps donné
		 */
		Vecteur acceleration = Vecteur.pscalaire(force, (1/this.masse));
		this.vitesse.operation(Vecteur.pscalaire(acceleration,t), Vecteur.OPE_PLUS);
	}
}
