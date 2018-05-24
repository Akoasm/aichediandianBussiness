package com.jnyilin.basiclib.utils.ViewPagerUtils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jnyilin.basiclib.R;

import java.util.List;

/**
 * Created by HRR on 2017/10/16.
 * 轮播工具类
 */

public class ViewPagerUtils {
    private LinearLayout dots_ll;
    private ViewPager viewpager;
    private Context context;
    private MyAdapter myAdapter;
    private RunnableTask runnableTask = new RunnableTask();
    // 准备要显示的图片资源
    private View dots[];
    // 轮播图显示的当前页
    private int currentPosition;
    private List<String> imglist;
    private ViewPagerClickListener clickListener;

    /**
     * 不带点击监听，用于普通的轮播
     * @param dots_ll
     * @param viewpager
     * @param context
     * @param imglist
     */
    public ViewPagerUtils(LinearLayout dots_ll, ViewPager viewpager, final Context context, final List<String> imglist) {
        this.dots_ll = dots_ll;
        this.viewpager = viewpager;
        this.context = context;
        this.imglist = imglist;
        this.clickListener=new ViewPagerClickListener() {
            @Override
            public void onClick(int postion) {
                String imgUrl=imglist.get(postion-1);
                //如果图片没有头地址，则添加头地址
                if ((!imgUrl.startsWith("http://"))&&(!imgUrl.startsWith("https://"))){
                    imgUrl= "";
                }
            }
        };
        currentPosition = imglist.size() * 100 / 2;
    }

    /**
     * 带有点击监听的初始化方法
     * @param dots_ll
     * @param viewpager
     * @param context
     * @param imglist
     * @param viewPagerClickListener
     */
    public ViewPagerUtils(LinearLayout dots_ll, ViewPager viewpager, Context context, List<String> imglist, ViewPagerClickListener viewPagerClickListener) {
        this.dots_ll = dots_ll;
        this.viewpager = viewpager;
        this.context = context;
        this.imglist = imglist;
        this.clickListener=viewPagerClickListener;
        currentPosition = imglist.size() * 100 / 2;
    }


    public void initdolls() {
        // 点，同样是根据图片数量确定
        dots = new View[imglist.size()];
        // 将点加入右下角的布局中
        for (int i = 0; i < imglist.size(); i++) {
            View dot = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            params.setMargins(20, 5, 5, 5);

            dot.setLayoutParams(params);

            if (i == 0) {
                dot.setBackgroundResource(R.mipmap.dot_focused);
            } else {
                dot.setBackgroundResource(R.mipmap.dot_normal);
            }
            dots[i] = dot;
            dots_ll.addView(dot);
        }
    }

    /**
     * 开始轮播
     */
    public void viewpagergo() {

        if (imglist.size() != 1) {
            initdolls();
        }
        startRoll();
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                //当轮播图显示当前页时，一定要把currentPosition设为arg0，
                //否则，当你用手指滑动轮播图时，放手后，轮播图显示的下一张页面是并不是当前显示页面的下一页
                //而是，他自己根据3秒钟计算得到的下一页
                currentPosition = arg0;
                if (imglist.size() != 1) {
                    for (int i = 0; i < imglist.size(); i++) {
                        if (i == currentPosition % imglist.size()) {
                            dots[i].setBackgroundResource(R.mipmap.dot_focused);
                        } else {
                            dots[i].setBackgroundResource(R.mipmap.dot_normal);
                        }
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    /**
     * 让轮播图viewpager滚动起来
     */
    public void startRoll() {
        // 滚动viewpager
        if (myAdapter == null) {
            // 1.第一次初始化适配器
            myAdapter = new MyAdapter();
            viewpager.setAdapter(myAdapter);
            viewpager.setCurrentItem(currentPosition);
        } else {// 8.第二次，只需要通知适配器数据发生了变化，要刷新Ui
            myAdapter.notifyDataSetChanged();
        }
        // 发送一个延时的消息，3秒后执行runnableTask类里run方法里的操作
        // （为什么执行的是runnableTask，而不是handleMessage呢？这里涉及到handler消息机制源码解析）
//        Log.i("lkymsgqq","111111111111111111111111");
        if(imglist.size() != 1){
            handler.postDelayed(runnableTask, 3000);
        }

    }

    class RunnableTask implements Runnable {
        @Override
        public void run() {
            // 变化轮播图当前要显示的页面位置，递增1，为了不使这个数字递增超过轮播图 图片的个数，取余数
            currentPosition = currentPosition + 1;
//            Log.i("lkymsgqq","222222222222222222222222222222222222");
            // 发送消息给主线程的handler
            if(imglist.size() != 1){
                handler.obtainMessage().sendToTarget();
            }

        }
    }

    private Handler handler = new Handler() {
        // 5.接收并处理run方法发来的消息
        public void handleMessage(android.os.Message msg) {
            // viewpager设置新的当前页
            viewpager.setCurrentItem(currentPosition);
            // 继续执行startRoll方法，成为一个循环
//            Log.i("lkymsg",msg.toString());
            startRoll();
        }
    };

    /**
     * 当手指按住轮播图不动时，轮播图停止滚动；当点击轮播图时，跳转到相关界面
     */
    public void onTouchViewPager(View view, final int position) {
        // 给图片注册触摸事件监听器
        view.setOnTouchListener(new View.OnTouchListener() {

            private long downTime;
            private int downX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 按下去时，记录按下的坐标和时间，用于判断是否是点击事件
                        handler.removeCallbacksAndMessages(null);// 手指按下时，取消所有事件，即轮播图不在滚动了
                        downX = (int) event.getX();
                        downTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:// 抬起手指时，判断落下抬起的时间差和坐标，符合以下条件为点击
                        // Toast.makeText(context, "手指抬起了", 0).show();
                        if (System.currentTimeMillis() - downTime < 500
                                && Math.abs(downX - event.getX()) < 30) {// 考虑到手按下和抬起时的坐标不可能完全重合，这里给出30的坐标偏差
                            // 点击事件被触发
                            //点击事件回调,接口对象为空即表示没有设置点击回调
                            if (clickListener!=null){
//                                int p=currentPosition % imglist.size()+1;
                                clickListener.onClick(position);
                            }
                        }
                        startRoll();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        // 让用户在手指滑动完图片后，能够让轮播图继续自动滚动
                        startRoll();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void restart() {
        startRoll();
    }

    public void removeCallbacksAndMessages() {
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 适配器，要重写下面四个方法
     */
    class MyAdapter extends PagerAdapter {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return imglist.size() * 100;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(context, R.layout.layout_roll_view, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            String imgUrl=imglist.get(position % imglist.size());
            //如果图片没有头地址，则添加头地址
            if ((!imgUrl.startsWith("http://"))&&(!imgUrl.startsWith("https://"))){
                imgUrl= "";
            }
            Glide.with(context).load(imgUrl).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // onTouchViewPager方法一定要写在instantiateItem内部，表示触摸的是当前位置的页面
            onTouchViewPager(view,position % imglist.size()+1);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public interface ViewPagerClickListener{
        void onClick(int postion);
    }
}
