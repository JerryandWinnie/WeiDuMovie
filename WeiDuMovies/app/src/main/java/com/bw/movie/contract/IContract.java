package com.bw.movie.contract;

import com.bw.movie.base.IBasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.model.MyAllModel;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/11 9:37
 */
public interface IContract {
    interface IView extends IBaseView {}


    abstract class Presenter extends IBasePresenter {
        public Presenter(IBaseView iBaseView) {
            super(iBaseView);
        }
        //微信登录
        public abstract void wxLogin(String code);
        //发送验证码
        public abstract void send(String email);
        //注册
        public abstract void regist(String nickName,String pwd,String email,String code);
        //登录
        public abstract void login(String email,String pwd);
        //banner
        public abstract void banner();
        //正在上映
        public abstract void atHotList(int userId,String sessionId,int page,int count);
        //即将上映
        public abstract void soonList(int userId,String sessionId,int page,int count);
        //热门列表
        public abstract void hotList(int userId,String sessionId,int page,int count);
        //推荐影院
        public abstract void tuiList(int userId,String sessionId,int page,int count);
        //附近影院
        public abstract void nearbyList(int userId,String sessionId,String longitude,String latitude,int page, int count);
        //电影详情
        public abstract void infoList(int userId, String sessionId, int movieId);
        //根据电影ID和影院ID查询电影排期列表
        public abstract void scheduleList(int movieId,int cinemaId);
        //根据影厅id 查询座位信息
        public abstract void seatInfoList(int hallId);
        //查询区域列表
        public abstract void areaList();
        //根据电影id,区域id 查询播放影院信息
        public abstract void cinemaInfoList(int movieId,int regionId,int page,int count);
        //购票下单
        public abstract void orderList(int userId, String sessionId, int scheduleId,String seat,String sign);
        //微信支付
        public abstract void wxPayList(int userId, String sessionId, int payType, String orderId);
        //支付宝支付
        public abstract void zfbPayList(int userId, String sessionId, int payType, String orderId);


        public abstract void onDestroy();
    }
}
