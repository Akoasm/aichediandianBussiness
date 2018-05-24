package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.login.activity.RegisterActivity;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:wj
 * @datetime：2018/2/2
 * @describe：
 * @modifyRecord:
 */


public class UpdatePasswordActivity extends BaseActivity {
    private ImageView back;
    private EditText phone, code, newpassword, againpassword;
    private Button getcode, confirm;
    private String mPhonenumber;//电话号码
    private String mPassword;//密码
    private String mRepassword;//确认密码
    private String mCodeFromUser;//验证码
    /**跳转过来的类型（修改密码或者忘记密码）*/
    private String type;
    /**头标题*/
    private TextView title;
    @Override
    public void initParms(Bundle params) {
        type=params.getString("type");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        title=findViewById(R.id.activity_update_password_title);
        title.setText(type);
        back = findViewById(R.id.activity_update_password_back);
        phone = findViewById(R.id.activity_update_password_phone);
        code = findViewById(R.id.activity_update_password_code);
        newpassword = findViewById(R.id.activity_update_password_new);
        againpassword = findViewById(R.id.activity_update_password_renew);
        getcode = findViewById(R.id.activity_update_password_getcode);
        confirm = findViewById(R.id.activity_update_password_commit);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        getcode.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.activity_update_password_back) {
            finish();
        } else if (i == R.id.activity_update_password_getcode) {
            mPhonenumber = phone.getText().toString();
            if (TextUtils.isEmpty(mPhonenumber)) {
                Toast.makeText(UpdatePasswordActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            } else {
                getCode();
            }
        } else if (i == R.id.activity_update_password_commit) {
            mPhonenumber = phone.getText().toString().trim();
            mPassword = newpassword.getText().toString();
            mRepassword = againpassword.getText().toString();
            mCodeFromUser = code.getText().toString();
            if(!TextUtils.isEmpty(mPhonenumber) && !TextUtils.isEmpty(mPassword) && !TextUtils.isEmpty(mRepassword) && !TextUtils.isEmpty(mCodeFromUser)){
                if(!mPassword.equals(mRepassword)){
                    Toast.makeText(UpdatePasswordActivity.this, "两次密码填写不一致", Toast.LENGTH_SHORT).show();
                }else{
                    commoit();
                }
            }else{
                Toast.makeText(UpdatePasswordActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private void commoit() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone",mPhonenumber);
        params.put("verify",mCodeFromUser);
        params.put("password",mPassword);
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e(result);
                ToastUtils.showShort("修改成功");
                CommonParametersUtils.savePwd(getApplicationContext(),mPassword);
                finish();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.UPDATE_PASSWORD, params);
    }

    private void getCode() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", mPhonenumber);
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (code==0){
                    CountDownUtils.countDown(getcode,60);
                }else {
                    showToast(msg);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETCODE, params);
    }


}
