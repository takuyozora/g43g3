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
	
	public Vecteur lancer(){
		Vecteur pimpact = new Vecteur(0,0,0);
		int impact = 0; 
		while(impact != Espace.IMPACT_SOL){
			if(impact != Espace.PAS_IMPACT){
				this.rebond(impact);
			}
			this.subirForce(Espace.GRAVITE, GRANULARITE_TEMPS);
			impact = Espace.impact(this.position);
		}
		return pimpact;
	}
	
	public void rebond(int impact){
		/*
		 * Gère les rebonds
		 */
	}
}
