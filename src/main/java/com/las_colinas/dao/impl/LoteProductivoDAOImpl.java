package com.las_colinas.dao.impl;

import com.las_colinas.config.ConexionBD;
import com.las_colinas.dao.LoteProductivoDAO;
import com.las_colinas.model.LoteProductivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoteProductivoDAOImpl implements LoteProductivoDAO {

    private static final String SQL_INSERT =
            "INSERT INTO lotes_productivos " +
            "(codigo_lote, especie, cantidad_inicial, fecha_ingreso, id_unidad_productiva) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL =
            "SELECT id_lote_productivo, codigo_lote, especie, cantidad_inicial, " +
            "fecha_ingreso, id_unidad_productiva " +
            "FROM lotes_productivos ORDER BY id_lote_productivo";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id_lote_productivo, codigo_lote, especie, cantidad_inicial, " +
            "fecha_ingreso, id_unidad_productiva " +
            "FROM lotes_productivos WHERE id_lote_productivo = ?";

    private static final String SQL_SELECT_BY_CODIGO =
            "SELECT id_lote_productivo, codigo_lote, especie, cantidad_inicial, " +
            "fecha_ingreso, id_unidad_productiva " +
            "FROM lotes_productivos WHERE codigo_lote LIKE ? ORDER BY id_lote_productivo";

    private static final String SQL_UPDATE =
            "UPDATE lotes_productivos SET codigo_lote = ?, especie = ?, " +
            "cantidad_inicial = ?, fecha_ingreso = ?, id_unidad_productiva = ? " +
            "WHERE id_lote_productivo = ?";

    private static final String SQL_DELETE =
            "DELETE FROM lotes_productivos WHERE id_lote_productivo = ?";

    @Override
    public void guardar(LoteProductivo lote) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
            ps.setString(1, lote.getCodigoLote());
            ps.setString(2, lote.getEspecie());
            ps.setInt(3, lote.getCantidadInicial());
            ps.setDate(4, lote.getFechaIngreso());
            ps.setInt(5, lote.getIdUnidadProductiva());
            ps.executeUpdate();
        }
    }

    @Override
    public List<LoteProductivo> listar() throws SQLException {
        List<LoteProductivo> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearLote(rs));
            }
        }
        return lista;
    }

    @Override
    public LoteProductivo buscarPorId(int id) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearLote(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<LoteProductivo> buscarPorCodigo(String codigo) throws SQLException {
        List<LoteProductivo> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_CODIGO)) {
            ps.setString(1, "%" + codigo + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearLote(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public void actualizar(LoteProductivo lote) throws SQLException {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, lote.getCodigoLote());
            ps.setString(2, lote.getEspecie());
            ps.setInt(3, lote.getCantidadInicial());
            ps.setDate(4, lote.getFechaIngreso());
            ps.setInt(5, lote.getIdUnidadProductiva());
            ps.setInt(6, lote.getIdLoteProductivo());
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

    private LoteProductivo mapearLote(ResultSet rs) throws SQLException {
        LoteProductivo lote = new LoteProductivo();
        lote.setIdLoteProductivo(rs.getInt("id_lote_productivo"));
        lote.setCodigoLote(rs.getString("codigo_lote"));
        lote.setEspecie(rs.getString("especie"));
        lote.setCantidadInicial(rs.getInt("cantidad_inicial"));
        lote.setFechaIngreso(rs.getDate("fecha_ingreso"));
        lote.setIdUnidadProductiva(rs.getInt("id_unidad_productiva"));
        return lote;
    }
}
