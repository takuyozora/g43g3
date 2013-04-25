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
		
	private static double[] calculFourchette(Vecteur posinitiale, int murmur){
		double[]result = {0,0,0,0};
			
		if (murmur == LancerTrois.MUR_NORD){
			
			double phimin = Vecteur.fractionPi(1,2) - Math.atan((Espace.LARGEUR - posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
			double phimax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
			
			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else if (murmur == LancerTrois.MUR_SUD){
			
			double phimin = Vecteur.fractionPi(3, 2) - Math.atan((posinitiale.x)/(posinitiale.y));
			double phimax = Vecteur.fractionPi(3, 2) + Math.atan((Espace.LARGEUR - posinitiale.x)/(posinitiale.y));
			double thetamin = Vecteur.fractionPi(1, 2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.y));
			double thetamax = Vecteur.fractionPi(1, 2) + Math.atan((posinitiale.z)/(posinitiale.y));

			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else if (murmur == LancerTrois.MUR_EST){

			double phimin = - Math.atan((posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
			double phimax = Math.atan((Espace.PROFONDEUR - posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
			
			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else if (murmur == LancerTrois.MUR_OUEST){
			
			double phimin = Vecteur.fractionPi(1, 1) - Math.atan((Espace.PROFONDEUR - posinitiale.y)/(posinitiale.x));
			double phimax = Vecteur.fractionPi(1, 1) + Math.atan((posinitiale.y)/(posinitiale.x));
			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.x));
			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(posinitiale.x));

			result[0] = phimin;
			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else{
			
			throw new IllegalArgumentException("On ne définit pas correctement le mur voulu");
		}
				
		return result;
		
	}
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
	
	public static double calculPhiTir(Vecteur posTireur, Vecteur posCible, int murCible){
		double phi = 0;
		if (murCible == MUR_OUEST){
			phi =  Vecteur.fractionPi(1, 1) - Math.atan((posCible.y - posTireur.y)/(posCible.x + posTireur.x));
		}
		else if(murCible == MUR_NORD){
			phi = Vecteur.fractionPi(1, 2) - Math.atan((posCible.x - posTireur.x)/(2*Espace.PROFONDEUR - posCible.y - posTireur.y));
		}
		else if(murCible == MUR_EST){
			phi = -Math.atan((posTireur.y - posCible.y)/(2*Espace.LARGEUR -posCible.x - posTireur.x)); 
		}
		else if(murCible == MUR_SUD){
			phi = Vecteur.fractionPi(3, 2) - Math.atan((posTireur.x - posCible.x)/(posCible.y + posTireur.y));
		}
		return phi;
	}
	
	public static Vecteur multiTry(Vecteur posTireur, Vecteur posCible, int murCible){
		Vecteur vitesseTry = new Vecteur();
		
		Vecteur vitesseLancer = new Vecteur();
		vitesseLancer.setPhi(LancerTrois.calculPhiTir(posTireur, posCible, murCible));
		
		double[] fourchette = LancerTrois.calculFourchette(posTireur, murCible);
		
		double vmax = 40;
		double ecartMax = 0;
		boolean downSpeed = true;
		double newVitesse = 0;
		boolean fixeVitesse = false;
		
		double errLast = 0;
		double err = 0;
		double vitesse = 40;
		double lastChangeVitesse = vmax;
		double maxIter = 1000;
		boolean rightWall = true;
		int affile = 1;
	
		for(double i=30;i<maxIter;i++){
//			vitesseTry.setX(0);
//			vitesseTry.setY(0);
//			vitesseTry.setZ(0);
			vitesseTry.setThetas(1.3);
			if (errLast == 0){
				vitesse -= 40/maxIter;
			}else{
				System.out.println("Vitesse : "+vitesse);
					if(err-errLast <0){
							if(downSpeed){
								vitesse -=  affile * affile * vmax / maxIter;
							}else{
								vitesse += affile * affile * vmax / maxIter;
							}
							affile += 1;
						
					}else{
						System.out.println("Change");
						if(downSpeed){
							// On change de sens vers une augmentation de vitesse
							ecartMax = Math.abs(lastChangeVitesse - vitesse);
							lastChangeVitesse = vitesse;
							vitesse += ecartMax/2;
							affile = 1;
							downSpeed = false;
						}else{
							// On change de sens vers une diminution de vitesse
							ecartMax = Math.abs(lastChangeVitesse - vitesse);
							lastChangeVitesse = vitesse;
							vitesse -= ecartMax/2;
							affile = 1;
							downSpeed = true;
						}
					}
				
//				if(err - errLast < 0){ // On a alors une amélioration, il faut continuer ainsi
//					if(affile >0){
//						affile+=1;
//					}else{
//						affile=1;
//					}
//					vitesse -= 40*affile/(maxIter+i);
//				}else{
//					if(affile <0){
//						affile -= 1;
//					}else{
//						affile=-1;
//					}
//					System.out.println("retour");
//					vitesse -= 40*affile/(maxIter+i);
//				}
			}
			vitesseTry.setRayons(vitesse);
			vitesseTry.setPhi(vitesseLancer.phi);
//			System.out.println(vitesseTry.thetas);
			
			Balle balleTry = new Balle(new Vecteur(posTireur.x,posTireur.y,posTireur.z),vitesseTry);
			Vecteur posArrivee = new Vecteur();
			rightWall = true;
			try{
				posArrivee = balleTry.lancer(1);
			}catch(RuntimeException e){
				//System.out.println(e);
				//vitesse *= 0.97;
				rightWall = false;
				continue;
			}
			errLast = err;
			err = Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme();
			System.out.println("   Erreur" +Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme() );
			if(Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme() <= 0.20){
				System.out.println("Trouvée en :"+i+" iteration avec "+Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme()+" d'erreur");
				break;
			}
			
		}
		
		
		return vitesseTry;
	}
	
	public static Vecteur comboTry(Vecteur posTireur, Vecteur posCible, int murCible){
		Vecteur vitesseTry = new Vecteur();
		
		Vecteur vitesseLancer = new Vecteur();
		double thePhi = LancerTrois.calculPhiTir(posTireur, posCible, murCible);
		
		double errMin = 100; // Pour etre sur que la première valeur ne soit pas interprété
		double errLast = 0;
		double err = 0;
		
		double exceptLoop = 1;
		
		double aLaSuite = 1;
		boolean slow = true;
		
		final double vMax = 40;
		final int maxIter = 1000;
		final double k0 = 2*vMax/maxIter; // k0 pour la suite de kn (le pas)
		final double theTheta = 1.3; // Pour l'instant on fixe theta
		
		double vitesse = vMax/2;

		vitesseTry.setPhi(thePhi);
		vitesseTry.setThetas(theTheta);
		vitesseTry.setRayons(vitesse);
		
		Vecteur posTrouvee = new Vecteur();
		
		for(double n=1;n<maxIter;n++){
			vitesseTry.setRayons(vitesse);
			Balle balle = new Balle(posTireur,vitesseTry);
			try{
				posTrouvee = balle.lancer(1);
			}catch(RuntimeException e){
				System.out.println(e);
//				vitesse -= k0 * exceptLoop * exceptLoop *exceptLoop;
				if(slow){
					vitesse -= 0.1;
				}else{
					vitesse += 0.1;
				}
				exceptLoop += 1;
				continue;
			}
			if(errMin == 100){
				err = Vecteur.operation(posCible, posTrouvee, Vecteur.OPE_MOINS).norme();
				errMin = err;
				vitesse -= k0;
				continue;
			}
			exceptLoop = 1;
			errLast = err;
			err = Vecteur.operation(posCible, posTrouvee, Vecteur.OPE_MOINS).norme();
			errMin = Math.min(err,errMin);
			
			if(err-errLast < 0){ // On s'améliore continuons !
				double modif = (double)(k0 * aLaSuite * aLaSuite) * (double)(maxIter/(maxIter+(n*n))) ;
				if (slow){
					modif *= -1;
				}
				vitesse -= modif;
				aLaSuite += 1;
			}else{
				aLaSuite = 1;
				slow = !slow; // On inverse
				double modif = (k0 * aLaSuite * aLaSuite)* (maxIter/(maxIter+(n*n))) ;
				if (slow){
					modif *= -1;
				}
				vitesse -= modif;
			}
			
			if(err < 0.02){
				System.out.println("Touvée !! en "+n+"itteration !");
				System.out.println("Cible:  X:"+posCible.x+" Y:"+posCible.y+" Z:"+posCible.z);
				System.out.println("Trouv:  X:"+posTrouvee.x+" Y:"+posTrouvee.y+" Z:"+posTrouvee.z);
				System.out.println("Vitesse:  Norme:"+vitesseTry.norme()*3.6+" km/h");
				break;
			}
			
				
				
		}
		
	
		return vitesseTry;
	}
	
	
	public static Vecteur Try(Vecteur posTireur, Vecteur posCible, int murCible){
		Vecteur vitesseTry = new Vecteur();
		
		Vecteur vitesseLancer = new Vecteur();
		vitesseLancer.setPhi(LancerTrois.calculPhiTir(posTireur, posCible, murCible));
		
		double[] fourchette = LancerTrois.calculFourchette(posTireur, murCible);
		

		
		double errLast = 0;
		double err = 0;
		double vitesse = 40;

		double maxIter = 2000;
		int affile = 1;
	
		for(double i=10;i<maxIter;i++){
			vitesse = 40 - (40*i/maxIter);
			vitesseTry.setRayons(vitesse);
			vitesseTry.setPhi(vitesseLancer.phi);
			vitesseTry.setThetas(1.4);
			System.out.println("Vitesse :"+vitesse);
//			System.out.println(vitesseTry.thetas);
			
			Balle balleTry = new Balle(new Vecteur(posTireur.x,posTireur.y,posTireur.z),vitesseTry);
			Vecteur posArrivee = new Vecteur();
			
			try{
				posArrivee = balleTry.lancer(1);
			}catch(RuntimeException e){
				//System.out.println(e);
				//vitesse *= 0.97;
				continue;
			}
			errLast = err;
			err = Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme();
			System.out.println("   Erreur" +Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme() );
			if(Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme() <= 0.1){
				System.out.println("Trouvée en :"+i+" iteration avec "+Vecteur.operation(posCible, posArrivee, Vecteur.OPE_MOINS).norme()+" d'erreur");
				break;
			}
			
		}
		
		
		return vitesseTry;
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
		Vecteur posf = new Vecteur(0.1, 0.1, 0);
		Vecteur posi = new Vecteur(4.1, 3 , 1.5);
		int murun = LancerTrois.MUR_SUD;
		LancerTrois lancer = new LancerTrois(posi, murun, posf);
		double phi = lancer.calculPhiTir();
		double phidegre = phi*(180/(Math.PI));
		System.out.println(phidegre);
		
		Vecteur vitesse = LancerTrois.comboTry(posi,posf,murun);
		
	}
}
