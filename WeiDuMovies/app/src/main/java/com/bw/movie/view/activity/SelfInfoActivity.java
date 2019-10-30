package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.view.constant.Constant;
import com.bw.movie.view.view.TitleView;

public class SelfInfoActivity extends IBaseActivity implements View.OnClickListener {

    private TitleView title_my;
    private ImageView img_myinfo;
    private TextView tv_myinfo_name,tv_myinfo_sex,tv_myinfo_date,tv_myinfo_email;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_self_info;
    }

    @Override
    protected void initView() {
        title_my = findViewById(R.id.title_my);
        img_myinfo = findViewById(R.id.img_myinfo);
        tv_myinfo_name = findViewById(R.id.tv_myinfo_name);
        tv_myinfo_sex = findViewById(R.id.tv_myinfo_sex);
        tv_myinfo_date = findViewById(R.id.tv_myinfo_date);
        tv_myinfo_email = findViewById(R.id.tv_myinfo_email);
    }

    @Override
    protected void initData() {
        title_my.title_tv.setText("我的");
        title_my.title_img.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences(Constant.SP_USER_HEAD, MODE_PRIVATE);
        String uriPath = sp.getString(Constant.HEAD_NAME, "");
        Uri uri = Uri.parse(uriPath);
        //头像
        Glide.with(this).load(uri)
                .placeholder(R.mipmap.my_head)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_myinfo);


        img_myinfo.setOnClickListener(this);
    }

    @Override
    public void onDataSuccess(Object obj) {

    }

    @Override
    public void onDataError(String msg) {

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.title_img:
                intent = new Intent(this,MainActivity.class);
                intent.putExtra("intent",2);
                startActivity(intent);
                finish();
                break;
            case R.id.img_myinfo:
                initPopWindow();
                break;
        }
    }


    public void initPopWindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_self_info, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.WRAP_CONTENT, true);
        //引入依附的布局
        View parentView = inflate;
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(false);
        //设置焦点
        popupWindow.setFocusable(true);
        //找控件
        TextView tv_self_info_select = parentView.findViewById(R.id.tv_self_info_select);
        TextView tv_self_info_take_photo = parentView.findViewById(R.id.tv_self_info_take_photo);
        //相册
        tv_self_info_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,100);
                popupWindow.dismiss();
            }
        });
        //拍照
        tv_self_info_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            Uri uri = data.getData();

            SharedPreferences sp = getSharedPreferences(Constant.SP_USER_HEAD, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(Constant.HEAD_NAME, String.valueOf(uri));
            editor.commit();

            Glide.with(this).load(uri)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(img_myinfo);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
