package com.bw.movie.fragment.infofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.adapter.MyActorAdapter;
import com.bw.movie.model.adapter.MyDirectorAdapter;
import com.bw.movie.model.entity.MovieInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/17 20:37
 */
public class InfoFragmentOne extends Fragment {


    private TextView tv_info_summary,tv_info_director,tv_info_actor;
    private RecyclerView rv_info_director,rv_info_actor;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_info_one, null, false);
        tv_info_summary = inflate.findViewById(R.id.tv_info_summary);
        tv_info_director = inflate.findViewById(R.id.tv_info_director);
        tv_info_actor = inflate.findViewById(R.id.tv_info_actor);
        rv_info_director = inflate.findViewById(R.id.rv_info_director);
        rv_info_actor = inflate.findViewById(R.id.rv_info_actor);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getInfoBean(MovieInfoBean movieInfoBean){
        MovieInfoBean.ResultBean result = movieInfoBean.getResult();
        //导演
        List<MovieInfoBean.ResultBean.MovieDirectorBean> movieDirector = result.getMovieDirector();
        int directorSize = movieDirector.size();
        tv_info_director.setText("导演（"+directorSize+"）");
        //创建适配器
        MyDirectorAdapter myDirectorAdapter = new MyDirectorAdapter(movieDirector, getContext());
        //网格布局
        GridLayoutManager directorManager = new GridLayoutManager(getContext(),3);
        directorManager.setOrientation(RecyclerView.VERTICAL);
        rv_info_director.setLayoutManager(directorManager);
        //设置适配器
        rv_info_director.setAdapter(myDirectorAdapter);


        //演员
        List<MovieInfoBean.ResultBean.MovieActorBean> movieActor = result.getMovieActor();
        int actorSize = movieActor.size();
        tv_info_actor.setText("演员（"+actorSize+"）");
        //创建适配器
        MyActorAdapter myActorAdapter = new MyActorAdapter(movieActor, getContext());
        //线性布局
        LinearLayoutManager actorManager = new LinearLayoutManager(getContext());
        actorManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_info_actor.setLayoutManager(actorManager);
        //设置适配器
        rv_info_actor.setAdapter(myActorAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
