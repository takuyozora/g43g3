package calcul;

public class Vecteur {
	
	static final int TYPE_CARTESIEN = 10;
	static final int TYPE_SPHERIQUE = 11;
	static final int TYPE_CYLINDRIQUE = 12;
	
	static final int OPE_PLUS = 20;
	static final int OPE_MOINS = 21;
	static final int OPE_PRODUIT = 22;
	
	public double x ;
	public double y ;
	public double z ;
	
	public double rayons;
	public double thetas;
	public double phi;
	
	public double rayonr;
	public double thetar;
	public double h;
	
	
	public double norme ;
	
	public Vecteur(){
		this.x = 0 ;
		this.y = 0 ;
		this.z = 0 ;
		this.updateCylindrique(TYPE_CARTESIEN);
		this.updateSpherique(TYPE_CARTESIEN);
	}
	
	public Vecteur(double x , double y , double z){
		this.x = x ;
		this.y = y ;
		this.z = z ;
		this.updateCylindrique(TYPE_CARTESIEN);
		this.updateSpherique(TYPE_CARTESIEN);
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
	
	public static Vecteur operation(Vecteur vect1, Vecteur vect2, int opeType){
		Vecteur result = new Vecteur();
		if (opeType == OPE_PLUS){
			result.x = vect1.x + vect2.x;
			result.y = vect1.y + vect2.y;
			result.z = vect1.z + vect2.z;
			return result;
		}
		else if (opeType == OPE_MOINS){
			result.x = vect1.x - vect2.x;
			result.y = vect1.y - vect2.y;
			result.z = vect1.z - vect2.z;
			return result;
		}
		else if (opeType == OPE_PRODUIT){
			result.x = vect1.y * vect2.z - vect1.z * vect2.y;
			result.y = vect1.z * vect2.x - vect1.x * vect2.z;
			result.z = vect1.x * vect2.y - vect1.y * vect2.x;
			return result;
		}
		throw new IllegalArgumentException();
	}
	
	public void operation(Vecteur vect2, int opeType){
		this = Vecteur.operation(this,vect2,opeType);
	}
}
