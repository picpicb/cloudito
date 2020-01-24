package com.ackincolor.cloudito.GeolocationService.GeolocationInterface;

import com.ackincolor.cloudito.entities.AccessPoint;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GeolocationRetrofitService {

    @GET("accesspoints")
    Call<ArrayList<AccessPoint>> getAccessPoints();

}
