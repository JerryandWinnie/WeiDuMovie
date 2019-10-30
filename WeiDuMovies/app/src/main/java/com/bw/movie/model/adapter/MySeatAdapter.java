package com.bw.movie.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.entity.SeatBean;

import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/25 15:01
 */
public class MySeatAdapter extends RecyclerView.Adapter<MySeatAdapter.SeatViewHolder>{

    private List<SeatBean.ResultBean> result;
    private Context context;
    private OnItemCheckListener onItemCheckListener;

    public MySeatAdapter(List<SeatBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_select_seat, null, false);
        return new SeatViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, final int position) {
        int status = result.get(position).getStatus();

        if(status==1){
            holder.check_seat.setChecked(false);
        }else {
            holder.check_seat.setChecked(true);
            holder.check_seat.setBackgroundResource(R.drawable.circle_orange);
            holder.check_seat.setEnabled(true);
        }

        final String row = result.get(position).getRow();
        final String seat = result.get(position).getSeat();
        holder.check_seat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onItemCheckListener.onCheck(position,row,seat,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class SeatViewHolder extends RecyclerView.ViewHolder{
        CheckBox check_seat;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            check_seat = itemView.findViewById(R.id.check_seat);
        }
    }

    public interface OnItemCheckListener{
        void onCheck(int position,String row,String seat,boolean isChecked);
    }

    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener) {
        this.onItemCheckListener = onItemCheckListener;
    }
}
