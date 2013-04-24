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
	
	public double phitir;
	
	public Vecteur posinit = new Vecteur();
	public int mur;
	public Vecteur cible = new Vecteur();
	
	public LancerTrois(Vecteur posinit, int mur, Vecteur cible){
		this.posinit = posinit;
		this.mur = mur;
		this.cible = cible;
		
	}
		
//	private double[] calculFourchette(Vecteur posinitiale, int murmur){
//		double[]result = {0,0,0,0};
//			
//		if (murmur == LancerTrois.MUR_NORD){
//			
//			double phimin = Vecteur.fractionPi(1,2) - Math.atan((Espace.LARGEUR - posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
//			double phimax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
//			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
//			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
//			
//			result[0] = phimin;
//			result[1] = phimax;
//			result[2] = thetamin;
//			result[3] = thetamax;
//		}else if (murmur == LancerTrois.MUR_SUD){
//			
//			double phimin = Vecteur.fractionPi(3, 2) - Math.atan((posinitiale.x)/(posinitiale.y));
//			double phimax = Vecteur.fractionPi(3, 2) + Math.atan((Espace.LARGEUR - posinitiale.x)/(posinitiale.y));
//			double thetamin = Vecteur.fractionPi(1, 2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.y));
//			double thetamax = Vecteur.fractionPi(1, 2) + Math.atan((posinitiale.z)/(posinitiale.y));
//
//			result[0] = phimin;
//			result[1] = phimax;
//			result[2] = thetamin;
//			result[3] = thetamax;
//		}else if (murmur == LancerTrois.MUR_EST){
//
//			double phimin = - Math.atan((posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
//			double phimax = Math.atan((Espace.PROFONDEUR - posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
//			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
//			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
//			
//			result[0] = phimin;
//			result[1] = phimax;
//			result[2] = thetamin;
//			result[3] = thetamax;
//		}else if (murmur == LancerTrois.MUR_OUEST){
//			
//			double phimin = Vecteur.fractionPi(1, 1) - Math.atan((Espace.PROFONDEUR - posinitiale.y)/(posinitiale.x));
//			double phimax = Vecteur.fractionPi(1, 1) + Math.atan((posinitiale.y)/(posinitiale.x));
//			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.x));
//			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(posinitiale.x));
//
//			result[0] = phimin;
//			result[1] = phimax;
//			result[2] = thetamin;
//			result[3] = thetamax;
//		}else{
//			
//			throw new IllegalArgumentException("On ne définit pas correctement le mur voulu");
//		}
//				
//		return result;
//		
//	}
//	
//	public Vecteur tir(){
//		double[]fourchette = calculFourchette(this.posinit, this.mur);
//		
//		int count = 0;
//		
//		Vecteur posfinale = new Vecteur();
//		Vecteur vitesseinit = new Vecteur();
//		
//		while (Vecteur.operation(posfinale, this.cible, Vecteur.OPE_MOINS).norme() >= 0.2){
//			
//			count += 1;
//			
//			vitesseinit.setPhi(Math.random()*(fourchette[1]-fourchette[0])+fourchette[0]);
//			vitesseinit.setThetas(Math.random()*(fourchette[3]-fourchette[2])+fourchette[2]);
//			vitesseinit.setRayons(Math.random()*40);
//			
//			Balle balletir = new Balle(new Vecteur (this.posinit.x, posinit.y, posinit.z) , new Vecteur (vitesseinit.x, vitesseinit.y, vitesseinit.z));
//			
//			System.out.println("Vitesse : "+(vitesseinit.rayons*3.6)+"km/h \n");
//			
//			try{
//				posfinale = balletir.lancer(1);
//				System.out.println("Ecart :"+Vecteur.operation(posfinale, this.cible, Vecteur.OPE_MOINS).norme());
//			}catch(UnsupportedOperationException e){
//				System.out.println(e);
//				continue;
//			}catch(RuntimeException e){
//				System.out.println(e);
//				continue;
//			
//			}
//		}
//		
//		System.out.println("Nbres d'itérations : " + count + "\n");
//		return vitesseinit;
//		
//	}
	
	public double calculPhiTir(){
		if (this.mur == MUR_OUEST){
			phitir =  Vecteur.fractionPi(1, 1) - Math.atan((this.cible.y - this.posinit.y)/(this.cible.x + this.posinit.x));
		}
		else if(this.mur == MUR_NORD){
			phitir = Vecteur.fractionPi(1, 2) - Math.atan((this.cible.x - this.posinit.x)/(2*Espace.PROFONDEUR - this.cible.y - this.posinit.y));
		}
		else if(this.mur == MUR_EST){
			phitir = -Math.atan((this.posinit.y - this.cible.y)/(2*Espace.LARGEUR -this.cible.x - this.posinit.x)); 
		}
		else if(this.mur == MUR_SUD){
			phitir = Vecteur.fractionPi(3, 2) - Math.atan((this.posinit.x - this.cible.x)/(this.cible.y + this.posinit.y));
		}
		return phitir;
	}
	
//	public static void main(String[] args){
//		Vecteur posi = new Vecteur(2, 2 , 1.5);
//		int murun = LancerTrois.MUR_SUD;
//		Vecteur posf = new Vecteur(4, 6 , 0);
//		LancerTrois lancer = new LancerTrois(posi, murun, posf);
//		long begin = System.currentTimeMillis();
//		Vecteur vinit = lancer.tir();
//		long end = System.currentTimeMillis();
//		float time = ((float) (end-begin));
//		System.out.println("Vitesse : " + vinit.norme() + " Temps : " +time);
//	}
	public static void main(String[] args){
		Vecteur posf = new Vecteur(2, 2 , 1.5);
		Vecteur posi = new Vecteur(4, 6 , 0);
		int murun = LancerTrois.MUR_SUD;
		LancerTrois lancer = new LancerTrois(posi, murun, posf);
		double phi = lancer.calculPhiTir();
		double phidegre = phi*(180/(Math.PI));
		System.out.println(phidegre);
	}
}
