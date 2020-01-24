package com.ackincolor.cloudito.ui.scope1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Scope1ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Scope1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}