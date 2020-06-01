package com.example.alumno.cineya.api.cine.info;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.alumno.cineya.helpers.OnSuccessCallback;
import com.example.alumno.cineya.dto.CineInfo;
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

public class CineInfoApiCliente {

    private static Context context;
    private static CineInfoApi cliente;

    public static void init(Context con){
        context = con;
    }

    private static CineInfoApi getClient(){
        if(cliente == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/naylatagle/demo/")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            cliente = retrofit.create(CineInfoApi.class);
        }
        return cliente;
    }

    public static void getInfoCines(final OnSuccessCallback callback){
        getClient().getInfoCines().enqueue(new Callback<List<CineInfo>>() {
            @Override
            public void onResponse(Call<List<CineInfo>> call, Response<List<CineInfo>> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<List<CineInfo>> call, Throwable throwable) {
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
