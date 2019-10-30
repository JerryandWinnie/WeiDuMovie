package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.fragment.infofragment.InfoFragmentFour;
import com.bw.movie.fragment.infofragment.InfoFragmentOne;
import com.bw.movie.fragment.infofragment.InfoFragmentThree;
import com.bw.movie.fragment.infofragment.InfoFragmentTwo;
import com.bw.movie.model.adapter.MyAllFragAdapter;
import com.bw.movie.model.entity.MovieInfoBean;
import com.bw.movie.util.TimeUtil;
import com.bw.movie.view.constant.Constant;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends IBaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "InfoActivity";
    private ImageView img_info_big,img_info_back;
    private TextView tv_info_score,tv_info_comment;
    private TextView tv_info_name,tv_info_type,tv_info_time,tv_info_date,tv_info_place,tv_info_attention;
    private CheckBox check_info_heart;
    private TabLayout tab_info;
    private ViewPager vp_info;
    private Button btn_info_write,btn_info_buy;
    private ArrayList<String> tabs;
    private ArrayList<Fragment> fragments;
    private int movieId = 1;
    private String imageUrl;
    private String videoUrl;
    private String name;


    @Override
    protected int initLayoutID() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {
        img_info_big = findViewById(R.id.img_info_big);
        img_info_back = findViewById(R.id.img_info_back);
        tv_info_score = findViewById(R.id.tv_info_score);
        tv_info_comment = findViewById(R.id.tv_info_comment);
        tv_info_name = findViewById(R.id.tv_info_name);
        tv_info_type = findViewById(R.id.tv_info_type);
        tv_info_time = findViewById(R.id.tv_info_time);
        tv_info_date = findViewById(R.id.tv_info_date);
        tv_info_place = findViewById(R.id.tv_info_place);
        tv_info_attention = findViewById(R.id.tv_info_attention);
        check_info_heart = findViewById(R.id.check_info_heart);
        tab_info = findViewById(R.id.tab_info);
        vp_info = findViewById(R.id.vp_info);
        btn_info_write = findViewById(R.id.btn_info_write);
        btn_info_buy = findViewById(R.id.btn_info_buy);
    }

    @Override
    protected void initData() {
        //设置预加载
        vp_info.setOffscreenPageLimit(4);

        //sp
        SharedPreferences sp = getSharedPreferences(Constant.USER_ID, MODE_PRIVATE);
        int userId = sp.getInt(Constant.USER_ID, 0);
        String sessionId = sp.getString(Constant.SESSION_ID, "");

        Intent intent = getIntent();
        movieId = intent.getIntExtra(Constant.MOVIE_ID, 1);

        presenter.infoList(userId,sessionId,movieId);


        tabs = new ArrayList<>();
        tabs.add("介绍");
        tabs.add("预告");
        tabs.add("剧照");
        tabs.add("影评");
        for (int i = 0; i < tabs.size(); i++) {
            tab_info.addTab(tab_info.newTab().setText(tabs.get(i)));
        }
        fragments = new ArrayList<>();
        fragments.add(new InfoFragmentOne());
        fragments.add(new InfoFragmentTwo());
        fragments.add(new InfoFragmentThree());
        fragments.add(new InfoFragmentFour());

        MyAllFragAdapter myAllFragAdapter = new MyAllFragAdapter(getSupportFragmentManager(),tabs,fragments);
        vp_info.setAdapter(myAllFragAdapter);
        tab_info.setupWithViewPager(vp_info);


        img_info_back.setOnClickListener(this);
        check_info_heart.setOnCheckedChangeListener(this);
        btn_info_buy.setOnClickListener(this);
        btn_info_write.setOnClickListener(this);
    }

    @Override
    public void onDataSuccess(Object obj) {
        MovieInfoBean movieInfoBean = (MovieInfoBean) obj;
        MovieInfoBean.ResultBean result = movieInfoBean.getResult();

        //eventbus
        EventBus.getDefault().postSticky(movieInfoBean);

        //大图
        Glide.with(this)
                .load(result.getImageUrl())
                .placeholder(R.mipmap.winnie)
                .error(R.mipmap.ic_launcher)
                .into(img_info_big);
        //评分
        tv_info_score.setText("评分  "+result.getScore()+"分");
        //评论
        tv_info_comment.setText("评论  "+result.getCommentNum()+"万条");
        //电影名
        name = result.getName();
        tv_info_name.setText(name);
        //类型
        tv_info_type.setText(result.getMovieType());
        //时长
        tv_info_time.setText(result.getDuration());
        //上映时间
        long releaseTime = result.getReleaseTime();
        String time = TimeUtil.getTimeString(releaseTime,"yyyy-MM-dd");
        tv_info_date.setText(time);
        //产地
        tv_info_place.setText(result.getPlaceOrigin()+"上映");
        //是否关注
        int whetherFollow = result.getWhetherFollow();
        if (whetherFollow == 1) {
            tv_info_attention.setText("已关注");
            check_info_heart.setChecked(true);
        }else {
            tv_info_attention.setText("未关注");
            check_info_heart.setChecked(false);
        }

        List<MovieInfoBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
        imageUrl = shortFilmList.get(0).getImageUrl();
        videoUrl = shortFilmList.get(0).getVideoUrl();


    }

    @Override
    public void onDataError(String msg) {
        Log.e(TAG, "onDataError: "+msg );
    }


    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.img_info_back:
                finish();
                break;
            case R.id.btn_info_buy:
                //选座购票
                intent = new Intent(this,SelectCinemaActivity.class);
                intent.putExtra(Constant.MOVIE_ID,movieId);
                intent.putExtra(Constant.MOVIE_NAME,name);
                intent.putExtra(Constant.MOVIE_IMGURL,imageUrl);
                intent.putExtra(Constant.MOVIE_URL,videoUrl);
                startActivity(intent);
                break;
            case R.id.btn_info_write:
                //写影评
                Toast.makeText(this, "暂无此功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            tv_info_attention.setText("已关注");
        }else {
            tv_info_attention.setText("未关注");
        }
    }

}
