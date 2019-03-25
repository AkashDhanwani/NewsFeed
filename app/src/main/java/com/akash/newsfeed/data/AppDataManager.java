package com.akash.newsfeed.data;

import android.content.Context;

import com.akash.newsfeed.data.network.ApiHelper;
import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.data.preferences.PreferencesHelper;
import com.akash.newsfeed.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private Context mContext;
    private ApiHelper mApiHelper;
    private PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper,
                          PreferencesHelper preferencesHelper){
        mContext = context;
        mApiHelper = apiHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Single<ApiResponse> doApiCall() {
        return mApiHelper.doApiCall();
    }

    @Override
    public void setList(String key, List<ApiResponse.Article> list) {
        mPreferencesHelper.setList(key, list);
    }

    @Override
    public List<ApiResponse.Article> getList(String key) {
        return mPreferencesHelper.getList(key);
    }
}
