package com.bw.movie.view.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bw.movie.view.constant.Constant;
import com.bw.movie.wxapi.WeiXinUtil;
import com.example.john.greendaodemo.gen.DaoMaster;
import com.example.john.greendaodemo.gen.DaoSession;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/15 15:43
 */
public class MyApplication extends Application {
    public static Context sContext;
    public static MyApplication myApplication = null;
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster daoMaster;
    private SQLiteDatabase db;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        myApplication = this;
        //注册微信
        WeiXinUtil.regToWx();

        //创建数据库
        helper = new DaoMaster.DevOpenHelper(this, Constant.DATEBASE_NAME, null);
        //获取可读写数据库
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
