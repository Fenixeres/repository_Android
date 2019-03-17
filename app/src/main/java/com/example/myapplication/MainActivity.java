package com.example.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.api.model.Client;
import com.example.myapplication.util.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Client> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Call<Client> clientCall = ApiClient.getApiService().getClient(70314868);
        clientCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Client> call, Response<Client> response) {
        if (response.isSuccessful()) {
            Client client = response.body();
            System.out.println("SE OBTUVO CORRECTAMENTE EL CLIENTE: " + client);
            Log.d("response:", "soy el cliente: " + client);
        }
    }

    @Override
    public void onFailure(Call<Client> call, Throwable t) {
        System.out.println("SE OBTUVO CORRECTAMENTE EL CLIENTE: " + "ERRORRRRR+++++++" + t);
    }
}
