package com.ackincolor.cloudito.controllers;

import android.content.Context;
import android.util.Log;

import com.ackincolor.cloudito.CourseService.CourseCache.CourseManager;
import com.ackincolor.cloudito.entities.Course;
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
    private Context context;

    public ParcoursController(Context context) {
        this.gson = new GsonBuilder().serializeNulls().create();
        this.context = context;
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

        final Call<Course> call = service.getParcours(id.toString());

        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if(response.isSuccessful()){
                    Log.d("DEBUG",response.body().toString());
                    //sauvegarde
                    CourseManager db = new CourseManager(context);
                    db.open();
                    db.saveParcours(response.body());
                    db.close();
                }else {
                    Log.d("DEBUG",response.toString());
                    CourseManager db = new CourseManager(context);
                    db.open();
                    Log.d("DEBUG",db.getParcours(UUID.randomUUID()));
                    db.close();
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                t.printStackTrace();
                CourseManager db = new CourseManager(context);
                db.open();
                Log.d("DEBUG",db.getParcours(UUID.randomUUID()));
                db.close();
            }
        });
    }
}
