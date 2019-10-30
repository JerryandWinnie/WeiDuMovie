package com.bw.movie.presenter;

import com.bw.movie.base.IBaseView;
import com.bw.movie.contract.IContract;
import com.bw.movie.model.MyAllModel;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/11 9:33
 */
public class MyAllPresenter extends IContract.Presenter {

    public MyAllPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }
    //微信登录
    @Override
    public void wxLogin(String code) {
        MyAllModel.weiXinLogin(code, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //发送验证码
    @Override
    public void send(String email) {
        MyAllModel.sendM(email, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                if (iBaseView != null) {
                    iBaseView.onDataSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iBaseView != null) {
                    iBaseView.onDataError(msg);
                }
            }
        });
    }
    //注册
    @Override
    public void regist(String nickName, String pwd, String email, String code) {
        MyAllModel.registM(nickName, pwd, email, code, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                if (iBaseView != null) {
                    iBaseView.onDataSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iBaseView != null) {
                    iBaseView.onDataError(msg);
                }
            }
        });
    }
    //登录
    @Override
    public void login(String email, String pwd) {
        MyAllModel.loginM(email, pwd, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                if (iBaseView != null) {
                    iBaseView.onDataSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iBaseView != null) {
                    iBaseView.onDataError(msg);
                }
            }
        });
    }
    //banner
    @Override
    public void banner() {
        MyAllModel.bannerM(new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                if (iBaseView != null) {
                    iBaseView.onDataSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iBaseView != null) {
                    iBaseView.onDataError(msg);
                }
            }
        });
    }
    //正在上映
    @Override
    public void atHotList(int userId,String sessionId,int page, int count) {
        MyAllModel.atHotM(userId,sessionId,page, count, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //即将上映
    @Override
    public void soonList(int userId,String sessionId,int page, int count) {
        MyAllModel.soonM(userId,sessionId,page, count, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //热门列表
    @Override
    public void hotList(int userId,String sessionId,int page, int count) {
        MyAllModel.hotM(userId,sessionId,page, count, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //推荐影院
    @Override
    public void tuiList(int userId,String sessionId,int page, int count) {
        MyAllModel.tuiM(userId,sessionId,page, count, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //附近影院
    @Override
    public void nearbyList(int userId, String sessionId, String longitude, String latitude, int page, int count) {
        MyAllModel.nearbyM(userId, sessionId, longitude, latitude, page, count, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //电影详情
    @Override
    public void infoList(int userId, String sessionId, int movieId) {
        MyAllModel.infoM(userId, sessionId, movieId, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //根据电影ID和影院ID查询电影排期列表
    @Override
    public void scheduleList(int movieId, int cinemaId) {
        MyAllModel.scheduleM(movieId, cinemaId, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                if (iBaseView != null) {
                    iBaseView.onDataSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iBaseView != null) {
                    iBaseView.onDataError(msg);
                }
            }
        });
    }
    //根据影厅id 查询座位信息
    @Override
    public void seatInfoList(int hallId) {
        MyAllModel.seatM(hallId, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                if (iBaseView != null) {
                    iBaseView.onDataSuccess(obj);
                }
            }

            @Override
            public void onError(String msg) {
                if (iBaseView != null) {
                    iBaseView.onDataError(msg);
                }
            }
        });
    }
    //查询区域列表
    @Override
    public void areaList() {
        MyAllModel.areaM(new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //根据电影id,区域id 查询播放影院信息
    @Override
    public void cinemaInfoList(int movieId, int regionId, int page, int count) {
        MyAllModel.cinemaInfoM(movieId,regionId,page, count, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //购票下单
    @Override
    public void orderList(int userId, String sessionId, int scheduleId, String seat, String sign) {
        MyAllModel.orderM(userId,sessionId,scheduleId,seat,sign, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //微信支付
    @Override
    public void wxPayList(int userId, String sessionId, int payType, String orderId) {
        MyAllModel.wxpayM(userId,sessionId,payType,orderId, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }
    //支付宝支付
    @Override
    public void zfbPayList(int userId, String sessionId, int payType, String orderId) {
        MyAllModel.zfbpayM(userId,sessionId,payType,orderId, new MyAllModel.ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iBaseView.onDataSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onDataError(msg);
            }
        });
    }

    @Override
    public void onDestroy() {
        if (iBaseView != null) {
            iBaseView = null;
        }
    }
}
