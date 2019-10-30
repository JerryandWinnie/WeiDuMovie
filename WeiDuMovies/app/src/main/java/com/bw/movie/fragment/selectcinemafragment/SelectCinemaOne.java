package com.bw.movie.fragment.selectcinemafragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.contract.IContract;
import com.bw.movie.model.adapter.MyAreaAdapter;
import com.bw.movie.model.adapter.MyCInfoAdapter;
import com.bw.movie.model.entity.AreaBean;
import com.bw.movie.model.entity.CinemaInfoBean;
import com.bw.movie.model.entity.InfoBean;
import com.bw.movie.presenter.MyAllPresenter;
import com.bw.movie.view.activity.SelectActivity;
import com.bw.movie.view.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/23 14:16
 */
public class SelectCinemaOne extends Fragment implements IContract.IView {

    private RecyclerView rv_select_cinema_area,rv_select_cinema_addr;
    private IContract.Presenter presenter;
    private int movieId,page=1,count=5;
    private String name,imageUrl,videoUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_select_cinema_one, container, false);
        presenter = new MyAllPresenter(this);
        rv_select_cinema_area = inflate.findViewById(R.id.rv_select_cinema_area);
        rv_select_cinema_addr = inflate.findViewById(R.id.rv_select_cinema_addr);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //查询区域
        presenter.areaList();

        Intent intent = getActivity().getIntent();
        movieId = intent.getIntExtra(Constant.MOVIE_ID, 1);
        name = intent.getStringExtra(Constant.MOVIE_NAME);
        imageUrl = intent.getStringExtra(Constant.MOVIE_IMGURL);
        videoUrl = intent.getStringExtra(Constant.MOVIE_URL);
    }



    @Override
    public void onDataSuccess(Object obj) {
        if (obj instanceof AreaBean) {
            AreaBean areaBean = (AreaBean) obj;
            List<AreaBean.ResultBean> result = areaBean.getResult();

            MyAreaAdapter myAreaAdapter = new MyAreaAdapter(result, getContext());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            rv_select_cinema_area.setLayoutManager(manager);
            rv_select_cinema_area.setAdapter(myAreaAdapter);

            presenter.cinemaInfoList(movieId,1,page,count);

            myAreaAdapter.setAreaListener(new MyAreaAdapter.AreaListener() {
                @Override
                public void getRegionId(int regionId) {
                    presenter.cinemaInfoList(movieId,regionId,page,count);
                }
            });
        }else if(obj instanceof CinemaInfoBean){
            CinemaInfoBean cinemaInfoBean = (CinemaInfoBean) obj;
            List<CinemaInfoBean.ResultBean> result = cinemaInfoBean.getResult();

            MyCInfoAdapter myCInfoAdapter = new MyCInfoAdapter(result, getContext());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            rv_select_cinema_addr.setLayoutManager(manager);
            rv_select_cinema_addr.setAdapter(myCInfoAdapter);

            myCInfoAdapter.setcInfoListener(new MyCInfoAdapter.CInfoListener() {
                @Override
                public void getCinemaId(int cinemaId) {
                    Intent intent = new Intent(getContext(), SelectActivity.class);
                    intent.putExtra(Constant.CINEMA_ID,cinemaId);
                    intent.putExtra(Constant.MOVIE_ID,movieId);
                    intent.putExtra(Constant.MOVIE_NAME,name);
                    intent.putExtra(Constant.MOVIE_IMGURL,imageUrl);
                    intent.putExtra(Constant.MOVIE_URL,videoUrl);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onDataError(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter = null;
        }
    }
}
