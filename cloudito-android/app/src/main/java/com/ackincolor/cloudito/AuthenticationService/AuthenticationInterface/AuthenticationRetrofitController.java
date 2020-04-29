package com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface;

import android.content.Context;

import com.ackincolor.cloudito.AuthenticationService.AuthenticationAndroidService;
import com.ackincolor.cloudito.entities.Credentials;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticationRetrofitController {
    private Context context;
    private AuthenticationAndroidService androidService;
    static final String BASE_URL = "http://ackincolor.ddns.net:3082/"; //port 3082 replace notif
    private Gson gson;
    public AuthenticationRetrofitController(Context context, AuthenticationAndroidService androidService) {
        this.context = context;
        this.androidService = androidService;
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

        service.authenticateLogin(crendentials).enqueue(new Callback<Credentials>() {

            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {

            }

            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {

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

        service.authenticateOtpCode(crendentials).enqueue(new Callback<Credentials>() {

            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {

            }

            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {

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

        service.authenticateInscription(crendentials).enqueue(new Callback<Credentials>() {

            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {

            }

            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {

            }
        });
    }

}
