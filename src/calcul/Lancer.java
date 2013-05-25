package calcul;

public class Lancer {
	/*
	 * Classe simulant le lancer d'une balle et retournant le point d'arrivé de la balle
	 * 
	 *  (( Pour l'instant vide, peut-être implémenter par la classe balle dans une fonction lancer
	 */
	
	public static final int SIMPLE = 10;
	public static final int REBOND = 20;
	public static final int TRIPLE = 30;
	
	public Vecteur posLanceur = new Vecteur();
	
	public Lancer(int type, Vecteur posLanceur){
		this.posLanceur = posLanceur;
		
	}
	
	/*public int[] getTrajectoire(){
		
	}*/

}
