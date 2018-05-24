package com.sina.rwxchina.aichediandianbussiness.home.entity;

/**
 * @author:wj
 * @datetime：2018/2/2
 * @describe：
 * @modifyRecord:
 */


public class HomeOrderInfo {

    /**
     * month_income : 169.00
     * month_num : 6
     * today_income : 0
     * today_num : 0
     * all_income : 547.00
     * shopinfo : {"uid":"136","shop_logo":"/Uploads/storelogo/9ed3d3170af66bec1b8275b48144_160_160.jpg","shop_name":"任我行集团（锦汇）汽车维修服务中心","shop_starlevel":"5","shop_sale":"24","shop_telephone":"02884200851","shop_address":"成都市三环路龙潭立交内侧辅道往成南立交前行150米"}
     */

    private String month_income;
    private String month_num;
    private String today_income;
    private String today_num;
    private String all_income;
    private ShopinfoBean shopinfo;

    public String getMonth_income() {
        return month_income;
    }

    public void setMonth_income(String month_income) {
        this.month_income = month_income;
    }

    public String getMonth_num() {
        return month_num;
    }

    public void setMonth_num(String month_num) {
        this.month_num = month_num;
    }

    public String getToday_income() {
        return today_income;
    }

    public void setToday_income(String today_income) {
        this.today_income = today_income;
    }

    public String getToday_num() {
        return today_num;
    }

    public void setToday_num(String today_num) {
        this.today_num = today_num;
    }

    public String getAll_income() {
        return all_income;
    }

    public void setAll_income(String all_income) {
        this.all_income = all_income;
    }

    public ShopinfoBean getShopinfo() {
        return shopinfo;
    }

    public void setShopinfo(ShopinfoBean shopinfo) {
        this.shopinfo = shopinfo;
    }

    public static class ShopinfoBean {
        /**
         * uid : 136
         * shop_logo : /Uploads/storelogo/9ed3d3170af66bec1b8275b48144_160_160.jpg
         * shop_name : 任我行集团（锦汇）汽车维修服务中心
         * shop_starlevel : 5
         * shop_sale : 24
         * shop_telephone : 02884200851
         * shop_address : 成都市三环路龙潭立交内侧辅道往成南立交前行150米
         */

        private String uid;
        private String shop_logo;
        private String shop_name;
        private String shop_starlevel;
        private String shop_sale;
        private String shop_telephone;
        private String shop_address;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_starlevel() {
            return shop_starlevel;
        }

        public void setShop_starlevel(String shop_starlevel) {
            this.shop_starlevel = shop_starlevel;
        }

        public String getShop_sale() {
            return shop_sale;
        }

        public void setShop_sale(String shop_sale) {
            this.shop_sale = shop_sale;
        }

        public String getShop_telephone() {
            return shop_telephone;
        }

        public void setShop_telephone(String shop_telephone) {
            this.shop_telephone = shop_telephone;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }
    }
}
