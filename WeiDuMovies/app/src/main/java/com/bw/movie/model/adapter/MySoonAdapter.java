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
import com.bw.movie.model.entity.SoonBean;
import com.bw.movie.util.TimeUtil;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/30 10:47
 */
public class MySoonAdapter extends RecyclerView.Adapter<MySoonAdapter.SoonViewHolder>{

    private MyHotAdapter.OnItemClickListener onItemClickListener;
    private List<SoonBean.ResultBean> soonResult;
    private Context context;
    private View inflate;

    public MySoonAdapter(List<SoonBean.ResultBean> soonResult, Context context) {
        this.soonResult = soonResult;
        this.context = context;
    }

    @NonNull
    @Override
    public SoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.layout_soon, null, false);
        return new SoonViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SoonViewHolder holder, final int position) {
        String imageUrl = soonResult.get(position).getImageUrl();
        Glide.with(context).load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(15)))
                .into(holder.img_soon_child);

        String name = soonResult.get(position).getName();
        holder.tv_soon_name.setText(name);

        long releaseTime = soonResult.get(position).getReleaseTime();
        String timeString = TimeUtil.getTimeString(releaseTime,"yyyy-MM-dd");
        holder.tv_soon_time.setText(timeString+"上映");

        int wantSeeNum = soonResult.get(position).getWantSeeNum();
        holder.tv_soon_num.setText(wantSeeNum+"人想看");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });

        holder.btn_soon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btn_soon.setBackgroundResource(R.drawable.button_yellow);
                holder.btn_soon.setText("已预约");
            }
        });
    }

    @Override
    public int getItemCount() {
        return soonResult.size();
    }

    public class SoonViewHolder extends RecyclerView.ViewHolder{
        ImageView img_soon_child;
        TextView tv_soon_name,tv_soon_time,tv_soon_num;
        Button btn_soon;

        public SoonViewHolder(@NonNull View itemView) {
            super(itemView);
            img_soon_child = itemView.findViewById(R.id.img_soon_child);
            tv_soon_name = itemView.findViewById(R.id.tv_soon_name);
            tv_soon_time = itemView.findViewById(R.id.tv_soon_time);
            tv_soon_num = itemView.findViewById(R.id.tv_soon_num);
            btn_soon = itemView.findViewById(R.id.btn_soon);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(MyHotAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
