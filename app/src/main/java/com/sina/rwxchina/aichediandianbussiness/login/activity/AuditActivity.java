package com.sina.rwxchina.aichediandianbussiness.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.webViewUtils.activity.DefaultWebViewActivity;
import com.sina.rwxchina.aichediandianbussiness.R;

/**
 * @author HRR
 * @datetime 2018/2/5
 * @describe 审核标识页面
 * @modifyRecord
 */

public class AuditActivity extends BaseActivity {
    private TextView audit_fail_tv,audit_data_tv;
    private RelativeLayout audit_fail_rl,audit_aduiting_rl,audit_data_rl;
    private  int type;
    @Override
    public void initParms(Bundle params) {
        type=params.getInt("type");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_audit;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        audit_aduiting_rl= findViewById(R.id.audit_auditing);
        audit_fail_rl=  findViewById(R.id.audit_fail);
        audit_data_rl=  findViewById(R.id.audit_data);
        audit_fail_tv=  findViewById(R.id.aduit_fail_go);
        audit_data_tv= findViewById(R.id.audit_data_go_tv);
    }

    @Override
    public void setListener() {
        audit_fail_tv.setOnClickListener(this);
        audit_data_tv.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.aduit_fail_go:
                Intent intent=new Intent(AuditActivity.this, DefaultWebViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url", HttpPath.MODIFY_SHOP);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.audit_data_go_tv:
                Intent intentg=new Intent(AuditActivity.this, DefaultWebViewActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("url", HttpPath.MODIFY_SHOP);
                intentg.putExtras(bundle1);
                startActivity(intentg);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setChange();
    }

    public void setChange(){
        switch (type){
            case 4://审核失败标示
                audit_fail_rl.setVisibility(View.VISIBLE);
                audit_aduiting_rl.setVisibility(View.GONE);
                audit_data_rl.setVisibility(View.GONE);
                break;
            case 6://没有资料标示
                audit_data_rl.setVisibility(View.VISIBLE);
                audit_fail_rl.setVisibility(View.GONE);
                audit_aduiting_rl.setVisibility(View.GONE);
                break;
            case 7://审核中标示
                audit_aduiting_rl.setVisibility(View.VISIBLE);
                audit_fail_rl.setVisibility(View.GONE);
                audit_data_rl.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
