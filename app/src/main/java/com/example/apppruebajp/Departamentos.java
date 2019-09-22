package com.example.apppruebajp;

public class Departamentos {
    private String id;
    private String nombreDepartamentos;
    private String codigoDepartamentos;


    public String getId() {
        return id;
    }

    public String getNombreDepartamentos() {
        return nombreDepartamentos;
    }

    public void setNombreDepartamentos(String nombreDepartamentos) {
        this.nombreDepartamentos = nombreDepartamentos;
    }

    public String getCodigoDepartamentos() {
        return codigoDepartamentos;
    }

    public void setCodigoDepartamentos(String codigoDepartamentos) {
        this.codigoDepartamentos = codigoDepartamentos;
    }

    public Departamentos(String id, String nombreDepartamentos, String codigoDepartamentos) {
        this.id = id;
        this.nombreDepartamentos = nombreDepartamentos;
        this.codigoDepartamentos = codigoDepartamentos;
    }
}