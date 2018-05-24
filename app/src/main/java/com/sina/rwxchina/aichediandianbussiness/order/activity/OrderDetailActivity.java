package com.sina.rwxchina.aichediandianbussiness.order.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.imageUtils.ImageUtils;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.order.entity.OrderDetailInfo;
import com.sina.rwxchina.aichediandianbussiness.order.entity.OrderInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe
 * @modifyRecord
 */

public class OrderDetailActivity extends BaseActivity{
    private TextView tvUserName;//用户名
    private TextView tvProjectName;//预约项目名
    private TextView tvTel;//电话
    private TextView tvMoneyAmoumt;//消费金额
    private TextView tvStatus;
    private TextView tvSn;//订单号
    private TextView tvXtime;//预约时间
    private TextView tvTotalPrice;//总价格
    private TextView tvVoullour;//代金券
    private TextView tvIntergral;//积分
    private TextView tvBalance;//余额
    private TextView tvPayAmount;//实付金额
    private TextView tvAddTime;//下单时间
    private ImageView ivBack;//
    private ImageView cvHead;
    private OrderDetailInfo order;
    private String order_son;
    @Override
    public void initParms(Bundle params) {
        order_son=params.getString("orderson");
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        tvUserName =findViewById(R.id.activity_order_detail_tv_name);
        tvProjectName = findViewById(R.id.activity_order_detail_tv_projectname);
        tvTel =  findViewById(R.id.activity_order_detail_tv_number);
        tvMoneyAmoumt =findViewById(R.id.activity_order_detail_tv_money);
        tvStatus =findViewById(R.id.activity_order_detail_status);
        tvSn = findViewById(R.id.activity_order_detail_sn);
        cvHead =findViewById(R.id.activity_order_detail_headphoto);
        tvXtime = findViewById(R.id.activity_order_detail_tv_time);
        tvTotalPrice = findViewById(R.id.activity_order_detail_totalprice_num);
        tvVoullour =  findViewById(R.id.activity_order_detail_vollour_num);
        tvIntergral = findViewById(R.id.activity_order_detail_integral_num);
        tvBalance = findViewById(R.id.activity_order_detail_balance_num);
        tvPayAmount = findViewById(R.id.activity_order_detail_actual_payment_amount);
        tvAddTime =findViewById(R.id.activity_order_detail_order_time);
        ivBack = findViewById(R.id.activity_order_detail_back);
    }

    @Override
    public void setListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.activity_order_detail_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getOrderDetail();
    }

    /**
     * 获取订单详情
     */
    private void getOrderDetail(){
        Map<String,String> params=new HashMap<>();
        params.put("orderson",order_son);
        ApiManager manager=new ApiManager(this, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e(result);
                order=new Gson().fromJson(result,OrderDetailInfo.class);
                showInfo();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GETORDERCONTENT,params);
    }

    private void showInfo() {
        if (order != null) {
            //用户昵称
            if (!TextUtils.isEmpty(order.getUser_name())){
                tvUserName.setText("用户昵称：" + order.getUser_name());
            }else {
                tvUserName.setText("用户昵称：暂无昵称" );
            }
            //项目名称
            tvProjectName.setText("项目预约：" + order.getGoods_name());
            //电话
            Log.i("hrr","tel="+order.getPhone());
            if (TextUtils.isEmpty(order.getPhone())) {
//                if (order.getMobile().equals("null")||order.getMobile().equals("")){
//                    tvTel.setText("暂未预留电话");
//                }else {
//                    tvTel.setText("预留电话：" + order.getMobile());
//                }

            } else {
                tvTel.setText("预留电话：" + order.getPhone());
            }
            //订单状态
            //订单状态
            String status=order.getIndent_state();
            switch (status){
                case "10":
                    status="待付款";
                    break;
                case "20":
                    status="订单取消";
                    break;
                case "51":
                    status="用户申请退款";
                    break;
                case "52":
                    status="用户申请换货";
                    break;
                case "53":
                    status="用户申请退货";
                    break;
                case "60":
                    status="平台驳回申请";
                    break;
                case "62":
                    status="商户驳回换货申请";
                    break;
                case "63":
                    status="商户驳回退货申请";
                    break;
                case "90":
                    status="订单完成";
                    break;
                case "91":
                    status="退款完成";
                    break;
                case "4101":
                    status="待验证";
                    break;
                case "4102":
                    status="待发货";
                    break;
                case "4103":
                    status="待收货";
                    break;
                default:
                    break;
            }
            tvStatus.setText(status);
            //订单编号
            tvSn.setText("订单编号：" + order.getOrderson());
            //头像
            ImageUtils.showCircleImage(this,HttpPath.IMAGEURL+order.getDefault_image(),cvHead);
            if (TextUtils.isEmpty(order.getCreatedate())){
                tvXtime.setText("预约时间：尚未预约时间" );
            }else{
                tvXtime.setText("预约时间：" + order.getCreatedate());
            }

            tvTotalPrice.setText("￥" + order.getTotal_money());
            tvVoullour.setText("￥" + order.getCoupon_money());
            tvIntergral.setText("￥" + order.getIntegral_money());
            tvBalance.setText("￥" + order.getMoney());
            tvPayAmount.setText("￥" + order.getTotal_money());
            tvMoneyAmoumt.setText("￥" + order.getPay_money());
            tvAddTime.setText("下单时间：" + order.getCreatedate());


        }

    }
}
