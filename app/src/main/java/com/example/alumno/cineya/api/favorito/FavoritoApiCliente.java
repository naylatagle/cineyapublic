package com.example.alumno.cineya.api.favorito;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.alumno.cineya.api.RemoteFactory;
import com.example.alumno.cineya.dto.Cine;
import com.example.alumno.cineya.dto.Favorito;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nayla on 29/11/2017.
 */

public class FavoritoApiCliente {

    private Context context;
    private FavoritoApi cliente;

    public FavoritoApiCliente(Context context) {
        this.context = context;
        cliente = new RemoteFactory().createApiClient(FavoritoApi.class);
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

    public  void getFavoritos(final OnSuccessCallback callback){
        cliente.getFavoritos().enqueue(new Callback<List<Favorito>>() {
            @Override
            public void onResponse(Call<List<Favorito>> call, Response<List<Favorito>> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<List<Favorito>> call, Throwable throwable) {
                Toast.makeText(context, "Fallo en la conexión con el servidor Favorito", Toast.LENGTH_SHORT).show();

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
