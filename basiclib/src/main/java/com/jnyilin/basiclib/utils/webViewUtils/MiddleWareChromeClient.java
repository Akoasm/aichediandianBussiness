package com.jnyilin.basiclib.utils.webViewUtils;

import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebView;

import com.just.agentweb.MiddleWareWebChromeBase;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe
 * @modifyRecord
 */

public class MiddleWareChromeClient extends MiddleWareWebChromeBase {
    public MiddleWareChromeClient() {
    }
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.i("Info","onJsAlert:"+url);
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        Log.i("Info","onProgressChanged:");
    }
}

