package com.example.myapplication.api.other;

import com.example.myapplication.api.model.Client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetroClient {

    @GET("dni/{dni}")
    Call<Client> getClient(@Path("dni")int dni);

}
