package com.example.alumno.cineya.dto;

public class Cine {

    private int logoCine;
    private String Nombre;
    private String Direccion;

    /*public Cine(int logo, String nombre, String direccion){
        logoCine = logo;
        Nombre = nombre;
        Direccion = direccion;
    }*/

    public void setLogoCine(int logoCine) {
        this.logoCine = logoCine;
    }

    public int getLogoCine(){ return logoCine; }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombre(){
        return Nombre;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDireccion(){
        return Direccion;
    }
}
