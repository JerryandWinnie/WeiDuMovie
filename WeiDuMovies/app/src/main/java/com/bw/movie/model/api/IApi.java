package com.bw.movie.model.api;

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
import com.bw.movie.model.entity.RegistBean;
import com.bw.movie.model.entity.ScheduleBean;
import com.bw.movie.model.entity.SeatBean;
import com.bw.movie.model.entity.SoonBean;
import com.bw.movie.model.entity.TuiBean;
import com.bw.movie.model.entity.WXBean;
import com.bw.movie.model.entity.WXPayBean;
import com.bw.movie.model.entity.ZFBPayBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/9/28 14:59
 */
public interface IApi {
    //微信登录
    @FormUrlEncoded
    @POST("movieApi/user/v1/weChatBindingLogin")
    Observable<WXBean> weiXinLogin(@Field("code")String code);

    //登录
    @FormUrlEncoded
    @POST("movieApi/user/v2/login")
    Observable<LoginBean> login(@Field("email")String email,
                                @Field("pwd")String pwd);
    //注册
    @FormUrlEncoded
    @POST("movieApi/user/v2/register")
    Observable<RegistBean> register(@Field("nickName")String nickName,
                                    @Field("pwd")String pwd,
                                    @Field("email")String email,
                                    @Field("code")String code);
    //发送验证码
    @FormUrlEncoded
    @POST("movieApi/user/v2/sendOutEmailCode")
    Observable<EmailBean> sendEmail(@Field("email")String email);

    //banner
    @GET("movieApi/tool/v2/banner")
    Observable<BannerBean> getBanner();

    //正在热映
    @GET("movieApi/movie/v2/findReleaseMovieList")
    Observable<AtHotBean> atHot(@Header ("userId")int userId,
                                @Header("sessionId")String sessionId,
                                @Query("page")int page,
                                @Query("count")int count);

    //热门列表
    @GET("movieApi/movie/v2/findHotMovieList")
    Observable<HotBean> Hot(@Header ("userId")int userId,
                            @Header("sessionId")String sessionId,
                            @Query("page")int page,
                            @Query("count")int count);

    //即将上映
    @GET("movieApi/movie/v2/findComingSoonMovieList")
    Observable<SoonBean> soon(@Header ("userId")int userId,
                              @Header("sessionId")String sessionId,
                              @Query("page")int page,
                              @Query("count")int count);

    //推荐影院
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<TuiBean> tui(@Header ("userId")int userId,
                            @Header("sessionId")String sessionId,
                            @Query("page")int page,
                            @Query("count")int count);

    //查询附近影院
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    Observable<NearbyBean> nearby(@Header ("userId")int userId,
                                  @Header("sessionId")String sessionId,
                                  @Query("longitude")String longitude,
                                  @Query("latitude")String latitude,
                                  @Query("page")int page,
                                  @Query("count")int count);

    //查看电影详情
    @GET("movieApi/movie/v2/findMoviesDetail")
    Observable<MovieInfoBean> movieInfo(@Header ("userId")int userId,
                                    @Header("sessionId")String sessionId,
                                    @Query("movieId")int movieId);

    //根据电影ID和影院ID查询电影排期列表
    @GET("movieApi/movie/v2/findMovieSchedule")
    Observable<ScheduleBean> movieSchedule(@Query("movieId")int movieId, @Query("cinemaId")int cinemaId);

    //根据影厅id 查询座位信息
    @GET("movieApi/movie/v2/findSeatInfo")
    Observable<SeatBean> seatInfo(@Query("hallId")int hallId);

    //查询区域列表
    @GET("movieApi/tool/v2/findRegionList")
    Observable<AreaBean> getArea();

    //根据电影id,区域id 查询播放影院信息
    @GET("movieApi/movie/v2/findCinemasInfoByRegion")
    Observable<CinemaInfoBean> getCinemaInfo(@Query("movieId")int movieId,
                                             @Query("regionId")int regionId,
                                             @Query("page")int page,
                                             @Query("count")int count);

    //购票下单
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/buyMovieTickets")
    Observable<OrderBean> order(@Header ("userId")int userId,
                                @Header("sessionId")String sessionId,
                                @Field("scheduleId")int scheduleId,
                                @Field("seat")String seat,
                                @Field("sign")String sign);

    //微信支付
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/pay")
    Observable<WXPayBean> wxPay(@Header ("userId")int userId,
                                @Header("sessionId")String sessionId,
                                @Field("payType")int payType,
                                @Field("orderId")String orderId);

    //支付宝支付
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/pay")
    Observable<ZFBPayBean> zfbPay(@Header ("userId")int userId,
                                  @Header("sessionId")String sessionId,
                                  @Field("payType")int payType,
                                  @Field("orderId")String orderId);
}
