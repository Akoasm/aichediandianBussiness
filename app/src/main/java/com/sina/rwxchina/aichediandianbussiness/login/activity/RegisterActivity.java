package com.sina.rwxchina.aichediandianbussiness.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.appUtils.CountDownUtils;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.jnyilin.basiclib.utils.webViewUtils.activity.DefaultWebViewActivity;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 注册
 * @modifyRecord
 */

public class RegisterActivity extends BaseActivity {
    private EditText etPhone;//手机
    private EditText etCode;
    private EditText etPassword;
    private EditText etRePassword;
    private TextView user_agreement;//用户协议
    private TextView btnCode;
    private Button btnRegister;
    private String phonenumber;//手机号码
    private String password;//密码
    private String code;//验证码
    @Override
    public void initParms(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        etPhone =findViewById(R.id.activity_register_et_phone);
        etPhone.addTextChangedListener(textWatcher);
        etCode = findViewById(R.id.activity_register_et_code);
        etPassword = findViewById(R.id.activity_register_et_password);
        etRePassword =  findViewById(R.id.activity_register_et_repassword);
        btnCode = findViewById(R.id.activity_register_btn_code);
        btnRegister =  findViewById(R.id.activity_register_btn_register);
        user_agreement =  findViewById(R.id.register_userAgreement);
    }

    @Override
    public void setListener() {
        btnRegister.setOnClickListener(this);
        btnCode.setOnClickListener(this);
        user_agreement.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_register_btn_code://验证码
                phonenumber = etPhone.getText().toString();
                if (phonenumber.equals("")) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                    // 开始计时
                    getVerificationCode();

                }

                break;
            case R.id.activity_register_btn_register://注册
                phonenumber = etPhone.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                code = etCode.getText().toString().trim();
                String rePassword=etRePassword.getText().toString().trim();
                Log.i("hrr","phonenumber="+phonenumber+"  password="+password+"  code="+code);
                if ((!TextUtils.isEmpty(phonenumber) && (!TextUtils.isEmpty(password)) && (!TextUtils.isEmpty(code))&& (!TextUtils.isEmpty(rePassword)))) {
                    if (!rePassword.equals(password)){
                        ToastUtils.showShort("两次密码不一致");
                        return;
                    }
                    register();
                } else {
                    Toast.makeText(RegisterActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_userAgreement:
                Intent intent = new Intent(RegisterActivity.this,DefaultWebViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",HttpPath.REGISTERAGREEMENT);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        CommonParametersUtils.saveToken(getApplicationContext(),"");
        CommonParametersUtils.saveUid(getApplicationContext(),"");
    }

    /**
     * 注册
     */
    private void register(){
        Map<String,String> params=new HashMap<>();
        params.put("phone",phonenumber);
        params.put("verify",code);
        params.put("password",password);
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e(result);
                JSONObject jsonObject=new JSONObject(result);
                String uid=jsonObject.optString("uid");
                String token=jsonObject.optString("token");
                CommonParametersUtils.saveUid(getApplicationContext(),uid);
                CommonParametersUtils.saveToken(getApplicationContext(),token);
                CommonParametersUtils.savePhone(getApplicationContext(),phonenumber);
                CommonParametersUtils.savePwd(getApplicationContext(),password);
                //注册成功，跳转到修改资料界面
                Intent intent=new Intent(RegisterActivity.this,DefaultWebViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",HttpPath.MODIFY_SHOP);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.REGISTER,params);
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode(){
        Map<String,String> params=new HashMap<>();
        params.put("phone",phonenumber);
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (code==0){
                    CountDownUtils.countDown(btnCode,60);
                }else {
                    showToast(msg);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GETCODE,params);
    }

    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if(s.toString().length()==0){
                showToast("设置用户名后不可更改");
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
