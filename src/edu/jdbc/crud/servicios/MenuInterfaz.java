package edu.jdbc.crud.servicios;

import java.util.Scanner;

/**
 * Interfaz que declara los métodos relacionados con el menú
 * @author phd - 05/05/2025
 */
public interface MenuInterfaz {

	/**
	 * Método que muestra el menú y solicita la acción
	 * @param entradaTeclado
	 * @return entero, opción seleccionada por el usuario
	 */
	public int mostrarMenuYSeleccion(Scanner entradaTeclado);
	
}
