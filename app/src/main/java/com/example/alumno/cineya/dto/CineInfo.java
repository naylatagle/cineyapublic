package com.example.alumno.cineya.dto;

public class CineInfo {

    private String opcionPelicula;
    private String horarioPelicula;
    private String mensaje;


    public String getOpcionPelicula(){
        return opcionPelicula;
    }

    public void setOpcionPelicula(String opcionPelicula) {
        this.opcionPelicula = opcionPelicula;
    }

    public String getHorarioPelicula(){
        return horarioPelicula;
    }

    public void setHorarioPelicula(String horarioPelicula) {
        this.horarioPelicula = horarioPelicula;
    }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
