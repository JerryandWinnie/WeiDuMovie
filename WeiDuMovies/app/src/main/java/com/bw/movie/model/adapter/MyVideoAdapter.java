package com.bw.movie.model.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.entity.MovieInfoBean;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/19 9:05
 */
public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.MyVdViewHolder>{

    private List<MovieInfoBean.ResultBean.ShortFilmListBean> shortFilmList;
    private Context context;

    public MyVideoAdapter(List<MovieInfoBean.ResultBean.ShortFilmListBean> shortFilmList, Context context) {
        this.shortFilmList = shortFilmList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_info_two_jcv, null, false);
        return new MyVdViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVdViewHolder holder, int position) {
        String videoUrl = shortFilmList.get(position).getVideoUrl();
        String imageUrl = shortFilmList.get(position).getImageUrl();
        holder.jcv.setUp(videoUrl,JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"");

        ImageView thumbImageView = holder.jcv.thumbImageView;
        Glide.with(context).load(imageUrl).into(thumbImageView);


    }

    @Override
    public int getItemCount() {
        return shortFilmList.size();
    }

    public static class MyVdViewHolder extends RecyclerView.ViewHolder{
        static JCVideoPlayerStandard jcv;

        public MyVdViewHolder(@NonNull View itemView) {
            super(itemView);
            jcv = itemView.findViewById(R.id.jcv);
        }
    }


    public void releaseVideo(){
        MyVideoAdapter.MyVdViewHolder.jcv.releaseAllVideos();
    }
}
