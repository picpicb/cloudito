package com.ackincolor.cloudito.controllers;

import android.util.Log;

import com.ackincolor.cloudito.entities.Parcours;
import com.ackincolor.cloudito.services.ParcoursService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.UUID;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParcoursController {
    static final String BASE_URL = "http://172.31.254.54:3081/";
    private Gson gson;

    public ParcoursController() {
        this.gson = new GsonBuilder().serializeNulls().create();
    }

    public void getParcours(UUID id){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ParcoursService service = retrofit.create(ParcoursService.class);

        final Call<Parcours> call = service.getParcours(id.toString());

        call.enqueue(new Callback<Parcours>() {
            @Override
            public void onResponse(Call<Parcours> call, Response<Parcours> response) {
                if(response.isSuccessful()){
                    Log.d("DEBUG",response.body().toString());
                }else {
                    Log.d("DEBUG",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Parcours> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
