package com.example.alumno.cineya.dto;

public class User {

    boolean success;

    long idUsuario;

    private String usuario;

    private String nombre;

    public boolean isSuccess() {
        return success;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdUsuario() {
        return idUsuario;
    }}
