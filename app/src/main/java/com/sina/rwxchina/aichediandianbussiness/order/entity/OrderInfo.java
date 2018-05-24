package com.sina.rwxchina.aichediandianbussiness.order.entity;

/**
 * @author HRR
 * @datetime 2018/2/1
 * @describe 订单实体类
 * @modifyRecord
 */

public class OrderInfo {

    /**
     * uid : 2398
     * user_name : 15281063766
     * indent_name : 普通洗车（5座以下轿车）
     * orderson : s12018020116195854957634527050
     * indent_state : 4101
     * default_image : /Uploads/goods/963844468581be10057b93.jpeg
     * goods_name : 普通洗车（5座以下轿车）
     * pay_money : 0.00
     * total_money : 12.00
     * user_tel :
     * createdate : 2018-02-01 16:19:58
     * paytype : 12
     */

    private String uid;
    private String user_name;
    private String indent_name;
    private String orderson;
    private String indent_state;
    private String default_image;
    private String goods_name;
    private String pay_money;
    private String total_money;
    private String user_tel;
    private String createdate;
    private String paytype;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIndent_name() {
        return indent_name;
    }

    public void setIndent_name(String indent_name) {
        this.indent_name = indent_name;
    }

    public String getOrderson() {
        return orderson;
    }

    public void setOrderson(String orderson) {
        this.orderson = orderson;
    }

    public String getIndent_state() {
        return indent_state;
    }

    public void setIndent_state(String indent_state) {
        this.indent_state = indent_state;
    }

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
}
