package com.akash.newsfeed.view_presenter.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.akash.newsfeed.data.DataManager;
import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.utils.rx.SchedulerProvider;
import com.akash.newsfeed.view_presenter.base.BasePresenter;
import com.akash.newsfeed.view_presenter.base.MvpView;
import com.androidnetworking.error.ANError;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    public static final String TAG = "offline";
    public static final String key = "JSON";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
            .doApiCall()
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(new Consumer<ApiResponse>() {
                @Override
                public void accept(ApiResponse apiResponse) throws Exception {
                    if (apiResponse != null && apiResponse.getArticles() != null) {
                        getMvpView().updateNews(apiResponse.getArticles());
                    }
                    getMvpView().hideLoading();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    if (!isViewAttached()) {
                        return;
                    }

                    if(!getMvpView().isNetworkConnected()){
                        Log.d(TAG, "In offline mode");
                        getMvpView().updateNews(getDataManager().getList(key));
                    }
                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                }
            }));
    }

    @Override
    public void setList(String key, List<ApiResponse.Article> list) {
        getDataManager().setList(key, list);
    }

    @Override
    public List<ApiResponse.Article> getList(String key) {
        return getDataManager().getList(key);
    }

    @Override
    public void onCardClicked(Bundle bundle, View image, View title) {
        getMvpView().openDetailActivity(bundle, image, title);
    }
}
