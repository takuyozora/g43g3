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
			result = (3/2)*Math.PI + Math.atan(Math.abs(result-posinit.y)/posinit.x);
			
		}
		else if (mur == MUR_EST) {
			
			result = (posinitiale.y + target.y)/2 ;
			result = (1/2)*Math.PI - Math.atan(Math.abs(result-posinit.y)/Espace.LARGEUR - posinit.x);
			
		}	
		else if (mur == MUR_SUD){
			
			result = (posinitiale.x + target.x)/2 ;
			result = Math.PI + Math.atan(Math.abs(result-posinit.x)/posinit.y);
			
		}
		else if (mur == MUR_NORD) {
			result = (posinitiale.x + target.x)/2 ;
			result = 2*Math.PI - Math.atan(Math.abs(result-posinit.x)/Espace.PROFONDEUR - posinit.x);
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
		vitesseinit.setThetas(Math.PI/2);
		vitesseinit.setRayons(42);
		vitesseinit.setPhi(lignetir);
		
		int count = 0;		
			
		while (Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme() >= 1){
			
			Balle balletir = new Balle(posinitiale, vitesseinit);
				
			posfinale = balletir.lancer();	
				
			if (posfinale.x > target.x){
					
				vitesseinit.setRayons (vitesseinit.rayons / 1.2);
//				vitesseinit.setThetas (vitesseinit.thetas - Math.PI/12);
					
			}
				
			if (posfinale.x < target.x){
					
				vitesseinit.setRayons(vitesseinit.rayons * 1.2);
//				vitesseinit.setThetas(vitesseinit.thetas + Math.PI/12);
				
			}
			
			count += 1;
			
			System.out.println (count);
			System.out.println (Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme());
			
			if (count == 500){
				throw new RuntimeException("Trop d'itérations");
			}
			
		}
		
		return vitesseinit;
	}
	
	public static void main(String[] args){
		Vecteur posi = new Vecteur(3, 2 , 1);
		int mur1 = 12;
		Vecteur posf = new Vecteur(5, 4.5 , 0);
		LancerUn lancer = new LancerUn(posi, mur1, posf);
		Vecteur vinit = lancer.calculTir(posi, posf, mur1);
		System.out.println(vinit.phi + vinit.thetas + vinit.rayons);
	}
	
}
