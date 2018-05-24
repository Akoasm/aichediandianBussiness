package com.jnyilin.basiclib.utils.appupdateutils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author HRR
 * @datetime 2017/10/30
 * @describe App更新操作类
 * @modifyRecord
 *
 */

public class AppUpdateUtils {
    private Context mC;
    private ProgressDialog m_progressDlg;
    /** 下载到本地要给这个APP命的名字*/
    private String m_appNameStr;
    /**最新版的版本名*/
    private String m_newVerName;
    private Handler m_mainHandler;
    private ExecutorService executor;
    private String codes;
    public AppUpdateUtils(Context mC) {
        this.mC = mC;
        this.m_progressDlg=new ProgressDialog(mC);
        this.m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        this.m_progressDlg.setIndeterminate(false);
        this.m_appNameStr=mC.getPackageName();
        this.m_mainHandler=new Handler();
        this.executor= Executors.newSingleThreadExecutor();
        this.codes=getVerName(mC);
    }

    /**
     * 检查是否需要更新
     */
    public void ifUpdate(String updateUrl) {
        ApiManager apiManager=new ApiManager( (BaseActivity) mC, new StringCallBack() {
            @Override
            public void onResultNext(String resulte, String method, int code,String msg, PageInfo pageInfo) {
                LogUtils.e("AppUpdate","resulte="+resulte);
                String down = null;
                if (resulte != null) {
                    LogUtils.e("AppUpdate","resulte="+"不为空");
                    try {
                        JSONObject jsonObject = new JSONObject(resulte);
                        m_newVerName = jsonObject.getString("etition");
                        down = jsonObject.getString("downloadurl");
                    } catch (JSONException e) {
                        LogUtils.e("AppUpdate","解析异常="+e.toString());
                        e.printStackTrace();
                    }
                }
                LogUtils.e("AppUpdate","m_newVerName="+m_newVerName+"  down="+down);
                if (m_newVerName != null && down != null) {
//                    LogUtils.e("AppUpdate", "vername=" + m_newVerName + " getvername=" + getVerName(this.mC.getApplicationContext()) + "  down=" + down);
                    try {
                        if (!m_newVerName.equals(codes) ){//判断服务器apk版本号是否和本地相同
                            doNewVersionUpdate(down);
                        }
                    }catch (Exception e){
                        LogUtils.e("AppUpdate","异常="+e.toString());

                    }

                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.appUpdate(updateUrl,new HashMap<String, String>());
    }

    /**
     * 告诉HANDER已经下载完成了，可以安装了
     */
    private void down() {
        m_mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("lkymsg", "321321");
                m_progressDlg.cancel();
                try{
                    update();
                }catch (Exception e){
                    LogUtils.e("AppUpdate","安装异常="+e.toString());
                }
            }
        });
    }

    /**
     * 安装程序
     */
    private void update() {
        File file = new File(Environment.getExternalStorageDirectory()
                , m_appNameStr);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 以新压入栈
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mC, "com.sina.rwxchina.aichediandianbussiness.fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.addCategory("android.intent.category.DEFAULT");
            Uri abc = Uri.fromFile(file);
            intent.setDataAndType(abc, "application/vnd.android.package-archive");
        }

        if (mC.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            mC.startActivity(intent);
        }
    }
    /**
     * 提示更新新版本
     *
     * @param
     */
    private void doNewVersionUpdate(final String loadurl) {
        String verName = getVerName(mC);
        String str = "当前版本：" + verName + " ,发现新版本：" + m_newVerName + " ,是否更新？";
        Dialog dialog = new AlertDialog.Builder(mC)
                .setTitle("软件更新")
                .setMessage(str)
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                m_progressDlg.setTitle("正在下载");
                                m_progressDlg.setMessage("请稍候...");
                                downFile(loadurl); // 开始下载
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                //关闭对话框
                                dialog.dismiss();
                                // 点击"取消"按钮之后退出程序
//                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }


    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("msg", e.getMessage());
        }
        return verName;
    }

    /**
     * 下载安装包
     *
     * @param url
     */
    private void downFile(final String url) {
        m_progressDlg.show();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    long length = connection.getContentLength();
                    Log.i("hrr", "length=" + length);
                    m_progressDlg.setMax(100);// 设置进度条的最大值

                    InputStream is = (InputStream) connection.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory()
                                , m_appNameStr);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[4154];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                int size= (int) (((double)count/(double)length)*100);
                                m_progressDlg.setProgress(size);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    Log.i("lkymsg", "123123");

                    down(); // 告诉HANDER已经下载完成了，可以安装了
                } catch (IOException e) {
                    Log.i("hrr", "IOException=" + e.toString());
                    e.printStackTrace();
                } catch (Exception e) {
                    Log.i("hrr", "Exception=" + e.toString());
                    e.printStackTrace();
                }
            }
        });
    }

}
