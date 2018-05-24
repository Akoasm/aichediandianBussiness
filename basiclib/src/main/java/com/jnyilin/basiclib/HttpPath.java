package com.jnyilin.basiclib;

/**
 * @author HRR
 * @datetime 2018/1/31
 * @describe
 * @modifyRecord
 */

public class HttpPath {
//    public static final String BASEHOST="http://192.168.0.166:8088/";//测试服
    public static final String BASEHOST="http://chengdu.cdrwx.cn/";//正式服
//    public static final String BASEHOST="http://chengducs.cdrwx.cn/";//测试正式服
    public static final String BASEURL=BASEHOST+"api.php/acdd_shop/";
//    public static final String IMAGEURL = "http://192.168.0.166:8088";
    public static final String IMAGEURL = "http://chengdu.cdrwx.cn";
//    public static final String IMAGEURL = "http://chengducs.cdrwx.cn";
    /*登录注册*/
    /**密码登录*/
    public static final String LOGIN_PWD=BASEURL+"v1/Other/Login/pass";
    /**注册商户*/
    public static final String REGISTER=BASEURL+"v1/Other/Login/register";
    /**获取验证码*/
    public static final String GETCODE=BASEURL+"v1/Other/Verify/getVerify";
    /**商户注册协议*/
    public static final String REGISTERAGREEMENT=BASEHOST+"h5/xieyi/acdd_shop.html";
    /**版本更新*/
    public static final String APP_UPDATE=BASEHOST+"apk/merchant_update.php";

    /*首页*/
    /**订单收入*/
    public static final String HOME_ORDER=BASEURL+"v1/Indent/Index/getIndentIncome";
    /**订单验证*/
    public static final String ORDER_VERIFICATION=BASEURL+"v1/Indent/Index/checkCodestr";

    /*商品管理*/
    /**订单管理H5*/
    public static final String H5COMMODITY=BASEHOST+"h5/acdd_shop/html/merchandiseControl.html";

    /*订单管理*/
    /**获取指定店铺的订单列表*/
    public static final String GETSHOPORDERLIST="v1/Indent/index/getIndentList";
    /**获取指定店铺的订单列表*/
    public static final String GETORDERCONTENT="v1/Indent/index/getIndentInfo";

    /*客户中心*/
    /**获取商铺信息*/
    public static final String GETSHOP="v1/My/index/getShopInfo";
    /**修改密码/找回密码*/
    public static final String UPDATE_PASSWORD="v1/Other/login/los_pwd";
    /**添加银行卡*/
    public static final String ADDBANKCARD = "v1/My/index/add_bank_card";
    /**银行卡列表*/
    public static final String BANKCARDLIST = "v1/My/index/bank_list";
    /**资金明细*/
    public static final String BALANCEDETAIL = "v1/My/index/getMoneyLog";
    /**提现申请*/
    public static final String WITHDRAW = "v1/My/index/addShopWithdrawals";
    /**提现明细列表*/
    public static final String WITHDRAWDETAILLIST = "v1/My/index/withdrawal_list";
    /**提现界面数据*/
    public static final String WITHDRAWDATA = "v1/My/index/withdrawal_do";
    /**提现获取验证码*/
    public static final String GETVERIFICATIONCODE = "v1/Other/Verify/send_code";
    /**提现状态*/
    public static final String WITHDRAWSTATUS = "v1/My/index/withdrawal_status";
    /**获取分享内容*/
    public static final String MYSHARE = "v1/Home/Index/share";
    /**我的分享H5*/
    public static final String WEB_MYSHARE = BASEHOST+"/app.php/Spread/Index/index?uid=";
    /**商业联盟H5*/
    public static final String WEB_BUSINESSALLIANCE = BASEHOST+"app.php/Spread/Index/h5";
    /**修改或完善商户资料*/
    public static final String MODIFY_SHOP=BASEHOST+"h5/acdd_shop/html/reviseGoods.html";
    /**统计报表*/
    public static final String REPORTFROM="v1/Indent/Index/getStatisticalReport";
}
