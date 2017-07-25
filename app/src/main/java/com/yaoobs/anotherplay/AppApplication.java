package com.yaoobs.anotherplay;

import android.app.Application;
import android.content.Context;

import com.yaoobs.anotherplay.di.component.AppComponent;
import com.yaoobs.anotherplay.di.component.DaggerAppComponent;
import com.yaoobs.anotherplay.di.module.AppModule;
import com.yaoobs.anotherplay.di.module.HttpModule;
//import com.yaoobs.anotherplay.di.component.DaggerAppComponent;

/**
 * Created by yaoobs on 2017/4/26.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;


    public static AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    public AppComponent getAppComponent(){

        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
    }



}
