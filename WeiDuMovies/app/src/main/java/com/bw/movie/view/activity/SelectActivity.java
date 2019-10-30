package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.model.adapter.MyScheduleAdapter;
import com.bw.movie.model.adapter.MySeatAdapter;
import com.bw.movie.model.entity.OrderBean;
import com.bw.movie.model.entity.WXPayBean;
import com.bw.movie.model.entity.ScheduleBean;
import com.bw.movie.model.entity.SeatBean;
import com.bw.movie.model.entity.ZFBPayBean;
import com.bw.movie.util.MD5Util;
import com.bw.movie.view.constant.Constant;
import com.bw.movie.view.view.SeatTable;
import com.bw.movie.view.view.TitleView;
import com.bw.movie.wxapi.WeiXinUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class SelectActivity extends IBaseActivity implements View.OnClickListener {
    private static final String TAG = "SelectActivity";
    public static final int SDK_PAY_FLAG = 1;//原生端调用支付宝
    private TitleView title_select;
    private JCVideoPlayerStandard jcv_select;
    private RecyclerView rv_select_seat,rv_select_time;
    private TextView tv_select_time;
    private Button btn_select;
    private int hallID,scheduleID;
    private int userId;
    private String sessionId;
    private String orderId;
    private int wxType=1,zfbType=2,index=1,count=0;
    private double price;
    private String str;
    private Handler handler = new Handler();
    private ArrayList<String> list;
    private String row;
    private String seat;


    @Override
    protected int initLayoutID() {
        return R.layout.activity_select;
    }


    @Override
    protected void initView() {
        title_select = findViewById(R.id.title_select);
        jcv_select = findViewById(R.id.jcv_select);
        tv_select_time = findViewById(R.id.tv_select_time);
        rv_select_seat = findViewById(R.id.rv_select_seat);
        rv_select_time = findViewById(R.id.rv_select_time);
        btn_select = findViewById(R.id.btn_select);
    }

    @Override
    protected void initData() {
        //从sp取出userId和sessionId
        SharedPreferences sp = getSharedPreferences(Constant.SP_USER_NAME, MODE_PRIVATE);
        userId = sp.getInt(Constant.USER_ID, 0);
        sessionId = sp.getString(Constant.SESSION_ID, "");


        Intent intent = getIntent();
        int movieId = intent.getIntExtra(Constant.MOVIE_ID, 1);
        int cinemaId = intent.getIntExtra(Constant.CINEMA_ID, 1);
        String name = intent.getStringExtra(Constant.MOVIE_NAME);
        String imageUrl = intent.getStringExtra(Constant.MOVIE_IMGURL);
        String videoUrl = intent.getStringExtra(Constant.MOVIE_URL);
        //标题
        title_select.title_tv.setText(name);
        //视频
        jcv_select.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"");
        ImageView thumbImageView = jcv_select.thumbImageView;
        Glide.with(this).load(imageUrl).into(thumbImageView);



        //请求排期接口
        presenter.scheduleList(movieId,cinemaId);

        list = new ArrayList<>();
        title_select.title_img.setOnClickListener(this);
        btn_select.setOnClickListener(this);
    }

    @Override
    public void onDataSuccess(Object obj) {
        if (obj instanceof ScheduleBean) {
            ScheduleBean scheduleBean = (ScheduleBean) obj;
            List<ScheduleBean.ResultBean> result = scheduleBean.getResult();

            int size = result.size();
            tv_select_time.setText("选择影厅和时间("+size+")");

            MyScheduleAdapter myScheduleAdapter = new MyScheduleAdapter(result, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            rv_select_time.setLayoutManager(linearLayoutManager);
            rv_select_time.setAdapter(myScheduleAdapter);


            myScheduleAdapter.setScheduleListener(new MyScheduleAdapter.ScheduleListener() {
                @Override
                public void getHallId(int id,int hallId, double fare) {
                    price = fare;
                    scheduleID = id;
                    if (id!=0 && hallId!=0){
                        //座位接口
                        presenter.seatInfoList(hallId);

                    }
                }

            });
        }else if(obj instanceof SeatBean){
            final SeatBean seatBean = (SeatBean) obj;
            final List<SeatBean.ResultBean> result = seatBean.getResult();

            MySeatAdapter mySeatAdapter = new MySeatAdapter(result, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,40);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 5;
                }
            });

            rv_select_seat.setLayoutManager(gridLayoutManager);
            rv_select_seat.setAdapter(mySeatAdapter);
            mySeatAdapter.notifyDataSetChanged();

            mySeatAdapter.setOnItemCheckListener(new MySeatAdapter.OnItemCheckListener() {
                @Override
                public void onCheck(int position, String row, String seat, boolean isChecked) {
                    if (isChecked) {
                        str = row+"-"+seat;

                        String sign = userId+""+scheduleID+"movie";
                        String md5Sign = MD5Util.MD5(sign);
                        presenter.orderList(userId,sessionId,scheduleID,str,md5Sign);

                        btn_select.setText("¥ "+price);
                    }else {
                        str = "";
                        btn_select.setText("¥ "+0.0);
                    }

                }
            });

        }else if(obj instanceof OrderBean){
            OrderBean orderBean = (OrderBean) obj;
            String status = orderBean.getStatus();
            if (status.equals("0000")) {
                Toast.makeText(this, "订单创建成功", Toast.LENGTH_SHORT).show();
                orderId = orderBean.getOrderId();
                Log.e(TAG, "onDataSuccess: "+ orderId);
            }else if(status.equals("9999")){
                Toast.makeText(this, "创建订单失败,请先登录", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "信息有误", Toast.LENGTH_SHORT).show();
            }
        }else if(obj instanceof WXPayBean){
            WXPayBean payBean = (WXPayBean) obj;
            String status = payBean.getStatus();
            if (status.equals("0000")) {
                String partnerId = payBean.getPartnerId();
                String prepayId = payBean.getPrepayId();
                String packageValue = payBean.getPackageValue();
                String nonceStr = payBean.getNonceStr();
                String timeStamp = payBean.getTimeStamp();
                String sign = payBean.getSign();

                WeiXinUtil.wakeWeiXinPay(partnerId,prepayId,packageValue,nonceStr,timeStamp,sign);
            }else {

            }

        }else if(obj instanceof ZFBPayBean){
            final ZFBPayBean zfbPayBean = (ZFBPayBean) obj;
            ;
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(SelectActivity.this);
                    Map<String,String> result = alipay.payV2(zfbPayBean.getResult(),true);

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    handler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        }


    }

    @Override
    public void onDataError(String msg) {

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_img:
                finish();
                break;
            case R.id.btn_select:
                if (scheduleID==0) {
                    Toast.makeText(this, "请先选择影厅和时间", Toast.LENGTH_SHORT).show();
                }else {
                    if(str.equals("")){
                        Toast.makeText(this, "请先选座", Toast.LENGTH_SHORT).show();
                    }else {
                        initPopWindow();
                    }
                }
                break;
        }
    }


    public void initPopWindow(){
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_popwindow, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.WRAP_CONTENT, true);
        //引入依附的布局
        View parentView = inflate;
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(false);
        //设置焦点
        popupWindow.setFocusable(true);
        //找控件
        LinearLayout wx_pay = parentView.findViewById(R.id.linear_pop_wxpay);
        LinearLayout zfb_pay = parentView.findViewById(R.id.linear_pop_zfbpay);
        //微信
        wx_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.wxPayList(userId,sessionId,wxType,orderId);
                popupWindow.dismiss();
            }
        });
        //支付宝
        zfb_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.zfbPayList(userId,sessionId,zfbType,orderId);
                popupWindow.dismiss();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        jcv_select.releaseAllVideos();
    }
}
