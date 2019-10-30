package com.bw.movie.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.entity.AreaBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/23 14:59
 */
public class MyAreaAdapter extends RecyclerView.Adapter<MyAreaAdapter.AreaViewHolder>{

    private List<AreaBean.ResultBean> result;
    private Context context;
    private AreaListener areaListener;

    public MyAreaAdapter(List<AreaBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_area, null, false);
        return new AreaViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        final int regionId = result.get(position).getRegionId();
        String regionName = result.get(position).getRegionName();

        holder.tv_area_name.setText(regionName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaListener.getRegionId(regionId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class AreaViewHolder extends RecyclerView.ViewHolder{
        TextView tv_area_name;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_area_name = itemView.findViewById(R.id.tv_area_name);
        }
    }


    public interface AreaListener{
        void getRegionId(int regionId);
    }

    public void setAreaListener(AreaListener areaListener) {
        this.areaListener = areaListener;
    }
}
