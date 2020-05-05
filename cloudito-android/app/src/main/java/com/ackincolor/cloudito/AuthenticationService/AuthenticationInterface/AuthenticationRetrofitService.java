package com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface;

import com.ackincolor.cloudito.entities.AuthStatus;
import com.ackincolor.cloudito.entities.Credentials;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthenticationRetrofitService {

    @POST("authenticate/login")
    Call<AuthStatus> authenticateLogin(@Body Credentials credentials);

    @POST("authenticate/code")
    Call<AuthStatus> authenticateOtpCode(@Body Credentials credentials);

    @POST("authenticate/inscription")
    Call<HashMap<String,String>> authenticateInscription(@Body Credentials credentials);
}
