package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MapService {

    @GET("map/stores")
    Call<ArrayList<ArrayList<ArrayList<Double>>>> getMap();
}
