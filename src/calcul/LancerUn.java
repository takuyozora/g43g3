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
	
	public LancerUn(double xinit, double yinit, double zinit, int mur, double xfinal, double yfinal, double zfinal){
		this.posinit.setX(xinit);
		this.posinit.setY(yinit);
		this.posinit.setZ(zinit);
		this.mur= mur;
		this.cible.setX(xfinal);
		this.cible.setY(yfinal);
		this.cible.setZ(zfinal);
		
	}
	
	private double calculLigneTir(Vecteur posinitiale, int mur, Vecteur target ){
		
		double result = 0;
		
		if (mur == MUR_NORD || mur == MUR_SUD) {
			result = (posinitiale.x + target.x)/2 ;
		}
		else if (mur == MUR_EST || mur == MUR_OUEST){
			result = (posinitiale.y + target.y)/2 ;			
		}
		else{
			throw new IllegalArgumentException();
		}
		return result ;
	}
	
	public Vecteur calculTir(Vecteur posinitiale, Vecteur target, int mur){
		
		double lignetir = this.calculLigneTir(posinitiale, mur, target);
		Vecteur posfinale = new Vecteur();
		
		Vecteur vitesseinit = new Vecteur();
		
		if (this.mur == MUR_OUEST){
			
			while (Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme() >= 0.05){
				
				vitesseinit.phi = (3/2)*Math.PI + Math.atan(Math.abs(lignetir-posinit.y)/posinit.x);
				vitesseinit.thetas = Math.PI/2;
				vitesseinit.rayons = 42;
				
				Balle balletir = new Balle(posinit, vitesseinit);
				
				posfinale = balletir.lancer();	
				
			if (posfinale.x > target.x){
				vitesseinit.rayons = vitesseinit.rayons - 1;
				vitesseinit.thetas = vitesseinit.thetas - Math.PI/12;
			}
			if (posfinale.x < target.x){
				vitesseinit.rayons = vitesseinit.rayons + 1;
				vitesseinit.thetas = vitesseinit.thetas + Math.PI/12;
			}
			
			}
			
		}
		
	}
	
}
