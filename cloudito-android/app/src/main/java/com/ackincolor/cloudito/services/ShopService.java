package com.ackincolor.cloudito.services;

import com.ackincolor.cloudito.entities.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ShopService {
    public static final String ENDPOINT = "http://ackincolor.ddns.net:3081/";

    @GET("/shops")
    Call<List<Shop>> listRepos();

}
