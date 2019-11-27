package juanf846.motorGravedad;

import java.util.ArrayList;
import java.util.List;

import juanf846.motorGravedad.Configuracion.Clave;
import juanf846.motorGravedad.objetosConGravedad.ObjetoConGravedad;

public class Fisicas {
	public List<ObjetoConGravedad> objetos;
	
	Fisicas(){
		objetos = new ArrayList<ObjetoConGravedad>();
	}
	
	public void calcularFisicas() {
		//Gravedad
		for (int i = 0; i < objetos.size(); i++){
            for (int j = i + 1; j < objetos.size(); j++){
            	objetos.get(i).calcularGravedad(objetos.get(j));
            }
		}
		//Movimiento
		int pasosFisicas = Configuracion.getInstance().getInt(Clave.PASOS_FISICAS);
		for(int j=0;j<pasosFisicas;j++) {
			for (int i = 0; i < objetos.size(); i++) {
				objetos.get(i).calcularMovimiento(
						objetos.toArray(new ObjetoConGravedad[objetos.size()]),pasosFisicas);
			}
		}
		//Colisiones (en desarrollo)
		if(Configuracion.getInstance().getBoolean(Clave.COLISIONES)) {
			for (int i = 0; i < objetos.size(); i++){
	            for (int j = i + 1; j < objetos.size(); j++){
	            	objetos.get(i).calcularColision(objetos.get(j));
	            }
			}
		}
		
	}
	
}
