package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Parcours;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ParcoursService {

    @GET("parcours/{id}")
    Call<Parcours> getParcours(@Path("id") String id);
}
