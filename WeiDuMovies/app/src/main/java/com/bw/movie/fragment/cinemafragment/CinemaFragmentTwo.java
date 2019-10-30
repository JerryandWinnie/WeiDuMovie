package com.bw.movie.fragment.cinemafragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.contract.IContract;
import com.bw.movie.model.adapter.MyNearbyAdapter;
import com.bw.movie.model.entity.InfoBean;
import com.bw.movie.model.entity.NearbyBean;
import com.bw.movie.util.HttpUtil;
import com.bw.movie.presenter.MyAllPresenter;
import com.bw.movie.view.activity.SelectActivity;
import com.bw.movie.view.constant.Constant;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/14 20:13
 */
public class CinemaFragmentTwo extends Fragment implements IContract.IView {

    private XRecyclerView xrv_cinema_two;
    private IContract.Presenter presenter;
    private int page=1,count=10;
    private LinearLayout linear_cinema_two;
    private MyNearbyAdapter myNearbyAdapter;
    private List<NearbyBean.ResultBean> result;
    private int index;
    private int movieId;
    private String name;
    private String imageUrl;
    private String videoUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cinema_fragment_two, container, false);
        linear_cinema_two = inflate.findViewById(R.id.linear_cinema_two);
        presenter = new MyAllPresenter(this);
        xrv_cinema_two = inflate.findViewById(R.id.xrv_cinema_two);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //从sp获取经纬度和userid
        SharedPreferences sp_ll = getActivity().getSharedPreferences(Constant.SP_LL_NAME, Context.MODE_PRIVATE);
        String longitude = sp_ll.getString(Constant.LONGITUDE, "");
        String latitude = sp_ll.getString(Constant.LATITUDE, "");
        SharedPreferences sp_user = getActivity().getSharedPreferences(Constant.SP_USER_NAME, Context.MODE_PRIVATE);
        int userId = sp_user.getInt(Constant.USER_ID, 0);
        String sessionId = sp_user.getString(Constant.SESSION_ID, "");

        //判断网络
        boolean netWork = HttpUtil.getInstance().isNetWork();
        if (netWork) {
            linear_cinema_two.setVisibility(View.GONE);
            xrv_cinema_two.setVisibility(View.VISIBLE);
            //请求数据
            presenter.nearbyList(userId,sessionId,longitude,latitude,page,count);

            xrv_cinema_two.setPullRefreshEnabled(true);
            xrv_cinema_two.setLoadingMoreEnabled(true);
            xrv_cinema_two.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    //重新获取
                    //从sp获取经纬度和userid
                    SharedPreferences sp_ll = getActivity().getSharedPreferences(Constant.SP_LL_NAME, Context.MODE_PRIVATE);
                    String longitude = sp_ll.getString(Constant.LONGITUDE, "");
                    String latitude = sp_ll.getString(Constant.LATITUDE, "");
                    SharedPreferences sp_user = getActivity().getSharedPreferences(Constant.SP_USER_NAME, Context.MODE_PRIVATE);
                    int userId = sp_user.getInt(Constant.USER_ID, 0);
                    String sessionId = sp_user.getString(Constant.SESSION_ID, "");
                    page = 1;
                    presenter.nearbyList(userId,sessionId,longitude,latitude,page,count);
                    xrv_cinema_two.refreshComplete();
                }

                @Override
                public void onLoadMore() {

                }
            });
        }else {
            linear_cinema_two.setVisibility(View.VISIBLE);
            xrv_cinema_two.setVisibility(View.GONE);
        }


    }

    @Override
    public void onDataSuccess(Object obj) {
        NearbyBean nearbyBean = (NearbyBean) obj;
        result = nearbyBean.getResult();

        myNearbyAdapter = new MyNearbyAdapter(result, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        xrv_cinema_two.setLayoutManager(manager);
        //设置适配器
        xrv_cinema_two.setAdapter(myNearbyAdapter);

        myNearbyAdapter.setNearbyListener(new MyNearbyAdapter.NearbyListener() {
            @Override
            public void onSelect(int position,String name) {
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            }
        });
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
        EventBus.getDefault().unregister(this);
    }
}
