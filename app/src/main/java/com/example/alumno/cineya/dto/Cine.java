package com.example.alumno.cineya.dto;

import com.google.gson.annotations.SerializedName;

public class Cine {

    @SerializedName("Id_cine")
    private long idCine;
    private int logoCine;
    private String Nombre;
    private String Direccion;
    @SerializedName("favorito")
    private boolean esFavorito;


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

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }

    public long getIdCine() {
        return idCine;
    }
}
