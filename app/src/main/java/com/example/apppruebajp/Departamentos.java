package com.example.apppruebajp;

/**
 * Clase DEPARTAMENTOS
 */
public class Departamentos {
    private String id;
    private String nombreDepartamentos;
    private String codigoDepartamentos;

    /**
     * Método para obtener el Id del Departamento
     * @return id del Departamento
     */
    public String getId() {
        return id;
    }

    /**
     * Método para obtener el Nombre del Departamento
     * @return nombre del Departamento
     */
    public String getNombreDepartamentos() {
        return nombreDepartamentos;
    }

    /**
     * Metodo para "setear" el Nombre del Departamento
     * @param nombreDepartamentos nombre del Departamento
     */
    public void setNombreDepartamentos(String nombreDepartamentos) {
        this.nombreDepartamentos = nombreDepartamentos;
    }

    /**
     * Método para obtener el código del Departamento
     * @return codigo del Departamento
     */
    public String getCodigoDepartamentos() {
        return codigoDepartamentos;
    }

    /**
     * Metodo para "setear" el codigo del Departamento
     * @param codigoDepartamentos codigo del Departamento
     */
    public void setCodigoDepartamentos(String codigoDepartamentos) {
        this.codigoDepartamentos = codigoDepartamentos;
    }

    /**
     * Constructor de la clase
     * @param id id del Departamento
     * @param nombreDepartamentos nombre del Departamento
     * @param codigoDepartamentos codigo del Departamento
     */
    public Departamentos(String id, String nombreDepartamentos, String codigoDepartamentos) {
        this.id = id;
        this.nombreDepartamentos = nombreDepartamentos;
        this.codigoDepartamentos = codigoDepartamentos;
    }
}