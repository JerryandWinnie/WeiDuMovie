package com.bw.movie.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/19 10:22
 */
public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoAdapter.MyPViewHolder>{

    private List<String> posterList;
    private Context context;

    public MyPhotoAdapter(List<String> posterList, Context context) {
        this.posterList = posterList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_info_three_photo, null, false);
        return new MyPViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPViewHolder holder, int position) {
        String photo = posterList.get(position);
        Glide.with(context).load(photo)
                .placeholder(R.mipmap.neza1)
                .error(R.mipmap.neza2)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(holder.img_info_photo);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class MyPViewHolder extends RecyclerView.ViewHolder{
        ImageView img_info_photo;

        public MyPViewHolder(@NonNull View itemView) {
            super(itemView);
            img_info_photo = itemView.findViewById(R.id.img_info_photo);
        }
    }

}
