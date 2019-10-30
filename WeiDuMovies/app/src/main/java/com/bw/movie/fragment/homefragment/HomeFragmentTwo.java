package com.bw.movie.fragment.homefragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.location.AMapLocation;
import com.bw.movie.R;
import com.bw.movie.fragment.cinemafragment.CinemaFragmentOne;
import com.bw.movie.fragment.cinemafragment.CinemaFragmentThree;
import com.bw.movie.fragment.cinemafragment.CinemaFragmentTwo;
import com.bw.movie.map.AMapUtil;
import com.bw.movie.model.adapter.MyAllFragAdapter;
import com.bw.movie.model.entity.InfoBean;
import com.bw.movie.view.constant.Constant;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/28 20:23
 */
public class HomeFragmentTwo extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomeFragmentTwo";
    private TabLayout tab_cinema;
    private ViewPager vp_cinema;
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;
    private ImageView img_location;
    private TextView tv_location_name;
    private int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment_two, null, false);
        tab_cinema = inflate.findViewById(R.id.tab_cinema);
        vp_cinema = inflate.findViewById(R.id.vp_cinema);
        img_location = inflate.findViewById(R.id.img_location);
        tv_location_name = inflate.findViewById(R.id.tv_location_name);
        return inflate;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //添加标题
        tabs = new ArrayList<>();
        tabs.add("推荐影院");
        tabs.add("附近影院");
        tabs.add("番禺区");

        for (int i = 0; i < tabs.size(); i++) {
            tab_cinema.addTab(tab_cinema.newTab().setText(tabs.get(i)));
        }

        fragments = new ArrayList<>();
        fragments.add(new CinemaFragmentOne());
        fragments.add(new CinemaFragmentTwo());
        fragments.add(new CinemaFragmentThree());

        MyAllFragAdapter myCinemaFragAdapter = new MyAllFragAdapter(getActivity().getSupportFragmentManager(),tabs,fragments);
        vp_cinema.setAdapter(myCinemaFragAdapter);

        tab_cinema.setupWithViewPager(vp_cinema);

        //定位
        img_location.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_location:
                AMapUtil.getLoaction(new AMapUtil.AMapInterface() {
                    @Override
                    public void getAMapLocation(AMapLocation aMapLocation) {
                        if (aMapLocation != null) {
                            if (aMapLocation.getErrorCode() == 0) {
                                //可在其中解析amapLocation获取相应内容。
                                String city = aMapLocation.getCity();
                                tv_location_name.setText(city);
                                Log.e(TAG, "onLocationChanged: "+city );

                                //获取经纬度
                                String longitude = String.valueOf(aMapLocation.getLongitude());//经度
                                String latitude = String.valueOf(aMapLocation.getLatitude());//纬度
                                Log.e(TAG, "getAMapLocation: 经度"+longitude+",纬度"+latitude );

                                //把经纬度存入sp
                                SharedPreferences sp = getActivity().getSharedPreferences(Constant.SP_LL_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString(Constant.LONGITUDE, longitude);
                                editor.putString(Constant.LATITUDE, latitude);
                                editor.commit();

                            }else {
                                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                                Log.e("AmapError","location Error, ErrCode:"
                                        + aMapLocation.getErrorCode() + ", errInfo:"
                                        + aMapLocation.getErrorInfo());
                            }
                        }
                    }
                });
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        AMapUtil.onDestroy();
    }
}
