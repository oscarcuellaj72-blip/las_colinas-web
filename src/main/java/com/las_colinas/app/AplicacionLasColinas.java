package com.las_colinas.app;

import com.las_colinas.model.LoteProductivo;
import com.las_colinas.model.TipoUnidadProductiva;
import com.las_colinas.service.LoteProductivoService;
import com.las_colinas.service.TipoUnidadProductivaService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AplicacionLasColinas {

    private static final TipoUnidadProductivaService tipoService = new TipoUnidadProductivaService();
    private static final LoteProductivoService loteService = new LoteProductivoService();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            do {
                mostrarMenuPrincipal();
                opcion = leerEntero(scanner, "Seleccione una opción: ");

                try {
                    switch (opcion) {
                        case 1 -> menuTiposUnidad(scanner);
                        case 2 -> menuLotesProductivos(scanner);
                        case 0 -> System.out.println("Saliendo del sistema Las Colinas...");
                        default -> System.out.println("Opción no válida.");
                    }
                } catch (IllegalArgumentException | SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                System.out.println();
            } while (opcion != 0);
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("=== GRANJA LAS COLINAS - MÓDULOS CRUD (GA7-220501096-AA2-EV01) ===");
        System.out.println("1. Módulo Tipos de Unidad Productiva");
        System.out.println("2. Módulo Lotes Productivos");
        System.out.println("0. Salir");
    }

    // ==================== MÓDULO 1: TIPOS DE UNIDAD PRODUCTIVA ====================

    private static void menuTiposUnidad(Scanner scanner) throws SQLException {
        int opcion;
        do {
            System.out.println("--- MÓDULO: TIPOS DE UNIDAD PRODUCTIVA ---");
            System.out.println("1. Crear / Registrar tipo");
            System.out.println("2. Listar todos los tipos");
            System.out.println("3. Consultar tipo por ID");
            System.out.println("4. Actualizar tipo");
            System.out.println("5. Eliminar tipo");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {
                case 1 -> crearTipoUnidad(scanner);
                case 2 -> listarTiposUnidad();
                case 3 -> buscarTipoPorId(scanner);
                case 4 -> actualizarTipoUnidad(scanner);
                case 5 -> eliminarTipoUnidad(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private static void crearTipoUnidad(Scanner scanner) throws SQLException {
        System.out.print("Nombre del tipo de unidad productiva (ej. Avicultura, Piscicultura): ");
        String nombre = scanner.nextLine();

        TipoUnidadProductiva tipo = new TipoUnidadProductiva();
        tipo.setNombreTipoUnidadProductiva(nombre);

        tipoService.guardar(tipo);
        System.out.println("Tipo de unidad productiva registrado correctamente.");
    }

    private static void listarTiposUnidad() throws SQLException {
        List<TipoUnidadProductiva> tipos = tipoService.listar();
        if (tipos.isEmpty()) {
            System.out.println("No hay tipos de unidad productiva registrados.");
            return;
        }
        for (TipoUnidadProductiva t : tipos) {
            System.out.println(t);
        }
    }

    private static void buscarTipoPorId(Scanner scanner) throws SQLException {
        int id = leerEntero(scanner, "ID del tipo a consultar: ");
        TipoUnidadProductiva tipo = tipoService.buscarPorId(id);
        if (tipo == null) {
            System.out.println("No se encontró un tipo de unidad productiva con ID " + id + ".");
        } else {
            System.out.println(tipo);
        }
    }

    private static void actualizarTipoUnidad(Scanner scanner) throws SQLException {
        int id = leerEntero(scanner, "ID del tipo a actualizar: ");
        TipoUnidadProductiva existente = tipoService.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe un tipo de unidad productiva con ID " + id + ".");
            return;
        }

        System.out.print("Nuevo nombre (actual: " + existente.getNombreTipoUnidadProductiva() + "): ");
        String nombre = scanner.nextLine();

        existente.setNombreTipoUnidadProductiva(nombre);
        tipoService.actualizar(existente);
        System.out.println("Tipo de unidad productiva actualizado correctamente.");
    }

    private static void eliminarTipoUnidad(Scanner scanner) throws SQLException {
        int id = leerEntero(scanner, "ID del tipo a eliminar: ");
        tipoService.eliminar(id);
        System.out.println("Tipo de unidad productiva eliminado correctamente.");
    }

    // ==================== MÓDULO 2: LOTES PRODUCTIVOS ====================

    private static void menuLotesProductivos(Scanner scanner) throws SQLException {
        int opcion;
        do {
            System.out.println("--- MÓDULO: LOTES PRODUCTIVOS ---");
            System.out.println("1. Crear / Registrar lote");
            System.out.println("2. Listar todos los lotes");
            System.out.println("3. Consultar lote por ID");
            System.out.println("4. Actualizar lote");
            System.out.println("5. Eliminar lote");
            System.out.println("0. Volver al menú principal");
            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {
                case 1 -> crearLoteProductivo(scanner);
                case 2 -> listarLotesProductivos();
                case 3 -> buscarLotePorId(scanner);
                case 4 -> actualizarLoteProductivo(scanner);
                case 5 -> eliminarLote(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private static void crearLoteProductivo(Scanner scanner) throws SQLException {
        System.out.print("Código del lote: ");
        String codigoLote = scanner.nextLine();
        System.out.print("Especie: ");
        String especie = scanner.nextLine();
        int cantidadInicial = leerEntero(scanner, "Cantidad inicial: ");
        Date fechaIngreso = leerFecha(scanner, "Fecha de ingreso (YYYY-MM-DD) o Enter para hoy: ");
        int idUnidadProductiva = leerEntero(scanner, "ID de la Unidad Productiva: ");

        LoteProductivo lote = new LoteProductivo();
        lote.setCodigoLote(codigoLote);
        lote.setEspecie(especie);
        lote.setCantidadInicial(cantidadInicial);
        lote.setFechaIngreso(fechaIngreso);
        lote.setIdUnidadProductiva(idUnidadProductiva);

        loteService.guardar(lote);
        System.out.println("Lote productivo registrado correctamente.");
    }

    private static void listarLotesProductivos() throws SQLException {
        List<LoteProductivo> lotes = loteService.listar();
        if (lotes.isEmpty()) {
            System.out.println("No hay lotes productivos registrados.");
            return;
        }
        for (LoteProductivo l : lotes) {
            System.out.println(l);
        }
    }

    private static void buscarLotePorId(Scanner scanner) throws SQLException {
        int id = leerEntero(scanner, "ID del lote a consultar: ");
        LoteProductivo lote = loteService.buscarPorId(id);
        if (lote == null) {
            System.out.println("No se encontró un lote productivo con ID " + id + ".");
        } else {
            System.out.println(lote);
        }
    }

    private static void actualizarLoteProductivo(Scanner scanner) throws SQLException {
        int id = leerEntero(scanner, "ID del lote a actualizar: ");
        LoteProductivo existente = loteService.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe un lote productivo con ID " + id + ".");
            return;
        }

        System.out.print("Nuevo código del lote (actual: " + existente.getCodigoLote() + "): ");
        String codigoLote = scanner.nextLine();
        System.out.print("Nueva especie (actual: " + existente.getEspecie() + "): ");
        String especie = scanner.nextLine();
        int cantidadInicial = leerEntero(scanner, "Nueva cantidad inicial (actual: " + existente.getCantidadInicial() + "): ");
        Date fechaIngreso = leerFecha(scanner, "Nueva fecha de ingreso YYYY-MM-DD o Enter para mantener (" + existente.getFechaIngreso() + "): ", existente.getFechaIngreso());
        int idUnidadProductiva = leerEntero(scanner, "Nuevo ID Unidad Productiva (actual: " + existente.getIdUnidadProductiva() + "): ");

        existente.setCodigoLote(codigoLote);
        existente.setEspecie(especie);
        existente.setCantidadInicial(cantidadInicial);
        existente.setFechaIngreso(fechaIngreso);
        existente.setIdUnidadProductiva(idUnidadProductiva);

        loteService.actualizar(existente);
        System.out.println("Lote productivo actualizado correctamente.");
    }

    private static void eliminarLote(Scanner scanner) throws SQLException {
        int id = leerEntero(scanner, "ID del lote a eliminar: ");
        loteService.eliminar(id);
        System.out.println("Lote productivo eliminado correctamente.");
    }

    // ==================== UTILIDADES DE ENTRADA ====================

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static Date leerFecha(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) {
                return Date.valueOf(LocalDate.now());
            }
            try {
                return Date.valueOf(linea);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }

    private static Date leerFecha(Scanner scanner, String mensaje, Date valorPorDefecto) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) {
                return valorPorDefecto;
            }
            try {
                return Date.valueOf(linea);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }
}
