package com.sina.rwxchina.aichediandianbussiness.order.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.utils.imageUtils.ImageUtils;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.order.entity.OrderInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/2/1
 * @describe 订单列表适配器
 * @modifyRecord
 */

public class OrderListAdapter extends BaseQuickAdapter<OrderInfo,BaseViewHolder>{
    private Context mC;
    public OrderListAdapter(Context context,@LayoutRes int layoutResId, @Nullable List<OrderInfo> data) {
        super(layoutResId, data);
        this.mC=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item) {
        //用户昵称
        String name=item.getUser_name();
        if (TextUtils.isEmpty(name)){
            name="暂无昵称";
        }
        //订单状态
        String status=item.getIndent_state();
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
        helper.setText(R.id.lv_tv_ytime,item.getCreatedate())
            .setText(R.id.lv_tv_name,name)
            .setText(R.id.lv_tv_number,item.getPay_money())
            .setText(R.id.lv_tv_projectname,item.getGoods_name())
            .setText(R.id.lv_tv_status,status)
            .setText(R.id.lv_tv_phone,item.getUser_tel())
            .setText(R.id.lv_tv_money,item.getTotal_money());
        TextView son=helper.getView(R.id.lv_sn);
//        son.setMaxLines(1);
//        son.setEllipsize(TextUtils.TruncateAt.END);
        String orderson="订单编号:"+item.getOrderson();
        son.setText(orderson);
        //头像
        ImageView logo=helper.getView(R.id.lv_headphoto);
        ImageUtils.showCircleImage(mC, HttpPath.IMAGEURL+item.getDefault_image(),logo);
        //支付方式
        ImageView type=helper.getView(R.id.zhifu_type);
        String payType=item.getPaytype();
        switch (payType){
            case "0":
                type.setVisibility(View.GONE);
                break;
            case "1":
                type.setVisibility(View.VISIBLE);
                type.setImageResource(R.drawable.xianj);
                break;
            case "13":
                type.setVisibility(View.VISIBLE);
                type.setImageResource(R.drawable.weixin_order);
                break;
            case "12":
                type.setVisibility(View.VISIBLE);
                type.setImageResource(R.drawable.zhifubao);
                break;
            default:
                type.setVisibility(View.GONE);
                break;
        }
    }
}
