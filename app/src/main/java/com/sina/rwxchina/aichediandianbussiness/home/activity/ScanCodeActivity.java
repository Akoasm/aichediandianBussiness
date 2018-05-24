package com.sina.rwxchina.aichediandianbussiness.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * @author:wj
 * @datetime：2018/2/1
 * @describe：
 * @modifyRecord:
 */


public class ScanCodeActivity extends BaseActivity  implements QRCodeView.Delegate {
    private QRCodeView qrCodeView;
    private TextView title;
    private ImageView back;

    @Override
    public void initParms(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_scan_code;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        qrCodeView = findViewById(R.id.scan_code_zxing);
        title = findViewById(R.id.textview_title);
        back = findViewById(R.id.back_title);
        title.setText("扫描二维码");
        qrCodeView.setDelegate(this);

    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if(v.getId() == R.id.back_title){
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        qrCodeView.startCamera();
        qrCodeView.showScanRect();
        qrCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        qrCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        qrCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.e("coderesult:"+result);
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        qrCodeView.startSpot();
        Map<String, String> params = new HashMap<String, String>();
        params.put("codestr", result);
        ApiManager apiManager = new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                ToastUtils.showLong(msg);
                    finish();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.ORDER_VERIFICATION, params);

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
}
