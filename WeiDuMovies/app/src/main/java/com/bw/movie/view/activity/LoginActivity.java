package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.contract.IContract;
import com.bw.movie.model.entity.LoginBean;
import com.bw.movie.model.entity.WXBean;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.presenter.MyAllPresenter;
import com.bw.movie.view.constant.Constant;
import com.bw.movie.wxapi.WeiXinUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends IBaseActivity implements View.OnClickListener, IContract.IView {
    private static final String TAG = "LoginActivity";
    private EditText edit_login_email,edit_login_pwd;
    private TextView tv_login_forget,tv_login_regist;
    private Button btn_login;
    private IContract.Presenter presenter;
    private ImageView img_weixin;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        edit_login_email = findViewById(R.id.edit_login_email);
        edit_login_pwd = findViewById(R.id.edit_login_pwd);
        tv_login_forget = findViewById(R.id.tv_login_forget);
        tv_login_regist = findViewById(R.id.tv_login_regist);
        btn_login = findViewById(R.id.btn_login);
        img_weixin = findViewById(R.id.img_weixin);
        presenter = new MyAllPresenter(this);
    }

    @Override
    protected void initData() {
        tv_login_forget.setOnClickListener(this);
        tv_login_regist.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        //微信登录
        img_weixin.setOnClickListener(this);

        Glide.with(this)
                .load(R.mipmap.weixin_copy)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_weixin);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.tv_login_forget:
                Toast.makeText(this, "暂无此功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_login_regist:
                intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String email = edit_login_email.getText().toString();
                String pwd = edit_login_pwd.getText().toString();
                String encryptPwd=null;
                if (!TextUtils.isEmpty(pwd)) {
                    encryptPwd = EncryptUtil.encrypt(pwd);
                }
                Log.e(TAG, "onClick: "+encryptPwd );
                presenter.login(email,encryptPwd);
                break;
            case R.id.img_weixin:
                WeiXinUtil.wakeWeiXin();
                break;
        }
    }

    @Override
    public void onDataSuccess(Object obj) {

        if(obj instanceof LoginBean){
            LoginBean loginBean = (LoginBean) obj;
            String message = loginBean.getMessage();
            String status = loginBean.getStatus();
            if (status.equals("0000")) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                //存入sharedpreference
                SharedPreferences sp = getSharedPreferences(Constant.SP_USER_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                LoginBean.ResultBean result = loginBean.getResult();
                int userId = result.getUserId();
                String sessionId = result.getSessionId();
                editor.putInt(Constant.USER_ID,userId);
                editor.putString(Constant.SESSION_ID,sessionId);
                editor.commit();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }else if(obj instanceof WXBean){
            WXBean wxBean = (WXBean) obj;
            Log.e(TAG, "onDataSuccess: "+wxBean );

            String status = wxBean.getStatus();
            if (status.equals("0000")) {
                WXBean.ResultBean result = wxBean.getResult();
                int userId = result.getUserId();
                String sessionId = result.getSessionId();
                //微信登录成功后把userid和sessionid存入sharedpreference
                SharedPreferences sp_wx = getSharedPreferences(Constant.SP_WX_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp_wx.edit();
                editor.putInt(Constant.WX_USER_ID,userId);
                editor.putString(Constant.WX_SESSION_ID,sessionId);
                editor.commit();

                WXBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
                EventBus.getDefault().postSticky(userInfo);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public void onDataError(String msg) {
        Log.e(TAG, "onDataError: "+msg );
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getWXLoginCode(String code){
        Log.e(TAG, "getWXLoginCode: "+code );
        presenter.wxLogin(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
