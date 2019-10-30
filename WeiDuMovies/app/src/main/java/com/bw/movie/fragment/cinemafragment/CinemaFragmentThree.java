package com.bw.movie.fragment.cinemafragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.util.HttpUtil;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/14 20:13
 */
public class CinemaFragmentThree extends Fragment {

    private LinearLayout linear_cinema_three;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cinema_fragment_three, container, false);
        linear_cinema_three = inflate.findViewById(R.id.linear_cinema_three);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //判断网络
        boolean netWork = HttpUtil.getInstance().isNetWork();
        if (netWork) {
            linear_cinema_three.setVisibility(View.GONE);
        }else {
            linear_cinema_three.setVisibility(View.VISIBLE);
        }

    }
}
