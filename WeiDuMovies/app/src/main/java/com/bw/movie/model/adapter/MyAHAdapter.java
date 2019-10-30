package com.bw.movie.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.entity.AtHotBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/30 9:38
 */
public class MyAHAdapter extends RecyclerView.Adapter<MyAHAdapter.AHViewHolder>{

    private MyHotAdapter.OnItemClickListener onItemClickListener;
    private List<AtHotBean.ResultBean> atHotResult;
    private Context context;

    public MyAHAdapter(List<AtHotBean.ResultBean> atHotResult, Context context) {
        this.atHotResult = atHotResult;
        this.context = context;
    }

    @NonNull
    @Override
    public AHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_athot, null, false);
        return new AHViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AHViewHolder holder, final int position) {
        String imageUrl = atHotResult.get(position).getImageUrl();
        Glide.with(context).load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(15)))
                .into(holder.img_athot_child);

        double score = atHotResult.get(position).getScore();
        holder.tv_athot_score.setText(score+"分");

        String name = atHotResult.get(position).getName();
        holder.tv_athot_name.setText(name);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return atHotResult.size();
    }

    public class AHViewHolder extends RecyclerView.ViewHolder {
        ImageView img_athot_child;
        TextView tv_athot_score,tv_athot_name;
        Button btn_athot;

        public AHViewHolder(@NonNull View itemView) {
            super(itemView);
            img_athot_child = itemView.findViewById(R.id.img_athot_child);
            tv_athot_score = itemView.findViewById(R.id.tv_athot_score);
            tv_athot_name = itemView.findViewById(R.id.tv_athot_name);
            btn_athot = itemView.findViewById(R.id.btn_athot);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(MyHotAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
