package com.ackincolor.cloudito.AuthenticationService.AuthenticationInterface;

import com.ackincolor.cloudito.entities.Credentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthenticationRetrofitService {

    @POST("authenticate/login")
    Call<Credentials> authenticateLogin(@Body Credentials credentials);

    @POST("authenticate/code")
    Call<Credentials> authenticateOtpCode(@Body Credentials credentials);

    @POST("authenticate/inscription")
    Call<Credentials> authenticateInscription(@Body Credentials credentials);
}
