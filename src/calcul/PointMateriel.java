package calcul;

public class PointMateriel extends Point {
	
	public double masse = 0 ;

	public PointMateriel(){
		super();
		masse = 0;
	}

	public PointMateriel(double x , double y , double z , double masse){
		super(x , y , z);
		this.masse = masse;
	}
}
