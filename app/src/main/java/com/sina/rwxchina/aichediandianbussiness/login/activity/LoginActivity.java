package com.sina.rwxchina.aichediandianbussiness.login.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.base.baseentity.BaseShopInfo;
import com.jnyilin.basiclib.utils.appUtils.PhoneNumberUtil;
import com.jnyilin.basiclib.utils.appupdateutils.AppUpdateUtils;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.MainActivity;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.activity.UpdatePasswordActivity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 登录
 * @modifyRecord
 */

public class LoginActivity extends BaseActivity {
    private TextView tvRegister;//注册
    private TextView tvForgetPasswords;//忘记密码
    private EditText etPhone;//电话号码
    private EditText etPassword;//密码
    private Button btnLogin;//登陆按钮
    private String phonenumber;//电话号码
    private String password;//密码
    @Override
    public void initParms(Bundle params) {
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        tvRegister =findViewById(R.id.activity_login_register);
        etPhone =  findViewById(R.id.activity_login_phone);
        etPassword =findViewById(R.id.activity_login_password);
        btnLogin = findViewById(R.id.activity_login_btn_login);
        tvForgetPasswords =  findViewById(R.id.activity_login_forget_passwords);
    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgetPasswords.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_btn_login://登陆
                phonenumber = etPhone.getText().toString().trim();//手机号
                password = etPassword.getText().toString();//密码
                if ("".equals(phonenumber) || (!PhoneNumberUtil.isPhoneNumber(phonenumber))) {
                    showToast("手机号输入不正确");
                } else if ("".equals(password)) {
                    showToast("您输入的密码不正确，请重新输入！");
                } else {
                    login();//登陆
                }
                break;
            case R.id.activity_login_register://注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_login_forget_passwords://忘记密码
                Intent i2 = new Intent(LoginActivity.this, UpdatePasswordActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("type","忘记密码");
                i2.putExtras(bundle);
                startActivity(i2);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        init();
    }

    private void init() {
        getpermissions();
        initData();
        update();
//        ifUpdate();
//        login();
    }

    /**
     * 登录
     */
    private void login(){
        Map<String,String> params=new HashMap<>();
        params.put("username",phonenumber);
        params.put("password",password);
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e(result);
                BaseShopInfo shopInfo=new Gson().fromJson(result,BaseShopInfo.class);
                CommonParametersUtils.saveUid(getApplicationContext(),shopInfo.getUid());
                CommonParametersUtils.saveToken(getApplicationContext(),shopInfo.getToken());
                CommonParametersUtils.savePhone(getApplicationContext(),phonenumber);
                CommonParametersUtils.savePwd(getApplicationContext(),password);
                String request=shopInfo.getResult();
                switch (request){
                    case "4"://审核失败标示
                        Intent intent4=new Intent(LoginActivity.this,AuditActivity.class);
                        intent4.putExtra("type",4);
                        startActivity(intent4);
                        break;
                    case "5"://审核成功
                        Intent intent5=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case "6"://没有资料标示
                        Intent intent6=new Intent(LoginActivity.this,AuditActivity.class);
                        intent6.putExtra("type",6);
                        startActivity(intent6);
                        break;
                    case "7"://审核中标示
                        Intent intent7=new Intent(LoginActivity.this,AuditActivity.class);
                        intent7.putExtra("type",7);
                        startActivity(intent7);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.LOGIN_PWD,params);
    }

    /**
     * 设置初始数据
     */
    private void initData(){
        String phone=CommonParametersUtils.getPhone(getApplicationContext());
        String pwd=CommonParametersUtils.getPwd(getApplicationContext());
        if (!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(pwd)){
            etPhone.setText(phone);
            etPassword.setText(pwd);
        }
    }

    /**
     * 版本更新
     */
    private void update(){
        AppUpdateUtils appUpdateUtils=new AppUpdateUtils(this);
        appUpdateUtils.ifUpdate(HttpPath.APP_UPDATE);
    }

    //检测是否获取了系统权限
    private void getpermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,},
                    0);
        }
    }
}
