package com.ackincolor.cloudito.CourseService.CourseInterface;

import com.ackincolor.cloudito.entities.Course;
import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CourseRetrofitService {
    @GET("course/{id}")
    Call<Course> getParcours(@Path("id") String id);

    @GET("map/course/{A}/{B}")
    Call<Course> getCourse(@Path("A") int start,@Path("B") int end);

    @GET("map/stores")
    Call<ArrayList<ArrayList<ArrayList<Double>>>> getMap();
}
