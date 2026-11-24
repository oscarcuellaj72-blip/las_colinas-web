package com.las_colinas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
        System.getenv("LAS_COLINAS_DB_URL");

    private static final String USUARIO =
        System.getenv("LAS_COLINAS_DB_USER");

    private static final String PASSWORD =
        System.getenv("LAS_COLINAS_DB_PASSWORD");

    private ConexionBD() {
    }

    public static Connection obtenerConexion()
            throws SQLException {

        if (URL == null || URL.isBlank()
                || USUARIO == null || USUARIO.isBlank()
                || PASSWORD == null) {

            throw new SQLException(
                "Faltan variables de entorno de MySQL (LAS_COLINAS_DB_URL, LAS_COLINAS_DB_USER, LAS_COLINAS_DB_PASSWORD)."
            );
        }

        return DriverManager.getConnection(
            URL, USUARIO, PASSWORD
        );
    }
}


