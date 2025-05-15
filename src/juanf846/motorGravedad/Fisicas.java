package juanf846.motorGravedad;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import juanf846.motorGravedad.Configuracion.Clave;
import juanf846.motorGravedad.objetosConGravedad.Circulo;
import juanf846.motorGravedad.objetosConGravedad.ObjetoConGravedad;
import juanf846.motorGravedad.util.Vector2;

/**
 * Esta clase guarda una referencia a todos los objetos para calcular sus gravedades, movimiento y colisiones
 * @author juanf
 *
 */
public class Fisicas {
	private static final Logger LOG = Logger.getLogger(Fisicas.class.getName());
	
	private boolean activado;
	
	public List<ObjetoConGravedad> objetos;
	
	
	public void activar() {
		activado = true;
		LOG.info("Fisicas activadas");
	}
	public void desactivar() {
		activado = false;
		LOG.info("Fisicas desactivadas");
	}
	
	Fisicas(){
		objetos = new ArrayList<ObjetoConGravedad>();
		activado = true;
	}
	
	public void calcularFisicas() {
		if(activado) {
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
		            	calcularColisiones(objetos.get(i), objetos.get(j));
		            }
				}
			}
		}
		
	}
	
	public void calcularColisiones(ObjetoConGravedad obj1, ObjetoConGravedad obj2) {
		
		if(obj1 instanceof Circulo && obj2 instanceof Circulo) {
			Circulo c1 = (Circulo) obj1;
			Circulo c2 = (Circulo) obj2;

			if(c2.getMomento().getX()==0 && c2.getMomento().getY()==0){
				Circulo aux = c1;
				c1 = c2;
				c2 = aux;
			}

			Vector2 p1 = c1.getPosicion();
			Vector2 p2 = c2.getPosicion();

			Vector2 m1 = c1.getMomento();
			Vector2 m2 = c2.getMomento();
			
			
			if(p1.distancia(p2) <= c1.getRadio()+c2.getRadio()) {
				LOG.info("Colision detectada ");
				LOG.info("Distancia = "+p1.distancia(p2));


				Vector2 directionNewForceNormalized = p1.substract(p2).getNormalized();
				Vector2 directionOldForceNormalized = m2.getNormalized();
				if(!directionOldForceNormalized.isNaN()) {
					double distanceBeetweenForces = directionNewForceNormalized.distancia(directionOldForceNormalized);
					double newAmplitude = distanceBeetweenForces / 1.414213562373095048801688724209; //Raiz de 2
					newAmplitude = (newAmplitude - 1) * -1;
					LOG.info("newAmplitude = " + newAmplitude);
					newAmplitude *= m2.getAmplitude();

					Vector2 m2New = directionNewForceNormalized.addAmplitude(newAmplitude);
					c1.setMomento(m1.add(m2New));
					c2.setMomento(m2.add(m2New.negate()));
				}
/*
				Vector2 directionNewForceNormalized2 = p2.substract(p1).getNormalized();
				Vector2 directionOldForceNormalized2 = m1.getNormalized();
				if(!directionOldForceNormalized2.isNaN()) {
					double distanceBeetweenForces2 = directionNewForceNormalized2.distancia(directionOldForceNormalized2);
					double newAmplitude2 = distanceBeetweenForces2 / 1.414213562373095048801688724209; //Raiz de 2
					newAmplitude2 = (newAmplitude2 - 1) * -1;
					LOG.info("newAmplitude2 = " + newAmplitude2);
					newAmplitude2 *= m1.getAmplitude();

					Vector2 m1New = directionNewForceNormalized2.addAmplitude(newAmplitude2);
					c2.setMomento(m2.add(m1New));
					c1.setMomento(m1.add(m1New.negate()));
				}*/
			}
		}
	}
	
}
