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
import com.bw.movie.model.entity.HotBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/17 15:08
 */
public class MyHotAdapter extends RecyclerView.Adapter{

    private OnItemClickListener onItemClickListener;
    private List<HotBean.ResultBean> result;
    private Context context;

    public MyHotAdapter(List<HotBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_hot_one, null, false);
            viewHolder = new ViewHolderOne(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_hot_two, null, false);
            viewHolder = new ViewHolderTwo(inflate);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (position == 0) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.tv_hot_name_one.setText(result.get(position).getName());
            viewHolderOne.tv_hot_score_one.setText(result.get(position).getScore()+"分");
            Glide.with(context)
                    .load(result.get(position).getImageUrl())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                    .into(viewHolderOne.img_hot_one);

            viewHolderOne.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });

        }else {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.tv_hot_name_two.setText(result.get(position).getName());
            viewHolderTwo.tv_hot_score_two.setText(result.get(position).getScore()+"分");
            Glide.with(context)
                    .load(result.get(position).getImageUrl())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                    .into(viewHolderTwo.img_hot_two);

            viewHolderTwo.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder{
        ImageView img_hot_one;
        TextView tv_hot_name_one,tv_hot_score_one;
        Button btn_hot_one;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            img_hot_one = itemView.findViewById(R.id.img_hot_one);
            tv_hot_name_one = itemView.findViewById(R.id.tv_hot_name_one);
            tv_hot_score_one = itemView.findViewById(R.id.tv_hot_score_one);
            btn_hot_one = itemView.findViewById(R.id.btn_hot_one);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder{
        ImageView img_hot_two;
        TextView tv_hot_name_two,tv_hot_score_two;
        Button btn_hot_two;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            img_hot_two = itemView.findViewById(R.id.img_hot_two);
            tv_hot_name_two = itemView.findViewById(R.id.tv_hot_name_two);
            tv_hot_score_two = itemView.findViewById(R.id.tv_hot_score_two);
            btn_hot_two = itemView.findViewById(R.id.btn_hot_two);
        }
    }

}
