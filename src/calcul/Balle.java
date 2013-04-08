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
		int impact = 0; 
		while(impact != Espace.IMPACT_SOL){
			if(impact != Espace.PAS_IMPACT){
				this.rebond(impact);
			}
			this.subirForce(Espace.GRAVITE, GRANULARITE_TEMPS);
			impact = Espace.impact(this.position);
		}
		return this.position;
	}
	
	public void rebond(int impact){
		/*
		 * Gère les rebonds
		 */
		if( impact == Espace.IMPACT_NORD || impact == Espace.IMPACT_SUD){
			this.vitesse.setY(-this.vitesse.y);
		}else if( impact == Espace.IMPACT_EST || impact == Espace.IMPACT_OUEST){
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_PLAFOND){
			throw new UnsupportedOperationException("Pas encore prévus");
		}
	}
}
