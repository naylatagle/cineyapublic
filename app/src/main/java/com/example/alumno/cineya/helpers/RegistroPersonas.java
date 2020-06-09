package com.example.alumno.cineya.helpers;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegistroPersonas extends StringRequest {

    private static final String REGISTRO_PERSONAS_URL="http://cineyaa.000webhostapp.com/conexionandroid.php";


    private Map<String,String>params;

    public RegistroPersonas(String nombre, String usuario, String contrasena, String repcontrasena, Response.Listener<String>listener){
        super(Method.POST,REGISTRO_PERSONAS_URL,listener,null);
        params=new HashMap<>();
        params.put("nombre",nombre);
        params.put("usuario",usuario);
        params.put("contrasena",contrasena);
        params.put("repcontrasena",repcontrasena);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
