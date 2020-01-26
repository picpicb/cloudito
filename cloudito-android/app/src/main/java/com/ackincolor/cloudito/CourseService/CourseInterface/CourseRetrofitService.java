package com.ackincolor.cloudito.CourseService.CourseInterface;

import com.ackincolor.cloudito.entities.Course;
import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CourseRetrofitService {
    @GET("course/{id}")
    Call<Course> getParcours(@Path("id") String id);

    @GET("map/course/{A}/{B}")
    Call<ArrayList<CourseNode>> getCourseNodesBtwAandB(@Path("A") int start,@Path("B") int end);

    @GET("map/nodes")
    Call<ArrayList<CourseNode>> getCourseNodes();

    @GET("map/stores")
    Call<ArrayList<ArrayList<ArrayList<Double>>>> getMap();
}
