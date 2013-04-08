package calcul;

public static class Espace {
	/*
	 * Cette classe est là pour définire l'espace dans lequel la balle peut évoluer.
	 * Son but est de prévenir à chaque itération si la balle a touchée un des murs.
	 */
	
	public static int IMPACTE_SOL = 10;
	public static int IMPACTE_PLAFOND = 11;
	public static int IMPACTE_NORD = 20;
	public static int IMPACTE_SUD = 21;
	public static int IMPACTE_EST = 22;
	public static int IMPACT_OUEST = 23;
	
	public static Vecteur GRAVITE = new Vecteur(0,0,9.81);
	
	public static double LARGEUR = ***;
	public static double PROFONDEUR = ***;
	public static double HAUTEUR_HAUTE = ***;
	public static double HAUTEUR_BASSE = ***;
	
	public static int impacte(Vecteur position){
		if(position.x < 0){
			
		}
	}

}
