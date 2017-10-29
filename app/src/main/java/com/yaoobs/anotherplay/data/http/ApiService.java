package com.yaoobs.anotherplay.data.http;


import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.BaseBean;
import com.yaoobs.anotherplay.bean.IndexBean;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.bean.requestbean.LoginRequestBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";


//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    public Observable<BaseBean<IndexBean>> index();

    @GET("toplist")
    public Observable<BaseBean<AppInfo>> topList(@Query("page") int page); //p={"page":0}

    //{"phone":"","password":""}
    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);

    //    public static final MediaType JSON
//            = MediaType.parse("application/json; charset=utf-8");
//
//    OkHttpClient client = new OkHttpClient();
//
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    @FormUrlEncoded //FormBody
    @POST
    public void login2(@Field("phone") String phone);

    //
//    FormBody.Builder builder = new FormBody.Builder();
//
//    builder.addEncoded("phone","");
//
//    body = builde.build();

//    Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }
}
