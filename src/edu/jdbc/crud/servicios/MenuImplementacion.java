package edu.jdbc.crud.servicios;

import java.util.Scanner;

/**
 * Implementación de la interfaz de menú
 * @author phd - 05/05/2025
 */
public class MenuImplementacion implements MenuInterfaz {

	@Override
	public int mostrarMenuYSeleccion(Scanner entradaTeclado) {
		
		int seleccionUsuario;
		
		System.out.println("#####################");
		System.out.println("0. Cerrar aplicacion");
		System.out.println("1. Listado libros");
		System.out.println("2. Informacion libro");
		System.out.println("#####################");
		System.out.println("Elija una opcion: ");
		
		seleccionUsuario = entradaTeclado.nextInt();
		
		return seleccionUsuario;
	}

}
