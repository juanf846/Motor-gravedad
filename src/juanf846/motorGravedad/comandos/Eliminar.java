package juanf846.motorGravedad.comandos;

import java.io.PrintStream;

import juanf846.javaSimpleCLI.annotations.Command;
import juanf846.javaSimpleCLI.annotations.Help;
import juanf846.javaSimpleCLI.annotations.Output;
import juanf846.javaSimpleCLI.annotations.Run;
import juanf846.motorGravedad.Main;
import juanf846.motorGravedad.objetosConGravedad.ObjetoConGravedad;

@Command(name = "delete")
public class Eliminar {
	
	@Output
	private PrintStream out;
	
	@Run
	public void run(String[] args) {
		if(args.length!=1) {
			out.println(getHelp());
			return;
		}
		try {
			int id = Integer.parseInt(args[0]);
			for(ObjetoConGravedad o : Main.getFisicas().objetos) {
				if(o.getId()==id) {
					Main.getFisicas().objetos.remove(o);
					out.println("Eliminado correctamente");
					return;
				}
			}
			throw new NumberFormatException();
		}catch(NumberFormatException e) {
			out.println("El ID no es valido");
		}
	}
	
	@Help
	public String getHelp() {
		return "Elimina un objeto\n"
				+ "Uso: delete id\n"
				+ "id\t\tid del objeto a eliminar (se puede ver usando el comando list)";
	}
}
