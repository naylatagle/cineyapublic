package com.example.alumno.cineya.activities;

import android.content.Context;
import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.alumno.cineya.R;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    private EditText userEt;
    private EditText passwordEt;
    private Button enterBtn;
    private Button crearcuenta;
    private Context context;
    private SharedPreferences sharedPreferences;
    private LoginButton loginButton;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageView = (ImageView) findViewById(R.id.logoapp);
        imageView.setImageResource(R.drawable.logocineya);

        context = this;
        userEt = (EditText) findViewById(R.id.usuario);
        passwordEt = (EditText) findViewById(R.id.pass);
        enterBtn = (Button) findViewById(R.id.ingresar);
        crearcuenta = (Button) findViewById(R.id.crearcuenta);


        //Obtengo una instancia de las SharedPreferences.
        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        //Consulto por los valores de las claves que me interesan.
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        //Si ambas existen significa que se hizo login anteriormente.
        if(!username.isEmpty() && !password.isEmpty()) {
            //Voy al menu de busqueda
            gotoBuscarPor();
        } else {
            //Defino el comportamiento para onClick del boton Ingresar.
            enterBtn .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isLoginSuccessful(userEt.getText().toString(), passwordEt.getText().toString())) {
                        //persistencia resuleta con SharedPreferences
                        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
                        //Guardo asincronicamente las credenciales de logueo
                        sharedPreferences.edit()
                                .putString("username", userEt.getText().toString())
                                .putString("password", passwordEt.getText().toString())
                                .apply();
                        gotoBuscarPor();
                    } else {
                        Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                startActivity(new Intent(LoginActivity.this, Registro.class));
                //Intent intent2 = new Intent (v.getContext(), Registro.class);
                //startActivityForResult(intent2, 0);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                gotoBuscarPor();
            }

            @Override
            public void onCancel() {
                //Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                //Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearcuenta = findViewById(R.id.crearcuenta);

        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Remplazar por tu codigo", Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

        /*Button ingresar = (Button) findViewById(R.id.ingresar);
        ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String usuario = ((EditText) findViewById(R.id.usuario)).getText().toString();
                String password = ((EditText) findViewById(R.id.pass)).getText().toString();
                if (usuario.equals("Cine") && password.equals("Ya")) {
                    Intent buscarPorListener = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(buscarPorListener);
                } else {
                    Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/

    private void gotoBuscarPor() {
        //Llamo al ciclo de cierre del LoginActivity.
        finish();
        //Redirijo hacia el LoginActivity.
        startActivity(new Intent(context, MainActivity.class));
    }

    private boolean isLoginSuccessful(String username, String password) {
        return username.equals("Cine") && password.equals("Ya");
    }




}
