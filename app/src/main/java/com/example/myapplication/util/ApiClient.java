package com.example.myapplication.util;

import com.example.myapplication.api.other.RetroClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static RetroClient API_SERVICE;

    public static RetroClient getApiService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /// OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String baseUrl = "https://api.reniec.cloud/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client(interceptor)) // <-- usamos el log level
                    .build();
            API_SERVICE = retrofit.create(RetroClient.class);
        }

        return API_SERVICE;
    }

    public static OkHttpClient client(HttpLoggingInterceptor interceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                //   .writeTimeout(6, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(false)
                .build();
        return okHttpClient;
    }
}
