package gui.includes;

import calcul.LancerTrois;
import calcul.Vecteur;

public class Trajectoire {
	
	static Vecteur posi;
	static Vecteur posf;
	static int murun;
	
	private static LancerTrois monlancer;
	
	private static double[][] trajectoire;
	
	public Trajectoire(int xCible, int yCible, int xLanceur, int yLanceur, double zLanceur, int mur) {
		posf = new Vecteur(cmToMetres(xCible), convertY(cmToMetres(yCible)), 0);
		posi = new Vecteur(cmToMetres(xLanceur), convertY(cmToMetres(yLanceur)), zLanceur);
		murun = mur;
		trajectoire = LancerTrois.lancer(posi, posf, murun);
	}
	
	public double cmToMetres(int i) {
		double d;
		d = (double)i;
		d = d/100;
		return d;
	}
	public double convertY(double d) {
		return 9.75 - d;
	}
	
	public double[] getTabX() {
		double[] tab = new double[trajectoire.length];
		for(int i=0; i<trajectoire.length; i++) {
			tab[i] = trajectoire[i][0];
		//	System.out.println(tab[i]);
		}
		return tab;
	}
	public double[] getTabY() {
		double[] tab = new double[trajectoire.length];
		for(int i=0; i<trajectoire.length; i++) {
			tab[i] = trajectoire[i][2];
		}
		return tab;
	}
	public double[] getTabZ() {
		double[] tab = new double[trajectoire.length];
		for(int i=0; i<trajectoire.length; i++) {
			tab[i] = -(trajectoire[i][1]-9.75);
		}
		return tab;
	}
/*	
	public static void main(String[] args) {
		Trajectoire matraj = new Trajectoire(200, 50, 480 ,720, 13);
		// trajectoire = LancerTrois.lancer(posi, posf, murun);
		matraj.getTabX();
		
	} */
}
