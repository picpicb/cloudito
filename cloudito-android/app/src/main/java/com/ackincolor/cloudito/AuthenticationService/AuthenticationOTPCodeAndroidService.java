package com.ackincolor.cloudito.AuthenticationService;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface.AuthenticationRetrofitController;
import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;
import com.ackincolor.cloudito.ui.authentication.GoogleAuthFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationOTPCodeAndroidService {

    private GoogleAuthFragment currentActivity;
    private AuthenticationRetrofitController retrofitController;

    private final String REGEX_CODE = "^[A-Za-z0-9+_.-]+@(.+)$";

    public AuthenticationOTPCodeAndroidService(GoogleAuthFragment activity){
        this.currentActivity = activity;
        this.retrofitController = new AuthenticationRetrofitController(this);
    }

    // Request to first authent
    public void authenticateOTPCode(Credentials credentials){
        final Pattern pattern = Pattern.compile( REGEX_CODE );
        final Matcher matcher = pattern.matcher( credentials.getCode() );
        if(matcher.matches()){
            retrofitController.authenticateOtpCode(credentials);
        }else{
            onFailureAuthenticationOTPCode();
        }

    }

    // Response from first authent request
    public void onResponseAuthenticateOTPCode(AuthStatus authStatus){

    }

    public void onFailureAuthenticationOTPCode(){

    }
}
