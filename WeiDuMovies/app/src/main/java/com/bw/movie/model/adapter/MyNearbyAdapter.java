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
import com.bw.movie.model.entity.NearbyBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/16 16:14
 */
public class MyNearbyAdapter extends RecyclerView.Adapter<MyNearbyAdapter.MyNearViewHolder>{

    private List<NearbyBean.ResultBean> result;
    private Context context;
    private NearbyListener nearbyListener;

    public MyNearbyAdapter(List<NearbyBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public MyNearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_nearby, null, false);
        return new MyNearViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNearViewHolder holder, final int position) {
        String logo = result.get(position).getLogo();
        Glide.with(context).load(logo)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.img_nb);

        final String name = result.get(position).getName();
        holder.tv_nb_name.setText(name);

        String address = result.get(position).getAddress();
        holder.tv_nb_addr.setText(address);

        int distance = result.get(position).getDistance();
        holder.tv_nb_distance.setText(distance/1000d+"km");


        //条目点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyListener.onSelect(position,name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyNearViewHolder extends RecyclerView.ViewHolder {
        ImageView img_nb;
        TextView tv_nb_name,tv_nb_addr,tv_nb_distance;

        public MyNearViewHolder(@NonNull View itemView) {
            super(itemView);
            img_nb = itemView.findViewById(R.id.img_nb);
            tv_nb_name = itemView.findViewById(R.id.tv_nb_name);
            tv_nb_addr = itemView.findViewById(R.id.tv_nb_addr);
            tv_nb_distance = itemView.findViewById(R.id.tv_nb_distance);
        }
    }


    public interface NearbyListener{
        void onSelect(int position,String name);
    }

    public void setNearbyListener(NearbyListener nearbyListener) {
        this.nearbyListener = nearbyListener;
    }
}
