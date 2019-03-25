package com.akash.newsfeed.data.network;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppApiHelper implements ApiHelper{

    @Inject
    public AppApiHelper(){}

    @Override
    public Single<ApiResponse> doApiCall() {
        return Rx2AndroidNetworking.get(ApiAddress.NEWS_INDIA)
                .build()
                .getObjectSingle(ApiResponse.class);
    }
}
