package juanf846.motorGravedad.util;

public class Vector2 {
	private double x;
	private double y;

	public double amplitude;
	public Vector2 normalized = null;
	
	public Vector2(double x, double y) {
		this.x=x;
		this.y=y;
		resetCache();
	}
	public Vector2() {
		x=0;
		y=0;
		resetCache();
	}

	private void resetCache(){
		amplitude = Double.NaN;
		normalized = null;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isNaN(){
		return Double.isNaN(x) && Double.isNaN(y);
	}

	public double distancia(Vector2 v1) {
		double distanciaX;
		double distanciaY;
		distanciaX = v1.x-this.x;
		if(distanciaX<0) distanciaX *= -1;
		
		distanciaY = v1.y-this.y;
		if(distanciaY<0) distanciaY *= -1;
		
		return Math.sqrt(Math.pow(distanciaX,2)+Math.pow(distanciaY, 2));
	}

	public Vector2 add(Vector2 v2) {
		return new Vector2(this.x+v2.x, this.y+v2.y);
	}
	
	public Vector2 substract(Vector2 v2) {
		return new Vector2(this.x-v2.x, this.y-v2.y);
	}
	
	public Vector2 multiply(Vector2 v2) {
		return new Vector2(this.x*v2.x, this.y*v2.y);
	}
	
	public Vector2 division(Vector2 v2) {
		return new Vector2(this.x/v2.x,this.y/v2.y);
	}
	
	public void divisionSelf(double x) {
		this.x = this.x/x;
		this.y = this.y/x;
		resetCache();
	}
	
	public Vector2 negate() {
		return new Vector2(this.x*-1,this.y*-1);
	}

	public Vector2 addAmplitude(double amplitude){
		return new Vector2(this.x*amplitude, this.y*amplitude);
	}

	public Vector2 getNormalized(){
		if(normalized == null){
			double amplitude = getAmplitude();
			normalized = new Vector2(this.x/amplitude,this.y/amplitude);
		}
		return normalized;
	}

	public double getAmplitude(){
		if(Double.isNaN(amplitude)){
			amplitude = Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y, 2));
		}
		return amplitude;
	}
	
	
	public static double distancia(Vector2 v1, Vector2 v2) {
		double distanciaX;
		double distanciaY;
		distanciaX = v1.x-v2.x;
		if(distanciaX<0) distanciaX *= -1;
		
		distanciaY = v1.y-v2.y;
		if(distanciaY<0) distanciaY *= -1;
		
		return (double) Math.sqrt(Math.pow(distanciaX,2)+Math.pow(distanciaY, 2));
	}
	
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		Vector2 p = new Vector2();
		p.x = v1.x+v2.x;
		p.y = v1.y+v2.y;
		return p;
	}
	
	public static Vector2 add(Vector2 v1, double x) {
		Vector2 p = new Vector2();
		p.x = v1.x+x;
		p.y = v1.y+x;
		return p;
	}
	
	public static Vector2 substract(Vector2 v1, Vector2 v2) {
		Vector2 p = new Vector2();
		p.x = v1.x-v2.x;
		p.y = v1.y-v2.y;
		return p;
	}
	
	public static Vector2 substract(Vector2 v1, double x) {
		Vector2 p = new Vector2();
		p.x = v1.x-x;
		p.y = v1.y-x;
		return p;
	}
	
	public static Vector2 multiplicacion(Vector2 v1, Vector2 v2) {
		Vector2 p = new Vector2();
		p.x = v1.x*v2.x;
		p.y = v1.y*v2.y;
		return p;
	}
	
	public static Vector2 multiplicacion(Vector2 v1, double x) {
		Vector2 p = new Vector2();
		p.x = v1.x*x;
		p.y = v1.y*x;
		return p;
	}
	
	public static Vector2 division(Vector2 v1, Vector2 v2) {
		Vector2 p = new Vector2();
		p.x = v1.x/v2.x;
		p.y = v1.y/v2.y;
		return p;
	}
	
	public static Vector2 division(Vector2 v1, double x) {
		Vector2 p = new Vector2();
		p.x = v1.x/x;
		p.y = v1.y/x;
		return p;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vector2) {
			Vector2 p = (Vector2) obj;
			if(x==p.x && y==p.y) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	@Override
	public Object clone() {
		Vector2 nuevo = new Vector2(x, y);
		return nuevo;
	}
}
