package com.bw.movie.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

import static com.bw.movie.wxapi.WeiXinUtil.api;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/16 19:58
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {


        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {

        int errCode = baseResp.errCode;
        switch (errCode){
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                EventBus.getDefault().post(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "用户取消", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this, "用户拒绝", Toast.LENGTH_SHORT).show();
                break;
                default:
                    Toast.makeText(this, "用户返回", Toast.LENGTH_SHORT).show();
                    break;
        }
        finish();
    }
}
