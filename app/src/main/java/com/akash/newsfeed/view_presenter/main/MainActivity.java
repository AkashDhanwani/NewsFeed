package com.akash.newsfeed.view_presenter.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.akash.newsfeed.MvpApp;
import com.akash.newsfeed.R;
import com.akash.newsfeed.data.DataManager;
import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.akash.newsfeed.view_presenter.base.BaseActivity;
import com.akash.newsfeed.view_presenter.detail.DetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

    public static final String TAG = "offline";
    public static final String file_name = "JSONData";
    public static final String key = "JSON";

    @Inject
    MainMvpPresenter<MainMvpView> mMainMvpPresenter;

    @Inject
    LinearLayoutManager mLinearLayoutManager;

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientaton = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientaton);

        sharedPreferences = getSharedPreferences(file_name,MODE_PRIVATE);

        getActivityComponent().inject(this);

        mMainMvpPresenter.onAttach(this);

        mRecyclerView = findViewById(R.id.recyclerViewMain);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mMainMvpPresenter.onViewPrepared();

    }

    @Override
    public void updateNews(List<ApiResponse.Article> newsList) {
        Log.d(TAG, "now in updateNew and "+newsList.size());
        mAdapter = new Adapter(newsList, getApplicationContext(), mMainMvpPresenter);
        mRecyclerView.setAdapter(mAdapter);
        mMainMvpPresenter.setList(key, newsList);
        //setList(key, newsList);
    }

    @Override
    public void openDetailActivity(Bundle bundle, View image, View title) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(image, "image"),
                Pair.create(title, "title"));
        intent.putExtras(bundle);
        startActivity(intent, options.toBundle());
    }

}
