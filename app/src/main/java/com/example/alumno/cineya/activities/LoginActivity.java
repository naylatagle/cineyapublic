package com.example.alumno.cineya.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alumno.cineya.Constants;
import com.example.alumno.cineya.R;
import com.example.alumno.cineya.api.login.LoginApiCliente;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private final static int GOOGLE_LOGIN_REQUEST_CODE = 134;

    private EditText userEt;
    private EditText passwordEt;
    private Button enterBtn;
    private Button crearcuenta;
    private Context context;
    private SharedPreferences sharedPreferences;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private SignInButton mBtnGoogleSignIn;
    private GoogleSignInClient mGoogleSignInClient;

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
        mBtnGoogleSignIn = (SignInButton) findViewById(R.id.logingoogle);


        //Obtengo una instancia de las SharedPreferences.
        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        //Consulto por los valores de las claves que me interesan.
        String user = sharedPreferences.getString("user", "");
        String name = sharedPreferences.getString("name", "");
        //String id_user = sharedPreferences.getString("id_user", "");
        Long id_user = sharedPreferences.getLong("userID", 0l);

        int tipo = sharedPreferences.getInt(Constants.SHARED_KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_NONE);

        if (tipo == Constants.LOGIN_TYPE_FACEBOOK) {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
            if (isLoggedIn) {
                gotoBuscarPor();
            } else {
                laSesionConLasRedesYaNoEsValidaTengoQueBorrarUsuarioGuardado();
            }
        } else if (tipo == Constants.LOGIN_TYPE_GMAIL) {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if (account != null)
                gotoBuscarPor();
            else {
                laSesionConLasRedesYaNoEsValidaTengoQueBorrarUsuarioGuardado();
            }

        } else if (tipo == Constants.LOGIN_TYPE_USER) {
            gotoBuscarPor();
        } else {
            //SI NO ESTOY LOGUEADO PUEDO INICIALIZAR COSAS DEL LOGIN
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestId()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        }


        //Defino el comportamiento para onClick del boton Ingresar.
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUsername(userEt.getText().toString(), passwordEt.getText().toString());
            }
        });

        // Crear Cuenta
        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
                //Intent intent2 = new Intent (v.getContext(), Registro.class);
                //startActivityForResult(intent2, 0);
            }
        });

        // Login con Facebook

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userId = loginResult.getAccessToken().getUserId();

                //POST LOGIN CON FACEBOOK -> facebookUserId <- Datos de login

                gotoBuscarPor();
                loginResult.getAccessToken().getUserId();
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


        mBtnGoogleSignIn.setOnClickListener(getGoogleSignInClickListener());

    }

    // Login con Google
    private View.OnClickListener getGoogleSignInClickListener() {
        return v -> {
            Intent signInInten = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInInten, GOOGLE_LOGIN_REQUEST_CODE);
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_LOGIN_REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null && !account.isExpired()) {
                    String mail = account.getEmail();
                    String userId = account.getId();

                    //TODO POST LOGIN CON GOOGLE -> Pedi mail y userId
                    sharedPreferences.edit().putInt(Constants.SHARED_KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_GMAIL).apply();

                    gotoBuscarPor();
                } else {
                    Toast.makeText(LoginActivity.this,
                            String.format("Ocurrio un error..."), Toast.LENGTH_LONG).show();
                }

            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this,
                        String.format("Error: %s", e.getMessage()), Toast.LENGTH_LONG).show();
            }

        }
    }


    private void gotoBuscarPor() {
        //Llamo al ciclo de cierre del LoginActivity.
        finish();
        //Redirijo hacia el LoginActivity.
        startActivity(new Intent(context, MainActivity.class));
    }

    private void loginUsername(String usuario, String contrasena) {

        new LoginApiCliente(getApplicationContext()).getLogin(body -> {
            if(body.isSuccess()) {
                //persistencia resuelta con SharedPreferences
                sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
                //Guardo asincronicamente las credenciales de logueo
                sharedPreferences.edit()
                        .putInt(Constants.SHARED_KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_USER)
                        .putString("user", body.getUsuario())
                        .putString("name", body.getNombre())
                        .putLong("userID", body.getId_usuario())
                        .apply();
                gotoBuscarPor();
            } else {
                Toast.makeText(getApplicationContext(), "Los datos ingresados son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }, usuario, contrasena);

       /* new LoginApiCliente(getContext()).getCines(new OnSuccessCallback() {
            @Override
            public void execute(Object body) {

                cineC.setAdapter(new AdaptadorCine(getContext(), (List<Cine>) body));
                hideLoading();
            }*/
//        return usuario.equals("Cine") && password.equals("Ya");
    }

    private void laSesionConLasRedesYaNoEsValidaTengoQueBorrarUsuarioGuardado() {
        //TODO ver aca. limpiar datos locales del usuario
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


}
