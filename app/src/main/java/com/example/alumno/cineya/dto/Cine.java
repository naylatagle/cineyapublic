package com.example.alumno.cineya.dto;

public class Cine {

    private int logoCine;
    private String nombreCine;
    private String direccionCine;

    /*public Cine(int logo, String nombre, String direccion){
        logoCine = logo;
        nombreCine = nombre;
        direccionCine = direccion;
    }*/

    public void setLogoCine(int logoCine) {
        this.logoCine = logoCine;
    }

    public int getLogoCine(){
        return logoCine;
    }

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public String getNombreCine(){
        return nombreCine;
    }

    public void setDireccionCine(String direccionCine) {
        this.direccionCine = direccionCine;
    }

    public String getDireccionCine(){
        return direccionCine;
    }
}
