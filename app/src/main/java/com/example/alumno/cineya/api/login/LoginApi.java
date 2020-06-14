package com.example.alumno.cineya.api.login;

import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.dto.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Nayla on 29/11/2017.
 */

interface LoginApi {

        @POST("registroandroid.php")
    public Call<List<Request>> getLogin(@usuario "usuario") String title,
                                        @contrasena ("contrasena") String body);
}
