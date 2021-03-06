package com.example.alumno.cineya.api.cine;

import com.example.alumno.cineya.dto.Cine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nayla on 29/11/2017.
 */

interface CineApi {

    @GET("mostrarcinesandroid")
    public Call<List<Cine>> getCines();

    @POST("mostrarfavoritos")
    @FormUrlEncoded
    public Call<List<Cine>> getFavoritosCines(@Field("Id_usuario") long userID);

    @POST("favoritos")
    @FormUrlEncoded
    public Call<Cine> addToFavorite(@Field("Id_usuario") String userID,
                                    @Field("Id_cine") String cineId);

//    @POST("favoritos")
//    public Call<Cine> addToFavorite(@Body CineApiCliente.FavoriteRequest request);

    @POST("removenfavorito")
    public Call<Cine> removeFavorito();

}
