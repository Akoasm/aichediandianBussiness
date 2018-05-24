package com.sina.rwxchina.aichediandianbussiness.order.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.base.BaseFragment;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.order.activity.OrderDetailActivity;
import com.sina.rwxchina.aichediandianbussiness.order.adapter.OrderListAdapter;
import com.sina.rwxchina.aichediandianbussiness.order.entity.OrderInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/2/1
 * @describe 进行中的订单
 * @modifyRecord
 */

public class OrderListFragment extends BaseFragment {
    /**订单列表数据*/
    private List<OrderInfo> orderList;
    /**是否是下拉刷新动作*/
    private boolean isRefresh=true;
    /**下拉刷新上拉加载控件*/
    private SmartRefreshLayout refreshLayout;
    /**订单列表*/
    private RecyclerView datalist;
    /**订单列表适配器*/
    private OrderListAdapter adapter;
    private int isMore;//是否还有更多0:没有下一页，1：有下一页
    /**当前页数*/
    private int page=1;
    /**订单分类（complete：已完成  doing：进行中）默认已完成*/
    private String orderType="complete";
    /**模糊搜素的关键词*/
    private String key="";
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_carriedout;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        orderType=getArguments().getString("type");
        orderList=new ArrayList<OrderInfo>();
        refreshLayout=view.findViewById(R.id.fragment_order_carriedout_listview);
        datalist=view.findViewById(R.id.order_list);
        datalist.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new OrderListAdapter(getContext(),R.layout.listview_item_order,orderList);
        datalist.setAdapter(adapter);

    }

    @Override
    public void setListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String orderson=((OrderInfo)adapter.getData().get(position)).getOrderson();
                Intent intent=new Intent(getActivity(), OrderDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("orderson",orderson);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setRefreshLayout();
        getOrderList(true);
    }

    private void getOrderList(boolean isLoading){
        Map<String,String> params=new HashMap<String ,String>();
        params.put("state",orderType);
        params.put("key",key);
        ApiManager manager=new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e(result);
                isMore=pageInfo.getIsMore();
                orderList=new Gson().fromJson(result,new TypeToken<List<OrderInfo>>(){}.getType());
                if (isRefresh){
                    refreshData();
                }else {
                    loadMoreData();
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {
                refreshLayout.finishRefresh();//结束刷新
            }
        });
        manager.post(HttpPath.GETSHOPORDERLIST,params,isLoading);
    }

    /**
     * 设置refreshLayout
     */
    private void setRefreshLayout(){
        //是否启用上拉加载功能
        refreshLayout.setEnableLoadmore(true);
        //是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setEnableAutoLoadmore(true);

        //设置加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMore();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh();
            }
        });
    }


    /**
     * 加载更多的数据设置
     */
    private void loadMore(){
        if (isMore==0){
            //结束加载
            refreshLayout.finishLoadmore();
            //全部加载完成
            refreshLayout.setLoadmoreFinished(true);
            return;
        }
        page++;
        isRefresh=false;
        getOrderList(true);
    }

    /**
     * 刷新数据
     */
    private void refresh(){
        page=1;
        isRefresh=true;
        getOrderList(true);
    }

    /**
     * 刷新数据
     */
    private void refreshData(){
        refreshLayout.finishRefresh();//结束刷新
        adapter.setNewData(orderList);
        adapter.notifyDataSetChanged();
        if (isMore==1){
            refreshLayout.setLoadmoreFinished(false);
        }else {
            refreshLayout.setLoadmoreFinished(true);
        }
    }

    /**
     * 加载更多数据
     */
    private void loadMoreData(){
        adapter.addData(orderList);
        refreshLayout.finishLoadmore();//结束加载
        adapter.notifyDataSetChanged();
        if (isMore==1){
            refreshLayout.setLoadmoreFinished(false);
        }else {
            refreshLayout.setLoadmoreFinished(true);
        }
    }

    /**
     * 模糊搜素
     * @param string
     */
    public void updata(String string){
        key=string;
        getOrderList(false);
    }
}
