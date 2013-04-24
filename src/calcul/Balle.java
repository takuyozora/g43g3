package calcul;

public class Balle extends PointMateriel {
	/*
	 * Classe représentant la balle
	 */
	
	private static double MASSE_OFFICIELLE = 0.024;
	private static double GRANULARITE_TEMPS = 0.1;
	private static double COEFFICIENT_ABSORBTION = 0.45;
	private static double COEFFICIENT_FROTTEMENT = 0.15;
	
	public int nbrebond = 0;
	
	private boolean hitAWall = false;
	
	public Balle(){
		this.masse = MASSE_OFFICIELLE;
	}
	
	public Balle(Vecteur pinit, Vecteur vinit){
		this.vitesse = vinit;
		this.position = pinit;
		this.masse = MASSE_OFFICIELLE;
	}
	
	public Vecteur lancer(){
		double time = 0;
		int impact = Espace.PAS_IMPACT; 
		while(impact != Espace.IMPACT_SOL){
//			System.out.println(time+","+this.position.x+","+this.position.y+","+this.position.z);
//			System.out.println(this.position.x+","+this.position.y);
			if(impact != Espace.PAS_IMPACT){
				
				this.rebond(impact);
				this.nbrebond += 1;				
			}
			
			this.subirForce(Vecteur.pscalaire(Espace.GRAVITE, this.masse), GRANULARITE_TEMPS);
			//this.subirForce(Vecteur.pscalaire(this.vitesse,-COEFFICIENT_FROTTEMENT*this.vitesse.rayons), GRANULARITE_TEMPS);
			this.continuerMouvement(GRANULARITE_TEMPS);
			impact = Espace.impact(this.position);
			
		}
//		if (this.hitAWall == false){
//			throw new RuntimeException("Pas Assez de puissance");
//		}

		System.out.println("X: " + this.position.x + " , Y: " + this.position.y + " , Z: " + this.position.z + "\n");
		
		return this.position;
	}
	
	public void rebond(int impact){
		/*
		 * Gère les rebonds
		 */
		this.hitAWall = true;
		if( impact == Espace.IMPACT_NORD ){
			System.out.println("A touché le mur NORD \n");
			this.vitesse.setY(-this.vitesse.y);
		}else if (impact == Espace.IMPACT_SUD){
			System.out.println("A touché le mur SUD \n");
			this.vitesse.setY(-this.vitesse.y);
		}else if( impact == Espace.IMPACT_EST){
			System.out.println("A touché le mur EST \n");
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_OUEST){
			System.out.println("A touché le mur OUEST \n");
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_PLAFOND){
			throw new UnsupportedOperationException("Pas encore prévus");
		}
		this.vitesse.pscalaire(1-COEFFICIENT_ABSORBTION); // Ici on ammorti la balle
	}
	
//	public static void main(String[] args) {
//		System.out.println("Hello World !");
//		
//		calcul.Vecteur vinit = new calcul.Vecteur();
//		vinit.setPhi(2.1);//(2/3)*Math.PI
//		vinit.setThetas(1.31);// (5/12)*Math.PI
//		vinit.setRayons(45);
//		calcul.Balle balle = new calcul.Balle(new Vecteur(3,1,1), vinit);
//		
//
//	    long begin = System.currentTimeMillis();
//		Vecteur position = balle.lancer();
//		long end = System.currentTimeMillis();
//		float time = ((float) (end-begin));
//		
//		System.out.println("End normaly, erreur sur z :"+(position.z*100)+" temps d'execution :"+time);
//	}
}
