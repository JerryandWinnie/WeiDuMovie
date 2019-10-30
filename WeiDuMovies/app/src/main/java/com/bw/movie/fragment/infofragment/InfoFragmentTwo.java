package com.bw.movie.fragment.infofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.adapter.MyVideoAdapter;
import com.bw.movie.model.entity.MovieInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/17 20:37
 */
public class InfoFragmentTwo extends Fragment {

    private RecyclerView rv_info_jcv;
    private MyVideoAdapter myVideoAdapter;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_info_two, null, false);
        rv_info_jcv = inflate.findViewById(R.id.rv_info_jcv);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getInfoBean(MovieInfoBean movieInfoBean){
        MovieInfoBean.ResultBean result = movieInfoBean.getResult();
        List<MovieInfoBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();

        myVideoAdapter = new MyVideoAdapter(shortFilmList, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rv_info_jcv.setLayoutManager(manager);
        rv_info_jcv.setAdapter(myVideoAdapter);
    }


    @Override
    public void onPause() {
        super.onPause();
        //myVideoAdapter.releaseVideo();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
