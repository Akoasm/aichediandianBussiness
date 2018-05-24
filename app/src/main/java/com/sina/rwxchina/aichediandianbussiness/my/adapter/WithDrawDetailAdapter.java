package com.sina.rwxchina.aichediandianbussiness.my.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.activity.WithdrawStatusActivity;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithdrawDetailBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/1
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithDrawDetailAdapter extends BaseAdapter {
    private List<WithdrawDetailBean.ListBean> listBeen;
    private BaseActivity baseActivity;

    public List<WithdrawDetailBean.ListBean> getListBeen() {
        return listBeen;
    }

    public void setListBeen(List<WithdrawDetailBean.ListBean> listBeen) {
        this.listBeen = listBeen;
    }

    public WithDrawDetailAdapter(List<WithdrawDetailBean.ListBean> listBeen, BaseActivity baseActivity) {

        this.listBeen = listBeen;
        this.baseActivity = baseActivity;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(baseActivity).inflate(R.layout.item_withdrawdetail,null);
            viewHolder.date = convertView.findViewById(R.id.item_withdrawTime_tv);
            viewHolder.offMoney = convertView.findViewById(R.id.item_offMoney_tv);
            viewHolder.status = convertView.findViewById(R.id.item_withdrawStatus_tv);
            viewHolder.bank = convertView.findViewById(R.id.item_withdrawBank_tv);
            viewHolder.content = convertView.findViewById(R.id.item_withdrawDetail_rl);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LogUtils.e("xxx",listBeen.get(position).toString());
        switch (listBeen.get(position).getStatus()){
            case "1":
                setStatus(viewHolder,"待处理");
                break;
            case "2":
                setStatus(viewHolder,"已驳回");
                break;
            case "3":
                setStatus(viewHolder,"同意并提现");
                break;
        }

        viewHolder.date.setText(listBeen.get(position).getCreatedate());
        viewHolder.offMoney.setText(listBeen.get(position).getMoney());
        String bank = listBeen.get(position).getDescribe().substring(listBeen.get(position).getDescribe().indexOf("到"));
        viewHolder.bank.setText("—"+bank);
        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",listBeen.get(position).getId());
                baseActivity.startActivity(WithdrawStatusActivity.class,bundle);
            }
        });
        return convertView;

    }
     void setStatus(ViewHolder viewHolder,String str){
         viewHolder.status.setText(str);
     }
    class ViewHolder{
        private TextView date,status,offMoney,bank;
        private RelativeLayout content;
    }
}
