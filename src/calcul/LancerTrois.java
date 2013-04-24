package calcul;

public class LancerTrois {
	/*
	 * LancerTrois est la classe qui permet de trouver les paramètres de tirs avec comme 
	 * paramètres d'entrée une puissance (faible, moyen, fort, très fort) donc une fourchette
	 * de vitesse, le mur du rebond, la cible au sol et la position du lanceur
	 */
	
	static final int MUR_NORD = 10 ;
	static final int MUR_SUD = 11 ; 
	static final int MUR_EST = 12 ;
	static final int MUR_OUEST = 13 ;
	
	public Vecteur posinit = new Vecteur();
	public int mur;
	public Vecteur cible = new Vecteur();
	
	public LancerTrois(Vecteur posinit, int mur, Vecteur cible){
		this.posinit = posinit;
		this.mur= mur;
		this.cible = cible;
		
	}
		
	private double[] calculFourchette(Vecteur posinitiale, int murmur){
		
		double[]result = {0,0,0,0};
			
		if (murmur == LancerTrois.MUR_NORD){
			
			double phimin = Math.PI*(1/2) - Math.atan((Espace.LARGEUR - posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
			double phimax = Math.PI*(1/2) + Math.atan((posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
			double thetamin = Math.PI*(1/2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
			double thetamax = Math.PI*(1/2) - Math.atan((posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
			
			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}
		
		if (murmur == LancerTrois.MUR_SUD){
			
			double phimin = Math.PI*(3/2) - Math.atan((posinitiale.x)/(posinitiale.y));
			double phimax = Math.PI*(3/2) + Math.atan((Espace.LARGEUR - posinitiale.x)/(posinitiale.y));
			double thetamin = Math.PI*(1/2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.y));
			double thetamax = Math.PI*(1/2) - Math.atan((posinitiale.z)/(posinitiale.y));

			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}
		
		if (murmur == LancerTrois.MUR_EST){

			double phimin = - Math.atan((posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
			double phimax = Math.atan((Espace.PROFONDEUR - posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
			double thetamin = Math.PI*(1/2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
			double thetamax = Math.PI*(1/2) - Math.atan((posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
			
			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}
		
		if (murmur == LancerTrois.MUR_OUEST){
			
			double phimin = Math.PI - Math.atan((Espace.PROFONDEUR - posinitiale.y)/(posinitiale.x));
			double phimax = Math.PI + Math.atan((posinitiale.y)/(posinitiale.x));
			double thetamin = Math.PI*(1/2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.x));
			double thetamax = Math.PI*(1/2) - Math.atan((posinitiale.z)/(posinitiale.x));

			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}

		else{
			
			throw new IllegalArgumentException("On ne définit pas correctement le mur voulu");
		}
				
		return result;
		
	}
	
	public Vecteur tir(){
		
		double[]fourchette = calculFourchette(this.posinit, this.mur);
		
		int count = 0;
		
		Vecteur posfinale = new Vecteur();
		
		Vecteur vitesseinit = new Vecteur();
		
		while (Vecteur.operation(posfinale, this.cible, Vecteur.OPE_MOINS).norme() >= 0.15){
			
			count += 1;
			
			vitesseinit.setPhi(Math.random()*(fourchette[1]-fourchette[0])+fourchette[0]);
			vitesseinit.setThetas(Math.random()*(fourchette[3]-fourchette[2])+fourchette[2]);
			vitesseinit.setRayons(Math.random()*45);
			
			Balle balletir = new Balle(new Vecteur (this.posinit.x, posinit.y, posinit.z) , new Vecteur (vitesseinit.x, vitesseinit.y, vitesseinit.z));
			
			try{
				posfinale = balletir.lancer();
			}
			catch(UnsupportedOperationException e){
				continue;
			}
			catch(RuntimeException e){
				continue;
			
			}
			if (balletir.nbrebond > 1){
				continue;
			}
			
		}
		
		System.out.println("Nbres d'itérations : " + count + "\n");
		return vitesseinit;
		
	}
	
	public static void main(String[] args){
		Vecteur posi = new Vecteur(2, 2 , 1.5);
//		int murun = 12;
		Vecteur posf = new Vecteur(4, 6 , 0);
		LancerTrois lancer = new LancerTrois(posi, 12, posf);
		Vecteur vinit = lancer.tir();
		System.out.println(vinit.phi + vinit.thetas + vinit.rayons);
	}
	
}
