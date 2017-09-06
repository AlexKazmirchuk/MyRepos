package com.alexkaz.myrepos.model.api;


import com.alexkaz.myrepos.model.entities.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BasicAuthApi {

    String END_POINT = "https://api.github.com/";

    @GET("user")
    Call<UserEntity> checkCredentials(@Header("Authorization") String token);

}
