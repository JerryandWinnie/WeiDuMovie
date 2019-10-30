package com.bw.movie.model.entity;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/25 10:50
 */
public class ZFBPayBean {

    /**
     * result : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018080760951276&biz_content=%7B%22out_trade_no%22%3A%2220191024152922934%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.25%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiTest%2FaliPay%2FmovieNotification&sign=m6F%2B0h%2FbDKLUbcAxDAqxiwv9TsGS9w1CTRkXiYDZAkNRpvCZVR9%2BmSy6BuOdY6%2B%2Fsvd%2Fuc10kn0472PJW4sr7UHOV9D3f613AHDW%2B%2B2y6ruq4Vc4zizQfBeLzYuGHQw7GR1naIwCOegdoxQ0Q4pGt49sel5Xo%2BMWzd886SOu47LCWaWpK6KXMYX4QcnoV6kuV87MKgiP49TcTD6K0AQlAbnJvzEkL8nECYzBmM1lPRAGOIux1gd5XSC3E%2B%2Bf9xVGFPgePR6lBSwWeL7YCz0QaBqNoD%2BY0vxiYpH4K2Ca%2B6rbIQL%2BnnK%2BvRPAnHwf3WCFxWF4tcYr42BJlCt1AlMrbg%3D%3D&sign_type=RSA2&timestamp=2019-10-25+10%3A48%3A05&version=1.0
     * message : ok
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
