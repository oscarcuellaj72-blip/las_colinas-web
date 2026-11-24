package com.las_colinas.service;

import com.las_colinas.dao.TipoUnidadProductivaDAO;
import com.las_colinas.dao.impl.TipoUnidadProductivaDAOImpl;
import com.las_colinas.model.TipoUnidadProductiva;

import java.sql.SQLException;
import java.util.List;

public class TipoUnidadProductivaService {

    private final TipoUnidadProductivaDAO tipoDAO;

    public TipoUnidadProductivaService() {
        this.tipoDAO = new TipoUnidadProductivaDAOImpl();
    }

    public TipoUnidadProductivaService(TipoUnidadProductivaDAO tipoDAO) {
        this.tipoDAO = tipoDAO;
    }

    public void guardar(TipoUnidadProductiva tipo) throws SQLException {
        validarTipo(tipo, false);
        tipoDAO.guardar(tipo);
    }

    public List<TipoUnidadProductiva> listar() throws SQLException {
        return tipoDAO.listar();
    }

    public TipoUnidadProductiva buscarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del tipo de unidad productiva debe ser mayor que cero.");
        }
        return tipoDAO.buscarPorId(id);
    }

    public List<TipoUnidadProductiva> buscarPorNombre(String nombre) throws SQLException {
        if (nombre == null || nombre.isBlank()) {
            return listar();
        }
        return tipoDAO.buscarPorNombre(nombre.trim());
    }

    public void actualizar(TipoUnidadProductiva tipo) throws SQLException {
        validarTipo(tipo, true);
        tipoDAO.actualizar(tipo);
    }

    public void eliminar(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del tipo de unidad productiva debe ser mayor que cero.");
        }
        tipoDAO.eliminar(id);
    }

    private void validarTipo(TipoUnidadProductiva tipo, boolean requiereId) {
        if (tipo == null) {
            throw new IllegalArgumentException("Los datos del tipo de unidad productiva no pueden ser nulos.");
        }
        if (requiereId && tipo.getIdTipoUnidadProductiva() <= 0) {
            throw new IllegalArgumentException("El ID del tipo de unidad productiva es obligatorio para actualizar.");
        }
        if (tipo.getNombreTipoUnidadProductiva() == null || tipo.getNombreTipoUnidadProductiva().isBlank()) {
            throw new IllegalArgumentException("El nombre del tipo de unidad productiva es obligatorio.");
        }
        tipo.setNombreTipoUnidadProductiva(tipo.getNombreTipoUnidadProductiva().trim());
    }
}
