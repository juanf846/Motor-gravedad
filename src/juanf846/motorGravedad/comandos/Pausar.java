package juanf846.motorGravedad.comandos;

import juanf846.javaSimpleCLI.annotations.Command;
import juanf846.javaSimpleCLI.annotations.Help;
import juanf846.javaSimpleCLI.annotations.Run;
import juanf846.motorGravedad.Main;

@Command(name = "pause")
public class Pausar {
	
	@Run
	public void run(String[] args) {
		Main.getFisicas().desactivar();
	}
	
	@Help
	public String getHelp() {
		return "Pausa la simulacion";
	}
	
}
