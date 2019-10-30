package com.bw.movie.model;

import com.bw.movie.model.api.IApi;
import com.bw.movie.model.entity.AreaBean;
import com.bw.movie.model.entity.AtHotBean;
import com.bw.movie.model.entity.BannerBean;
import com.bw.movie.model.entity.CinemaInfoBean;
import com.bw.movie.model.entity.EmailBean;
import com.bw.movie.model.entity.HotBean;
import com.bw.movie.model.entity.LoginBean;
import com.bw.movie.model.entity.MovieInfoBean;
import com.bw.movie.model.entity.NearbyBean;
import com.bw.movie.model.entity.OrderBean;
import com.bw.movie.model.entity.WXPayBean;
import com.bw.movie.model.entity.RegistBean;
import com.bw.movie.model.entity.ScheduleBean;
import com.bw.movie.model.entity.SeatBean;
import com.bw.movie.model.entity.SoonBean;
import com.bw.movie.model.entity.TuiBean;
import com.bw.movie.model.entity.WXBean;
import com.bw.movie.model.entity.ZFBPayBean;
import com.bw.movie.util.HttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/10 14:36
 */
public class MyAllModel {

    public interface ICallBack{
        void onSuccess(Object obj);
        void onError(String msg);
    }

    //微信登录
    public static void weiXinLogin(String code, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.weiXinLogin(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WXBean>() {
                    @Override
                    public void accept(WXBean wxBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(wxBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //发送邮箱验证码
    public static void sendM(final String email, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.sendEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmailBean>() {
                    @Override
                    public void accept(EmailBean emailBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(emailBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //注册
    public static void registM(String nickName, String pwd, String email, String code, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.register(nickName,pwd,email,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistBean>() {
                    @Override
                    public void accept(RegistBean registBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(registBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //登录
    public static void loginM(final String email, final String pwd, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.login(email,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(loginBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //轮播图
    public static void bannerM(final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(bannerBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //正在上映
    public static void atHotM(int userId,String sessionId,int page, int count, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.atHot(userId,sessionId,page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AtHotBean>() {
                    @Override
                    public void accept(AtHotBean atHotBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(atHotBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //即将上映
    public static void soonM(int userId,String sessionId,int page, int count, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.soon(userId,sessionId,page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SoonBean>() {
                    @Override
                    public void accept(SoonBean soonBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(soonBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //热门列表
    public static void hotM(int userId,String sessionId,int page, int count, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.Hot(userId,sessionId,page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotBean>() {
                    @Override
                    public void accept(HotBean hotBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(hotBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //推荐影院
    public static void tuiM(int userId,String sessionId,int page, int count, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.tui(userId,sessionId,page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TuiBean>() {
                    @Override
                    public void accept(TuiBean tuiBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(tuiBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //附近影院
    public static void nearbyM(int userId,String sessionId,String longitude,String latitude,int page, int count, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.nearby(userId,sessionId,longitude,latitude,page,count).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NearbyBean>() {
                    @Override
                    public void accept(NearbyBean nearbyBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(nearbyBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //电影详情
    public static void infoM(int userId, String sessionId, int movieId, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.movieInfo(userId,sessionId,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieInfoBean>() {
                    @Override
                    public void accept(MovieInfoBean movieInfoBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(movieInfoBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //根据电影ID和影院ID查询电影排期列表
    public static void scheduleM(int movieId, int cinemaId, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.movieSchedule(movieId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ScheduleBean>() {
                    @Override
                    public void accept(ScheduleBean scheduleBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(scheduleBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //根据影厅id 查询座位信息
    public static void seatM(int hallId, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.seatInfo(hallId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SeatBean>() {
                    @Override
                    public void accept(SeatBean seatBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(seatBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //查询区域列表
    public static void areaM(final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.getArea().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaBean>() {
                    @Override
                    public void accept(AreaBean areaBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(areaBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //根据电影id,区域id 查询播放影院信息
    public static void cinemaInfoM(int movieId,int regionId,int page,int count,final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.getCinemaInfo(movieId,regionId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaInfoBean>() {
                    @Override
                    public void accept(CinemaInfoBean cinemaInfoBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(cinemaInfoBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //购票下单
    public static void orderM(int userId, String sessionId, int scheduleId,String seat,String sign,final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.order(userId,sessionId,scheduleId,seat,sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderBean>() {
                    @Override
                    public void accept(OrderBean orderBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(orderBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //微信支付
    public static void wxpayM(int userId, String sessionId, int payType, String orderId, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.wxPay(userId,sessionId,payType,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WXPayBean>() {
                    @Override
                    public void accept(WXPayBean payBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(payBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

    //支付宝支付
    public static void zfbpayM(int userId, String sessionId, int payType, String orderId, final ICallBack iCallBack){
        Retrofit retrofit = HttpUtil.getInstance().getRetrofit();
        IApi iApi = retrofit.create(IApi.class);
        iApi.zfbPay(userId,sessionId,payType,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZFBPayBean>() {
                    @Override
                    public void accept(ZFBPayBean zfbPayBean) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onSuccess(zfbPayBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iCallBack != null) {
                            iCallBack.onError(throwable.getMessage());
                        }
                    }
                });
    }

}
