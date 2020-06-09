package com.example.alumno.cineya.helpers;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emmanuel on 13/10/2018.
 */

public class ValidacionRegistro extends StringRequest{

    private static final String VALIDACION_REGISTRO_URL="https://vanadous-inches.000webhostapp.com/registroandroid.php";


    private Map<String,String> params;

    public ValidacionRegistro( String usuario, String contrasena ,Response.Listener<String>listener){
        super(Request.Method.POST,VALIDACION_REGISTRO_URL,listener,null);
        params=new HashMap<>();
        params.put("usuario",usuario);
        params.put("contrasena",contrasena);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}