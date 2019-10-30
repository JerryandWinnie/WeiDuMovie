package com.bw.movie.fragment.homefragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.contract.IContract;
import com.bw.movie.map.AMapUtil;
import com.bw.movie.model.adapter.MyAHAdapter;
import com.bw.movie.model.adapter.MyHotAdapter;
import com.bw.movie.model.adapter.MySoonAdapter;
import com.bw.movie.model.entity.AtHotBean;
import com.bw.movie.model.entity.BannerBean;
import com.bw.movie.model.entity.HotBean;
import com.bw.movie.model.entity.JsonBean;
import com.bw.movie.model.entity.SoonBean;
import com.bw.movie.util.HttpUtil;
import com.bw.movie.presenter.MyAllPresenter;
import com.bw.movie.view.activity.InfoActivity;
import com.bw.movie.view.activity.MoreActivity;
import com.bw.movie.view.app.MyApplication;
import com.bw.movie.view.constant.Constant;
import com.example.john.greendaodemo.gen.DaoSession;
import com.example.john.greendaodemo.gen.JsonBeanDao;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/28 20:23
 */
public class HomeFragmentOne extends Fragment implements IContract.IView, View.OnClickListener{
    private static final String TAG = "HomeFragmentOne";
    private static final String NUM = "num";
    private Banner banner;
    private ArrayList<String> images;
    private TextView tv_location_name,tv_more_one,tv_more_two,tv_more_three;
    private RecyclerView rv_search_athot,rv_search_soon,rv_search_hot;
    private int page=1,count=6;
    private IContract.Presenter presenter;
    private ImageView img_location;
    private LinearLayout linear_home_one;
    private ScrollView sl_home;
    private Intent intent = null;
    private DaoSession daoSession;
    private JsonBean jsonBean;
    private JsonBeanDao jsonBeanDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daoSession = MyApplication.getInstance().getDaoSession();
        jsonBean = new JsonBean();
        jsonBeanDao = daoSession.getJsonBeanDao();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment_one, null, false);
        presenter = new MyAllPresenter(this);
        banner = inflate.findViewById(R.id.banner);
        linear_home_one = inflate.findViewById(R.id.linear_home_one);
        sl_home = inflate.findViewById(R.id.sl_home);
        tv_more_one = inflate.findViewById(R.id.tv_more_one);
        tv_more_two = inflate.findViewById(R.id.tv_more_two);
        tv_more_three = inflate.findViewById(R.id.tv_more_three);
        rv_search_athot = inflate.findViewById(R.id.rv_search_athot);
        rv_search_soon = inflate.findViewById(R.id.rv_search_soon);
        rv_search_hot = inflate.findViewById(R.id.rv_search_hot);
        img_location = inflate.findViewById(R.id.img_location);
        tv_location_name = inflate.findViewById(R.id.tv_location_name);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //更多
        tv_more_one.setOnClickListener(this);
        tv_more_two.setOnClickListener(this);
        tv_more_three.setOnClickListener(this);

        //定位
        img_location.setOnClickListener(this);

        //从sharedpreference获取userId和sessionId
        SharedPreferences sp = getActivity().getSharedPreferences(Constant.SP_USER_NAME, Context.MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID,"");

        //获取数据库数据
        List<JsonBean> list = null;

        boolean netWork = HttpUtil.getInstance().isNetWork();
        if (netWork) {
            list = jsonBeanDao.queryBuilder().list();
            list.clear();
            linear_home_one.setVisibility(View.GONE);
            sl_home.setVisibility(View.VISIBLE);
            //banner
            presenter.banner();
            //正在热映
            presenter.atHotList(userId,sessionId,page,count);
            //即将上映
            presenter.soonList(userId,sessionId,page,3);
            //热门电影
            presenter.hotList(userId,sessionId,page,4);
        }else {
            list = jsonBeanDao.queryBuilder().list();
            Gson gson = new Gson();
            images = new ArrayList<>();
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    //获取banner
                    String bannerJson = list.get(i).getBannerJson();
                    BannerBean bannerBean = gson.fromJson(bannerJson, BannerBean.class);
                    List<BannerBean.ResultBean> result = bannerBean.getResult();
                    for (int j = 0; j < result.size(); j++) {
                        images.add(result.get(j).getImageUrl());
                    }
                    getBanner(images);
                    images.clear();

                    //正在上映
                    String atHotJson = list.get(i).getAtHotJson();
                    AtHotBean atHotBean = gson.fromJson(atHotJson, AtHotBean.class);
                    final List<AtHotBean.ResultBean> atHotResult = atHotBean.getResult();
                    MyAHAdapter myAHAdapter = new MyAHAdapter(atHotResult, getContext());
                    //线性
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(RecyclerView.HORIZONTAL);
                    rv_search_athot.setLayoutManager(manager);
                    rv_search_athot.setAdapter(myAHAdapter);
                    myAHAdapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Toast.makeText(getContext(), "当前无网络", Toast.LENGTH_SHORT).show();
                            int movieId = atHotResult.get(position).getMovieId();
                            intent = new Intent(getContext(), InfoActivity.class);
                            intent.putExtra(Constant.MOVIE_ID,movieId);
                            startActivity(intent);
                        }
                    });


                    //即将上映
                    String soonJson = list.get(i).getSoonJson();
                    SoonBean soonBean = gson.fromJson(soonJson, SoonBean.class);
                    final List<SoonBean.ResultBean> soonResult = soonBean.getResult();
                    MySoonAdapter mySoonAdapter = new MySoonAdapter(soonResult, getContext());
                    //线性
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                    rv_search_soon.setLayoutManager(layoutManager);
                    rv_search_soon.setAdapter(mySoonAdapter);
                    mySoonAdapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Toast.makeText(getContext(), "当前无网络", Toast.LENGTH_SHORT).show();
                            int movieId = soonResult.get(position).getMovieId();
                            intent = new Intent(getContext(), InfoActivity.class);
                            intent.putExtra(Constant.MOVIE_ID,movieId);
                            startActivity(intent);
                        }
                    });


                    //热门列表
                    String hotJson = list.get(i).getHotJson();
                    HotBean hotBean = gson.fromJson(hotJson, HotBean.class);
                    final List<HotBean.ResultBean> hotResult = hotBean.getResult();
                    MyHotAdapter myHotAdapter = new MyHotAdapter(hotResult, getContext());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
                    gridLayoutManager.setAutoMeasureEnabled(true);
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position == 0) {
                                return 3;
                            }
                            return 1;
                        }
                    });
                    rv_search_hot.setLayoutManager(gridLayoutManager);
                    rv_search_hot.setAdapter(myHotAdapter);
                    myHotAdapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            Toast.makeText(getContext(), "网络连接不可用，请稍后重试", Toast.LENGTH_SHORT).show();
                            int movieId = hotResult.get(position).getMovieId();
                            intent = new Intent(getContext(), InfoActivity.class);
                            intent.putExtra(Constant.MOVIE_ID,movieId);
                            startActivity(intent);
                        }
                    });

                }
            }else {
                linear_home_one.setVisibility(View.VISIBLE);
                sl_home.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public void onDataSuccess(Object obj) {

        if(obj instanceof BannerBean){
            images = new ArrayList<>();
            BannerBean bannerBean = (BannerBean) obj;
            List<BannerBean.ResultBean> result = bannerBean.getResult();
            for (int i = 0; i < result.size(); i++) {
                images.add(result.get(i).getImageUrl());
            }
            getBanner(images);

            //把banner的json数据存入数据库
            Gson gson = new Gson();
            String bannerJson = gson.toJson(bannerBean);
            jsonBean.setBannerJson(bannerJson);

        }else if(obj instanceof AtHotBean){
            //正在上映
            AtHotBean atHotBean = (AtHotBean) obj;
            final List<AtHotBean.ResultBean> atHotResult = atHotBean.getResult();

            MyAHAdapter myAHAdapter = new MyAHAdapter(atHotResult, getContext());
            //线性
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.HORIZONTAL);
            rv_search_athot.setLayoutManager(manager);
            rv_search_athot.setAdapter(myAHAdapter);

            myAHAdapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    int movieId = atHotResult.get(position).getMovieId();
                    intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra(Constant.MOVIE_ID,movieId);
                    startActivity(intent);
                }
            });


            //把正在上映的json数据存入数据库
            Gson gson = new Gson();
            String atHotJson = gson.toJson(atHotBean);
            jsonBean.setAtHotJson(atHotJson);
        }else if(obj instanceof SoonBean){
            //即将上映
            SoonBean soonBean = (SoonBean) obj;
            final List<SoonBean.ResultBean> soonResult = soonBean.getResult();

            MySoonAdapter mySoonAdapter = new MySoonAdapter(soonResult, getContext());
            //线性
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.VERTICAL);
            rv_search_soon.setLayoutManager(manager);
            rv_search_soon.setAdapter(mySoonAdapter);

            mySoonAdapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    int movieId = soonResult.get(position).getMovieId();
                    intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra(Constant.MOVIE_ID,movieId);
                    startActivity(intent);
                }
            });


            //把即将上映的json数据存入数据库
            Gson gson = new Gson();
            String soonJson = gson.toJson(soonBean);
            jsonBean.setSoonJson(soonJson);
        }else if(obj instanceof HotBean){
            //热门列表
            HotBean hotBean = (HotBean) obj;
            final List<HotBean.ResultBean> hotResult = hotBean.getResult();

            MyHotAdapter myHotAdapter = new MyHotAdapter(hotResult, getContext());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
            gridLayoutManager.setAutoMeasureEnabled(true);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 3;
                    }
                    return 1;
                }
            });

            rv_search_hot.setLayoutManager(gridLayoutManager);
            rv_search_hot.setAdapter(myHotAdapter);

            myHotAdapter.setOnItemClickListener(new MyHotAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    int movieId = hotResult.get(position).getMovieId();
                    intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra(Constant.MOVIE_ID,movieId);
                    startActivity(intent);
                }
            });


            //把热门列表的json数据存入数据库
            Gson gson = new Gson();
            String hotJson = gson.toJson(hotBean);
            jsonBean.setHotJson(hotJson);
        }

        daoSession.insertOrReplace(jsonBean);
    }

    @Override
    public void onDataError(String msg) {
        Log.e(TAG, "onDataError: "+msg );
    }


    private void getBanner(ArrayList<String> images) {
        banner.setImages(images);
        //设置动画
        banner.setBannerAnimation(Transformer.Accordion);
        //设置样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.start();
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), MoreActivity.class);
        switch (view.getId()){
            case R.id.tv_more_one:
                intent.putExtra(NUM,1);
                startActivity(intent);
                break;
            case R.id.tv_more_two:
                intent.putExtra(NUM,2);
                startActivity(intent);
                break;
            case R.id.tv_more_three:
                intent.putExtra(NUM,3);
                startActivity(intent);
                break;
            case R.id.img_location:
                AMapUtil.getLoaction(new AMapUtil.AMapInterface() {
                    @Override
                    public void getAMapLocation(AMapLocation aMapLocation) {
                        if (aMapLocation != null) {
                            if (aMapLocation.getErrorCode() == 0) {
                                //可在其中解析amapLocation获取相应内容。
                                String city = aMapLocation.getCity();
                                tv_location_name.setText(city);
                                Log.e("aaa", "onLocationChanged: "+city );
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
        if (presenter != null) {
            presenter = null;
        }
    }
}
