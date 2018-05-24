package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 消费统计订单实体类
 * @modifyRecord
 */

public class ReportOrderInfo {

    /**
     * user_head : /./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg
     * orderson : s12018020217201123603227982150
     * user_name : 测试静
     * goods_name : 自助买单
     * pay_money : 0.00
     * user_tel :
     * createdate : 2018-02-02 17:20:11
     * paytype : 13
     * indent_state : 90
     */

    private String user_head;
    private String orderson;
    private String user_name;
    private String goods_name;
    private String pay_money;
    private String user_tel;
    private String createdate;
    private String paytype;
    private String indent_state;

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getOrderson() {
        return orderson;
    }

    public void setOrderson(String orderson) {
        this.orderson = orderson;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getIndent_state() {
        return indent_state;
    }

    public void setIndent_state(String indent_state) {
        this.indent_state = indent_state;
    }
}
