package calcul;

public class Balle extends PointMateriel {
	/*
	 * Classe représentant la balle
	 */
	
	private static double MASSE_OFFICIEL = 0.024;
	//private static double GRANULARITE_TEMPS = 0.005;
	private static double GRANULARITE_TEMPS = 0.05;
	private static double COEFFICIENT_ABSORBTION = 0.45;
	private static double COEFFICIENT_FROTTEMENT = 0.45;
	
	public Balle(){
		this.masse = MASSE_OFFICIEL;
	}
	
	public Balle(Vecteur pinit, Vecteur vinit){
		this.vitesse = vinit;
		this.position = pinit;
		this.masse = MASSE_OFFICIEL;
	}
	
	public Vecteur lancer(){
		double time = 0;
		int impact = Espace.PAS_IMPACT; 
		while(impact != Espace.IMPACT_SOL){
			//System.out.println(time+","+this.position.x+","+this.position.y+","+this.position.z);
			System.out.println(this.position.x+","+this.position.y);
			if(impact != Espace.PAS_IMPACT){
				this.rebond(impact);
				//System.out.println("----"+impact);
			}
			this.subirForce(Espace.GRAVITE, GRANULARITE_TEMPS);
			this.subirForce(Vecteur.pscalaire(this.vitesse,-COEFFICIENT_FROTTEMENT*this.vitesse.rayons), GRANULARITE_TEMPS);
			this.continuerMouvement(GRANULARITE_TEMPS);
			impact = Espace.impact(this.position);
			//time += GRANULARITE_TEMPS;
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
		this.vitesse.pscalaire(1-COEFFICIENT_ABSORBTION); // Ici on ammorti la balle
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World !");
		
		calcul.Vecteur vinit = new calcul.Vecteur();
		vinit.setPhi(2.8);
		vinit.setThetas(1.3);
		vinit.setRayons(35);
		calcul.Balle balle = new calcul.Balle(new Vecteur(3,1,1), vinit);
		

	    long begin = System.currentTimeMillis();
		Vecteur position = balle.lancer();
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin));
		
		System.out.println("End normaly, erreur sur z :"+(position.z*100)+" temps d'execution :"+time);
	}
}
