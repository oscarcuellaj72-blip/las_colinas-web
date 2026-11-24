package com.las_colinas.config;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {

        System.out.println("Iniciando prueba de conexión...");

        try (Connection conexion = ConexionBD.obtenerConexion()) {

            if (conexion.isValid(5)) {
                System.out.println("CONEXIÓN EXITOSA A MYSQL");
                System.out.println("Base de datos: las_colinas");
            } else {
                System.out.println("La conexión no es válida.");
            }

        } catch (SQLException e) {
            System.err.println("ERROR DE CONEXIÓN: " + e.getMessage());
        }
    }
}