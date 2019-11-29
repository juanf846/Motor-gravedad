package juanf846.motorGravedad.objetosConGravedad;

import java.awt.Color;

import juanf846.motorGravedad.util.Vector2;

public abstract class ObjetoConGravedad {

    public static final double CONSTANTE_GRAVEDAD = 6.67428e-11f; //6.67428e-11f
	public static final double MULTIPLICADOR=1e+9f;

	public abstract void setCalcularGravedad(boolean x);
	public abstract void setColor(Color c);
	public abstract Color getColor();
    public abstract double getMasa();
	public abstract Vector2 getPosicion();
	public abstract void agregarFuerza(Vector2 v);
	public abstract void calcularGravedad(ObjetoConGravedad objeto2);
	public abstract void calcularMovimiento(ObjetoConGravedad[] otrosObjetos, int pasos);
	public abstract void calcularColision(ObjetoConGravedad obj);
	
}
