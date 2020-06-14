package com.example.alumno.cineya.api.cine;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.alumno.cineya.api.RemoteFactory;
import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.example.alumno.cineya.dto.Cine;
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

public class CineApiCliente {

    private Context context;
    private CineApi cliente;

    public CineApiCliente(Context context) {
        this.context = context;
        cliente = new RemoteFactory().createApiClient(CineApi.class);
    }

//    public  CineApi getClient(){
//        if(cliente == null){
//            Retrofit retrofit = new Retrofit.Builder()
//                    //.baseUrl("https://my-json-server.typicode.com/naylatagle/demo/")
//                    .baseUrl("https://vanadous-inches.000webhostapp.com/")
//                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
//                    .build();
//            cliente = retrofit.create(CineApi.class);
//        }
//        return cliente;
//    }

    public void getCines(final OnSuccessCallback callback){
        cliente.getCines().enqueue(new Callback<List<Cine>>() {
            @Override
            public void onResponse(Call<List<Cine>> call, Response<List<Cine>> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<List<Cine>> call, Throwable throwable) {
                Toast.makeText(context, "Fallo en la conexión con el servidor", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Cierre del programa (App)
                        System.exit(0);
                    }
                }, 2000); //Especifico un delay de 2 segundos ( 2000 milisegundos )
            }
        });
    }
}
