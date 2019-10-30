package com.bw.movie.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HomeActivity extends IBaseActivity{

    private ImageView img_home;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        img_home = findViewById(R.id.img_home);
    }

    @Override
    protected void initData() {

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onDataSuccess(Object obj) {

    }

    @Override
    public void onDataError(String msg) {

    }
}
