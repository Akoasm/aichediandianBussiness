package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

import java.io.Serializable;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithDrawBean implements Serializable {

    /**
     * balance : 9763.00
     * bank : {"id":"1","bank_name":"建设银行","card_number":"6666666666665555","last_num":"5555","user_name":"老冉","uid":"4"}
     */

    private String balance;
    private BankBean bank;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public static class BankBean implements Serializable{
        /**
         * id : 1
         * bank_name : 建设银行
         * card_number : 6666666666665555
         * last_num : 5555
         * user_name : 老冉
         * uid : 4
         */

        private String id;
        private String bank_name;
        private String card_number;
        private String last_num;
        private String user_name;
        private String uid;

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

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
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

        @Override
        public String toString() {
            return id + '\''+ bank_name + '\'' + card_number + '\''+ last_num + '\'' + user_name + '\'' + uid + '\'';
        }
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
