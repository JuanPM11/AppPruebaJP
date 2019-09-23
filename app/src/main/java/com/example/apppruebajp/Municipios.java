package com.example.apppruebajp;

public class Municipios {

    private String id;
    private String nombreMunicipios;
    private String codigoMunicipios;
    private String departamentoId;

    /**
     * Metodo para obtener el Id del Municipio
     * @return id del municipio
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo para obtener el id del Departamento
     * @return id del departamento
     */
    public String getDepartamentoId() {
        return departamentoId;
    }

    /**
     * Metodo para "settear" el id del Departamento
     * @param departamentoId id del departamento
     */
    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    /**
     * Metodo para obtener el nombre del Municipio
     * @return nombre del municipio
     */
    public String getNombreMunicipios() {
        return nombreMunicipios;
    }

    /**
     * Metodo para "settear" el nombre del Departamento
     * @param nombreDepartamentos nombre del Departamento
     */
    public void setNombreMunicipios(String nombreDepartamentos) {
        this.nombreMunicipios = nombreDepartamentos;
    }

    /**
     * Metodo para obtener el codigo del Municipio
     * @return codigo del Municipio
     */
    public String getCodigoMunicipios() {
        return codigoMunicipios;
    }

    /**
     * Metodo para "settear" el codigo del Municipio
     * @param codigoDepartamentos codigo del Municipio
     */
    public void setCodigoMunicipios(String codigoDepartamentos) {
        this.codigoMunicipios = codigoDepartamentos;
    }

    /**
     * Metodo para "settear" el id del Municipio
     * @param id id del Municipio
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Constructor de la clase Municipios
     * @param id del Municipio
     * @param nombreMunicipios nombre del Municipio
     * @param codigoMunicipios codigo del Municipio
     * @param departamentoId id del departamento
     */
    public Municipios(String id, String nombreMunicipios, String codigoMunicipios, String departamentoId) {
        this.id = id;
        this.nombreMunicipios = nombreMunicipios;
        this.codigoMunicipios = codigoMunicipios;
        this.departamentoId = departamentoId;
    }
}
