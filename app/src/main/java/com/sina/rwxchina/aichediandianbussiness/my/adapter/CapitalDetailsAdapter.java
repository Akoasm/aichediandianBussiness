package com.sina.rwxchina.aichediandianbussiness.my.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.CapitalInfo;

import java.util.List;

/**
 * @author:wj
 * @datetime：2018/2/2
 * @describe：资金明细列表适配器
 * @modifyRecord:
 */


public class CapitalDetailsAdapter extends BaseQuickAdapter<CapitalInfo.ListBean,BaseViewHolder> {
    private Context mC;
    public CapitalDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<CapitalInfo.ListBean> data, Context context) {
        super(layoutResId, data);
        this.mC = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CapitalInfo.ListBean item) {
            helper.setText(R.id.tx_time,item.getCreatedate())
                    .setText(R.id.tx_money,item.getOff_money())
                    .setText(R.id.tx_tel,item.getUser_tel())
                    .setText(R.id.tx_type,item.getTypestr())
                    .setText(R.id.tx_context,item.getOrderson());
    }
}
