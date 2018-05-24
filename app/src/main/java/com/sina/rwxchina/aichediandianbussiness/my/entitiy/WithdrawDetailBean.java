package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/12/1
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithdrawDetailBean {

    /**
     * total : {"total_money":"1049"}
     * list : [{"createdate":"2017-11-27 13:59:27","money":"50","id":"12","remarks":"余额提现--提现到成都银行(6789)","status":"1"},{"createdate":"2017-11-27 11:51:19","money":"999","id":"11","remarks":"提现","status":"1"}]
     */

    private TotalBean total;
    private List<ListBean> list;

    @Override
    public String toString() {
        return  ""+total + list;
    }

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class TotalBean {
        /**
         * total_money : 1049
         */

        private String total_money;

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }
    }

    public static class ListBean {
        /**
         * createdate : 2017-11-27 13:59:27
         * money : 50
         * id : 12
         * remarks : 余额提现--提现到成都银行(6789)
         * status : 1
         */

        private String createdate;
        private String money;
        private String id;
        private String describe;
        private String status;

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }


        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
