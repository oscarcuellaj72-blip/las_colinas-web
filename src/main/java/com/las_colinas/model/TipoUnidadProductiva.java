package com.las_colinas.model;

public class TipoUnidadProductiva {

    private int idTipoUnidadProductiva;
    private String nombreTipoUnidadProductiva;

    public TipoUnidadProductiva() {
    }

    public TipoUnidadProductiva(int idTipoUnidadProductiva, String nombreTipoUnidadProductiva) {
        this.idTipoUnidadProductiva = idTipoUnidadProductiva;
        this.nombreTipoUnidadProductiva = nombreTipoUnidadProductiva;
    }

    public int getIdTipoUnidadProductiva() {
        return idTipoUnidadProductiva;
    }

    public void setIdTipoUnidadProductiva(int idTipoUnidadProductiva) {
        this.idTipoUnidadProductiva = idTipoUnidadProductiva;
    }

    public String getNombreTipoUnidadProductiva() {
        return nombreTipoUnidadProductiva;
    }

    public void setNombreTipoUnidadProductiva(String nombreTipoUnidadProductiva) {
        this.nombreTipoUnidadProductiva = nombreTipoUnidadProductiva;
    }

    @Override
    public String toString() {
        return "TipoUnidadProductiva{" +
                "idTipoUnidadProductiva=" + idTipoUnidadProductiva +
                ", nombreTipoUnidadProductiva='" + nombreTipoUnidadProductiva + '\'' +
                '}';
    }
}

