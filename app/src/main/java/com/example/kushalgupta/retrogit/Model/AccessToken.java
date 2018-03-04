package com.example.kushalgupta.retrogit.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kushalgupta on 05/03/18.
 */

public class AccessToken {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
