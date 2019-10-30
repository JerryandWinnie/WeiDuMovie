package com.bw.movie.fragment.cinemafragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.bw.movie.model.adapter.MyTuiAdapter;
import com.bw.movie.model.entity.TuiBean;
import com.bw.movie.util.HttpUtil;
import com.bw.movie.presenter.MyAllPresenter;
import com.bw.movie.view.activity.SelectActivity;
import com.bw.movie.view.constant.Constant;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/14 20:13
 */
public class CinemaFragmentOne extends Fragment implements IContract.IView {
    private static final String TAG = "CinemaFragmentOne";
    private XRecyclerView xrv_cinema_one;
    private IContract.Presenter myAllPresenter;
    private int page=1,count=10;
    private LinearLayout linear_cinema_one;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cinema_fragment_one, container, false);
        linear_cinema_one = inflate.findViewById(R.id.linear_cinema_one);
        xrv_cinema_one = inflate.findViewById(R.id.xrv_cinema_one);
        myAllPresenter = new MyAllPresenter(this);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //从sharedpreference获取userId和sessionId
        SharedPreferences sp = getActivity().getSharedPreferences(Constant.SP_USER_NAME, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID,"");

        //判断网络
        boolean netWork = HttpUtil.getInstance().isNetWork();
        if (netWork) {
            linear_cinema_one.setVisibility(View.GONE);
            xrv_cinema_one.setVisibility(View.VISIBLE);
            //调用接口
            myAllPresenter.tuiList(userId,sessionId,page,count);
        }else {
            linear_cinema_one.setVisibility(View.VISIBLE);
            xrv_cinema_one.setVisibility(View.GONE);
        }


    }

    @Override
    public void onDataSuccess(Object obj) {
        TuiBean tuiBean = (TuiBean) obj;
        final List<TuiBean.ResultBean> result = tuiBean.getResult();

        MyTuiAdapter myTuiAdapter = new MyTuiAdapter(result, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        xrv_cinema_one.setLayoutManager(manager);
        xrv_cinema_one.setAdapter(myTuiAdapter);


        myTuiAdapter.setBuyTicketsListener(new MyTuiAdapter.BuyTicketsListener() {
            @Override
            public void buy(int position) {
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDataError(String msg) {
        Log.e(TAG, "onDataError: "+msg );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myAllPresenter != null) {
            myAllPresenter.onDestroy();
        }
    }
}
