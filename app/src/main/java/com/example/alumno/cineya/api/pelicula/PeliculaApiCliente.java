package com.example.alumno.cineya.api.pelicula;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.alumno.cineya.api.RemoteFactory;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.example.alumno.cineya.dto.Pelicula;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nayla on 29/11/2017.
 */

public class PeliculaApiCliente {

    private Context context;
    private PeliculaApi cliente;

    public PeliculaApiCliente(Context context) {
        this.context = context;
        cliente = new RemoteFactory().createApiClient(PeliculaApi.class);
    }

    /*public static void init(Context con){
        context = con;
    }

    private static PeliculaApi getClient(){
        if(cliente == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/naylatagle/demo/")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            cliente = retrofit.create(PeliculaApi.class);
        }
        return cliente;
    }*/

    public void getPeliculas(final OnSuccessCallback<List<Pelicula>> callback){
        cliente.getPeliculas().enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable throwable) {
                Toast.makeText(context, "Fallo en la conexión con el servidor", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Cierre del programa (App)
                        System.exit(0);
                    }
                }, 10000); //Especifico un delay de 2 segundos ( 2000 milisegundos )
            }
        });
    }
}
