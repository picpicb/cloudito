package com.ackincolor.cloudito.GeolocationService.GeolocationInterface;

import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GeolocationRetrofitService {

    @GET("geolocation/accessPoints")
    Call<ArrayList<AccessPoint>> getAccessPoints();

    @POST("geolocation/customerLocation")
    Call<Location> sendCustomerLocation(@Body Location location);

}
