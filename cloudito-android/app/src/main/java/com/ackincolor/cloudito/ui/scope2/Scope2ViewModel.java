package com.ackincolor.cloudito.ui.scope2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Scope2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Scope2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}