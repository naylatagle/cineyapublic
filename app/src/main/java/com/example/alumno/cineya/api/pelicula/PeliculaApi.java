package com.example.alumno.cineya.api.pelicula;

import com.example.alumno.cineya.dto.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nayla on 29/11/2017.
 */

interface PeliculaApi {

    @GET("pelicula")
    public Call<List<Pelicula>> getPeliculas();
}
