package com.akash.newsfeed.view_presenter.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akash.newsfeed.R;
import com.akash.newsfeed.view_presenter.webview.WebViewActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailActivity extends AppCompatActivity {

    ImageView imageDetail;
    TextView  titleDetail, descriptionDetail;
    Button viewfullbtnDetail;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageDetail = findViewById(R.id.imageDetail);
        titleDetail = findViewById(R.id.titleDetail);
        descriptionDetail = findViewById(R.id.descriptionDetail);
        viewfullbtnDetail = findViewById(R.id.viewfullbtnDetail);

        bundle = getIntent().getExtras();

        Glide.with(DetailActivity.this).load(bundle.getString("imagepath")).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageDetail);
        titleDetail.setText(bundle.getString("title"));
        descriptionDetail.setText(bundle.getString("content"));

        viewfullbtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, WebViewActivity.class);
                intent.putExtra("url", bundle.getString("urltopage"));
                startActivity(intent);
            }
        });
    }
}
