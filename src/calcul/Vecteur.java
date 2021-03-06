package calcul;

import java.lang.Math;

public class Vecteur {
	
	static final int TYPE_CARTESIEN = 10;
	static final int TYPE_SPHERIQUE = 11;
	static final int TYPE_CYLINDRIQUE = 12;
	
	public static final int OPE_PLUS = 20;
	public static final int OPE_MOINS = 21;
	public static final int OPE_PRODUIT = 22;
	
	public double x ;
	public double y ;
	public double z ;
	
	public double rayons;
	public double thetas;
	public double phi;
	
	public double rayonr;
	public double thetar;
	public double h;
	
	
	// (inutile) public double norme ;
	
	public Vecteur(){
		this.x = 0 ;
		this.y = 0 ;
		this.z = 0 ;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public Vecteur(double x , double y , double z){
		this.x = x ;
		this.y = y ;
		this.z = z ;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public Vecteur(Vecteur vect){
		this.x = vect.x;
		this.y = vect.y;
		this.z = vect.z;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	/* (inutile) public double norme(double x , double y , double z){
		norme = Math.sqrt(x*x+y*y+z*z) ;
		return norme ;
	}*/
	
	private void updateCartesien(int fromType){
		if (fromType == TYPE_CYLINDRIQUE){
			this.x = this.rayonr * Math.cos(this.thetar);
			this.y = this.rayonr * Math.sin(this.thetar);
			this.z = this.h;
		}else if(fromType == TYPE_SPHERIQUE){
			this.x = this.rayons * Math.sin(this.thetas) * Math.cos(this.phi);
			this.y = this.rayons * Math.sin(this.thetas) * Math.sin(this.phi);
			this.z = this.rayons * Math.cos(this.thetas);
		}
	}
	
	private void updateSpherique(int fromType){
		if (fromType == TYPE_CYLINDRIQUE){
			this.rayons = Math.sqrt(this.rayonr*this.rayonr + this.h*this.h);
			this.thetas = Math.acos(this.h / this.rayons);
			this.phi = this.thetar;
		}else if(fromType == TYPE_CARTESIEN){
			this.rayons = Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
			this.thetas = Math.acos(this.z / this.rayons);
			this.phi = Math.acos(this.x / Math.sqrt(this.x*this.x + this.y*this.y));
			if ( Math.sin(this.y / Math.sqrt(this.x*this.x + this.y*this.y)) < 0 ){
				this.phi = 2 * Math.PI - this.phi;
			}
		}
	}
	
	private void updateCylindrique(int fromType){
		if (fromType == TYPE_SPHERIQUE){
			this.rayonr = this.rayons * Math.sin(this.thetas);
			this.thetar = this.phi;
			this.h = this.rayons * Math.cos(this.thetas);
		}else if(fromType == TYPE_CARTESIEN){
			this.rayonr = Math.sqrt(this.x*this.x + this.y*this.y);
			this.thetar = Math.acos(this.x/this.rayonr);
			if(Math.sin(this.y/this.rayonr) < 0){
				this.thetar = 2 * Math.PI - this.thetar;
			}
			this.h = this.z;
		}
	}
	
	private void updateAll(int fromType){
		this.updateCartesien(fromType);
		this.updateCylindrique(fromType);
		this.updateSpherique(fromType);
	}
	
	public static Vecteur operation(Vecteur vect1, Vecteur vect2, int opeType){
		Vecteur result = new Vecteur();
		if (opeType == OPE_PLUS){
			result.x = vect1.x + vect2.x;
			result.y = vect1.y + vect2.y;
			result.z = vect1.z + vect2.z;
		}
		else if (opeType == OPE_MOINS){
			result.x = vect1.x - vect2.x;
			result.y = vect1.y - vect2.y;
			result.z = vect1.z - vect2.z;
		}
		else if (opeType == OPE_PRODUIT){
			result.x = vect1.y * vect2.z - vect1.z * vect2.y;
			result.y = vect1.z * vect2.x - vect1.x * vect2.z;
			result.z = vect1.x * vect2.y - vect1.y * vect2.x;
		}
		else{
			throw new IllegalArgumentException();
		}
		result.updateAll(TYPE_CARTESIEN);
		return result;
		
	}
	
	public static boolean comparer(Vecteur vect1, Vecteur vect2){
		if (vect1.x == vect2.x && vect1.y == vect2.y && vect1.z == vect2.z){
			return true;
		}else{
			return false;
		}
	}
	
	public static Vecteur pscalaire(Vecteur vect, double scalaire){
		/*
		 * Retourne v = k * u
		 */
		Vecteur result = new Vecteur(vect.x*scalaire,vect.y*scalaire,vect.z*scalaire);
		return result;
	}
	
	public void pscalaire(double scalaire){
		this.x *= scalaire;
		this.y *= scalaire;
		this.z *= scalaire;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public void operation(Vecteur vect2, int opeType){
		Vecteur result = Vecteur.operation(this,vect2,opeType);
		this.x = result.x;
		this.y = result.y;
		this.z = result.z;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public void setX(double x){
		this.x = x;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public void setY(double y){
		this.y = y;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public void setZ(double z){
		this.z = z;
		this.updateAll(TYPE_CARTESIEN);
	}
	
	public void setPhi(double phi){
		this.phi = phi;
		this.updateAll(TYPE_SPHERIQUE);
	}
	
	public void setThetas (double thetas){
		this.thetas = thetas;
		this.updateAll(TYPE_SPHERIQUE);
	}
	
	public void setRayons (double rayons){
		this.rayons = rayons;
		this.updateAll(TYPE_SPHERIQUE);
	}
	
	public void setThetar(double thetar){
		this.thetar = thetar;
		this.updateAll(TYPE_CYLINDRIQUE);
	}
	
	public void setH (double h){
		this.h = h;
		this.updateAll(TYPE_CYLINDRIQUE);
	}
	
	public void setRayonr(double rayonr){
		this.rayonr = rayonr;
		this.updateAll(TYPE_CYLINDRIQUE);
	}
	
	public double norme(){
		return this.rayons;
	}
	
	public static double fractionPi(int a, int b){
		
		double c = Math.PI;
		
		double d = (double)(a)/(double)(b);
		
		double result = c*d;
		
		return result;
	}
	
}
