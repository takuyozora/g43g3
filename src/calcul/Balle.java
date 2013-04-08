package calcul;

public class Balle extends PointMateriel {
	/*
	 * Classe représentant la balle
	 */
	
	private static double MASSE_OFFCIEL = 0.024;
	private static double GRANULARITE_TEMPS = 0.002;
	private static double COEFFICIENT_ABSORBTION = 0.45;
	
	public Balle(){
		this.masse = MASSE_OFFCIEL;
	}
	
	public Balle(Vecteur pinit, Vecteur vinit){
		this.vitesse = vinit;
		this.position = pinit;
		this.masse = MASSE_OFFCIEL;
	}
	
	public Vecteur lancer(){
		int impact = 0; 
		while(impact != Espace.IMPACT_SOL){
			if(impact != Espace.PAS_IMPACT){
				this.rebond(impact);
				System.out.println("Impacte : "+impact);
			}
			this.subirForce(Espace.GRAVITE, GRANULARITE_TEMPS);
			this.continuerMouvement(GRANULARITE_TEMPS);
			impact = Espace.impact(this.position);
		}
		return this.position;
	}
	
	public void rebond(int impact){
		/*
		 * Gère les rebonds
		 */
		if( impact == Espace.IMPACT_NORD ){
			this.vitesse.setY(-this.vitesse.y);
		}else if (impact == Espace.IMPACT_SUD){
			this.vitesse.setY(-this.vitesse.y);
		}else if( impact == Espace.IMPACT_EST){
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_OUEST){
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_PLAFOND){
			throw new UnsupportedOperationException("Pas encore prévus");
		}
		this.vitesse.pscalaire(COEFFICIENT_ABSORBTION);
		// ici on amorti la balle !
		/*
		this.vitesse.setX(this.vitesse.x * 0.8);
		this.vitesse.setY(this.vitesse.y * 0.8);//(1-COEFFICIENT_ABSORBTION);
		*/
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World !");
		
		calcul.Vecteur vinit = new calcul.Vecteur();
		// Temporaire, modifier avec les accecsseurs !
		vinit.phi = 2.6;
		vinit.thetas = 0.5;
		vinit.rayons = 42;
		vinit.tmp_updateAll(Vecteur.TYPE_SPHERIQUE); // À modifier !!
		calcul.Balle balle = new calcul.Balle(new Vecteur(3,1,1), vinit);
		

	    long begin = System.currentTimeMillis();
		Vecteur position = balle.lancer();
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin)) / 1000f;
		
		System.out.println("End normaly, erreur sur z :"+(position.z*100)+" temps d'execution :"+time);
	}
}
