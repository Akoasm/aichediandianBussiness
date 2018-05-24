package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnyilin.basiclib.base.BaseActivity;
import com.loopeer.cardstack.CardStackView;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.adapter.BankCardAdapter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.BankCardContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BankCardBean;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;
import com.sina.rwxchina.aichediandianbussiness.my.presenter.BankCardPresenter;


import java.util.List;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BankCardActivity extends BaseActivity implements BankCardContract.View {
    private CardStackView cardStackView;
    private ImageView back;
    private TextView title, rightIcon;
    private BankCardPresenter bankCardPresenter;
    private BankCardAdapter bankCardAdapter;

    @Override
    public void initParms(Bundle params) {
        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bankcard;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        cardStackView = view.findViewById(R.id.bankCard_cs);
        View titleView = view.findViewById(R.id.bankCard_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
    }

    @Override
    public void setListener() {
        back.setOnClickListener(this);
        rightIcon.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {
            finish();
        } else if (i == R.id.rightIcon_tv) {
            startActivity(AddBankCardActivity.class);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        bankCardPresenter = new BankCardPresenter(this);
        bankCardPresenter.attachView(this);
    }

    @Override
    protected void onResume() {
        bankCardPresenter.getData();
        super.onResume();
    }

    @Override
    public void showView(final List<WithDrawBean.BankBean> bankCardBeen) {
        bankCardAdapter = new BankCardAdapter(this);
        cardStackView.setAdapter(bankCardAdapter);
        bankCardAdapter.updateData(bankCardBeen);
    }

    private void setTitle() {
        title.setText("银行卡");
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_add_white);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        rightIcon.setCompoundDrawables(null, null, drawable, null);
    }

}
