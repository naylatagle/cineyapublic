package com.example.alumno.cineya.activities;

import android.content.Context;
import android.content.SharedPreferences;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alumno.cineya.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userEt;
    private EditText passwordEt;
    private Button enterBtn;
    private Context context;
    private SharedPreferences sharedPreferences;




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
