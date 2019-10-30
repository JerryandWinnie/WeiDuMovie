package com.bw.movie.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.fragment.searchfragment.SearchFragmentOne;
import com.bw.movie.model.adapter.MyAllFragAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/29 14:41
 */
public class MoreActivity extends IBaseActivity implements View.OnClickListener {

    private static final String NUM = "num";
    private ImageView title_img_search;
    private EditText edit_search;
    private TabLayout tab_search;
    private ViewPager vp_search;
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_more;
    }

    @Override
    protected void initView() {
        title_img_search = findViewById(R.id.title_img_search);
        edit_search = findViewById(R.id.edit_search);
        tab_search = findViewById(R.id.tab_search);
        vp_search = findViewById(R.id.vp_search);
    }

    @Override
    protected void initData() {

        //获取intent值
        Intent intent = getIntent();
        int num = intent.getIntExtra(NUM, 0);

        //添加标题
        tabs = new ArrayList<>();
        tabs.add("正在热映");
        tabs.add("即将上映");
        tabs.add("热门电影");

        for (int i = 0; i < tabs.size(); i++) {
            tab_search.addTab(tab_search.newTab().setText(tabs.get(i)));
        }

        fragments = new ArrayList<>();
        fragments.add(new SearchFragmentOne());
        fragments.add(new SearchFragmentOne());
        fragments.add(new SearchFragmentOne());

        MyAllFragAdapter myAllFragAdapter = new MyAllFragAdapter(getSupportFragmentManager(),tabs,fragments);
        vp_search.setAdapter(myAllFragAdapter);
        //联动
        tab_search.setupWithViewPager(vp_search);

        switch (num){
            case 1:
                vp_search.setCurrentItem(0);
                break;
            case 2:
                vp_search.setCurrentItem(1);
                break;
            case 3:
                vp_search.setCurrentItem(2);
                break;
        }


        title_img_search.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_img_search:
                finish();
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
