package com.example.alumno.cineya.api.login;

import com.example.alumno.cineya.dto.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;


interface LoginApi {

    @POST("registroandroid.php")
    public Call<User> getLogin(@Field("usuario") String title,
                                     @Field("contrasena") String body);
}
