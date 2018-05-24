package com.sina.rwxchina.aichediandianbussiness.my.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jnyilin.basiclib.base.BaseActivity;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BalanceDetailBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BalanceDetailAdapter extends BaseAdapter{
    private BaseActivity baseActivity;
    private List<BalanceDetailBean> list;

    public BalanceDetailAdapter(BaseActivity baseActivity, List<BalanceDetailBean> list) {
        this.baseActivity = baseActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView == null){
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_balancedetail,null);
            viewHolder = new ViewHolder();
            viewHolder.balance = convertView.findViewById(R.id.item_balance_tv);
            viewHolder.time = convertView.findViewById(R.id.item_createTime_tv);
            viewHolder.offMoney = convertView.findViewById(R.id.item_offMoney_tv);
            viewHolder.type = convertView.findViewById(R.id.item_balanceType_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.type.setText(list.get(position).getTypestr());
        viewHolder.time.setText(list.get(position).getDate());
        viewHolder.offMoney.setText(list.get(position).getOff_money());
        viewHolder.balance.setText("余额："+list.get(position).getMoney_new());
        return convertView;
    }

    public List<BalanceDetailBean> getList() {
        return list;
    }

    public void setList(List<BalanceDetailBean> list) {
        this.list = list;
    }

    class ViewHolder{
        private TextView type,time,balance,offMoney;
    }
}
