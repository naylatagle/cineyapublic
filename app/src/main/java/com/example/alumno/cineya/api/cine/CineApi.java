package com.example.alumno.cineya.api.cine;

import com.example.alumno.cineya.dto.Cine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Nayla on 29/11/2017.
 */

interface CineApi {

    @GET("mostrarcinesandroid")
    public Call<List<Cine>> getCines();

    @GET("traeme los favorittos")
    public Call<List<Cine>> getFavoritosCines(@Field("userId") long userID);

    @POST("agregarAfav")
    public Call<Cine> addToFavorite();

    @POST("agregarAfav")
    public Call<Cine> removeFavorite();

}
