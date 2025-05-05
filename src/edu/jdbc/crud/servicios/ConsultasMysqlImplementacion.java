package edu.jdbc.crud.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.jdbc.crud.dtos.LibroDto;
import edu.jdbc.crud.util.ADto;

/**
 * Implementación de la interfaz consultas para MySQL
 * @author phd - 05/05/2025
 */
public class ConsultasMysqlImplementacion implements ConsultasInterfaz {

    @Override
    public List<LibroDto> listarLibros(Connection conexion, List<LibroDto> listaLibrosObtenida, String isbnAConsultar) {

        ResultSet resultadoConsulta = null;
        ADto adto = new ADto();

        if (isbnAConsultar.isEmpty()) {
            try {
                Statement declaracionSQL = conexion.createStatement();
                String query = "SELECT * FROM gbp_alm_cat_libros";
                resultadoConsulta = declaracionSQL.executeQuery(query);

                adto.resultsALibrosDto(resultadoConsulta, listaLibrosObtenida);

                resultadoConsulta.close();
                declaracionSQL.close();

            } catch (SQLException e) {
                System.err.println("[ERROR-ConsultasMysqlImplementacion-listarLibros] Error al listar todos los libros: " + e);
            }
        } else {
            try {
                String query = "SELECT * FROM gbp_alm_cat_libros WHERE isbn = ?";
                PreparedStatement sentencia = conexion.prepareStatement(query);
                sentencia.setString(1, isbnAConsultar);
                resultadoConsulta = sentencia.executeQuery();

                adto.resultsALibrosDto(resultadoConsulta, listaLibrosObtenida);

                resultadoConsulta.close();
                sentencia.close();

            } catch (SQLException e) {
                System.err.println("[ERROR-ConsultasMysqlImplementacion-listarLibros] Error al consultar libro por ISBN: " + e);
            }
        }

        return listaLibrosObtenida;
    }
}
