package com.ackincolor.cloudito.controllers;

import android.content.Context;
import android.util.Log;

import com.ackincolor.cloudito.entities.Map;
import com.ackincolor.cloudito.services.MapService;
import com.ackincolor.cloudito.services.ParcoursService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapController {
    static final String BASE_URL = "http://172.31.254.54:3081/";
    private Gson gson;
    private Context context;

    public MapController(Context context) {
        this.gson = new GsonBuilder().serializeNulls().create();
        this.context = context;
    }

    public void getMap(com.ackincolor.cloudito.ui.components.Map mapComponent){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MapService service = retrofit.create(MapService.class);
        final Call<Map> call = service.getMap();

        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.d("MAP",response.toString());
                mapComponent.setMap(response.body());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
