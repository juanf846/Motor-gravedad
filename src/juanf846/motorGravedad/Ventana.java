package juanf846.motorGravedad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juanf846.motorGravedad.Configuracion.Clave;
import juanf846.motorGravedad.objetosConGravedad.Circulo;
import juanf846.motorGravedad.util.Vector2;

/**
 * Ventana principal del programa
 * @author juanf846
 *
 */
@SuppressWarnings("serial")
public class Ventana extends JPanel implements KeyListener{
	
	private static final int RADIO_CIRCULO = 5;

	private float zoom = 2;
	private int desplazamientoX = 0;
	private int desplazamientoY = 0;
	
	private Vector2[] particulasTrayectorias;
	private int particulasTrayectoriasIndex; 
	
	Ventana() {
		JFrame f = new JFrame();
		f.setName("Sistema de gravedad");
		f.setBounds(100, 100, 500, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.addKeyListener(this);
		
		particulasTrayectorias = new Vector2[200000];
		particulasTrayectoriasIndex = 0;
		
	}
	
	@Override
	public void paint(Graphics g) {
		int particulasDisponibles = 0;
		super.paint(g);
		boolean trayectorias = Configuracion.getInstance().getBoolean(Clave.TRAYECTORIAS);
		if(trayectorias) {
			g.setColor(Color.BLACK);
			for(int i=0;i<particulasTrayectorias.length;i++) {
				if(particulasTrayectorias[i]!=null) {
					int particulaX = (int)(particulasTrayectorias[i].x*zoom+desplazamientoX+(RADIO_CIRCULO*zoom)/2);
					int particulaY = (int)(particulasTrayectorias[i].y*zoom+desplazamientoY+(RADIO_CIRCULO*zoom)/2);
					g.drawLine(particulaX, particulaY, particulaX, particulaY); 
				}else {
					particulasDisponibles++;
				}
			}
		}
		Fisicas fisicas = Main.getFisicas();
		for(int i=0;i<fisicas.objetos.size();i++) {
			g.setColor(fisicas.objetos.get(i).getColor());
			g.fillOval((int)(fisicas.objetos.get(i).getPosicion().x*zoom)+desplazamientoX
					,(int)(fisicas.objetos.get(i).getPosicion().y*zoom)+desplazamientoY
					,(int)(RADIO_CIRCULO*zoom), (int)(RADIO_CIRCULO*zoom));
			if(trayectorias) {
				particulasTrayectorias[particulasTrayectoriasIndex]=(Vector2) fisicas.objetos.get(i).getPosicion().clone();
				particulasTrayectoriasIndex++;
				if(particulasTrayectoriasIndex>=particulasTrayectorias.length)particulasTrayectoriasIndex=0;
			}
		}
		//DEBUG
		g.setColor(Color.BLACK);
		g.drawString("Ultimo frame: "+Main.ultimoFrame+" ms", 20, 50);
		g.drawString("FPS: "+Main.frameRate, 20, 70);
		g.drawString("Cuerpos muy cerca: "+Circulo.cuerposMuyCerca, 20, 90);
		g.drawString("Particulas disponibles: "+particulasDisponibles, 20, 110);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private static final int MOVIMIENTO_DESPLAZAMIENTO = 10;
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) desplazamientoY+=MOVIMIENTO_DESPLAZAMIENTO;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) desplazamientoY-=MOVIMIENTO_DESPLAZAMIENTO;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) desplazamientoX-=MOVIMIENTO_DESPLAZAMIENTO;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) desplazamientoX+=MOVIMIENTO_DESPLAZAMIENTO;
		if(e.getKeyCode()==KeyEvent.VK_PLUS) {
			zoom*=2;		
			desplazamientoX*=2;
			desplazamientoY*=2;
		}
		if(e.getKeyCode()==KeyEvent.VK_MINUS) {
			zoom/=2;			
			desplazamientoX/=2;
			desplazamientoY/=2;
		}
		if(e.getKeyCode()==KeyEvent.VK_R) {
			desplazamientoX=0;
			desplazamientoY=0;
			zoom = 2;
		}	
		if(e.getKeyCode()==KeyEvent.VK_T) {
			if(Configuracion.getInstance().getBoolean(Clave.TRAYECTORIAS))
				Configuracion.getInstance().set(Clave.TRAYECTORIAS, false);
			else 
				Configuracion.getInstance().set(Clave.TRAYECTORIAS, true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}
	
}
