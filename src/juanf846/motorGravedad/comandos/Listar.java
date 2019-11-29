package juanf846.motorGravedad.comandos;

import java.io.PrintStream;

import juanf846.javaSimpleCLI.annotations.Command;
import juanf846.javaSimpleCLI.annotations.Help;
import juanf846.javaSimpleCLI.annotations.Output;
import juanf846.javaSimpleCLI.annotations.Run;
import juanf846.motorGravedad.Main;
import juanf846.motorGravedad.objetosConGravedad.ObjetoConGravedad;

@Command(name = "list")
public class Listar {
	@Output
	private PrintStream out;
	
	@Run
	public void run(String[] args) {
		out.println("Objetos:");
		for(ObjetoConGravedad o : Main.getFisicas().objetos) {
			out.println(o);
		}
	}
	
	@Help
	public String getHelp() {
		return "Muestra todos los objetos en la simulacion";
	}
}
