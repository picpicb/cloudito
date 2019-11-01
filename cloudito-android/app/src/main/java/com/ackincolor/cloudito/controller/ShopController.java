package com.ackincolor.cloudito.controller;

import com.ackincolor.cloudito.entities.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopController implements Callback<List<Shop>> {
    @Override
    public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {

    }

    @Override
    public void onFailure(Call<List<Shop>> call, Throwable t) {

    }
}
