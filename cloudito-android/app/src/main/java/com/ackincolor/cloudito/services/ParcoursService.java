package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Course;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ParcoursService {

    @GET("parcours/{id}")
    Call<Course> getParcours(@Path("id") String id);
}
