package juanf846.motorGravedad.comandos;

import java.io.PrintStream;

import juanf846.javaSimpleCLI.annotations.Command;
import juanf846.javaSimpleCLI.annotations.Help;
import juanf846.javaSimpleCLI.annotations.Output;
import juanf846.javaSimpleCLI.annotations.Run;
import juanf846.motorGravedad.Fisicas;
import juanf846.motorGravedad.Main;
import juanf846.motorGravedad.objetosConGravedad.Circulo;
import juanf846.motorGravedad.objetosConGravedad.ObjetoConGravedad;

/**
 * 
 * Uso: add tipo x y masa
 * @author juanf846
 *
 */
@Command(name = "add")
public class Agregar {
	
	@Output
	private PrintStream out;
	
	@Run
	public void run(String[] args) {
		if(args.length != 4) {
			out.println(getHelp());
			return;
		}
		ObjetoConGravedad nuevo;
		double x;
		double y;
		double masa;
		
		try {
			x = Double.valueOf(args[1]);
		}catch(NumberFormatException e) {
			out.println("Error: Parametro x no valido");
			return;
		}
		try {
			y = Double.valueOf(args[2]);
		}catch(NumberFormatException e) {
			out.println("Error: Parametro y no valido");
			return;
		}
		try {
			masa = Double.valueOf(args[3]);
		}catch(NumberFormatException e) {
			out.println("Error: Parametro masa no valido");
			return;
		}
		switch(args[0]) {
			case "circulo":
				nuevo = new Circulo(x, y, masa);
				break;
			default:
				out.println("Error: Tipo no valido");
				return;
		}
		Main.getFisicas().objetos.add(nuevo);
		
	}
	
	@Help
	public String getHelp() {
		return "Uso: add tipo x y masa\n"
				+ "tipo\t\tTipo de objeto a crear, puede ser: circulo.\n"
				+ "x\t\tPosicion X del objeto\n"
				+ "y\t\tPosicion Y del objeto\n"
				+ "masa\t\tMasa del objeto";
	}
	
}
