package com.bw.movie.model.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.model.entity.ScheduleBean;
import com.bw.movie.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/22 14:04
 */
public class MyScheduleAdapter extends RecyclerView.Adapter<MyScheduleAdapter.SdViewHolder>{

    private List<ScheduleBean.ResultBean> result;
    private Context context;
    private ScheduleListener scheduleListener;

    public MyScheduleAdapter(List<ScheduleBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public SdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_schedule, null, false);
        return new SdViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SdViewHolder holder, final int position) {
        //电影厅
        String screeningHall = result.get(position).getScreeningHall();
        holder.tv_schedule_hall.setText(screeningHall);

        //开始时间和结束时间
        String bgTime = result.get(position).getBeginTime();
        String edTime = result.get(position).getEndTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            String beginTime = simpleDateFormat.format(simpleDateFormat.parse(bgTime));
            String endTime = simpleDateFormat.format(simpleDateFormat.parse(edTime));
            holder.tv_schedule_time.setText(beginTime+" --- "+endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.rb_schedule.isChecked();
                if (checked) {
                    holder.rb_schedule.setChecked(false);
                    scheduleListener.getHallId(0,0,0.0);
                }else {
                    holder.rb_schedule.setChecked(true);
                    int hallId = result.get(position).getHallId();
                    double fare = result.get(position).getFare();
                    int id = result.get(position).getId();
                    scheduleListener.getHallId(id,hallId,fare);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class SdViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_schedule;
        TextView tv_schedule_hall,tv_schedule_time;
        RadioButton rb_schedule;

        public SdViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_schedule = itemView.findViewById(R.id.rl_schedule);
            tv_schedule_hall = itemView.findViewById(R.id.tv_schedule_hall);
            tv_schedule_time = itemView.findViewById(R.id.tv_schedule_time);
            rb_schedule = itemView.findViewById(R.id.rb_schedule);
        }
    }


    public interface ScheduleListener{
        void getHallId(int id,int hallId,double fare);
    }

    public void setScheduleListener(ScheduleListener scheduleListener) {
        this.scheduleListener = scheduleListener;
    }
}
