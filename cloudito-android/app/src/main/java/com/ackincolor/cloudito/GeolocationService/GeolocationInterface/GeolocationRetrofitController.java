package com.ackincolor.cloudito.GeolocationService.GeolocationInterface;

import android.content.Context;
import android.util.Log;

import com.ackincolor.cloudito.GeolocationService.GeolocationAndroidService;
import com.ackincolor.cloudito.GeolocationService.GeolocationCache.GeolocationManager;
import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeolocationRetrofitController {

    private Context context;
    private GeolocationAndroidService androidService;
    static final String BASE_URL = "http://ackincolor.ddns.net:3083/"; //port 3083
    private Gson gson;
    public GeolocationRetrofitController(Context context, GeolocationAndroidService androidService) {
        this.context = context;
        this.androidService = androidService;
    }

    public void insertAccessPoints(){
        this.gson = new Gson();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build();

        GeolocationRetrofitService service = retrofit.create(GeolocationRetrofitService.class);

        final Call<ArrayList<AccessPoint>> call = service.getAccessPoints();

        call.enqueue(new Callback<ArrayList<AccessPoint>>() {
            @Override
            public void onResponse(Call<ArrayList<AccessPoint>> call, Response<ArrayList<AccessPoint>> response) {
                if(response.isSuccessful()){
                    Log.d("DEBUG GEOLOCATION CONTROLLER",response.body().toString());
                    //sauvegarde
                    androidService.callBackInsertAccessPoint(response.body());
                }else {
                    Log.d("DEBUG GEOLOCATION CONTROLLER",response.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AccessPoint>> call, Throwable t) {
                t.printStackTrace();
                Log.d("DEBUG GEOLOCATION CONTROLLER","FAILURE CALLBACK RETROFIT");
            }
        });
    }

    public void sendCustomerLocation(CourseNode location){
        this.gson = new Gson();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build();

        GeolocationRetrofitService service = retrofit.create(GeolocationRetrofitService.class);

        service.sendCustomerLocation(1,location).enqueue(new Callback<CourseNode>() {
            @Override
            public void onResponse(Call<CourseNode> call, Response<CourseNode> response) {
                androidService.callBackSendCustomerLocation();
            }

            @Override
            public void onFailure(Call<CourseNode> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
