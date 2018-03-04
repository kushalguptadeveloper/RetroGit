package com.example.kushalgupta.retrogit.interfaces;

import com.example.kushalgupta.retrogit.Model.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by kushalgupta on 05/03/18.
 */

public interface GitHubClient {
@Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String ClientId,
            @Field("client_secret") String ClientSecret,
            @Field("code") String code
);
}
