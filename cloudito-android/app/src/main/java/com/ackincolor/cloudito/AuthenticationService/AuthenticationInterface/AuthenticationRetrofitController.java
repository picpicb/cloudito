package com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface;

import android.content.Context;
import android.util.Log;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationLoginAndroidService;
import com.ackincolor.cloudito.AuthenticationService.AuthenticationOTPCodeAndroidService;
import com.ackincolor.cloudito.AuthenticationService.AuthenticationRegisterAndroidService;
import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticationRetrofitController {
    private AuthenticationLoginAndroidService authenticationLoginAndroidService;
    private AuthenticationOTPCodeAndroidService authenticationOTPCodeAndroidService;
    private AuthenticationRegisterAndroidService authenticationRegisterAndroidService;
    static final String BASE_URL = "http://ackincolor.ddns.net:3082/"; //port 3082 replace notif
    private Gson gson;

    public AuthenticationRetrofitController(AuthenticationLoginAndroidService androidService) {
        this.authenticationLoginAndroidService = androidService;
    }

    public AuthenticationRetrofitController(AuthenticationOTPCodeAndroidService androidService) {
        this.authenticationOTPCodeAndroidService = androidService;
    }

    public AuthenticationRetrofitController (AuthenticationRegisterAndroidService authenticationRegisterAndroidService){
        this.authenticationRegisterAndroidService = authenticationRegisterAndroidService;
    }

    public void authenticateLogin(Credentials crendentials){
        this.gson = new Gson();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build();

        AuthenticationRetrofitService service = retrofit.create(AuthenticationRetrofitService.class);

        service.authenticateLogin(crendentials).enqueue(new Callback<AuthStatus>() {

            @Override
            public void onResponse(Call<AuthStatus> call, Response<AuthStatus> response) {
                if(response.code()!=200){
                    authenticationLoginAndroidService.onFailureAuthenticateLogin();
                    return;
                }
                authenticationLoginAndroidService.onResponseAuthenticateLogin(response.body());
            }

            @Override
            public void onFailure(Call<AuthStatus> call, Throwable t) {
                authenticationLoginAndroidService.onFailureAuthenticateLogin();
            }
        });
    }

    public void authenticateOtpCode(Credentials crendentials){
        this.gson = new Gson();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build();

        AuthenticationRetrofitService service = retrofit.create(AuthenticationRetrofitService.class);

        service.authenticateOtpCode(crendentials).enqueue(new Callback<AuthStatus>() {

            @Override
            public void onResponse(Call<AuthStatus> call, Response<AuthStatus> response) {
                if(response.code()!=200){
                    authenticationOTPCodeAndroidService.onFailureAuthenticationOTPCode();
                    return;
                }
                authenticationOTPCodeAndroidService.onResponseAuthenticateOTPCode(response.body());
            }

            @Override
            public void onFailure(Call<AuthStatus> call, Throwable t) {
                authenticationOTPCodeAndroidService.onFailureAuthenticationOTPCode();
            }
        });
    }

    public void authenticateInscription(Credentials crendentials){
        this.gson = new Gson();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build();

        AuthenticationRetrofitService service = retrofit.create(AuthenticationRetrofitService.class);

        service.authenticateInscription(crendentials).enqueue(new Callback<HashMap<String,String>>() {

            @Override
            public void onResponse(Call<HashMap<String,String>> call, Response<HashMap<String,String>> response) {
                Log.d("DEBUG",""+response.code());
                if(response.code()!=201){
                    authenticationRegisterAndroidService.onFailureAuthenticateInscription();
                    return;
                }
                authenticationRegisterAndroidService.onResponseAuthenticateInscription(response.body().get("key"));
            }

            @Override
            public void onFailure(Call<HashMap<String,String>> call, Throwable t) {
                t.printStackTrace();
                authenticationRegisterAndroidService.onFailureAuthenticateInscription();
            }
        });
    }

}
