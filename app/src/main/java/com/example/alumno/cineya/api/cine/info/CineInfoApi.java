package com.example.alumno.cineya.api.cine.info;

import com.example.alumno.cineya.dto.CineInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nayla on 29/11/2017.
 */

interface CineInfoApi {

    @GET("cineinfo")
    public Call<List<CineInfo>> getInfoCines();
}
