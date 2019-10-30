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
import com.bw.movie.model.entity.TuiBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/14 20:46
 */
public class MyTuiAdapter extends RecyclerView.Adapter<MyTuiAdapter.MyTuiViewHolder>{
    private List<TuiBean.ResultBean> result;
    private Context context;
    private BuyTicketsListener buyTicketsListener;

    public MyTuiAdapter(List<TuiBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTuiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_tui, null, false);
        return new MyTuiViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTuiViewHolder holder, final int position) {
        String logo = result.get(position).getLogo();
        Glide.with(context).load(logo)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.img_tui);

        String name = result.get(position).getName();
        holder.tv_tui_name.setText(name);

        String address = result.get(position).getAddress();
        holder.tv_tui_addr.setText(address);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyTicketsListener.buy(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyTuiViewHolder extends RecyclerView.ViewHolder {
        ImageView img_tui;
        TextView tv_tui_name,tv_tui_addr;

        public MyTuiViewHolder(@NonNull View itemView) {
            super(itemView);
            img_tui = itemView.findViewById(R.id.img_tui);
            tv_tui_name = itemView.findViewById(R.id.tv_tui_name);
            tv_tui_addr = itemView.findViewById(R.id.tv_tui_addr);
        }
    }


    public interface BuyTicketsListener{
        void buy(int position);
    }

    public void setBuyTicketsListener(BuyTicketsListener buyTicketsListener) {
        this.buyTicketsListener = buyTicketsListener;
    }
}
