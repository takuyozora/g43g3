package calcul;

public class LancerUn {
	/* 
	 * LancerUn est la classe qui permet de trouver les paramètres de tir (angle et vitesse)
	 * avec comme paramètres d'entrée un mur pour le premier rebond, la cible au sol et la position
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
	
	public static double calculPhiTirUn(Vecteur posTireur, Vecteur posCible, int murUnCible ){
		
		double phi = 0;
		
		if (murUnCible == LancerUn.MUR_OUEST) {
			if (posCible.y < posTireur.y){
				phi = Vecteur.fractionPi(1, 1) + Math.atan((posTireur.y + posCible.y)/(posTireur.x + posCible.x));
			}
			else if (posCible.y > posTireur.y){
				phi = Vecteur.fractionPi(1, 1) - Math.atan((2*Espace.PROFONDEUR + posCible.y - posTireur.y)/(posTireur.x + posCible.x));
			}
			else if (posCible.y == posTireur.y){
				phi = Vecteur.fractionPi(1, 1);
			}
		}
		
		else if (murUnCible == LancerUn.MUR_EST) {
			if (posCible.y > posTireur.y){
				phi = Math.atan((2*Espace.PROFONDEUR - posCible.y - posTireur.y)/(2*Espace.LARGEUR - posTireur.x - posCible.x));
			}
			else if (posCible.y < posTireur.y){
				phi = - Math.atan((2*Espace.PROFONDEUR - posCible.y + posTireur.y)/(2*Espace.LARGEUR - posTireur.x - posCible.x));
			}
			else if (posCible.y == posTireur.y){
				phi = 0;
			}
		}	
		
		else if (murUnCible == LancerUn.MUR_SUD){
			if (posCible.x > posTireur.x){
				phi = Vecteur.fractionPi(3, 2) + Math.atan((2*Espace.LARGEUR - posTireur.x - posCible.x)/(posTireur.y + posCible.y));
			}
			else if (posCible.x < posTireur.x){
				phi = Vecteur.fractionPi(3, 2) - Math.atan((2*Espace.LARGEUR - posCible.x + posTireur.x)/(posTireur.y + posCible.y));
			}
			else if (posCible.x == posTireur.x){
				phi = Vecteur.fractionPi(3,2);
			}
		}
		else if (murUnCible == LancerUn.MUR_NORD) {
			if (posTireur.x > posCible.x){
				phi = Vecteur.fractionPi(1, 2) + Math.atan((posTireur.x + posCible.x)/(2*Espace.PROFONDEUR - posCible.y - posTireur.y));
			}
			else if (posTireur.x < posCible.x){
				phi = Vecteur.fractionPi(1, 2) - Math.atan((2*Espace.LARGEUR + posCible.x - posTireur.x)/(2*Espace.PROFONDEUR - posTireur.y - posCible.y));
			}
			else if (posTireur.x == posCible.x){
				phi = Vecteur.fractionPi(1, 2);
			}
		}
		
		else{
			throw new IllegalArgumentException("On ne définit pas correctement le murUnCible voulu");
		}
		return phi ;
	}
	
	private static double[] calculFourchetteUn(Vecteur posinitiale, int murmur){
		double[]result = {0,0,0,0};
			
		if (murmur == LancerUn.MUR_NORD){
			
//			double phimin = Vecteur.fractionPi(1,2) - Math.atan((Espace.LARGEUR - posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
//			double phimax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.x)/(Espace.PROFONDEUR - posinitiale.y));
			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(Espace.PROFONDEUR - posinitiale.y));
			
//			result[0] = phimin;
//			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else if (murmur == LancerUn.MUR_SUD){
			
//			double phimin = Vecteur.fractionPi(3, 2) - Math.atan((posinitiale.x)/(posinitiale.y));
//			double phimax = Vecteur.fractionPi(3, 2) + Math.atan((Espace.LARGEUR - posinitiale.x)/(posinitiale.y));
			double thetamin = Vecteur.fractionPi(1, 2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.y));
			double thetamax = Vecteur.fractionPi(1, 2) + Math.atan((posinitiale.z)/(posinitiale.y));

//			result[0] = phimin;
//			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else if (murmur == LancerUn.MUR_EST){

//			double phimin = - Math.atan((posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
//			double phimax = Math.atan((Espace.PROFONDEUR - posinitiale.y)/(Espace.LARGEUR - posinitiale.x));
			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(Espace.LARGEUR - posinitiale.x));
			
//			result[0] = phimin;
//			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else if (murmur == LancerUn.MUR_OUEST){
			
//			double phimin = Vecteur.fractionPi(1, 1) - Math.atan((Espace.PROFONDEUR - posinitiale.y)/(posinitiale.x));
//			double phimax = Vecteur.fractionPi(1, 1) + Math.atan((posinitiale.y)/(posinitiale.x));
			double thetamin = Vecteur.fractionPi(1,2) - Math.atan((Espace.HAUTEUR_HAUTE - posinitiale.z)/(posinitiale.x));
			double thetamax = Vecteur.fractionPi(1,2) + Math.atan((posinitiale.z)/(posinitiale.x));

//			result[0] = phimin;
//			result[1] = phimax;
			result[2] = thetamin;
			result[3] = thetamax;
		}else{
			
			throw new IllegalArgumentException("On ne définit pas correctement le mur voulu");
		}
				
		return result;
		
	}
	
//	Ancienne méthode : A supprimer dans le futur	
	
//	public Vecteur tirdeuxrebond(Vecteur posinitiale, Vecteur target, int mur){
//		
//		double lignetir = this.calculPhiTirUn(posinitiale, mur, target);
//		
//		Vecteur posfinale = new Vecteur();
//		
//		Vecteur vitesseinit = new Vecteur();
//		vitesseinit.setThetas(1.53);//(Math.PI)*(5/12)
//		vitesseinit.setRayons(20);
//		vitesseinit.setPhi(lignetir);
//		
//		int count = 0;		
//			
//		while (Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme() >= 1){
//			if (count == 10){
//				throw new RuntimeException("Trop d'itérations");
//			}
//			
//			Balle balletir = new Balle(new Vecteur (posinitiale.x, posinitiale.y, posinitiale.z) , new Vecteur (vitesseinit.x, vitesseinit.y, vitesseinit.z));
//			
//			System.out.println("Vitesse : "+(vitesseinit.rayons*3.6)+"km/h \n");
//			System.out.println("Angle phi en degrés :" + (vitesseinit.phi*57.3) + "\n");
////			posfinale = balletir.lancer();
//			try{
//				posfinale = balletir.lancer(1);
//			}
//			catch(UnsupportedOperationException e){
//				System.out.println("Plafond -> baisser theta !!");
//				vitesseinit.setThetas(vitesseinit.thetas - 0.1);
//				continue;
//			}
//			catch(RuntimeException e){
//				System.out.println("EXCEPT !!");
//				vitesseinit.setRayons(vitesseinit.rayons * 1.25);
//				continue;
//			}
//			
//			if (mur == MUR_OUEST){	
//				
//				if (posfinale.x > target.x){
//					
//					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
//					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
//					
//				}
//				
//				if (posfinale.x < target.x){
//					
//					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
//					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
//				
//				}
//			}
//			
//			if (mur == MUR_EST){
//				
//				if (posfinale.x < target.x){
//					
//					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
//					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
//						
//				}
//					
//				if (posfinale.x > target.x){
//						
//					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
//					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
//					
//				}
//			}
//				
//			if (mur == MUR_NORD){
//
//				if (posfinale.y < target.y){
//					
//					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
//					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
//					
//				}
//				
//				if (posfinale.y > target.y){
//					
//					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
//					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
//				
//				}
//			
//			}
//			
//			if (mur == MUR_SUD){
//
//				if (posfinale.y < target.x){
//					
//					vitesseinit.setRayons (vitesseinit.rayons * 0.95);
//					balletir.vitesse.setThetas (vitesseinit.thetas + 0.2);
//					
//				}
//				
//				if (posfinale.x > target.x){
//					
//					vitesseinit.setRayons(vitesseinit.rayons * 1.05);
//					vitesseinit.setThetas(vitesseinit.thetas - 0.2);
//				
//				}
//			}
//			
//			count += 1;
//			
//			System.out.println ("Itération n°: "+count +"\n");
//			System.out.println ("Erreur : "+Vecteur.operation(posfinale, target, Vecteur.OPE_MOINS).norme() + "\n ---------- \n" );
//			
//		}
//		
//		return vitesseinit;
//	}
	

	public static Vecteur comboTryUn(Vecteur posTireur, Vecteur posCible, int murCible){
		Vecteur bestTry = new Vecteur();
		Vecteur vitesseTry = new Vecteur();
		
		Vecteur vitesseLancer = new Vecteur();  // N'est pas utiliser, à supprimer
		double thePhi = LancerUn.calculPhiTirUn(posTireur, posCible, murCible);
		
		double errMin = 100; // Pour etre sur que la première valeur ne soit pas interprété
		double errLast = 0;
		double err = 0;
		
		double exceptLoop = 1;
		
		double aLaSuite = 1;
		boolean slow = true;
		
		final double vMax = 50;
		final int maxIter = 20000;
		final double k0 = 2*vMax/maxIter; // k0 pour la suite de kn (le pas)
		
		double[] fourchette = LancerUn.calculFourchetteUn(posTireur, murCible);
		final double theTheta = 1.35 + Math.random()/5 - Math.random()/12 ; // Pour l'instant on fixe theta
		
		System.out.println("Theta :"+theTheta);
		
		double vitesse = vMax/2;

		vitesseTry.setPhi(thePhi);
		vitesseTry.setThetas(theTheta);
		vitesseTry.setRayons(vitesse);
		
		Vecteur posTrouvee = new Vecteur();
		double modif = 0;
		long begin = System.currentTimeMillis();
		
		for(double n=1;n<maxIter;n++){
			vitesseTry.setRayons(vitesse);
			Balle balle = new Balle(posTireur,vitesseTry);
			try{
				posTrouvee = balle.lancer(2);
			}catch(Balle.TropDeRebondError e){
//				System.out.println(e);
				modif = k0 *exceptLoop;
				if(slow){
					vitesse -= modif;
				}else{
					vitesse += modif;
				}
				exceptLoop += 1;
				continue;
			}catch(Balle.PasAssezDeRebondError e){
//				System.out.println(e);
				modif = k0*exceptLoop;
				if(slow){
					vitesse -= modif;
				}else{
					vitesse += modif;
				}
				exceptLoop += 1;
				continue;
			}catch(Balle.RebondPlafondError e){
//				System.out.println(e);
				modif = k0 *exceptLoop;
				vitesse -= modif;
				exceptLoop += 1;
				continue;
			}
			
			if(errMin == 100){ // Premier tour de boucle
				err = Vecteur.operation(posCible, posTrouvee, Vecteur.OPE_MOINS).norme();
				errMin = err;
				vitesse -= k0;
				continue;
			}
			exceptLoop = 1;
			errLast = err;
			err = Vecteur.operation(posCible, posTrouvee, Vecteur.OPE_MOINS).norme();
			if(err <= errMin){
				bestTry = new Vecteur(vitesseTry);
				errMin = Math.min(err,errMin);
			}			
			if(err-errLast < 0){ // On s'améliore continuons !
				modif = (double)(k0 * aLaSuite * aLaSuite) * (double)(maxIter/(maxIter+(n*n))) ;
				if (slow){
					modif *= -1;
				}
				vitesse -= modif;
				aLaSuite += 1;
			}else{
				aLaSuite = 1;
				slow = !slow; // On inverse
				modif = (k0 * aLaSuite * aLaSuite)* (maxIter/(maxIter+(n*n))) ;
				if (slow){
					modif *= -1;
				}
				vitesse -= modif;
			}
			
			if(err < 2){
				System.out.println("Touvé !! en "+n+" iterations !");
				System.out.println("Cible:  X:"+posCible.x+" Y:"+posCible.y+" Z:"+posCible.z);
				System.out.println("Trouvée:  X:"+posTrouvee.x+" Y:"+posTrouvee.y+" Z:"+posTrouvee.z);
				System.out.println("Vitesse:  Norme:"+vitesseTry.norme()*3.6+" km/h");
				return vitesseTry;
			}else if (n%50 == 0){
				if(System.currentTimeMillis() - begin > 8000){
					break;
				}
			}
			
				
				
		}
		System.out.println("Meilleur résultat en "+maxIter+" iterations !");
		System.out.println(" --> Erreur :"+errMin*100 +" cm");
		System.out.println("Cible:  X:"+posCible.x+" Y:"+posCible.y+" Z:"+posCible.z);
		System.out.println("Trouvée:  X:"+posTrouvee.x+" Y:"+posTrouvee.y+" Z:"+posTrouvee.z);
		System.out.println("Vitesse:  Norme:"+vitesseTry.norme()*3.6+" km/h");
		return bestTry;
	
		
	}
	
	

//	public static void main(String[] args){
//		Vecteur posi = new Vecteur(2, 2 , 1);
//		int murun = 13;
//		Vecteur posf = new Vecteur(4, 6 , 0);
//		LancerUn lancer = new LancerUn(posi, murun, posf);
//		Vecteur vinit = lancer.calculTir(posi, posf, murun);
//		System.out.println(vinit.phi + vinit.thetas + vinit.rayons);
//	}
	
	public static void main(String[] args){
		Vecteur posf = new Vecteur(4, 9, 0);
		Vecteur posi = new Vecteur(6, 3 , 1.5);
		int murun = LancerUn.MUR_EST;
		LancerUn lancer = new LancerUn(posi, murun, posf);
		
//		double phi = lancer.calculPhiTirTrois();
//		double phidegre = phi*(180/(Math.PI));
//		System.out.println(phidegre);
		
		long begin = System.currentTimeMillis();
		Vecteur vitesse = LancerUn.comboTryUn(posi,posf,murun);
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin));
		
		System.out.println("Temps d'exec :"+time);
		
	}
	
}
