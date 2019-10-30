package com.bw.movie.wxapi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;
import com.bw.movie.view.constant.Constant;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import static com.bw.movie.wxapi.WeiXinUtil.api;

/**
 * @Author: 张伟成
 * @CreateDate: 2019/10/23 19:38
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

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

        if(baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            Log.e("aaa", "onResp: "+baseResp.errCode );

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_tip);
            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(baseResp.errCode)));
            builder.show();
        }

        finish();
    }
}
