import calcul.*;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hello World !");
		
		calcul.Vecteur vinit = new calcul.Vecteur();
		vinit.phi = 0.3;
		vinit.thetas = 1;
		vinit.rayons = 20;
		vinit.operation(calcul.Vecteur(), calcul.Vecteur.OPE_MOINS);
		calcul.Balle balle = new calcul.Balle(Vecteur(3,1,1), vinit);
	}
	
}
