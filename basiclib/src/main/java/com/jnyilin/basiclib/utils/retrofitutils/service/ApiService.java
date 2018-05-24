package com.jnyilin.basiclib.utils.retrofitutils.service;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by HRR on 2017/9/13.
 */

public interface ApiService {


    @GET("")
    Observable<String> appUpdate(@QueryMap Map<String, String> params);

    @GET
    Observable<String> get(@Url String path, @QueryMap Map<String, String> params);

    @POST
    Observable<String> post(@Url String path, @QueryMap Map<String, String> params);


    @POST()
    @Multipart
    Observable<String> postFile(@Url String path, @QueryMap Map<String, String> param, @Part MultipartBody.Part[] file);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST
    Observable<String> postJson(@Url String path, @Body RequestBody body);

}
