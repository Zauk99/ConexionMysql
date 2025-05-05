package edu.jdbc.crud.servicios;

import java.sql.Connection;

/**
 * Interfaz que declara los métodos relacionados con la generación
 * de conexión a base de datos
 * @author phd - 05/05/2025
 */
public interface ConexionInterfaz {

	/**
	 * Método que genera la conexión a partir de la configuración 
	 * guardada en conexion_{sgbd}.properties
	 * @author phd - 05/05/2025
	 * @return objeto connection con la conexión a bd abierta
	 */
	public Connection generaConexion();
	
	/**
	 * Método para cerrar la conexión
	 * @author phd - 05/05/2025
	 */
	public void cerrarConexion(Connection conexion);
	
}
