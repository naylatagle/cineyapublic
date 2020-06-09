package com.example.alumno.cineya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.alumno.cineya.R;
import com.example.alumno.cineya.helpers.RegistroPersonas;

import org.json.JSONException;
import org.json.JSONObject;


public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText etnombre,etusuario,etcontraseña,etrepcontraseña;
    Button btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etnombre= (EditText) findViewById(R.id.nombre);

        etusuario= (EditText) findViewById(R.id.usuario);
        etcontraseña= (EditText) findViewById(R.id.pass);
        etrepcontraseña= (EditText) findViewById(R.id.reppass);


        btn_registrar= (Button) findViewById(R.id.registrarse);

        btn_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String nombre=etnombre.getText().toString();
        final String usuario=etusuario.getText().toString();
        final String contrasena=etcontraseña.getText().toString();
        final String repcontrasena=etrepcontraseña.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse =new JSONObject(response);
                    boolean success=jsonResponse.getBoolean("success");

                    if (success){
                        Intent intent =new Intent(Registro.this,MainActivity.class);
                        Registro.this.startActivity(intent);
                    }else{

                        AlertDialog.Builder builder= new AlertDialog.Builder(Registro.this);
                        builder.setMessage("Error de registro")
                                .setNegativeButton("Retry",null)
                                .create().show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegistroPersonas registroPersonas=new RegistroPersonas(nombre,usuario,contrasena,repcontrasena,respoListener);

        RequestQueue queue= Volley.newRequestQueue(Registro.this);
        queue.add(registroPersonas);


    }
}