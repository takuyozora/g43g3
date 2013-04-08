package calcul;

public final class Espace {
	/*
	 * Cette classe est là pour définir l'espace dans lequel la balle peut évoluer.
	 * Son but est de prévenir à chaque itération si la balle a touchée un des murs.
	 */
	
	public static int IMPACT_SOL = 10;
	public static int IMPACT_PLAFOND = 11;
	public static int IMPACT_NORD = 20;
	public static int IMPACT_SUD = 21;
	public static int IMPACT_EST = 22;
	public static int IMPACT_OUEST = 23;
	public static int PAS_IMPACT = 30;
	
	public static Vecteur GRAVITE = new Vecteur(0,0,-9.81);
	

	
	public static double LARGEUR = 6.40;
	public static double PROFONDEUR = 9.75;
	public static double HAUTEUR_HAUTE =  4.57;
	public static double HAUTEUR_BASSE = 2.13;

	public static int impact(Vecteur position){
		if(position.x < 0){
			position.setX(0);
			return IMPACT_OUEST ;
		}
		
		else if(position.y < 0){
			position.setY(0);
			return IMPACT_SUD;
		}
		
		else if (position.y > PROFONDEUR){
			position.setY(PROFONDEUR);
			return IMPACT_NORD;
		}
		
		else if (position.x > LARGEUR){
			position.setX(LARGEUR);
			return IMPACT_EST;
		}
		else if(position.z < 0){
			return IMPACT_SOL;
		}
		else{
			return PAS_IMPACT;
		}
	}

}
