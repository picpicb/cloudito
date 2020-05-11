package com.ackincolor.cloudito.AuthenticationService;

import android.util.Log;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface.AuthenticationRetrofitController;
import com.ackincolor.cloudito.entities.Credentials;
import com.ackincolor.cloudito.ui.authentication.RegisterActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationRegisterAndroidService {

    private AuthenticationRetrofitController retrofitController;
    private RegisterActivity currentActivity;

    private final String REGEX_LOGIN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private final String TAG = "DEBUG AUTHENTICATION";

    public AuthenticationRegisterAndroidService (RegisterActivity activity){
        this.currentActivity = activity;
        retrofitController = new AuthenticationRetrofitController(this);
    }

    public void authenticateInscription(Credentials credentials){
        Pattern pattern = Pattern.compile(REGEX_LOGIN);
        Matcher matcher = pattern.matcher(credentials.getLogin());
        if(matcher.matches()){
            retrofitController.authenticateInscription(credentials);
        }else{
            currentActivity.badLogin();
        }
    }

    public void onResponseAuthenticateInscription(String key){
        Log.d(TAG, "Passage dans le on response");
        currentActivity.successRegister(key);
    }

    public void onFailureAuthenticateInscription(){
        Log.d(TAG, "Passage dans le on Failure");
        currentActivity.failRegister();
    }
}
