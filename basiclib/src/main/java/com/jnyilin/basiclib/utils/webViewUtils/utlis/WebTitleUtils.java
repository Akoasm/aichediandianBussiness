package com.jnyilin.basiclib.utils.webViewUtils.utlis;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnyilin.basiclib.R;
import com.just.agentweb.AgentWeb;

/**
 * @author HRR
 * @datetime 2017/12/8
 * @describe 默认webview的title工具类
 * @modifyRecord
 */

public class WebTitleUtils {
    public static void setTitle(View view, final Activity activity, final AgentWeb agentWeb, String titleContent){
        ImageView back=view.findViewById(R.id.iv_back);
        final ImageView finish=view.findViewById(R.id.iv_finish);
        TextView title=view.findViewById(R.id.toolbar_title);
        title.setText(titleContent);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!agentWeb.back()){
                    activity.finish();
                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
