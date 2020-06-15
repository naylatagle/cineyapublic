package com.example.alumno.cineya.api.login;

import com.example.alumno.cineya.dto.LoginRequest;
import com.example.alumno.cineya.dto.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


interface LoginApi {


    @POST("registroandroid.php")
    @FormUrlEncoded
    public Call<User> getLogin(@Field("usuario") String title,
                               @Field("contrasena") String body);

    //public Call<User> getLogin(@Body LoginRequest loginrequest);
                               //@Body LoginRequest request ("contrasena") String body);
}
