package com.akash.newsfeed.di.component;


import com.akash.newsfeed.di.PerActivity;
import com.akash.newsfeed.di.module.ActivityModule;
import com.akash.newsfeed.view_presenter.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
