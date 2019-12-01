package juanf846.motorGravedad.objetosConGravedad;

import java.awt.Color;

import juanf846.motorGravedad.util.Vector2;

public abstract class ObjetoConGravedad {


	protected static int nextId=0;
	protected int id;
	protected Vector2 posicion;
	
	public ObjetoConGravedad() {
		id=nextId;
		nextId++;
	}
	
	public int getId() {
		return id;
	}
	
	public abstract void setCalcularGravedad(boolean x);
	public abstract void setColor(Color c);
	public abstract Color getColor();
    public abstract double getMasa();
	public abstract Vector2 getPosicion();
	public abstract void agregarFuerza(Vector2 v);
	public abstract void calcularGravedad(ObjetoConGravedad objeto2);
	public abstract void calcularMovimiento(ObjetoConGravedad[] otrosObjetos, int pasos);
	public abstract void calcularColision(ObjetoConGravedad obj);
	
	public String toString() {
		return "(Id="+id+"|Posicion="+getPosicion()+"|Masa="+getMasa()+")";
	}
}
