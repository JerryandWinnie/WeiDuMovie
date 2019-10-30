package com.bw.movie.view.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.view.view.TitleView;

public class SetActivity extends IBaseActivity implements View.OnClickListener {

    private TitleView title_set;
    private TextView tv_myinfo_cache;
    private ImageView img_my_set_update;
    private RelativeLayout rl_my_set_back;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        title_set = findViewById(R.id.title_set);
        rl_my_set_back = findViewById(R.id.rl_my_set_back);
        tv_myinfo_cache = findViewById(R.id.tv_myinfo_cache);
        img_my_set_update = findViewById(R.id.img_my_set_update);
    }

    @Override
    protected void initData() {
        title_set.title_tv.setText("设置");



        title_set.title_img.setOnClickListener(this);
        rl_my_set_back.setOnClickListener(this);
    }

    @Override
    public void onDataSuccess(Object obj) {

    }

    @Override
    public void onDataError(String msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_img:
                finish();
                break;
            case R.id.rl_my_set_back:
                Toast.makeText(this, "退出失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
