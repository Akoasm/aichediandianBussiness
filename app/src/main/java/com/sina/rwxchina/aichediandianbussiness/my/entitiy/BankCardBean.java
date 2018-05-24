package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

import java.io.Serializable;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BankCardBean extends Object implements Serializable {


    /**
     * id : 71
     * bank_name : 农业银行·金穗通宝卡(银联卡)
     * last_num : 0018
     * user_name : 冉亚
     * uid : 4
     * card_number : 6228480402564890018
     */

    private String id;
    private String bank_name;
    private String last_num;
    private String user_name;
    private String uid;

    public BankCardBean(String id, String bank_name, String last_num, String user_name, String uid, String card_number) {
        this.id = id;
        this.bank_name = bank_name;
        this.last_num = last_num;
        this.user_name = user_name;
        this.uid = uid;
        this.card_number = card_number;
    }

    private String card_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getLast_num() {
        return last_num;
    }

    public void setLast_num(String last_num) {
        this.last_num = last_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }
}
