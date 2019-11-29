package juanf846.motorGravedad.comandos;

import java.io.PrintStream;
import java.util.Scanner;

import juanf846.javaSimpleCLI.annotations.Command;
import juanf846.javaSimpleCLI.annotations.Help;
import juanf846.javaSimpleCLI.annotations.Input;
import juanf846.javaSimpleCLI.annotations.Output;
import juanf846.javaSimpleCLI.annotations.Run;
import juanf846.motorGravedad.Configuracion.Clave;

@Command(name = "config")
public class Configuracion {
	
	@Output
	private PrintStream out;
	
	@Run
	public void run(String[] args) {
		if(args.length==2) {
			Clave clave = Clave.getByNombre(args[0]);
			if(clave!=null) {
				String valor = args[1];
				if(clave.getTipo().equals(Integer.class)) {
					juanf846.motorGravedad.Configuracion.getInstance().set(clave, Integer.valueOf(valor));
				}else if(clave.getTipo().equals(Boolean.class)) {
					juanf846.motorGravedad.Configuracion.getInstance().set(clave, Boolean.valueOf(valor));
				}else if(clave.getTipo().equals(Float.class)) {
					juanf846.motorGravedad.Configuracion.getInstance().set(clave, Float.valueOf(valor));
				}else if(clave.getTipo().equals(String.class)) {
					juanf846.motorGravedad.Configuracion.getInstance().set(clave, String.valueOf(valor));
				}else {
					throw new ClassCastException("java.lang.String cannot be cast to "+clave.getTipo().getName());
				}
				out.println("Configuración cambiada correctamente");
			}else {
				out.println(args[0]+" no es una configuración valida");
			}
		}else {
			out.println(help());
		}
	}
	
	@Help
	public String help() {
		String help = "Este comando cambia una configuracion del programa\n"
				+ "Uso: config clave valor\n"
				+ "clave\t\tnombre de la propiedad a cambiar\n"
				+ "valor\t\tnuevo valor\n"
				+ "Las posibles configuraciones son: \n";
		for(Clave c : Clave.values()) {
			help += c.getNombre() + "\n";
		}
		return help;
	}
	
}
