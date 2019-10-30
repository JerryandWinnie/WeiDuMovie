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
 * @CreateDate: 2019/10/18 21:05
 */
public class MyActorAdapter extends RecyclerView.Adapter<MyActorAdapter.MyAtViewHolder>{

    private List<MovieInfoBean.ResultBean.MovieActorBean> movieActor;
    private Context context;

    public MyActorAdapter(List<MovieInfoBean.ResultBean.MovieActorBean> movieActor, Context context) {
        this.movieActor = movieActor;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_info_one_actor, null, false);
        return new MyAtViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAtViewHolder holder, int position) {
        String name = movieActor.get(position).getName();
        holder.tv_actor.setText(name);

        String photo = movieActor.get(position).getPhoto();
        Glide.with(context)
                .load(photo)
                .placeholder(R.mipmap.neza2)
                .error(R.mipmap.neza1)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.img_actor);
    }

    @Override
    public int getItemCount() {
        return movieActor.size();
    }

    public class MyAtViewHolder extends RecyclerView.ViewHolder {
        ImageView img_actor;
        TextView tv_actor;

        public MyAtViewHolder(@NonNull View itemView) {
            super(itemView);
            img_actor = itemView.findViewById(R.id.img_actor);
            tv_actor = itemView.findViewById(R.id.tv_actor);
        }
    }

}
