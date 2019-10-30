package com.bw.movie.fragment.selectcinemafragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bw.movie.R;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/23 14:16
 */
public class SelectCinemaThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_select_cinema_three, container, false);
        return inflate;
    }
}
