package com.example.alumno.cineya.dto;

public class User {

    boolean success;

    private long Id_usuario;

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

    public long getId_usuario() {
        return Id_usuario;
    }}
