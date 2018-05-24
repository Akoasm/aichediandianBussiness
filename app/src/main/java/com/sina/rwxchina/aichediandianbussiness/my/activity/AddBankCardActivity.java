package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.contract.AddBankCardContract;
import com.sina.rwxchina.aichediandianbussiness.my.presenter.AddBankCardPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：添加银行卡
 * @modifyRecord:修改记录
 */

public class AddBankCardActivity extends BaseActivity implements AddBankCardContract.View{
    private EditText bankCardOwner_et, cardNumber_et, bank_et;
    private TextView title,rightIcon;
    private ImageView back;
    private View statusBar;
    private Button sure;
    private AddBankCardPresenter addBankCardPresenter;

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_addbankcard;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        bankCardOwner_et = view.findViewById(R.id.bankCardOwner_et);
        cardNumber_et = view.findViewById(R.id.cardNumber_et);
        bank_et = view.findViewById(R.id.bank_et);
        statusBar = findViewById(R.id.home_title_fakeview);
        sure = view.findViewById(R.id.addBankCard_sure_btn);
        View titleView = view.findViewById(R.id.addBankCard_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
    }

    @Override
    public void setListener() {
        sure.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.addBankCard_sure_btn) {
            commit();
        } else if (i == R.id.back) {
            finish();
        }
    }

    public void commit() {
        final String cardowner = bankCardOwner_et.getText().toString().trim();
        final String cardNumber = cardNumber_et.getText().toString().trim();
        final String bank = bank_et.getText().toString().trim();
        if (!TextUtils.isEmpty(cardowner) && !TextUtils.isEmpty(cardNumber) && !TextUtils.isEmpty(bank)) {
            addBankCardPresenter = new AddBankCardPresenter(this, new AddBankCardPresenter.Params() {
                @Override
                public Map<String, String> addParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", cardowner);
                    map.put("card_number", cardNumber);
                    map.put("bank_name", bank);
                    return map;
                }
            });
            addBankCardPresenter.attachView(this);
            addBankCardPresenter.commitData();
        } else {
            ToastUtils.showShort("请输入完整信息");
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
    }

    public void setTitle() {
        title.setText("添加银行卡");
        rightIcon.setVisibility(View.GONE);

    }

    @Override
    public void showView() {
        ToastUtils.showShort("添加成功");
        setResult(101);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (addBankCardPresenter!=null)
        addBankCardPresenter.detachView();
        super.onDestroy();
    }

}
