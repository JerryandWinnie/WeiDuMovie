package com.bw.movie.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bw.movie.R;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/27 20:54
 */
public class TitleView extends LinearLayout {

    public ImageView title_img;
    public TextView title_tv;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_title, this, true);
        title_img = inflate.findViewById(R.id.title_img);
        title_tv = inflate.findViewById(R.id.title_tv);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
