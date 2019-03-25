package com.akash.newsfeed.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;

public class ApiHeader {

    @SerializedName("api_key")
    @Expose
    private String mApiKey;

    @Inject
    ApiHeader(String apiKey){
        mApiKey = apiKey;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }
}
