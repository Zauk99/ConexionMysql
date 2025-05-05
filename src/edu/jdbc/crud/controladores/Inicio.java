package edu.jdbc.crud.controladores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.jdbc.crud.dtos.LibroDto;
import edu.jdbc.crud.servicios.ConexionInterfaz;
import edu.jdbc.crud.servicios.ConexionMysqlImplementacion; // Cambiado a MySQL
import edu.jdbc.crud.servicios.ConsultasInterfaz;
import edu.jdbc.crud.servicios.ConsultasMysqlImplementacion; // Cambiado a MySQL
import edu.jdbc.crud.servicios.MenuImplementacion;
import edu.jdbc.crud.servicios.MenuInterfaz;

/**
 * Clase principal de la aplicación
 * @author phd - 05/05/2025
 */
public class Inicio {

	/**
	 * Método de acceso a la aplicación de consola
	 * @author phd - 05/05/2025
	 * @param args
	 */
	public static void main(String[] args) {
		
		ConexionInterfaz conexI = new ConexionMysqlImplementacion(); // Cambiado a MySQL
		MenuInterfaz mi = new MenuImplementacion();
		Scanner entradaTeclado = new Scanner(System.in);
		ConsultasInterfaz consulI = new ConsultasMysqlImplementacion(); // Cambiado a MySQL
		ArrayList<LibroDto> listaLibros = new ArrayList<>();

		Connection conexion = conexI.generaConexion();
		
		boolean cerrarMenu = false;
		String isbnConsultado = "";
		int opcionSeleccionadaUsuario;
		
		try {
			
			while(!cerrarMenu) {
				
				opcionSeleccionadaUsuario = mi.mostrarMenuYSeleccion(entradaTeclado);
				
				switch(opcionSeleccionadaUsuario) {
					case 0:
						System.out.println("[INFO-Main] Se cerrara la aplicacion");
						cerrarMenu = true;
						break;
					case 1:
						isbnConsultado = "";
						listaLibros.clear();
						System.out.println("[INFO-Main] LISTAR CATALOGO");						
						consulI.listarLibros(conexion, listaLibros, isbnConsultado);
						listarLibrosObtenidos(listaLibros);
						break;
					case 2:
						boolean continuarConsultando = false;
						do {
							isbnConsultado = "";
							listaLibros.clear();
							System.out.println("[INFO-Main] LISTAR UN LIBRO");
							consulI.listarLibros(conexion, listaLibros, isbnConsultado);
							listarLibrosObtenidos(listaLibros);
							listaLibros.clear();
							System.out.println("[INFO-Main] Indique el isbn del libro:");
							consulI.listarLibros(conexion, listaLibros, entradaTeclado.next());
							if(listaLibros.size()<1) {
								System.out.println("[WARNING-Main] El isbn elegido no existe.");
							}else {
								listarLibrosObtenidos(listaLibros);
							}
							System.out.println("[INFO-Main] ¿Quiere seguir consultando? 1 (si) 0 (no): ");
							if(entradaTeclado.nextInt()==0) {
								continuarConsultando = true;
							}
						}while(!continuarConsultando);
						break;
					default:
						System.out.println("[INFO-Main] No ha elegido una opcion existente");
						break;						
				}
				
			}
			
			conexI.cerrarConexion(conexion);
			
		} catch (Exception e) {
			System.err.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
			conexI.cerrarConexion(conexion);
		}
		
	}
	
	/**
	 * Método privado que lista por consola los libros obtenidos de las consultas
	 * de lectura
	 * @author phd - 05/05/2025
	 * @param listaLibrosObtenida
	 */
	private static void listarLibrosObtenidos(List<LibroDto> listaLibrosObtenida) {
		if(listaLibrosObtenida.size()>0) {
			for(int i=0;i<listaLibrosObtenida.size();i++) {
				System.out.println(listaLibrosObtenida.get(i).toString());
			}
		}else {
			System.out.println("[INFO-Main] No hay libros en el catálogo");
		}
	}

}
