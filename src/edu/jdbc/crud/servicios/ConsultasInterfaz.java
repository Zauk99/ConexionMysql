package edu.jdbc.crud.servicios;

import java.sql.Connection;
import java.util.List;

import edu.jdbc.crud.dtos.LibroDto;

/**
 * Interfaz que declara los métodos que realizan consultas
 * sobre base de datos
 * @author phd - 05/05/2025
 */
public interface ConsultasInterfaz {

	/**
	 * Método que lista el catálogo de libros o un solo libro
	 * @param conexion contiene la conexión a bd
	 * @param isbnAConsultar si está a null se listarán todos los libros, si no, el indicado
	 * @return devuelve un listado de tipos LibroDto
	 */
	public List<LibroDto> listarLibros(Connection conexion, List<LibroDto> listaLibrosObtenida, String isbnAConsultar);
	
}
