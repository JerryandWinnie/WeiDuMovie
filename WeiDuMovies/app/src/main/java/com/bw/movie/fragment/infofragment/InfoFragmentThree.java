package com.bw.movie.fragment.infofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bw.movie.R;
import com.bw.movie.model.adapter.MyPhotoAdapter;
import com.bw.movie.model.entity.MovieInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/17 20:37
 */
public class InfoFragmentThree extends Fragment {

    private RecyclerView rv_info_photo;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_info_three, null, false);
        rv_info_photo = inflate.findViewById(R.id.rv_info_photo);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getInfoBean(MovieInfoBean movieInfoBean){
        MovieInfoBean.ResultBean result = movieInfoBean.getResult();
        List<String> posterList = result.getPosterList();

        MyPhotoAdapter myPhotoAdapter = new MyPhotoAdapter(posterList, getContext());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rv_info_photo.setLayoutManager(manager);
        rv_info_photo.setAdapter(myPhotoAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
