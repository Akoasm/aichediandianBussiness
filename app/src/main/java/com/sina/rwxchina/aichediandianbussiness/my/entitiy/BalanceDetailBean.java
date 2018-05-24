package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BalanceDetailBean {


    /**
     * typestr : 订单支付
     * off_money : 12.00
     * date : 2018-01_26 10:49:10
     * money_new : 374.92
     */

    private String typestr;
    private String off_money;
    private String date;
    private String money_new;

    public String getTypestr() {
        return typestr;
    }

    public void setTypestr(String typestr) {
        this.typestr = typestr;
    }

    public String getOff_money() {
        return off_money;
    }

    public void setOff_money(String off_money) {
        this.off_money = off_money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney_new() {
        return money_new;
    }

    public void setMoney_new(String money_new) {
        this.money_new = money_new;
    }
}
