package com.akash.newsfeed.view_presenter.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.akash.newsfeed.MvpApp;
import com.akash.newsfeed.R;
import com.akash.newsfeed.di.component.ActivityComponent;
import com.akash.newsfeed.di.component.DaggerActivityComponent;
import com.akash.newsfeed.di.module.ActivityModule;
import com.akash.newsfeed.utils.CommonUtils;

public class BaseActivity extends AppCompatActivity implements MvpView {

    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return CommonUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            Toast.makeText(this, message+"", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.api_default_error)+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }
}
