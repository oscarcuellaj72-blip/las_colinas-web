package com.las_colinas.dao;

import com.las_colinas.model.TipoUnidadProductiva;
import java.sql.SQLException;
import java.util.List;

public interface TipoUnidadProductivaDAO {
    void guardar(TipoUnidadProductiva tipo) throws SQLException;
    List<TipoUnidadProductiva> listar() throws SQLException;
    TipoUnidadProductiva buscarPorId(int id) throws SQLException;
    List<TipoUnidadProductiva> buscarPorNombre(String nombre) throws SQLException;
    void actualizar(TipoUnidadProductiva tipo) throws SQLException;
    void eliminar(int id) throws SQLException;
}

