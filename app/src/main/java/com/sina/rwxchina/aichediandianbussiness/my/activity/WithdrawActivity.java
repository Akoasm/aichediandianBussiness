package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.appUtils.EditTextUtils;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.contract.WithDrawContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;
import com.sina.rwxchina.aichediandianbussiness.my.presenter.WithDrawPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/2/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithdrawActivity extends BaseActivity implements WithDrawContract.View {
    private ImageView mBack;
    private TextView mTitleLayoutTitleTv, getVerificationCode;
    private TextView mRightIconTv;
    private TextView mBankCardOwnerEt;
    private EditText mWithdrawMoneyEt;
    private EditText mPhoneNumberEt;
    private EditText mVerificationCodeEt;
    private TextView mWithdrawBalanceTv;
    private Button mWithdrawSureBtn;
    private TextView mWithdrawDetailTv;
    private WithDrawPresenter withDrawPresenter;
    private Map<String, String> params;
    private WithDrawBean withDrawBean;
    private boolean isEmptyBank = true;
    private WithDrawBean.BankBean bankBean;
    private String balance;
    private TimeCount timeCount;

    @Override
    public void initParms(Bundle param) {
        withDrawPresenter = new WithDrawPresenter(this);
        timeCount = new TimeCount(30000, 1000);
        params = new HashMap<>();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mBack = findViewById(R.id.back);
        mTitleLayoutTitleTv = findViewById(R.id.titleLayout_title_tv);
        mRightIconTv = findViewById(R.id.rightIcon_tv);
        mBankCardOwnerEt = findViewById(R.id.bankCardOwner_tv);
        mWithdrawMoneyEt = findViewById(R.id.withdrawMoney_et);
        mPhoneNumberEt = findViewById(R.id.phoneNumber_et);
        mVerificationCodeEt = findViewById(R.id.verificationCode_et);
        mWithdrawBalanceTv = findViewById(R.id.withdraw_balance_tv);
        mWithdrawSureBtn = findViewById(R.id.withdraw_sure_btn);
        mWithdrawDetailTv = findViewById(R.id.withdrawDetail_tv);
        getVerificationCode = findViewById(R.id.getVerificationCode_tv);
    }

    @Override
    public void setListener() {
        mWithdrawMoneyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditTextUtils.decimalLimit(s, 2, mWithdrawMoneyEt);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBack.setOnClickListener(this);
        getVerificationCode.setOnClickListener(this);
        mWithdrawSureBtn.setOnClickListener(this);
        mBankCardOwnerEt.setOnClickListener(this);
        mWithdrawDetailTv.setOnClickListener(this);
        mRightIconTv.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.withdraw_sure_btn:
                checkParams();
                break;
            case R.id.getVerificationCode_tv:
                if (bankBean.getBank_name()!=null)
                    getVerificationCode(bankBean);
                else
                    ToastUtils.showShort("请先添加银行卡");
                break;
            case R.id.rightIcon_tv:
                startActivity(AddBankCardActivity.class);
                break;
            case R.id.bankCardOwner_tv:
                if (isEmptyBank)
                    startActivityForResult(new Intent(this, AddBankCardActivity.class), 101);
                else
                    startActivityForResult(new Intent(this, BankCardActivity.class), 102);
                break;
            case R.id.withdrawDetail_tv:
                startActivity(WithDrawDetailActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 102:
                bankBean = (WithDrawBean.BankBean) data.getExtras().getSerializable("bankCard");
                mBankCardOwnerEt.setText(bankBean.getBank_name()+"("+bankBean.getLast_num()+")");
                isEmptyBank = false;
                break;
            case 101:
                withDrawPresenter.getData();
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        withDrawPresenter.attachView(this);
        withDrawPresenter.getData();
    }

    private void setParams(WithDrawBean.BankBean bankCardBean) {
        params.clear();
        params.put("bank_name", bankCardBean.getBank_name());
        params.put("card_number", bankCardBean.getCard_number());
        params.put("user_name", bankCardBean.getUser_name());
        params.put("id", bankCardBean.getId());
        params.put("code", mVerificationCodeEt.getText().toString().trim());
        params.put("phone", CommonParametersUtils.getPhone(this));
    }

    private void getVerificationCode(WithDrawBean.BankBean bankCardBean) {
        params.clear();
        params.put("last_num", bankCardBean.getLast_num());
        params.put("bank_name", bankCardBean.getBank_name());

        String withdrawmoney = mWithdrawMoneyEt.getText().toString().trim();
        if (!TextUtils.isEmpty(withdrawmoney)&&!TextUtils.isEmpty(mPhoneNumberEt.getText().toString().trim())) {
            params.put("money", withdrawmoney);
            params.put("phone", mPhoneNumberEt.getText().toString().trim());
            timeCount.start();
            withDrawPresenter.getVerificationCode(params);
        }else if (TextUtils.isEmpty(withdrawmoney)){
            ToastUtils.showShort("请输入提现金额");
        }else{
            ToastUtils.showShort("请输入联系方式");
        }


    }

    @Override
    public void showView(WithDrawBean withDrawBean) {
        this.withDrawBean = withDrawBean;
        mWithdrawBalanceTv.setText(withDrawBean.getBalance());
        balance = withDrawBean.getBalance();
        bankBean = withDrawBean.getBank();
        if (bankBean.getBank_name()!=null) {
            mBankCardOwnerEt.setText(bankBean.getBank_name()+"("+bankBean.getLast_num()+")");
            isEmptyBank = false;
        } else {
            isEmptyBank = true;
        }
    }

    @Override
    public void showVerificationCode(String msg) {
               timeCount.onFinish();
    }


    public void checkParams() {
        String withdrawmoney = mWithdrawMoneyEt.getText().toString().trim();
        String verificationCode = mVerificationCodeEt.getText().toString().trim();
        String phoneNumber = mPhoneNumberEt.getText().toString().trim();
        String balance = this.balance;
        if (!TextUtils.isEmpty(withdrawmoney) && !balance.equals("0.00") && !TextUtils.isEmpty(verificationCode) && !TextUtils.isEmpty(phoneNumber)) {
            Double withdrawCrash = EditTextUtils.round(parseDouble(withdrawmoney), 2);
            Double balancemoney = EditTextUtils.round(parseDouble(balance), 2);
            if (withdrawCrash <= balancemoney && balancemoney > 0 && !TextUtils.isEmpty(withDrawBean.getBank().getBank_name())) {
                setParams(bankBean);
                params.put("money", withdrawmoney);
                params.put("user_phone", phoneNumber);
                timeCount.cancel();
                commitData();
            } else if (TextUtils.isEmpty(withDrawBean.getBank().getBank_name())) {
                ToastUtils.showShort("请先添加银行卡");
            } else {
                ToastUtils.showShort("提现金额不能大于余额");
            }
        } else if (TextUtils.isEmpty(withdrawmoney)) {
            ToastUtils.showShort("请输入提现金额");
        } else if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showShort("请输入联系方式");
        } else if (TextUtils.isEmpty(verificationCode)) {
            ToastUtils.showShort("验证码不能为空");
        } else {
            ToastUtils.showShort("无可提现金额");
        }
    }

    private void commitData() {
        withDrawPresenter.commitData(params);
    }

    private void setTitle() {
        mTitleLayoutTitleTv.setText("提现");
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_add_white);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mRightIconTv.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    protected void onDestroy() {
        if (withDrawPresenter != null)
            withDrawPresenter.detachView();
        super.onDestroy();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        // 计时过程
        @Override
        public void onTick(long millisUntilFinished) {
            getVerificationCode.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.text_hint));
            getVerificationCode.setEnabled(false);
            getVerificationCode.setText("重新获取" + millisUntilFinished / 1000 + "s");

        }


        //计时完毕
        @Override
        public void onFinish() {
            getVerificationCode.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorOrange));
            getVerificationCode.setText("获取验证码");
            getVerificationCode.setEnabled(true);

        }
    }
}
