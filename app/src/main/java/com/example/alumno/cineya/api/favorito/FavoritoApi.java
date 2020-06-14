package com.example.alumno.cineya.api.favorito;

import com.example.alumno.cineya.dto.Cine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nayla on 29/11/2017.
 */

interface FavoritoApi {

        @GET("mostrarcinesandroid")
    public Call<List<Cine>> getCines();
}
