package com.bw.movie.model.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/14 20:24
 */
public class MyAllFragAdapter extends FragmentPagerAdapter {
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;

    public MyAllFragAdapter(FragmentManager supportFragmentManager, ArrayList<String> tabs, ArrayList<Fragment> fragments) {
        super(supportFragmentManager);
        this.tabs = tabs;
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
