import calcul.Vecteur;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hello World !");
		
		calcul.Vecteur vect1 = new calcul.Vecteur(1,2,3);
		calcul.Vecteur vect2 = new calcul.Vecteur(1,2,3);
		
		calcul.Vecteur vect3 = calcul.Vecteur.operation(vect1,vect2,calcul.Vecteur.OPE_PLUS);
		
		System.out.println(vect3);
		
		vect3.operation(vect1,calcul.Vecteur.OPE_PLUS);
		
		System.out.println(vect3);
	}
	
}
