package com.akash.newsfeed.view_presenter.main;

import android.os.Bundle;
import android.view.View;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.view_presenter.base.MvpPresenter;

import java.util.List;


public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

        void onCardClicked(Bundle bundle, View image, View title);

    void onViewPrepared();

    void setList(String key, List<ApiResponse.Article> list);

    List<ApiResponse.Article> getList(String key);
}
