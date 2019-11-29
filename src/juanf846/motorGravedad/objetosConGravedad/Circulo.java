package juanf846.motorGravedad.objetosConGravedad;

import java.awt.Color;
import java.util.logging.Logger;

import juanf846.motorGravedad.Configuracion;
import juanf846.motorGravedad.Configuracion.Clave;
import juanf846.motorGravedad.util.Vector2;

public class Circulo extends ObjetoConGravedad{

	private static Logger LOG = Logger.getLogger(Circulo.class.getName());
	
	private double radio;
	private Vector2 movimiento;
	private double masa;
	private Color color;
	private boolean calcularGravedad;
	
	public double getRadio() {
		return radio;
	}
	public void setRadio(double radio) {
		this.radio = radio;
	}
	
	@Override
	public void setCalcularGravedad(boolean x) {
		this.calcularGravedad=x;
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Vector2 getPosicion() {
		return posicion;
	}

	@Override
	public double getMasa() {
		return masa;
	}

	public Circulo(double posicionX, double posicionY, double masa) {
		posicion = new Vector2(posicionX,posicionY);
		movimiento = new Vector2();
		this.masa = masa;
		color = Color.BLACK;
		calcularGravedad = true;
		radio = 2.5;
	}
	
	/** 
	 * Fuerza en Newtons
	 */
	@Override
	public void agregarFuerza(Vector2 v) {
		movimiento.adicion(Vector2.division(v, masa));
	}
	
	@Override
	public void calcularMovimiento(ObjetoConGravedad[] otrosObjetos, int pasos) {
		Vector2 nuevaPosicion = (Vector2) posicion.clone();
		nuevaPosicion.adicion(Vector2.multiplicacion(movimiento, 
				(1f/pasos) 
				* (1000/Configuracion.getInstance().getInt(Clave.MAX_FRAMES))/1000f));
		boolean mover = true;
		/*for(int i=0;i<otrosObjetos.length;i++) {
			if(otrosObjetos[i] != this && otrosObjetos[i] instanceof Circulo) {
				Circulo obj2 = (Circulo) otrosObjetos[i];
				double distancia = obj2.getPosicion().distancia(this.posicion);
				double minimo = obj2.getRadio()+this.getRadio();
				if(minimo>distancia) {
					mover = false;
				}
			}
		}*/
		if(mover) {			
			posicion = nuevaPosicion;
		}
	}
	
	
	/*@Override
	public void calcularColision(ObjetoConGravedad obj) {
		if(obj instanceof Circulo) {
			Circulo c2 = (Circulo) obj;
			double minimo = c2.getRadio()+this.getRadio();
			double distancia = Vector2.distancia(c2.getPosicion(), this.getPosicion());
			double minimo2 = minimo-(minimo*MARGEN_DE_ERROR);
			if(minimo2>distancia) {
				System.out.println("Colision entre ("+obj+" y "+this+")");
				System.out.println(minimo2);
				System.out.println(distancia);
				Vector2 suma = Vector2.adicion(c2.movimiento, this.movimiento);
				this.movimiento = suma;
				c2.movimiento = suma;
				//System.out.println("Suma: "+suma);
				double porcentaje = distancia*100/minimo; 
				double distanciaX,nuevaDistanciaX;
				distanciaX = this.posicion.x - c2.posicion.x;
				nuevaDistanciaX = distanciaX/porcentaje*100;
				
				this.posicion.x = this.posicion.x - distanciaX/2 + nuevaDistanciaX/2;
				c2.posicion.x = c2.posicion.x + distanciaX/2 - nuevaDistanciaX/2;
				
				double distanciaY,nuevaDistanciaY;
				distanciaY = this.posicion.y - c2.posicion.y;
				nuevaDistanciaY = distanciaY/porcentaje*100;
				
				this.posicion.y = this.posicion.y - distanciaY/2 + nuevaDistanciaY/2;
				c2.posicion.y = c2.posicion.y + distanciaY/2 - nuevaDistanciaY/2;
				
				distancia = Vector2.distancia(c2.getPosicion(), this.getPosicion());
				//if(distancia<5) this.posicion.adicion(0.001);
				//distancia = Vector2.distancia(c2.getPosicion(), this.getPosicion());
				
				System.out.println(distancia);
				
			}
		}
	}*/
	public static final double MARGEN_DE_ERROR = 0.001;
	@Override
	public void calcularColision(ObjetoConGravedad obj) {
		if(obj instanceof Circulo) {
			Circulo c2 = (Circulo) obj;
			double minimo = c2.getRadio()+this.getRadio();
			double distancia = Vector2.distancia(c2.getPosicion(), this.getPosicion());
			double minimo2 = minimo-(minimo*MARGEN_DE_ERROR);
			if(minimo2>distancia) {
				LOG.info("Colision entre ("+obj+" y "+this+")");
				LOG.info(""+minimo2);
				LOG.info(""+distancia);
				Vector2 suma = Vector2.adicion(c2.movimiento, this.movimiento);
				this.movimiento = this.movimiento.negado();
				c2.movimiento = c2.movimiento.negado();
				
				this.calcularMovimiento(null, 1);
				c2.calcularMovimiento(null, 1);
				
				this.movimiento = this.movimiento.negado();
				c2.movimiento = c2.movimiento.negado();
				this.movimiento = suma;
				c2.movimiento = suma;
				
				LOG.info(""+distancia);
				distancia = Vector2.distancia(c2.getPosicion(), this.getPosicion());
				LOG.info(""+distancia);
			}
		}
	}
	
	public static int cuerposMuyCerca = 0;
	@Override
	public void calcularGravedad(ObjetoConGravedad obj2) {
		double CONSTANTE_GRAVEDAD = Configuracion.getInstance().getDouble(Clave.CONSTANTE_GRAVEDAD);
		double MULTIPLICADOR = Configuracion.getInstance().getDouble(Clave.MULTIPLICADOR_GRAVEDAD);
		
		double distancia = Vector2.distancia(this.getPosicion(), obj2.getPosicion());
		//if(distancia<1) distancia=1;
		double fuerza = (this.getMasa() * obj2.getMasa() / ((double)Math.pow(distancia, 2))) * CONSTANTE_GRAVEDAD;
        Vector2 tmp = Vector2.multiplicacion(
        		Vector2.sustraccion(this.getPosicion(), obj2.getPosicion())
        		,fuerza
        		*(1000/Configuracion.getInstance().getInt(Clave.MAX_FRAMES)/1000f)
        		*MULTIPLICADOR);
        obj2.agregarFuerza(tmp);
        tmp.negado();
        if(calcularGravedad)
        this.agregarFuerza(tmp);
        
        if(distancia<0.1) {
        	//System.out.println("Detectados cuerpos muy cerca!!!! tmp: "+tmp+" distancia: "+distancia+" fuerza: "+fuerza);
        	cuerposMuyCerca++;
        }
	}
	
}
