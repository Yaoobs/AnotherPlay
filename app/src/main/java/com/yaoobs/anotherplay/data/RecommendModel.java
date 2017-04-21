package com.yaoobs.anotherplay.data;


import com.yaoobs.anotherplay.bean.AppInfo;
import com.yaoobs.anotherplay.bean.PageBean;
import com.yaoobs.anotherplay.http.ApiService;
import com.yaoobs.anotherplay.http.HttpManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 2017/1/3.
 */

public class RecommendModel {

    public  void getApps(Callback<PageBean<AppInfo>> callback){
        HttpManager manager = new HttpManager();

        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        apiService.getApps("{'page':0}").enqueue(callback);

    }


}
