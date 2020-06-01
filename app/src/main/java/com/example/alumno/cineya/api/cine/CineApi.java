package com.example.alumno.cineya.api.cine;

import com.example.alumno.cineya.dto.Cine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nayla on 29/11/2017.
 */

interface CineApi {

        @GET("mostrarcinesandroid")
    public Call<List<Cine>> getCines();
}
