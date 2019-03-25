package com.akash.newsfeed.di.module;

import android.app.Application;
import android.content.Context;

import com.akash.newsfeed.data.AppDataManager;
import com.akash.newsfeed.data.DataManager;
import com.akash.newsfeed.data.network.ApiHelper;
import com.akash.newsfeed.data.network.AppApiHelper;
import com.akash.newsfeed.data.preferences.PreferencesHelper;
import com.akash.newsfeed.data.preferences.SharedPreferencesHelper;
import com.akash.newsfeed.di.ApplicationContext;
import com.akash.newsfeed.di.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        return sharedPreferencesHelper;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return "JSONData";
    }
}
