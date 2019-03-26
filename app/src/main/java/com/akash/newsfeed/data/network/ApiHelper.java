package com.akash.newsfeed.data.network;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;

import io.reactivex.Single;

public interface ApiHelper {

    Single<ApiResponse> doApiCall();
}
