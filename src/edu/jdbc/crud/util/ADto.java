package edu.jdbc.crud.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.jdbc.crud.dtos.LibroDto;

/**
 * Clase de utilidad que contiene los métodos de paso a DTO
 * @author phd - 05/05/2025
 */
public class ADto {

    /**
     * Método que pasa un ResultSet con libros a una lista de LibroDto
     * @param resultadoConsulta ResultSet de la consulta SQL
     * @param listaLibrosObtenida Lista donde se agregarán los libros obtenidos
     */
    public void resultsALibrosDto(ResultSet resultadoConsulta, List<LibroDto> listaLibrosObtenida) {

        try {
            while (resultadoConsulta.next()) {
            	listaLibrosObtenida.add(new LibroDto(
            		    resultadoConsulta.getLong("id_libro"),
            		    resultadoConsulta.getString("titulo"),
            		    resultadoConsulta.getString("autor"),
            		    resultadoConsulta.getString("isbn"),
            		    resultadoConsulta.getInt("edicion")
            		));
            }

            int i = listaLibrosObtenida.size();
            System.out.println("[INFO-ADto-resultsALibrosDto] Número de libros convertidos: " + i);

        } catch (SQLException e) {
            System.err.println("[ERROR-ADto-resultsALibrosDto] Error al convertir ResultSet a LibroDto: " + e);
        }
    }
}
