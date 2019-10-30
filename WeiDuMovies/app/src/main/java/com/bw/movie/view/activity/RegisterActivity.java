package com.bw.movie.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.IBaseActivity;
import com.bw.movie.contract.IContract;
import com.bw.movie.model.entity.RegistBean;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.presenter.MyAllPresenter;
import com.bw.movie.view.view.TitleView;

public class RegisterActivity extends IBaseActivity implements View.OnClickListener, IContract.IView {
    private static final String TAG = "RegisterActivity";
    private TitleView title;
    private EditText edit_regist_name,edit_regist_email,edit_regist_pwd,edit_regist_code;
    private TextView tv_regist_send,tv_regist_login;
    private Button btn_regist;
    private IContract.Presenter presenter;

    @Override
    protected int initLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        title = findViewById(R.id.title);
        edit_regist_name = findViewById(R.id.edit_regist_name);
        edit_regist_email = findViewById(R.id.edit_regist_email);
        edit_regist_pwd = findViewById(R.id.edit_regist_pwd);
        edit_regist_code = findViewById(R.id.edit_regist_code);
        tv_regist_send = findViewById(R.id.tv_regist_send);
        tv_regist_login = findViewById(R.id.tv_regist_login);
        btn_regist = findViewById(R.id.btn_regist);
        presenter = new MyAllPresenter(this);
    }

    @Override
    protected void initData() {
        title.title_tv.setText("注册");

        title.setOnClickListener(this);
        tv_regist_send.setOnClickListener(this);
        tv_regist_login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        String name = edit_regist_name.getText().toString();
        String email = edit_regist_email.getText().toString();
        String pwd = edit_regist_pwd.getText().toString();
        String code = edit_regist_code.getText().toString();
        switch (view.getId()){
            case R.id.tv_regist_send:
                presenter.send(email);
                Toast.makeText(this, "正在发送中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_regist_login:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_regist:
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(email) || TextUtils.isEmpty(code)){
                    Toast.makeText(this, "还有信息没输入，请继续输入", Toast.LENGTH_SHORT).show();
                }
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(code)){
                    String encryptPwd = EncryptUtil.encrypt(pwd);
                    presenter.regist(name,encryptPwd,email,code);
                }
                break;
            case R.id.title:
                finish();
                break;
        }
    }

    @Override
    public void onDataSuccess(Object obj) {
        /*//验证码
        EmailBean emailBean = (EmailBean) obj;
        String emailMessage = emailBean.message;
        Toast.makeText(this, emailMessage, Toast.LENGTH_SHORT).show();*/

        //注册
        RegistBean registBean = (RegistBean) obj;
        String registStatus = registBean.status;
        String registMessage = registBean.message;
        Log.e("aaa", "onDataSuccess: "+registMessage );
        if (registStatus.equals("0000")) {
            Toast.makeText(this, registMessage, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, registMessage, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDataError(String msg) {
        Log.e(TAG, "onDataError: "+msg );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
