package com.yaoobs.anotherplay.data;


import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.data.http.ApiService;

import retrofit2.Callback;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService){
        this.mApiService = apiService;
    }

    public  void getApps(Callback<PageBean<AppInfo>> callback){
//        HttpManager manager = new HttpManager();
//
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        mApiService.getApps("{'page':0}").enqueue(callback);

    }


}
