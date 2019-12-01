package juanf846.motorGravedad;

import java.awt.Color;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import juanf846.javaSimpleCLI.Shell;
import juanf846.motorGravedad.Configuracion.Clave;
import juanf846.motorGravedad.objetosConGravedad.Circulo;
import juanf846.motorGravedad.util.Vector2;

public class Main{	
	private static Logger LOG;
	private static Logger LOG_ROOT;
	private static Ventana ventana;
	private static Fisicas fisicas;
	private static Shell consola;
	
	
	public static int frame = 0;
	public static long ultimoFrame = 0;
	public static long frameRate = 0;

	public static Ventana getVentana() {
		return ventana;
	}
	public static Fisicas getFisicas() {
		return fisicas;
	}
	
	
	private static void inicializarConsola() {
		consola.addCommand(new juanf846.motorGravedad.comandos.Configuracion());
		consola.addCommand(new juanf846.motorGravedad.comandos.Agregar());
		consola.addCommand(new juanf846.motorGravedad.comandos.Play());
		consola.addCommand(new juanf846.motorGravedad.comandos.Pausar());
		consola.addCommand(new juanf846.motorGravedad.comandos.Listar());
		consola.addCommand(new juanf846.motorGravedad.comandos.Eliminar());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				consola.run();
			}
		}).start();
	}
	
	private static void inicializarLog() {
		LOG_ROOT = Logger.getLogger("juanf846.motorGravedad");
		StreamHandler streamHandler = new StreamHandler(System.out,new SimpleFormatter()) {
			@Override
			public synchronized void publish(LogRecord record) {
				super.publish(record);
				flush();
			}
		};

		streamHandler.setLevel(Level.ALL);
		LOG_ROOT.setUseParentHandlers(false);
		LOG_ROOT.addHandler(streamHandler);
		
		LOG = Logger.getLogger(Main.class.getName());
	}
	
	@SuppressWarnings("unused")
	private static void cargarObjetosDebug() {
		System.out.println("Objetos generados: ");
		Random ran = new Random(20);
		for(int i=0;i<3;i++) {
			Circulo c = new Circulo(ran.nextInt(100)+100, ran.nextInt(100)+100, ran.nextInt(50)+3);
			c.setColor(Color.BLUE);
			if(i==0) {
				c.agregarFuerza(new Vector2(-5,0));
			}
			LOG.info("Objeto["+i+"] pos: "+c.getPosicion()+" masa: "+c.getMasa());
			fisicas.objetos.add(c);
		}
	}
	
	public static void main(String[] args) {
		fisicas = new Fisicas();
		ventana = new Ventana();
		consola = new Shell();
		
		inicializarLog();
		cargarObjetosDebug();
		inicializarConsola();
		
		try {
			while(true) {
				//tiempo que tardó el ultimo frame (inicio)
				long tiempo = new Date().getTime();
				//calculo de gravedad
				fisicas.calcularFisicas();
				//renderizado(sincronizado)
				ventana.paintImmediately(0,0,ventana.getWidth(),ventana.getHeight());
				//calculo de frameRate
				frame++;
				ejecutarCadaSegundo();
				//tiempo que tardó el ultimo frame (fin)
				ultimoFrame = new Date().getTime()-tiempo;
				//sleep
				long sleep = (1000/Configuracion.getInstance().getInt(Clave.MAX_FRAMES))
						- ultimoFrame-1;
				//System.out.println(sleep);
				if(sleep>0)Thread.sleep(sleep);
				
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static long ultimoSegundo = 0;
	public static void ejecutarCadaSegundo() {
		long tiempo = new Date().getTime();
		if(tiempo>=ultimoSegundo+1000) {
			LOG.fine("segundo: "+tiempo+" / "+(ultimoSegundo));
			Circulo.cuerposMuyCerca=0;
			frameRate = frame+1;
			frame = 0;
			ultimoSegundo=tiempo;
		}
	}

}

