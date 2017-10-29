package com.yaoobs.anotherplay.data;


import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.BaseBean;
import com.yaoobs.anotherplay.bean.IndexBean;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.data.http.ApiService;

import retrofit2.Callback;
import rx.Observable;


public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService){
        this.mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){
//        HttpManager manager = new HttpManager();
//
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

//        mApiService.getApps("{'page':0}").enqueue(callback);

        return mApiService.getApps("{'page':0}");
    }

    public Observable<BaseBean<IndexBean>> index(){

        return  mApiService.index();
    }

}
