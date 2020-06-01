package com.example.alumno.cineya.api.pelicula.info;

import com.example.alumno.cineya.dto.PeliculaInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nayla on 29/11/2017.
 */

interface PeliculaInfoApi {

    @GET("peliculainfo ")
    public Call<List<PeliculaInfo>> getInfoPeliculas();
}
