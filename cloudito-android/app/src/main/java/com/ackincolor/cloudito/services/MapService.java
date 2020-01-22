package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MapService {

    @GET("map/")
    Call<Map> getMap();
}
