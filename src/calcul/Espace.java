package calcul;

public static class Espace {
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
	public static int PAS_IMPACT = 30
	
	public static Vecteur GRAVITE = new Vecteur(0,0,-9.81);
	

	
	public static double LARGEUR = ***;
	public static double PROFONDEUR = ***;
	public static double HAUTEUR_HAUTE = ***;
	public static double HAUTEUR_BASSE = ***;

	public static int impact(Vecteur position){
		if(position.x < 0){
			return IMPACT_EST ;
		}
		
		else if(position.y < 0){
			return IMPACT_SUD;
		}
		
		else if (position.y > PROFONDEUR){
			return IMPACT_NORD;
		}
		
		else if (position.x > LARGEUR){
			return IMPACT_OUEST;
		}
	}

}
