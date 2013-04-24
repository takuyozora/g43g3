package calcul;

public class LancerUn {
	/* 
	 * LancerUn est la classe qui permet de trouver les paramètres de tir (angle et vitesse)
	 * avec comme paramètres d'entrée un mur pour le rebond, la cible au sol et la position
	 * du lanceur 
	 */
	
	static final int MUR_NORD = 10 ;
	static final int MUR_SUD = 11 ; 
	static final int MUR_EST = 12 ;
	static final int MUR_OUEST = 13 ;
	
	public Vecteur posinit = new Vecteur();
	public int mur;
	public Vecteur cible = new Vecteur();
	
	public LancerUn(Vecteur posinit, int mur, Vecteur cible){
		this.posinit = posinit;
		this.mur= mur;
		this.cible = cible;
		
	}
	
	private double calculPhiTir(Vecteur posinitiale, int mur, Vecteur target ){
		
		double result;
		
		if (mur == MUR_OUEST) {
			
			result = (posinitiale.y + target.y)/2 ;
			result = (Math.PI) - Math.atan((result-posinitiale.y)/posinitiale.x);
			
		}
		else if (mur == MUR_EST) {
			
			result = (posinitiale.y + target.y)/2 ;
			result = Math.atan((result-posinitiale.y)/Espace.LARGEUR - posinitiale.x);
			
		}	
		else if (mur == MUR_SUD){
			
			result = (posinitiale.x + target.x)/2 ;
			result = (3/2)*(Math.PI) + Math.atan((result-posinit.x)/posinit.y);
			
		}
		else if (mur == MUR_NORD) {
			result = (posinitiale.x + target.x)/2 ;
			result = (1/2)*(Math.PI) - Math.atan((result-posinitiale.x)/Espace.PROFONDEUR - posinitiale.x);
		}
		
		else{
			throw new IllegalArgumentException("On ne définit pas correctement le mur voulu");
		}
		return result ;
	}
	
	public Vecteur calculTir(Vecteur posinitiale, Vecteur target, int mur){
		
		double lignetir = this.calculPhiTir(posinitiale, mur, target);
		
		Vecteur posfinale = new Vecteur();
		
		Vecteur vitesseinit = new Vecteur();
		vitesseinit.setThetas(1.53);//(Math.PI)*(5/12)
		vitesseinit.setRayons(20);
		vitesseinit.setPhi(lignetir);
		
		int count = 0;		
			
		while (Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme() >= 1){
			if (count == 10){
				throw new RuntimeException("Trop d'itérations");
			}
			
			Balle balletir = new Balle(new Vecteur (posinitiale.x, posinitiale.y, posinitiale.z) , new Vecteur (vitesseinit.x, vitesseinit.y, vitesseinit.z));
			
			System.out.println("Vitesse : "+(vitesseinit.rayons*3.6)+"km/h \n");
			System.out.println("Angle phi en degrés :" + (vitesseinit.phi*57.3) + "\n");
//			posfinale = balletir.lancer();
			try{
				posfinale = balletir.lancer(1);
			}
			catch(UnsupportedOperationException e){
				System.out.println("Plafond -> baisser theta !!");
				vitesseinit.setThetas(vitesseinit.thetas - 0.1);
				continue;
			}
			catch(RuntimeException e){
				System.out.println("EXCEPT !!");
				vitesseinit.setRayons(vitesseinit.rayons * 1.25);
				continue;
			}
			
			if (mur == MUR_OUEST){	
				
				if (posfinale.x > target.x){
					
					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
					
				}
				
				if (posfinale.x < target.x){
					
					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
				
				}
			}
			
			if (mur == MUR_EST){
				
				if (posfinale.x < target.x){
					
					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
						
				}
					
				if (posfinale.x > target.x){
						
					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
					
				}
			}
				
			if (mur == MUR_NORD){

				if (posfinale.y < target.y){
					
					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
					
				}
				
				if (posfinale.y > target.y){
					
					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
				
				}
			
			}
			
			if (mur == MUR_SUD){

				if (posfinale.y < target.x){
					
					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
					
				}
				
				if (posfinale.x > target.x){
					
					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
				
				}
			}
			
			count += 1;
			
			System.out.println ("Itération n°: "+count +"\n");
			System.out.println ("Erreur : "+Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme() + "\n ---------- \n" );
			
		}
		
		return vitesseinit;
	}
	
	public static void main(String[] args){
		Vecteur posi = new Vecteur(2, 2 , 1);
		int murun = 13;
		Vecteur posf = new Vecteur(4, 6 , 0);
		LancerUn lancer = new LancerUn(posi, murun, posf);
		Vecteur vinit = lancer.calculTir(posi, posf, murun);
		System.out.println(vinit.phi + vinit.thetas + vinit.rayons);
	}
	
}
