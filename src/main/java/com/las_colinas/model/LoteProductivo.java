package com.las_colinas.model;

import java.sql.Date;

public class LoteProductivo {

    private int idLoteProductivo;
    private String codigoLote;
    private String especie;
    private int cantidadInicial;
    private Date fechaIngreso;
    private int idUnidadProductiva;

    public LoteProductivo() {
    }

    public LoteProductivo(int idLoteProductivo, String codigoLote, String especie,
                          int cantidadInicial, Date fechaIngreso, int idUnidadProductiva) {
        this.idLoteProductivo = idLoteProductivo;
        this.codigoLote = codigoLote;
        this.especie = especie;
        this.cantidadInicial = cantidadInicial;
        this.fechaIngreso = fechaIngreso;
        this.idUnidadProductiva = idUnidadProductiva;
    }

    public int getIdLoteProductivo() {
        return idLoteProductivo;
    }

    public void setIdLoteProductivo(int idLoteProductivo) {
        this.idLoteProductivo = idLoteProductivo;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdUnidadProductiva() {
        return idUnidadProductiva;
    }

    public void setIdUnidadProductiva(int idUnidadProductiva) {
        this.idUnidadProductiva = idUnidadProductiva;
    }

    @Override
    public String toString() {
        return "LoteProductivo{" +
                "idLoteProductivo=" + idLoteProductivo +
                ", codigoLote='" + codigoLote + '\'' +
                ", especie='" + especie + '\'' +
                ", cantidadInicial=" + cantidadInicial +
                ", fechaIngreso=" + fechaIngreso +
                ", idUnidadProductiva=" + idUnidadProductiva +
                '}';
    }
}
