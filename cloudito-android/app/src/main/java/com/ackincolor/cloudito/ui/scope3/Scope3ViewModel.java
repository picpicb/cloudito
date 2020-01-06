package com.ackincolor.cloudito.ui.scope3;

import com.ackincolor.cloudito.controllers.ParcoursController;

import java.util.UUID;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Scope3ViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ParcoursController parcoursController;

    public Scope3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is scope 3 fragment");
        //debut recuperation
        this.parcoursController = new ParcoursController();
        this.parcoursController.getParcours(UUID.randomUUID());
    }

    public LiveData<String> getText() {
        return mText;
    }
}