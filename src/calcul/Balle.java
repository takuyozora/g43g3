package calcul;

public class Balle extends PointMateriel {
	/*
	 * Classe représentant la balle
	 */
	
	private static double MASSE_OFFCIEL = 0.024;
	private static double GRANULARITE_TEMPS = 0.001;
	
	public Balle(){
		this.masse = MASSE_OFFCIEL;
	}
	
	public Balle(Vecteur vinit, Vecteur pinit){
		this.vitesse = vinit;
		this.position = pinit;
		this.masse = MASSE_OFFCIEL;
	}
	
	public Balle(Vecteur vinit){
		this.vitesse = vinit;
		this.masse = MASSE_OFFCIEL;
	}
	
	public Vecteur lancer(){
		Vecteur pimpacte = new Vecteur(0,0,0);
		int impacte = 0; 
		while(impacte != Espace.IMPACTE_SOL){
			this.subirForce(Espace.GRAVITE, GRANULARITE_TEMPS);
			impacte = Espace.impacte(this.position);
		}
		return pimpacte;
	}
}
