package com.las_colinas.dao.impl;

import com.las_colinas.config.ConexionBD;
import com.las_colinas.dao.TipoUnidadProductivaDAO;
import com.las_colinas.model.TipoUnidadProductiva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoUnidadProductivaDAOImpl implements TipoUnidadProductivaDAO {

    private static final String SQL_INSERT =
            "INSERT INTO tipos_unidad_productiva (nombre_tipo_unidad_productiva) VALUES (?)";

    private static final String SQL_SELECT_ALL =
            "SELECT id_tipo_unidad_productiva, nombre_tipo_unidad_productiva " +
            "FROM tipos_unidad_productiva ORDER BY id_tipo_unidad_productiva";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id_tipo_unidad_productiva, nombre_tipo_unidad_productiva " +
            "FROM tipos_unidad_productiva WHERE id_tipo_unidad_productiva = ?";

    private static final String SQL_SELECT_BY_NOMBRE =
            "SELECT id_tipo_unidad_productiva, nombre_tipo_unidad_productiva " +
            "FROM tipos_unidad_productiva WHERE nombre_tipo_unidad_productiva LIKE ? " +
            "ORDER BY id_tipo_unidad_productiva";

    private static final String SQL_UPDATE =
            "UPDATE tipos_unidad_productiva SET nombre_tipo_unidad_productiva = ? " +
            "WHERE id_tipo_unidad_productiva = ?";

    private static final String SQL_DELETE =
            "DELETE FROM tipos_unidad_productiva WHERE id_tipo_unidad_productiva = ?";

    @Override
    public void guardar(TipoUnidadProductiva tipo) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
            ps.setString(1, tipo.getNombreTipoUnidadProductiva());
            ps.executeUpdate();
        }
    }

    @Override
    public List<TipoUnidadProductiva> listar() throws SQLException {
        List<TipoUnidadProductiva> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearTipo(rs));
            }
        }
        return lista;
    }

    @Override
    public TipoUnidadProductiva buscarPorId(int id) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearTipo(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<TipoUnidadProductiva> buscarPorNombre(String nombre) throws SQLException {
        List<TipoUnidadProductiva> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_NOMBRE)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTipo(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public void actualizar(TipoUnidadProductiva tipo) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, tipo.getNombreTipoUnidadProductiva());
            ps.setInt(2, tipo.getIdTipoUnidadProductiva());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private TipoUnidadProductiva mapearTipo(ResultSet rs) throws SQLException {
        TipoUnidadProductiva tipo = new TipoUnidadProductiva();
        tipo.setIdTipoUnidadProductiva(rs.getInt("id_tipo_unidad_productiva"));
        tipo.setNombreTipoUnidadProductiva(rs.getString("nombre_tipo_unidad_productiva"));
        return tipo;
    }
}
