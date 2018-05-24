package com.sina.rwxchina.aichediandianbussiness.my.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.utils.imageUtils.ImageUtils;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.ReportOrderInfo;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe
 * @modifyRecord
 */

public class OrderAdapter  extends BaseAdapter {
    private Context context;
    private List<ReportOrderInfo> orders;

    public OrderAdapter(Context context, List<ReportOrderInfo> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.listview_item_order, null);
            holder.tvStatus = view.findViewById(R.id.lv_tv_status);
            holder.tvName = view.findViewById(R.id.lv_tv_name);
            holder.lv_tv_phone =view.findViewById(R.id.lv_tv_phone);
            holder.tvMobile =  view.findViewById(R.id.lv_tv_number);
            holder.tvGoodsName = view.findViewById(R.id.lv_tv_projectname);
            holder.tvSvn = view.findViewById(R.id.lv_sn);
            holder.ivHeadPhoto =view.findViewById(R.id.lv_headphoto);
            holder.confirmTime=  view.findViewById(R.id.lv_tv_ytime);

            holder.zhifu_type= view.findViewById(R.id.zhifu_type);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        ReportOrderInfo order = orders.get(i);

        //下单时间
        if (order.getCreatedate()!=null){

            holder.confirmTime.setText(order.getCreatedate());

        }else {
            holder.confirmTime.setVisibility(View.GONE);

        }


        //名称
        if (TextUtils.isEmpty(order.getUser_name())){
            holder.tvName.setText( order.getUser_name());
        }else {
            holder.tvName.setText("暂无昵称" );
        }



        //支付价格
        holder.tvMobile.setText(order.getPay_money()+"元");


        //产品名称
        holder.tvGoodsName.setText(order.getGoods_name());


//        //状态
//        if (order.getStatus() == 1) {
//            holder.tvStatus.setText("待付款");
//
//        } else if (order.getStatus() == 5) {
//            holder.tvStatus.setText("确认收货");
//
//        } else if (order.getStatus() == 6) {
//            holder.tvStatus.setText("支付成功，待预约");
//
//        } else if (order.getStatus() == 7) {
//            holder.tvStatus.setText("预约成功");
//
//        } else if (order.getStatus() == 12) {
//            holder.tvStatus.setText("交易成功");
//
//        }


        //订单编号
        holder.tvSvn.setText("订单编号："+ order.getOrderson());
        //预留电话
        if (TextUtils.isEmpty(order.getUser_tel())) {
            holder.lv_tv_phone.setVisibility(View.GONE);

        }else {
            holder.lv_tv_phone.setText( order.getUser_tel());

        }
        //图片
        ImageUtils.showCircleImage(context, HttpPath.IMAGEURL+order.getUser_head(),holder.ivHeadPhoto);
        //支付方式
        if(order.getPaytype().equals("0"))
        {
            holder.zhifu_type.setVisibility(View.GONE);
        }else if(order.getPaytype().equals("1"))
        {
            holder.zhifu_type.setVisibility(View.VISIBLE);
            holder.zhifu_type.setImageResource(R.drawable.xianj);
        }else if(order.getPaytype().equals("2"))
        {   holder.zhifu_type.setVisibility(View.VISIBLE);
            holder.zhifu_type.setImageResource(R.drawable.weixin_order);
        }else
        {   holder.zhifu_type.setVisibility(View.VISIBLE);
            holder.zhifu_type.setImageResource(R.drawable.zhifubao);
        }

        return view;
    }

    class ViewHolder {
        private TextView tvStatus;
        private TextView tvName;
        private TextView tvMobile;
        private TextView tvGoodsName;
        private TextView tvSvn;
        private ImageView ivHeadPhoto;
        private TextView confirmTime;
        private TextView lv_tv_phone;
        private ImageView zhifu_type;
    }
}
