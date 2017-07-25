package com.yaoobs.anotherplay.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return  mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return  new Gson();
    }

}
