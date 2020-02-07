package com.ackincolor.cloudito.GeolocationService.GeolocationInterface;

import com.ackincolor.cloudito.entities.AccessPoint;
import com.ackincolor.cloudito.entities.CourseNode;
import com.ackincolor.cloudito.entities.Location;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GeolocationRetrofitService {

    @GET("accesspoints")
    Call<ArrayList<AccessPoint>> getAccessPoints();

    @POST("/customer/{customerId}/location")
    Call<CourseNode> sendCustomerLocation(@Path("customerId") int customerId,@Body CourseNode location);

}
