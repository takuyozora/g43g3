package calcul;

public class Vecteur {
	
	static final int TYPE_CARTESIEN = 10;
	static final int TYPE_SPHERIQUE = 11;
	static final int TYPE_CYLINDRIQUE = 12;
	
	static final int OPP_PLUS = 20;
	static final int OPP_MOINS = 21;
	static final int OPP_PRODUIT = 22;
	
	public double vectx ;
	public double vecty ;
	public double vectz ;
	
	public double rayons;
	public double thetas;
	public double phi;
	
	public double rayonr;
	public double thetar;
	public double vecth;
	
	
	public double norme ;
	
	public Vecteur(){
		vectx = 0 ;
		vecty = 0 ;
		vectz = 0 ;
		this.updateCylindrique(TYPE_CARTESIEN);
	}
	
	public Vecteur(double x , double y , double z){
		vectx = x ;
		vecty = y ;
		vectz = z ;
	}
	
	public double norme(double x , double y , double z){
		norme = Math.sqrt(x*x+y*y+z*z) ;
		return norme ;
	}
	
	private void updateCartesien(int fromType){
		if (fromType == TYPE_CYLINDRIQUE){
			
		}else if(fromType == TYPE_CYLINDRIQUE){
			
		}
	}
	
	private void updateSpherique(int fromType){
		if (fromType == TYPE_CYLINDRIQUE){
			
		}else if(fromType == TYPE_CARTESIEN){
			
		}
	}
	
	private void updateCylindrique(int fromType){
		if (fromType == TYPE_SPHERIQUE){
			
		}else if(fromType == TYPE_CARTESIEN){
			
		}
	}
	
	public Vecteur opperation(Vecteur vect1, Vecteur vect2, int oppType){
		Vecteur result = Vecteur();
		
	}
}
