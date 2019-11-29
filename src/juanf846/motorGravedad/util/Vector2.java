package juanf846.motorGravedad.util;

public class Vector2 {
	public double x;
	public double y;
	
	public Vector2(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public Vector2() {
		x=0;
		y=0;
	}
	
	public double distancia(Vector2 v1) {
		double distanciaX;
		double distanciaY;
		distanciaX = v1.x-this.x;
		if(distanciaX<0) distanciaX *= -1;
		
		distanciaY = v1.y-this.y;
		if(distanciaY<0) distanciaY *= -1;
		
		return (double) Math.sqrt(Math.pow(distanciaX,2)+Math.pow(distanciaY, 2));
	}

	public void adicion(Vector2 v2) {
		this.x = this.x+v2.x;
		this.y = this.y+v2.y;
	}
	
	public void adicion(double x) {
		this.x = this.x+x;
		this.y = this.y+x;
	}
	
	public void sustraccion(Vector2 v2) {
		this.x = this.x-v2.x;
		this.y = this.y-v2.y;
	}
	
	public void sustraccion(double x) {
		this.x = this.x-x;
		this.y = this.y-x;
	}
	
	public void multiplicacion(Vector2 v2) {
		this.x = this.x*v2.x;
		this.y = this.y*v2.y;
	}
	
	public void multiplicacion(double x) {
		this.x = this.x*x;
		this.y = this.y*x;
	}
	
	public void division(Vector2 v2) {
		this.x = this.x/v2.x;
		this.y = this.y/v2.y;
	}
	
	public void division(double x) {
		this.x = this.x/x;
		this.y = this.y/x;
	}
	
	public Vector2 negado() {
		this.x = this.x*-1;
		this.y = this.y*-1;
		return this;
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
	
	public static Vector2 adicion(Vector2 v1, Vector2 v2) {
		Vector2 p = new Vector2();
		p.x = v1.x+v2.x;
		p.y = v1.y+v2.y;
		return p;
	}
	
	public static Vector2 adicion(Vector2 v1, double x) {
		Vector2 p = new Vector2();
		p.x = v1.x+x;
		p.y = v1.y+x;
		return p;
	}
	
	public static Vector2 sustraccion(Vector2 v1, Vector2 v2) {
		Vector2 p = new Vector2();
		p.x = v1.x-v2.x;
		p.y = v1.y-v2.y;
		return p;
	}
	
	public static Vector2 sustraccion(Vector2 v1, double x) {
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
