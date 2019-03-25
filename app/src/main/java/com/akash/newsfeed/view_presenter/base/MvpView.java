package com.akash.newsfeed.view_presenter.base;

public interface MvpView {

    void showLoading();

    void hideLoading();

    boolean isNetworkConnected();

    void onError(String message);

    void onError(int resId);

}
