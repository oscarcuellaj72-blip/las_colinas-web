package com.las_colinas.dao;

import com.las_colinas.model.LoteProductivo;
import java.sql.SQLException;
import java.util.List;

public interface LoteProductivoDAO {
    void guardar(LoteProductivo lote) throws SQLException;
    List<LoteProductivo> listar() throws SQLException;
    LoteProductivo buscarPorId(int id) throws SQLException;
    List<LoteProductivo> buscarPorCodigo(String codigo) throws SQLException;
    void actualizar(LoteProductivo lote) throws SQLException;
    void eliminar(int id) throws SQLException;
}


