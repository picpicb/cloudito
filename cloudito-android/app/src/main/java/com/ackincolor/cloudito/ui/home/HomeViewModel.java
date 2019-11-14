package com.ackincolor.cloudito.ui.home;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.ackincolor.cloudito.data.Stores;
import com.ackincolor.cloudito.entities.Shop;
import com.ackincolor.cloudito.services.ShopService;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Shop>> listShop;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bienvenue chez Cloudito");
        this.listShop = new MutableLiveData<>();
        this.listShop.setValue(new ArrayList<Shop>());

    }
    public void reloadList(){
        ShopService shopService = new Retrofit.Builder()
                .baseUrl(ShopService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopService.class);
        shopService.listRepos().enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if(response.isSuccessful()){
                    System.out.println("recuperation success");
                    listShop.setValue( response.body());
                }else {
                    System.out.printf("erreur de recuperation");
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<Shop>> getListShop() {
        return listShop;
    }
}