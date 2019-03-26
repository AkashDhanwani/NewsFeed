package com.akash.newsfeed.view_presenter.main;

import android.os.Bundle;
import android.view.View;

import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.view_presenter.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void updateNews(List<ApiResponse.Article> newsList);

    void openDetailActivity(Bundle bundle, View image, View title);

}
