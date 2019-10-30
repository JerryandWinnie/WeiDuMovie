package com.bw.movie.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.entity.MovieInfoBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/18 20:12
 */
public class MyDirectorAdapter extends RecyclerView.Adapter<MyDirectorAdapter.MyDtViewHolder>{

    private List<MovieInfoBean.ResultBean.MovieDirectorBean> movieDirector;
    private Context context;

    public MyDirectorAdapter(List<MovieInfoBean.ResultBean.MovieDirectorBean> movieDirector, Context context) {
        this.movieDirector = movieDirector;
        this.context = context;
    }

    @NonNull
    @Override
    public MyDtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_info_one_director, null, false);
        return new MyDtViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDtViewHolder holder, int position) {
        String name = movieDirector.get(position).getName();
        holder.tv_director.setText(name);

        String photo = movieDirector.get(position).getPhoto();
        Glide.with(context)
                .load(photo)
                .placeholder(R.mipmap.neza1)
                .error(R.mipmap.neza2)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.img_director);
    }

    @Override
    public int getItemCount() {
        return movieDirector.size();
    }

    public class MyDtViewHolder extends RecyclerView.ViewHolder {
        ImageView img_director;
        TextView tv_director;

        public MyDtViewHolder(@NonNull View itemView) {
            super(itemView);
            img_director = itemView.findViewById(R.id.img_director);
            tv_director = itemView.findViewById(R.id.tv_director);
        }
    }

}
