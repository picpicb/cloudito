package com.ackincolor.cloudito.ui.scope4;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ackincolor.cloudito.entities.Coordinate;
import com.ackincolor.cloudito.services.Scope4Service;

public class Scope4ViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public Scope4ViewModel() {
        //Scope4Service srvc4 = new Scope4Service(new Coordinate(1,2),new Coordinate(3,3));


        //Log.d("DEBUG",srvc4.printMap());

        mText = new MutableLiveData<>();
        //mText.setValue(srvc4.printMap());
        mText.setValue("hello from scope 4");

        //debut recuperation

    }

    public LiveData<String> getText() {
        return mText;
    }
}