package com.example.alumno.cineya.api;

import android.util.Log;

import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteFactory {

    private static final String WEB_SERVICE_BASE_URL = "https://vanadous-inches.000webhostapp.com/";
    private static final HttpLoggingInterceptor.Level LOGIN_LEVEL = HttpLoggingInterceptor.Level.BODY;

    public <T> T createApiClient(Class<T> clazz) {
        return createApiClient(clazz, WEB_SERVICE_BASE_URL);
    }

    private <T> T createApiClient(Class<T> clazz, final String endPoint) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(getClient())
                .build();

        return restAdapter.create(clazz);
    }

    private OkHttpClient getClient() {
        return getBasicClientBuilder().build();
    }

    private static OkHttpClient.Builder getBasicClientBuilder() {
        return  new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor());

    }

    private static Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("RemoteFactory",message);
            }
        });
        loggingInterceptor.setLevel(LOGIN_LEVEL);
        return loggingInterceptor;
    }
}
