package com.sina.rwxchina.aichediandianbussiness.my.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jnyilin.basiclib.base.BaseActivity;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BankCardBean;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;

import java.util.List;
import java.util.Random;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BankCardAdapter extends StackAdapter {
    private BaseActivity baseActivity;
    private int[] background = {R.mipmap.bankcard_bg1, R.mipmap.bankcard_bg2, R.mipmap.bankcard_bg3};

    public BankCardAdapter(BaseActivity context) {
        super(context);
        this.baseActivity = context;
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_bankcard, parent, false);
        return new BankCardAdapter.ViewHolder(view);
    }

    @Override
    public void bindView(Object data, final int position, CardStackView.ViewHolder holder) {
        final WithDrawBean.BankBean bankCardBean = (WithDrawBean.BankBean) data;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bankName.setText(bankCardBean.getBank_name());
        viewHolder.lastNumber.setText(bankCardBean.getLast_num());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bankCard", bankCardBean);
                baseActivity.setResult(102,new Intent().putExtras(bundle));
                baseActivity.finish();
            }
        });
        viewHolder.backgroud.setBackgroundResource(background[new Random().nextInt(3)]);
    }


    class ViewHolder extends CardStackView.ViewHolder {
        private CardView cardView;
        private RelativeLayout backgroud;
        private TextView bankName, lastNumber;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.item_bankcard_cv);
            bankName = view.findViewById(R.id.item_bankName_tv);
            lastNumber = view.findViewById(R.id.item_lastNumber_tv);
            backgroud = view.findViewById(R.id.item_background_rl);
        }

        @Override
        public void onItemExpand(boolean b) {

        }

        private void onBind(final List<BankCardBean> list, final int position) {

        }

    }
}
