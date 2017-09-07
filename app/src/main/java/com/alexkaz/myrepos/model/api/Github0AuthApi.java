package com.alexkaz.myrepos.model.api;

import com.alexkaz.myrepos.model.entities.TokenEntity;
import com.alexkaz.myrepos.utils.GithubConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Github0AuthApi {

    String END_POINT = "https://github.com/login/oauth/";

    @Headers("Accept:application/json")
    @GET("access_token?client_id=" + GithubConfig.CLIENT_ID
                           + "&client_secret=" + GithubConfig.CLIENT_SECRET)
    Call<TokenEntity> getToken(@Query("code") String code);

}
