package com.ackincolor.cloudito.controller;

import com.ackincolor.cloudito.entities.Position;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionController implements Callback<List<Position>> {
    @Override
    public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {

    }

    @Override
    public void onFailure(Call<List<Position>> call, Throwable t) {

    }
    public void sendPositionGeoMarketing(){}
}
