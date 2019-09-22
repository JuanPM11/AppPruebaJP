package com.example.apppruebajp;

public class Municipios {

    private String id;
    private String nombreMunicipios;
    private String codigoMunicipios;
    private String departamentoId;

    public String getId() {
        return id;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getNombreMunicipios() {
        return nombreMunicipios;
    }

    public void setNombreMunicipios(String nombreDepartamentos) {
        this.nombreMunicipios = nombreDepartamentos;
    }

    public String getCodigoMunicipios() {
        return codigoMunicipios;
    }

    public void setCodigoMunicipios(String codigoDepartamentos) {
        this.codigoMunicipios = codigoDepartamentos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Municipios(String id, String nombreMunicipios, String codigoMunicipios, String departamentoId) {
        this.id = id;
        this.nombreMunicipios = nombreMunicipios;
        this.codigoMunicipios = codigoMunicipios;
        this.departamentoId = departamentoId;
    }
}
