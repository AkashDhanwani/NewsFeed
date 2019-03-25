package com.akash.newsfeed.di.component;


import android.app.Application;
import android.content.Context;

import com.akash.newsfeed.MvpApp;
import com.akash.newsfeed.data.DataManager;
import com.akash.newsfeed.di.ApplicationContext;
import com.akash.newsfeed.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp mvpApp);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
