package com.bw.movie.fragment.homefragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.entity.WXBean;
import com.bw.movie.view.activity.MainActivity;
import com.bw.movie.view.activity.SelfInfoActivity;
import com.bw.movie.view.activity.SetActivity;
import com.bw.movie.view.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/28 20:23
 */
public class HomeFragmentThree extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomeFragmentThree";
    private ImageView img_my_head,img_my_shape_one;
    private ImageView img_my_attention,img_my_record,img_my_see,img_my_comment,img_my_advise,img_my_set;
    private TextView tv_my_name;
    private RelativeLayout rl_my_one,rl_my_two;
    private LinearLayout img_my_subscribe;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment_three, null, false);
        rl_my_one = inflate.findViewById(R.id.rl_my_one);
        rl_my_two = inflate.findViewById(R.id.rl_my_two);
        img_my_subscribe = inflate.findViewById(R.id.img_my_subscribe);
        tv_my_name = inflate.findViewById(R.id.tv_my_name);
        img_my_head = inflate.findViewById(R.id.img_my_head);
        img_my_shape_one = inflate.findViewById(R.id.img_my_shape_one);
        img_my_attention = inflate.findViewById(R.id.img_my_attention);
        img_my_record = inflate.findViewById(R.id.img_my_record);
        img_my_see = inflate.findViewById(R.id.img_my_see);
        img_my_comment = inflate.findViewById(R.id.img_my_comment);
        img_my_advise = inflate.findViewById(R.id.img_my_advise);
        img_my_set = inflate.findViewById(R.id.img_my_set);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sp = getActivity().getSharedPreferences(Constant.SP_USER_HEAD, MODE_PRIVATE);
        String uriPath = sp.getString(Constant.HEAD_NAME, "");
        Uri uri = Uri.parse(uriPath);
        //头像
        Glide.with(this).load(uri)
                .placeholder(R.mipmap.my_head)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_my_head);


        //个人信息
        rl_my_one.setOnClickListener(this);
        //电影票
        rl_my_two.setOnClickListener(this);
        //各种功能
        //我的关注
        img_my_attention.setOnClickListener(this);
        //我的预约
        img_my_subscribe.setOnClickListener(this);
        //购票记录
        img_my_record.setOnClickListener(this);
        //看过的电影
        img_my_see.setOnClickListener(this);
        //我的评论
        img_my_comment.setOnClickListener(this);
        //意见反馈
        img_my_advise.setOnClickListener(this);
        //设置
        img_my_set.setOnClickListener(this);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getWXBean(WXBean.ResultBean.UserInfoBean infoBean){
        String headPic = infoBean.getHeadPic();
        Log.e(TAG, "getWXBean: "+headPic );
        Glide.with(getContext()).load(headPic)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_my_head);

        String nickName = infoBean.getNickName();
        Log.e(TAG, "getWXBean: "+nickName );
        tv_my_name.setText(nickName);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.rl_my_one:
                //个人信息
                intent = new Intent(getContext(), SelfInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_two:
                //电影票
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_attention:
                //我的关注
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_subscribe:
                //我的预约
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_record:
                //购票记录
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_see:
                //看过的电影
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_comment:
                //我的评论
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_advise:
                //意见反馈
                Toast.makeText(getContext(), "敬请期待...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_my_set:
                //设置
                intent = new Intent(getContext(), SetActivity.class);
                startActivity(intent);
                break;
        }
    }
}
