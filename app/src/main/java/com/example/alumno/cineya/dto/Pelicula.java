package com.example.alumno.cineya.dto;

public class Pelicula {

    private int logoPelicula;
    private String nombrePelicula;
    private String generoPelicula;

    /*public Pelicula(int logo, String nombre, String genero){
        logoPelicula = logo;
        nombrePelicula = nombre;
        generoPelicula = genero;
    }*/

    public void setLogoPelicula(int logoPelicula) {
        this.logoPelicula = logoPelicula;
    }

    public int getLogoPelicula(){
        return logoPelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getNombrePelicula(){
        return nombrePelicula;
    }

    public void setGeneroPelicula(String generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public String getGeneroPelicula(){
        return generoPelicula;
    }
}
