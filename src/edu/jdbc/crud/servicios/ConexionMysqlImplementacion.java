package edu.jdbc.crud.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implementación de la interfaz de conexion para MySQL
 * @author phd - 05/05/2025
 */
public class ConexionMysqlImplementacion implements ConexionInterfaz {

    @Override
    public Connection generaConexion() {

        System.out.println("[INFO-ConexionMysqlImplementacion-generaConexion] CONEXION MYSQL");
        Connection conexion = null;
        String[] parametrosConexion = configuracionConexion(); // url, user, pass

        if (!parametrosConexion[2].isEmpty()) {
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión
                conexion = DriverManager.getConnection(parametrosConexion[0], parametrosConexion[1], parametrosConexion[2]);
                boolean esValida = conexion.isValid(5000);
                if (!esValida) {
                    conexion = null;
                    System.err.println("[ERROR-ConexionMysqlImplementacion-generaConexion] Conexion no valida");
                } else {
                    System.out.println("[INFO-ConexionMysqlImplementacion-generaConexion] Conexion valida: " + parametrosConexion[0]);
                }

            } catch (ClassNotFoundException cnfe) {
                System.err.println("[ERROR-ConexionMysqlImplementacion-generaConexion] Error en registro driver: " + cnfe);
                conexion = null;
            } catch (SQLException sqle) {
                System.err.println("[ERROR-ConexionMysqlImplementacion-generaConexion] Error en conexión (" + parametrosConexion[0] + "): " + sqle);
                conexion = null;
            }
        } else {
            System.out.println("[ERROR-ConexionMysqlImplementacion-generaConexion] Los parametros de conexion no se han establecido correctamente");
            conexion = null;
        }

        return conexion;
    }

    /**
     * Método que configura los parámetros de conexión desde el archivo de propiedades de MySQL
     * @return vector de String con: url, user, pass
     * @author phd - 05/05/2025
     */
    private String[] configuracionConexion() {

        String user = "", pass = "", port = "", host = "", db = "", url = "";
        String[] stringConfiguracion = {"", "", ""};

        Properties propiedadesConexion = new Properties();
        try {
            propiedadesConexion.load(new FileInputStream(new File("/home/pheruez/Programación/edu.jdbc.crudEjemplo/src/edu/jdbc/crud/util//conexion_mysql.properties")));
            user = propiedadesConexion.getProperty("user");
            pass = propiedadesConexion.getProperty("pass");
            port = propiedadesConexion.getProperty("port");
            host = propiedadesConexion.getProperty("host");
            db = propiedadesConexion.getProperty("db");

            // Formato de URL para MySQL
            url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

            stringConfiguracion[0] = url;
            stringConfiguracion[1] = user;
            stringConfiguracion[2] = pass;

        } catch (FileNotFoundException e) {
            System.err.println("[ERROR-ConexionMysqlImplementacion-configuracionConexion] - Archivo de propiedades no encontrado: " + e);
        } catch (IOException e) {
            System.err.println("[ERROR-ConexionMysqlImplementacion-configuracionConexion] - Error al leer el archivo de propiedades: " + e);
        }

        return stringConfiguracion;
    }

    @Override
    public void cerrarConexion(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println("[ERROR-ConexionMysqlImplementacion-cerrarConexion] - La conexión a bd no se ha cerrado correctamente: " + e);
        }
    }
}
