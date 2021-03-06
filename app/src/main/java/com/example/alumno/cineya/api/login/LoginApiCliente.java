package com.example.alumno.cineya.api.login;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.alumno.cineya.api.RemoteFactory;
import com.example.alumno.cineya.dto.LoginRequest;
import com.example.alumno.cineya.dto.User;
import com.example.alumno.cineya.helpers.OnSuccessCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginApiCliente {

    private Context context;
    private LoginApi cliente;

    public LoginApiCliente(Context context) {
        this.context = context;
        cliente = new RemoteFactory().createApiClient(LoginApi.class);
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

    public void getLogin(final OnSuccessCallback<User> callback, String usuario, String contrasena){
        //LoginRequest request = new LoginRequest();
        //request.setUsuario(usuario);
        //request.setContrasena(contrasena);

        //cliente.getLogin(request).enqueue(new Callback<User>() {
        cliente.getLogin(usuario, contrasena).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.execute(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

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
