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
import com.bw.movie.model.entity.CinemaInfoBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/23 15:57
 */
public class MyCInfoAdapter extends RecyclerView.Adapter<MyCInfoAdapter.InfoViewHolder>{

    private List<CinemaInfoBean.ResultBean> result;
    private Context context;
    private CInfoListener cInfoListener;

    public MyCInfoAdapter(List<CinemaInfoBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_cinema_info, null, false);
        return new InfoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        String logo = result.get(position).getLogo();
        Glide.with(context).load(logo)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.img_cinema_info);

        String name = result.get(position).getName();
        holder.tv_cinema_info_name.setText(name);

        String address = result.get(position).getAddress();
        holder.tv_cinema_info_addr.setText(address);


        final int cinemaId = result.get(position).getCinemaId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cInfoListener.getCinemaId(cinemaId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{
        ImageView img_cinema_info;
        TextView tv_cinema_info_name,tv_cinema_info_addr;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cinema_info = itemView.findViewById(R.id.img_cinema_info);
            tv_cinema_info_name = itemView.findViewById(R.id.tv_cinema_info_name);
            tv_cinema_info_addr = itemView.findViewById(R.id.tv_cinema_info_addr);
        }
    }


    public interface CInfoListener{
        void getCinemaId(int cinemaId);
    }

    public void setcInfoListener(CInfoListener cInfoListener) {
        this.cInfoListener = cInfoListener;
    }
}
