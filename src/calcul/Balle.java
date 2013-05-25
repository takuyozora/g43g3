package calcul;

public class Balle extends PointMateriel {
	/*
	 * Classe représentant la balle
	 */
	
	class TropDeRebondError extends Exception {
		public TropDeRebondError(String message)
		  {
		    super(message);
		  }
	}
	
	class RebondPlafondError extends Exception {
		public RebondPlafondError(String message)
		  {
		    super(message);
		  }
	}

	class PasAssezDeRebondError extends Exception {
		public PasAssezDeRebondError(String message)
		  {
		    super(message);
		  }
	}
	
	private static double MASSE_OFFICIELLE = 0.024;
	private static double GRANULARITE_ESPACE = 0.03;
	private static double COEFFICIENT_ABSORBTION = 0.45;
	private static double COEFFICIENT_FROTTEMENT = 0.035;
	
	public int nbrebond;
	
	public Balle(){
		this.masse = MASSE_OFFICIELLE;
	}
	
	public Balle(Vecteur pinit, Vecteur vinit){
		this.vitesse = new Vecteur(vinit);
		this.position = new Vecteur(pinit);
		this.masse = MASSE_OFFICIELLE;
	}
	
	public double getDeltaT(){
		double deltaT = GRANULARITE_ESPACE / this.vitesse.norme();
		return deltaT;
	}
	
	public Vecteur lancer(int nbre) throws TropDeRebondError, PasAssezDeRebondError, RebondPlafondError{
		
		this.nbrebond = 0;

		int impact = Espace.PAS_IMPACT; 
		while(impact != Espace.IMPACT_SOL){
//			System.out.println(time+","+this.position.x+","+this.position.y+","+this.position.z);
//			System.out.println(this.position.x+","+this.position.y);
			if(impact != Espace.PAS_IMPACT){
				
				this.nbrebond += 1;
				
				if(nbrebond > nbre){
					throw new TropDeRebondError("Trop");
				}
								
					this.rebond(impact);
			}
			
			this.subirForce(Vecteur.pscalaire(Espace.GRAVITE, this.masse), this.getDeltaT());
			this.subirForce(Vecteur.pscalaire(this.vitesse,-COEFFICIENT_FROTTEMENT), this.getDeltaT());
			this.continuerMouvement(this.getDeltaT());
			impact = Espace.impact(this.position);
			
		}
//		if (this.nbrebond < nbre){
//			
//			throw new RuntimeException("Pas assez de rebond");
//		}
//		
//		System.out.println("X: " + this.position.x + " , Y: " + this.position.y + " , Z: " + this.position.z + "\n");
		if(nbrebond != nbre){
			if(nbrebond > nbre){
				throw new TropDeRebondError("Trop");
			}else{
				throw new PasAssezDeRebondError("Pas Assez");
			}
		}
//		System.out.println("Nombre de rebond : " + nbrebond);
		return this.position;
	}
	
	public double[][] getTrajectoire() throws RebondPlafondError{
		double[][] tmp = new double[1000][3];
		int i = 0;
		int impact = Espace.PAS_IMPACT; 
		while(impact != Espace.IMPACT_SOL){
			tmp[i][0] = this.position.x;
			tmp[i][1] = this.position.y;
			tmp[i][2] = this.position.z;
			i++;
			if(impact != Espace.PAS_IMPACT){
				this.rebond(impact);
			}
			this.subirForce(Vecteur.pscalaire(Espace.GRAVITE, this.masse), this.getDeltaT());
			this.subirForce(Vecteur.pscalaire(this.vitesse,-COEFFICIENT_FROTTEMENT), this.getDeltaT());
			this.continuerMouvement(this.getDeltaT());
			impact = Espace.impact(this.position);
		}
		double[][] trajectoire = new double[i][3];
		for(int j=0; j<i; j++){
			trajectoire[j] = tmp[j];
		}
		return trajectoire;
	}
	
	public void rebond(int impact) throws RebondPlafondError{
		/*
		 * Gère les rebonds
		 */
		if( impact == Espace.IMPACT_NORD ){
//			System.out.println("A touché le mur NORD \n");
			this.vitesse.setY(-this.vitesse.y);
		}else if (impact == Espace.IMPACT_SUD){
//			System.out.println("A touché le mur SUD \n");
			this.vitesse.setY(-this.vitesse.y);
		}else if( impact == Espace.IMPACT_EST){
//			System.out.println("A touché le mur EST \n");
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_OUEST){
//			System.out.println("A touché le mur OUEST \n");
			this.vitesse.setX(-this.vitesse.x);
		}else if( impact == Espace.IMPACT_PLAFOND){
			throw new RebondPlafondError("Pas encore prévus");
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
