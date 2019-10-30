package com.bw.movie.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.fragment.homefragment.HomeFragmentOne;
import com.bw.movie.fragment.homefragment.HomeFragmentThree;
import com.bw.movie.fragment.homefragment.HomeFragmentTwo;
import com.bw.movie.model.adapter.MyHomeFragAdapter;
import com.bw.movie.model.entity.InfoBean;
import com.bw.movie.view.constant.Constant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends IBaseActivity implements View.OnClickListener {

    private ViewPager vp;
    private LinearLayout linear_one,linear_two,linear_three;
    private LinearLayout linear_one_search,linear_two_movie,linear_three_me;
    private ImageView img_one,img_two,img_three;
    private ArrayList<Fragment> fragments;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        vp = findViewById(R.id.vp);
        linear_one = findViewById(R.id.linear_one);
        linear_two = findViewById(R.id.linear_two);
        linear_three = findViewById(R.id.linear_three);
        linear_one_search = findViewById(R.id.linear_one_search);
        linear_two_movie = findViewById(R.id.linear_two_movie);
        linear_three_me = findViewById(R.id.linear_three_me);
        img_one = findViewById(R.id.img_one);
        img_two = findViewById(R.id.img_two);
        img_three = findViewById(R.id.img_three);
    }

    @Override
    protected void initData() {

        //设置预加载
        vp.setOffscreenPageLimit(3);

        linear_one.setOnClickListener(this);
        linear_two.setOnClickListener(this);
        linear_three.setOnClickListener(this);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragmentOne());
        fragments.add(new HomeFragmentTwo());
        fragments.add(new HomeFragmentThree());

        MyHomeFragAdapter myFragAdapter = new MyHomeFragAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(myFragAdapter);


        Intent intent = getIntent();
        int index = intent.getIntExtra("intent", 0);
        if(index==0){
            img_one.setVisibility(View.GONE);
            img_two.setVisibility(View.VISIBLE);
            img_three.setVisibility(View.VISIBLE);
            linear_one_search.setVisibility(View.VISIBLE);
            linear_two_movie.setVisibility(View.GONE);
            linear_three_me.setVisibility(View.GONE);
            vp.setCurrentItem(0);
        }else if(index==2){
            img_one.setVisibility(View.VISIBLE);
            img_two.setVisibility(View.VISIBLE);
            img_three.setVisibility(View.GONE);
            linear_one_search.setVisibility(View.GONE);
            linear_two_movie.setVisibility(View.GONE);
            linear_three_me.setVisibility(View.VISIBLE);
            vp.setCurrentItem(2);
        }

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        img_one.setVisibility(View.GONE);
                        img_two.setVisibility(View.VISIBLE);
                        img_three.setVisibility(View.VISIBLE);
                        linear_one_search.setVisibility(View.VISIBLE);
                        linear_two_movie.setVisibility(View.GONE);
                        linear_three_me.setVisibility(View.GONE);
                        break;
                    case 1:
                        img_one.setVisibility(View.VISIBLE);
                        img_two.setVisibility(View.GONE);
                        img_three.setVisibility(View.VISIBLE);
                        linear_one_search.setVisibility(View.GONE);
                        linear_two_movie.setVisibility(View.VISIBLE);
                        linear_three_me.setVisibility(View.GONE);
                        break;
                    case 2:
                        img_one.setVisibility(View.VISIBLE);
                        img_two.setVisibility(View.VISIBLE);
                        img_three.setVisibility(View.GONE);
                        linear_one_search.setVisibility(View.GONE);
                        linear_two_movie.setVisibility(View.GONE);
                        linear_three_me.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linear_one:
                img_one.setVisibility(View.GONE);
                img_two.setVisibility(View.VISIBLE);
                img_three.setVisibility(View.VISIBLE);
                linear_one_search.setVisibility(View.VISIBLE);
                linear_two_movie.setVisibility(View.GONE);
                linear_three_me.setVisibility(View.GONE);
                vp.setCurrentItem(0);
                break;
            case R.id.linear_two:
                img_one.setVisibility(View.VISIBLE);
                img_two.setVisibility(View.GONE);
                img_three.setVisibility(View.VISIBLE);
                linear_one_search.setVisibility(View.GONE);
                linear_two_movie.setVisibility(View.VISIBLE);
                linear_three_me.setVisibility(View.GONE);
                vp.setCurrentItem(1);
                break;
            case R.id.linear_three:
                img_one.setVisibility(View.VISIBLE);
                img_two.setVisibility(View.VISIBLE);
                img_three.setVisibility(View.GONE);
                linear_one_search.setVisibility(View.GONE);
                linear_two_movie.setVisibility(View.GONE);
                linear_three_me.setVisibility(View.VISIBLE);
                vp.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onDataSuccess(Object obj) {

    }

    @Override
    public void onDataError(String msg) {

    }
}
