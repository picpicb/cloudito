package com.ackincolor.cloudito.CourseService.CourseInterface;

import android.util.Log;


import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.entities.Map;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseRetrofitController {
    private String BASE_URL = "http://172.31.254.54:3084/";
    private Gson gson;

    public CourseRetrofitController(){
        this.gson = new Gson();
    }
    public void getStoresMap(com.ackincolor.cloudito.ui.components.Map mapComponent){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CourseRetrofitService service = retrofit.create(CourseRetrofitService.class);

        final Call<ArrayList<ArrayList<ArrayList<Double>>>> call = service.getMap();
        call.enqueue(new Callback<ArrayList<ArrayList<ArrayList<Double>>>>() {
            @Override
            public void onResponse(Call<ArrayList<ArrayList<ArrayList<Double>>>> call, Response<ArrayList<ArrayList<ArrayList<Double>>>> response) {
                if(response.isSuccessful()){
                    //Log.d("DEBUG MAP",response.body().toString());
                    ArrayList<ArrayList<Location>> liste = new ArrayList<>();
                    ArrayList<Location> listeTemp = new ArrayList<>();
                    ArrayList<ArrayList<ArrayList<Double>>> body = response.body();
                    for(int j = 0; j < body.size(); j++){
                        listeTemp = new ArrayList<>();
                        for(int i = 0 ; i < body.get(j).size();i++){
                            listeTemp.add( new Location(0,0,body.get(j).get(i).get(0),body.get(j).get(i).get(1)));
                        }
                        liste.add(listeTemp);
                    }
                    Map m = new com.ackincolor.cloudito.entities.Map(liste);
                    mapComponent.setMap(m);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArrayList<ArrayList<Double>>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getAllCoursesNodes(CourseService cs) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CourseRetrofitService service = retrofit.create(CourseRetrofitService.class);

        final Call<ArrayList<CourseNode>> call = service.getCourseNodes();
        call.enqueue(new Callback<ArrayList<CourseNode>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseNode>> call, Response<ArrayList<CourseNode>> response) {
                if(response.isSuccessful()){
                    cs.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CourseNode>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getCourseNodesBtwAandB(com.ackincolor.cloudito.ui.components.Map mapComponent, int A, int B) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CourseRetrofitService service = retrofit.create(CourseRetrofitService.class);

        final Call<ArrayList<CourseNode>> call = service.getCourseNodesBtwAandB(A, B);
        call.enqueue(new Callback<ArrayList<CourseNode>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseNode>> call, Response<ArrayList<CourseNode>> response) {
                if(response.isSuccessful()){
                    Log.d("DEBUG MAP","setting course");
                    mapComponent.setCourse(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CourseNode>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
