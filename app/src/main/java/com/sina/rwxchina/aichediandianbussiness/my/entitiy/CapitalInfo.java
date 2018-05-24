package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

import java.util.List;

/**
 * @author:wj
 * @datetime：2018/2/2
 * @describe：
 * @modifyRecord:
 */


public class CapitalInfo {

    /**
     * balance : 600.00
     * list : [{"logid":"2","createdate":"2017-11-21 16:24:55","off_money":"30.00","user_tel":"","typestr":"系统赠送","orderson":"0201712111021"},{"logid":"1","createdate":"2017-11-21 16:24:55","off_money":"50.00","user_tel":"","typestr":"充值","orderson":"0201712111021"},{"logid":"3","createdate":"2017-11-21 16:24:55","off_money":"-50.00","user_tel":"","typestr":"提现","orderson":"0201712111021"}]
     */

    private String balance;
    private List<ListBean> list;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * logid : 2
         * createdate : 2017-11-21 16:24:55
         * off_money : 30.00
         * user_tel :
         * typestr : 系统赠送
         * orderson : 0201712111021
         */

        private String logid;
        private String createdate;
        private String off_money;
        private String user_tel;
        private String typestr;
        private String orderson;

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getOff_money() {
            return off_money;
        }

        public void setOff_money(String off_money) {
            this.off_money = off_money;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getTypestr() {
            return typestr;
        }

        public void setTypestr(String typestr) {
            this.typestr = typestr;
        }

        public String getOrderson() {
            return orderson;
        }

        public void setOrderson(String orderson) {
            this.orderson = orderson;
        }
    }
}
