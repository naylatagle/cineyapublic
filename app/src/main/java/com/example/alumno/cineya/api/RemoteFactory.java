package com.example.alumno.cineya.api;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class RemoteFactory {

    private static final String WEB_SERVICE_BASE_URL = "Constants.Remote.getEndPoint()";
    private static final HttpLoggingInterceptor.Level LOGIN_LEVEL = HttpLoggingInterceptor.Level.BODY;

    public <T> T createApiClient(Class<T> clazz) {
        return createApiClient(clazz, WEB_SERVICE_BASE_URL);
    }

    private <T> T createApiClient(Class<T> clazz, final String endPoint) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(endPoint)
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
