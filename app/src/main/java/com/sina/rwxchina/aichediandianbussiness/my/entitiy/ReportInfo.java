package com.sina.rwxchina.aichediandianbussiness.my.entitiy;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 消费统计实体类
 * @modifyRecord
 */

public class ReportInfo {

    /**
     * total : 25
     * consumption : 0.00
     * list : [{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018020217201123603227982150","user_name":"测试静","goods_name":"自助买单","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 17:20:11","paytype":"13","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018020217193789609981761354","user_name":"测试静","goods_name":"自助买单","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 17:19:37","paytype":"13","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018020217181315932693286163","user_name":"测试静","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 17:18:13","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018020217120725977554180500","user_name":"测试静","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 17:12:07","paytype":"13","indent_state":"90"},{"user_head":"","orderson":"s12018020216253510221710936973","user_name":"15281063766","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 16:25:35","paytype":"12","indent_state":"90"},{"user_head":"","orderson":"s12018020211344759488705215396","user_name":"15281063766","goods_name":"漆面快喷（10万--30万，5座及以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 11:34:47","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018020209100590646660434904","user_name":"测试静","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-02 09:10:03","paytype":"12","indent_state":"90"},{"user_head":"","orderson":"s12018020113545039757350565209","user_name":"15281063766","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-01 13:54:50","paytype":"13","indent_state":"90"},{"user_head":"","orderson":"s12018020113243199871583092355","user_name":"周书强","goods_name":"自助买单","pay_money":"0.00","user_tel":"","createdate":"2018-02-01 13:24:31","paytype":"12","indent_state":"90"},{"user_head":"","orderson":"s12018020113232321087873048282","user_name":"周书强","goods_name":"普通洗车（越野车）","pay_money":"0.00","user_tel":"","createdate":"2018-02-01 13:23:23","paytype":"12","indent_state":"90"},{"user_head":"","orderson":"s12018020109400290724683386908","user_name":"15281063766","goods_name":"自助买单","pay_money":"0.00","user_tel":"","createdate":"2018-02-01 09:40:02","paytype":"12","indent_state":"90"},{"user_head":"","orderson":"s12018020109353773166934326576","user_name":"15281063766","goods_name":"自助买单","pay_money":"0.00","user_tel":"","createdate":"2018-02-01 09:35:36","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018013116503986796318899813","user_name":"测试静","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:50:38","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/18214443990/userinfo/20180129/1f973591284691a377faebbd8803b9cf.jpg","orderson":"s12018013116502534505461269110","user_name":"13981888245","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:50:25","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/18214443990/userinfo/20180129/1f973591284691a377faebbd8803b9cf.jpg","orderson":"s12018013116495257234714555820","user_name":"13981888245","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:49:52","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018013116482776546426922529","user_name":"测试静","goods_name":"普通洗车（越野车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:48:26","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/1847/userinfo/20180201/s_a6e687034aca57addffd6925f3abf372.jpg","orderson":"s12018013116473426853658388596","user_name":"测试静","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:47:34","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/2514/userinfo/20180131/s_88e11d638a4dfa4f5b53c6e9e61960ba.jpg","orderson":"s12018013116425548049631449816","user_name":"测试","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:42:55","paytype":"12","indent_state":"90"},{"user_head":"","orderson":"s12018013116314936239540837073","user_name":"15281063766","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:31:49","paytype":"12","indent_state":"90"},{"user_head":"/./Uploads/User/2514/userinfo/20180131/s_88e11d638a4dfa4f5b53c6e9e61960ba.jpg","orderson":"s12018013116301073412123155808","user_name":"测试","goods_name":"普通洗车（5座以下轿车）","pay_money":"0.00","user_tel":"","createdate":"2018-01-31 16:30:10","paytype":"12","indent_state":"90"}]
     */

    private String total;
    private String consumption;
    private List<ReportOrderInfo> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public List<ReportOrderInfo> getList() {
        return list;
    }

    public void setList(List<ReportOrderInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ReportInfo{" +
                "total='" + total + '\'' +
                ", consumption='" + consumption + '\'' +
                ", list=" + list +
                '}';
    }
}
