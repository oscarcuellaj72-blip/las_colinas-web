package com.las_colinas.service;

import com.las_colinas.dao.LoteProductivoDAO;
import com.las_colinas.dao.impl.LoteProductivoDAOImpl;
import com.las_colinas.model.LoteProductivo;

import java.sql.SQLException;
import java.util.List;

public class LoteProductivoService {

    private final LoteProductivoDAO loteDAO;

    public LoteProductivoService() {
        this.loteDAO = new LoteProductivoDAOImpl();
    }

    public LoteProductivoService(LoteProductivoDAO loteDAO) {
        this.loteDAO = loteDAO;
    }

    public void guardar(LoteProductivo lote) throws SQLException {
        validarLote(lote, false);
        loteDAO.guardar(lote);
    }

    public List<LoteProductivo> listar() throws SQLException {
        return loteDAO.listar();
    }

    public LoteProductivo buscarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del lote productivo debe ser mayor que cero.");
        }
        return loteDAO.buscarPorId(id);
    }

    public List<LoteProductivo> buscarPorCodigo(String codigo) throws SQLException {
        if (codigo == null || codigo.isBlank()) {
            return listar();
        }
        return loteDAO.buscarPorCodigo(codigo.trim());
    }

    public void actualizar(LoteProductivo lote) throws SQLException {
        validarLote(lote, true);
        loteDAO.actualizar(lote);
    }

    public void eliminar(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del lote productivo debe ser mayor que cero.");
        }
        loteDAO.eliminar(id);
    }

    private void validarLote(LoteProductivo lote, boolean requiereId) {
        if (lote == null) {
            throw new IllegalArgumentException("Los datos del lote productivo no pueden ser nulos.");
        }
        if (requiereId && lote.getIdLoteProductivo() <= 0) {
            throw new IllegalArgumentException("El ID del lote productivo es obligatorio para actualizar.");
        }
        if (lote.getCodigoLote() == null || lote.getCodigoLote().isBlank()) {
            throw new IllegalArgumentException("El código del lote productivo es obligatorio.");
        }
        if (lote.getEspecie() == null || lote.getEspecie().isBlank()) {
            throw new IllegalArgumentException("La especie del lote productivo es obligatoria.");
        }
        if (lote.getCantidadInicial() <= 0) {
            throw new IllegalArgumentException("La cantidad inicial debe ser mayor que cero.");
        }
        if (lote.getFechaIngreso() == null) {
            throw new IllegalArgumentException("La fecha de ingreso es obligatoria.");
        }
        if (lote.getIdUnidadProductiva() <= 0) {
            throw new IllegalArgumentException("El ID de la unidad productiva es obligatorio.");
        }

        lote.setCodigoLote(lote.getCodigoLote().trim());
        lote.setEspecie(lote.getEspecie().trim());
    }
}
