package com.ackincolor.testbottomnav.ui.scope3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Scope3ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Scope3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is scope 3 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}