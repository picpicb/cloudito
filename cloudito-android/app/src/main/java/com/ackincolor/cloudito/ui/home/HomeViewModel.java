package com.ackincolor.cloudito.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ackincolor.cloudito.data.Stores;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Bienvenue chez Cloudito");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public ArrayList<Stores> getStores() {
        return null;
    }
}