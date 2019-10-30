package com.bw.movie.wxapi;

import com.bw.movie.view.app.MyApplication;
import com.bw.movie.view.constant.Constant;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/16 19:55
 */
public class WeiXinUtil {

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = Constant.WEIWIN_APPID;

    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI api;

    public static void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(MyApplication.sContext, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }


    //唤起微信
    public static void wakeWeiXin(){
        // send oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    //调用支付
    public static void wakeWeiXinPay(String partnerId,String prepayId,String packageValue,String nonceStr,String timeStamp,String sign){
        PayReq request = new PayReq();
        request.appId = APP_ID;
        request.partnerId = partnerId;
        request.prepayId= prepayId;
        request.packageValue = packageValue;
        request.nonceStr= nonceStr;
        request.timeStamp= timeStamp;
        request.sign= sign;
        api.sendReq(request);
    }

}
