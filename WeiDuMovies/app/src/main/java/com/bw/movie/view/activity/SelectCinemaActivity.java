package com.bw.movie.view.activity;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.fragment.cinemafragment.CinemaFragmentOne;
import com.bw.movie.fragment.cinemafragment.CinemaFragmentThree;
import com.bw.movie.fragment.cinemafragment.CinemaFragmentTwo;
import com.bw.movie.fragment.selectcinemafragment.SelectCinemaOne;
import com.bw.movie.fragment.selectcinemafragment.SelectCinemaThree;
import com.bw.movie.fragment.selectcinemafragment.SelectCinemaTwo;
import com.bw.movie.model.adapter.MyAllFragAdapter;
import com.bw.movie.view.constant.Constant;
import com.bw.movie.view.view.TitleView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SelectCinemaActivity extends IBaseActivity implements View.OnClickListener {

    private TitleView title_select_cinema;
    private TabLayout tab_select_cinema;
    private ViewPager vp_select_cinema;
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_select_cinema;
    }

    @Override
    protected void initView() {
        title_select_cinema = findViewById(R.id.title_select_cinema);
        tab_select_cinema = findViewById(R.id.tab_select_cinema);
        vp_select_cinema = findViewById(R.id.vp_select_cinema);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra(Constant.MOVIE_NAME);
        title_select_cinema.title_tv.setText(name);

        //添加标题
        tabs = new ArrayList<>();
        tabs.add("海淀区");
        tabs.add("今天");
        tabs.add("价格最低");

        for (int i = 0; i < tabs.size(); i++) {
            tab_select_cinema.addTab(tab_select_cinema.newTab().setText(tabs.get(i)));
        }

        fragments = new ArrayList<>();
        fragments.add(new SelectCinemaOne());
        fragments.add(new SelectCinemaTwo());
        fragments.add(new SelectCinemaThree());

        MyAllFragAdapter myCinemaFragAdapter = new MyAllFragAdapter(getSupportFragmentManager(),tabs,fragments);
        vp_select_cinema.setAdapter(myCinemaFragAdapter);

        tab_select_cinema.setupWithViewPager(vp_select_cinema);


        title_select_cinema.title_img.setOnClickListener(this);
    }

    @Override
    public void onDataSuccess(Object obj) {

    }

    @Override
    public void onDataError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_img:
                finish();
                break;
        }
    }
}
