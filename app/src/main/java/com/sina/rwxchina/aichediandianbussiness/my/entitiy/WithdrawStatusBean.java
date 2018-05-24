package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

/**
 * @author:zy
 * @detetime:2017/12/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithdrawStatusBean {

    /**
     * createdate : 2017-11-27 11:51:19
     * bank_name : 成都银行
     * card_number  : 6666
     * money : 999
     * status : 1
     */

    private String createdate;
    private String bank_name;
    private String card_number;
    private String money;
    private String status;

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
