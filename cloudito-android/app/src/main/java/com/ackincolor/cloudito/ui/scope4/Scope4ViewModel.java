package com.ackincolor.cloudito.ui.scope4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Scope4ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Scope4ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}