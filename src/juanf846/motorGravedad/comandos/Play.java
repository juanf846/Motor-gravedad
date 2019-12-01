package juanf846.motorGravedad.comandos;

import juanf846.javaSimpleCLI.annotations.Command;
import juanf846.javaSimpleCLI.annotations.Help;
import juanf846.javaSimpleCLI.annotations.Run;
import juanf846.motorGravedad.Main;

@Command(name = "play")
public class Play {
	
	@Run
	public void run(String[] args) {
		Main.getFisicas().activar();
	}
	
	@Help
	public String getHelp() {
		return "Reanuda la simulacion";
	}
	
}
