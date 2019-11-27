package juanf846.motorGravedad;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase guarda la configuracion del programa
 * @author juanf846
 *
 */
public class Configuracion {
	public enum Clave{
		MAX_FRAMES("maxFrames",Integer.class),
		PASOS_FISICAS("pasosFisicas",Integer.class),
		COLISIONES("colisiones",Boolean.class),
		TRAYECTORIAS("trayectorias",Boolean.class);
		
		private String nombre;
		private Class<?> tipo;
		
		private Clave(String nombre, Class<?> tipo) {
			this.nombre = nombre;
			this.tipo = tipo;
		}
		
		public String getNombre() {
			return nombre;
		}
		
		public Class<?> getTipo() {
			return tipo;
		}
		
		public static Clave getByNombre(String nombre) {
			for(Clave c : Clave.values()) {
				if(c.getNombre().equals(nombre)) {
					return c;
				}
			}
			return null;
		}
	}

	private Map<String, Object> clavesValor;
	private static Configuracion instance;
	
	private Configuracion() {
		clavesValor = new HashMap<String,Object>();
		set(Clave.MAX_FRAMES, 60);
		set(Clave.PASOS_FISICAS, 2);
		set(Clave.COLISIONES, false);
		set(Clave.TRAYECTORIAS,false);
	}
	
	public static Configuracion getInstance() {
		if(instance==null) instance = new Configuracion();
		return instance;
	}
	
	public int getInt(Clave clave) {
		Object valor = clavesValor.get(clave.getNombre());
		if(valor instanceof Integer) {
			return (int) valor;
		}
		throw new RuntimeException("La configuracion \""+clave+"\" no es de tipo int");
	}
	
	public float getFloat(Clave clave) {
		Object valor = clavesValor.get(clave.getNombre());
		if(valor instanceof Float) {
			return (float) valor;
		}
		throw new RuntimeException("La configuracion \""+clave+"\" no es de tipo float");
	}
	
	public String getString(Clave clave) {
		Object valor = clavesValor.get(clave.getNombre());
		if(valor instanceof String) {
			return (String) valor;
		}
		throw new RuntimeException("La configuracion \""+clave+"\" no es de tipo String");
	}
	
	public boolean getBoolean(Clave clave) {
		Object valor = clavesValor.get(clave.getNombre());
		if(valor instanceof Boolean) {
			return (boolean) valor;
		}
		throw new RuntimeException("La configuracion \""+clave+"\" no es de tipo boolean");
	}
	
	public void set(Clave clave, Object valor) {
		if(clave.tipo.isInstance(valor)) {
			clavesValor.put(clave.getNombre(), valor);
		}else {
			throw new RuntimeException("La configuracion \""+clave+"\" debe ser de tipo "+clave.tipo.getName());
		}
	}
	
}
