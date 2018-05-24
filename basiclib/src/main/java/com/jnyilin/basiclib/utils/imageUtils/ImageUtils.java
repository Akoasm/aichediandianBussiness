package com.jnyilin.basiclib.utils.imageUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jnyilin.basiclib.R;
import com.jnyilin.basiclib.utils.appUtils.ScreenUtils;
import com.jnyilin.basiclib.utils.imageUtils.style.GlideCircleTransform;
import com.jnyilin.basiclib.utils.imageUtils.style.GlideRoundTransform;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;

/**
 * Created by HRR on 2017/9/25.
 * 图片加载工具类(Glide)
 */

public class ImageUtils {
    /**
     * 图片默认图片（加载中）
     */
    private static final int DEFAULT = R.mipmap.background;
    /**
     * 图片地址为空
     */
    private static final int NULL = R.mipmap.background;
    /**
     * 图片加载错误
     */
    private static final int ERROR = R.mipmap.background;

    /**
     * 显示图片，默认以下内容
     * 1.图片加载错误
     * 2.图片url为空
     * 3.图片加载中，占位图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showImage(Context context, String url, final ImageView imageView) {
//        DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
//        DiskCacheStrategy.NONE 不使用磁盘缓存
//        DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
//        DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
//        DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。

        Glide.with(context)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                )
                .thumbnail(0.1f)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //加载失败
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // 加载完成
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 显示图片，默认以下内容
     * 1.图片加载错误
     * 2.图片url为空
     * 3.图片加载中，占位图
     *
     * @param \
     * @param url
     * @param imageView
     */
    public static void showImage(Activity activity, String url, final ImageView imageView) {
//        DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
//        DiskCacheStrategy.NONE 不使用磁盘缓存
//        DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
//        DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
//        DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。

        Glide.with(activity)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //加载失败
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // 加载完成
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 显示图片，默认以下内容
     * 1.图片加载错误
     * 2.图片url为空
     * 3.图片加载中，占位图
     *
     * @param \
     * @param url
     * @param imageView
     */
    public static void showImage(Fragment fragment, String url, final ImageView imageView) {
//        DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
//        DiskCacheStrategy.NONE 不使用磁盘缓存
//        DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
//        DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
//        DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。

        Glide.with(fragment)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .thumbnail(0.1f)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //加载失败
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // 加载完成
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 显示图片，默认以下内容
     * 1.图片url为空
     * 2.图片加载中，占位图
     *
     * @param context
     * @param url
     * @param imageView
     * @param error
     */
    public static void showImage(Context context, String url, ImageView imageView, int error) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .error(error))
                .thumbnail(0.1f)

                .into(imageView);
    }

    /**
     * 显示图片，bitmap类型
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showImageAsBitmap(final Context context, String url, final ImageView imageView) {
        LogUtils.e("asbitmap", "url=" + url);
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                )
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        int screenWidth = ScreenUtils.getScreenWidth(context);
                        int width = resource.getWidth();
                        int height = resource.getHeight();

                        double a = (double) height / width;
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                        params.height = (int) (a * screenWidth);
                        params.width = screenWidth;
                        imageView.setLayoutParams(params);
                        imageView.setImageBitmap(resource);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                });
    }

    /**
     * 显示圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showCircleImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .transform(new GlideCircleTransform(context)))
                .thumbnail(0.1f)
                .into(imageView);
    }
    /**
     * 显示圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showCircleImageAsUri(Context context, Uri url, ImageView imageView) {
        Glide.with(context)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .transform(new GlideCircleTransform(context)))
                .thumbnail(0.1f)
                .into(imageView);
    }
    /**
     * 显示圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param error
     */
    public static void showCircleImage(Context context, String url, ImageView imageView, int error) {
        Glide.with(context)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(error)//请求图片加载错误
                        .fallback(error)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .transform(new GlideCircleTransform(context)))
                .thumbnail(0.1f)
                .into(imageView);
    }

    /**
     * 显示圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showRoundImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .transform(new GlideRoundTransform(context)))
                .thumbnail(0.1f)
                .into(imageView);
    }

    /**
     * 显示圆角图片并指明角度
     *
     * @param context
     * @param url
     * @param imageView
     * @param dp
     */
    public static void showRoundImage(Context context, String url, ImageView imageView, int dp) {
        Glide.with(context)
                .load(url)//加载图片
                .apply(new RequestOptions()
                        .error(ERROR)//请求图片加载错误
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .transform(new GlideRoundTransform(context, dp)))
                .thumbnail(0.1f)
                .into(imageView);
    }

    /**
     * 显示uri图片，默认以下内容
     * 1.图片url为空
     * 2.图片加载中，占位图
     *
     * @param context
     * @param uri
     * @param imageView
     * @param x,y
     */
    public static void showImage(Context context, Uri uri, ImageView imageView, int x, int y) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .override(x, y))
                .thumbnail(0.1f)
                .into(imageView);
    }

    /**
     * 显示uri图片，默认以下内容
     * 1.图片url为空
     * 2.图片加载中，占位图
     *
     * @param context
     * @param uri
     * @param imageView
     * @param size
     */
    public static void showImage(Context context, Uri uri, ImageView imageView, int size) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .override(size))
                .thumbnail(0.1f)
                .into(imageView);
    }

    /**
     * 加载图片，可加载资源文件
     *
     * @param context
     * @param model
     * @param imageView
     */
    public static void showImage(Context context, Object model, ImageView imageView) {
        Glide.with(context)
                .load(model)
                .apply(new RequestOptions()
                        .fallback(NULL)//请求图片的url为空
                        .placeholder(DEFAULT)//请求图片加载中
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .error(ERROR))
                .thumbnail(0.1f)
                .into(imageView);
    }
}
