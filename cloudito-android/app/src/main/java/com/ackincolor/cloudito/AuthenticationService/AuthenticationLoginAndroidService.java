package com.ackincolor.cloudito.AuthenticationService;

import android.util.Log;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface.AuthenticationRetrofitController;
import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;
import com.ackincolor.cloudito.ui.authentication.AuthentificationFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationLoginAndroidService {

    private AuthentificationFragment currentActivity;
    private AuthenticationRetrofitController retrofitController;

    private final String REGEX_LOGIN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private final String TAG = "DEBUG AUTHENTICATION";
    private final int AUTHENT_STATUS_STATE =1;

    public AuthenticationLoginAndroidService(AuthentificationFragment activity){
       this.currentActivity = activity;
       retrofitController = new AuthenticationRetrofitController(this);
    }

    // Request to first authent
    public void authenticateLogin(Credentials credentials){
        Pattern pattern = Pattern.compile(REGEX_LOGIN);
        Matcher matcher = pattern.matcher(credentials.getLogin());
        if(matcher.matches()){
            retrofitController.authenticateLogin(credentials);
        }else{
            onFailureAuthenticateLogin();
        }

    }

    // Response from first authent request
    public void onResponseAuthenticateLogin(AuthStatus authStatus){
        Log.d(TAG, "Passage dans le on response");
        if(AUTHENT_STATUS_STATE != authStatus.getStateAuthent()) {
            onFailureAuthenticateLogin();
            return;
        }
        currentActivity.successAuthent(authStatus);
    }

    // Failure of authent
    public void onFailureAuthenticateLogin(){
        Log.d(TAG, "Passage dans le on failure");
        currentActivity.failAuthent();
    }
}
